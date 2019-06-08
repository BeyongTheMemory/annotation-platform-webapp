package com.hongying.service.builder;

import com.alibaba.fastjson.JSONArray;
import com.hongying.exceptions.ApException;
import com.hongying.repository.domain.UserFeedback;
import com.hongying.service.dto.UserFeedbackDTO;
import org.springframework.util.CollectionUtils;

public class UserFeedbackBuilder {
    public static UserFeedback build(Long userId,Integer type,UserFeedbackDTO userFeedbackDTO){
        UserFeedback userFeedback = new UserFeedback();
        userFeedback.setUserId(userId);
        userFeedback.setEntityCategoryType(type);
        userFeedback.setEntityCategoryId(userFeedbackDTO.getEntityCategoryId());
        userFeedback.setIsCorrect(userFeedbackDTO.getIsCorrect());
        if(!userFeedback.getIsCorrect()){
            if(CollectionUtils.isEmpty(userFeedbackDTO.getErrorReasons())){
                throw new ApException("error reason can't be null");
            }
            userFeedback.setErrorReason(JSONArray.toJSONString(userFeedbackDTO.getErrorReasons()));

        }
        return userFeedback;
    }
}
