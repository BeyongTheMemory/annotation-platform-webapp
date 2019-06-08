package com.hongying.service.request;

import com.hongying.service.dto.UserFeedbackDTO;

import java.util.List;

public class AddFeedbackRequest {
    private List<UserFeedbackDTO> userFeedbacks;

    public List<UserFeedbackDTO> getUserFeedbacks() {
        return userFeedbacks;
    }

    public void setUserFeedbacks(List<UserFeedbackDTO> userFeedbacks) {
        this.userFeedbacks = userFeedbacks;
    }
}
