package com.hongying.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hongying.exceptions.ApException;
import com.hongying.repository.domain.Sentence;
import com.hongying.repository.domain.SentenceFeedback;
import com.hongying.repository.mapper.SentenceDAO;
import com.hongying.repository.mapper.SentenceFeedbackDAO;
import com.hongying.service.SentenceService;
import com.hongying.service.dto.SentenceDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;

@Service
public class SentenceServiceImpl implements SentenceService {

    @Autowired
    private SentenceDAO sentenceDAO;

    @Autowired
    private SentenceFeedbackDAO sentenceFeedbackDAO;

    @Override
    @Transactional
    public boolean init() {
        Resource resource = new ClassPathResource("com/hongying/service/sentence.properties");
        File file = null;
        try {
            file = resource.getFile();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String lineStr = br.readLine();
            while (lineStr != null) {
                if(lineStr.isEmpty()){
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
    public SentenceDTO getNext(Long userId) {
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
        if (!sentenceId.equals(sentenceDTO.getId())) {
            throw new ApException("miss or repeat feedback,please check again");
        }

        SentenceFeedback sentenceFeedback = new SentenceFeedback();
        sentenceFeedback.setUserId(userId);
        sentenceFeedback.setSentenceId(sentenceId);
        sentenceFeedback.setReason(reason);
        sentenceFeedbackDAO.insertSelective(sentenceFeedback);
    }
}
