package com.hongying.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hongying.exceptions.ApException;
import com.hongying.repository.domain.Sentence;
import com.hongying.repository.domain.SentenceFeedback;
import com.hongying.repository.mapper.SentenceDAO;
import com.hongying.repository.mapper.SentenceFeedbackDAO;
import com.hongying.service.SentenceService;
import com.hongying.service.dto.FeedBackInitDTO;
import com.hongying.service.dto.SentenceDTO;
import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;

@Service
public class SentenceServiceImpl implements SentenceService {

    @Autowired
    private SentenceDAO sentenceDAO;

    @Autowired
    private SentenceFeedbackDAO sentenceFeedbackDAO;

    final private String INIT_REASON = "init";

    @Override
    @Transactional
    public boolean init() {
        Resource resource = new ClassPathResource("com/hongying/service/sentence.properties");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String lineStr = br.readLine();
            while (lineStr != null) {
                if (lineStr.isEmpty()) {
                    lineStr = br.readLine();
                    continue;
                }
                JSONObject jsonObject = JSON.parseObject(lineStr);
                Sentence sentence = new Sentence();
                sentence.setId(jsonObject.getLong("sentence_id"));
                sentence.setContent(lineStr);
                sentenceDAO.insert(sentence);
                lineStr = br.readLine();
            }
            return true;
        } catch (IOException e) {
            throw new ApException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean initFeedBack(List<FeedBackInitDTO> feedBackInitDTOs) {
        for (FeedBackInitDTO feedBackInitDTO : feedBackInitDTOs) {
            Sentence sentence = sentenceDAO.selectByIndex(feedBackInitDTO.getBeginIndex());
            if (sentence == null) {
                throw new ApException("out of index");
            }
            feedback(feedBackInitDTO.getUserId(), sentence.getId() - 1, INIT_REASON);
        }
        return true;
    }

    @Override
    public SentenceDTO getNext(Long userId) {
        int count = sentenceFeedbackDAO.selectCountByUserId(userId);
        if (count > 500) {
            return null;
        }
        Long minId = 0L;
        //query last record in feedback DB
        SentenceFeedback sentenceFeedback = sentenceFeedbackDAO.selectLastRecordByUserId(userId);
        if (sentenceFeedback != null) {
            minId = sentenceFeedback.getSentenceId();
        }
        Sentence sentence = sentenceDAO.selectOneByMinId(minId);
        if (sentence == null) {
            return null;
        }
        SentenceDTO sentenceDTO = new SentenceDTO();
        BeanUtils.copyProperties(sentence, sentenceDTO);
        return sentenceDTO;
    }

    @Override
    public void feedback(Long userId, Long sentenceId, String reason) {
        //query last record in userFeedback DB
        SentenceDTO sentenceDTO = getNext(userId);
        if (sentenceDTO == null) {
            throw new ApException("all record already feedback");
        }
        if ((!sentenceId.equals(sentenceDTO.getId())) && !reason.equals(INIT_REASON)) {
            throw new ApException("miss or repeat feedback,please check again");
        }

        SentenceFeedback sentenceFeedback = new SentenceFeedback();
        sentenceFeedback.setUserId(userId);
        sentenceFeedback.setSentenceId(sentenceId);
        sentenceFeedback.setReason(reason);
        sentenceFeedbackDAO.insertSelective(sentenceFeedback);
    }
}
