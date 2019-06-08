package com.hongying.service;

import com.hongying.service.dto.EntityCategoryDTO;
import com.hongying.service.request.AddFeedbackRequest;

public interface AnnotationService {
    boolean initData();

    EntityCategoryDTO getNextEntityCategory(Long userId,Integer type);

    void feedback(Long userId, AddFeedbackRequest request);
}
