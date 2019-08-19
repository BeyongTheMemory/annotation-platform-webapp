package com.hongying.service;

import com.hongying.service.dto.FeedBackInitDTO;
import com.hongying.service.dto.SentenceDTO;

import java.util.List;

public interface SentenceService {
    boolean init();

    SentenceDTO getNext(Long userId);

    boolean initFeedBack(List<FeedBackInitDTO> feedBackInitDTOs);

    void feedback(Long userId,Long sentenceId,String reason);
}
