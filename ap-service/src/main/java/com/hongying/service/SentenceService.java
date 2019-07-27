package com.hongying.service;

import com.hongying.service.dto.SentenceDTO;

public interface SentenceService {
    boolean init();

    SentenceDTO getNext(Long userId);

    void feedback(Long userId,Long sentenceId,String reason);
}
