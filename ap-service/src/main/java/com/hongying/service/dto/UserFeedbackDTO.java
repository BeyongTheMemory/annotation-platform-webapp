package com.hongying.service.dto;

import java.util.List;

public class UserFeedbackDTO {

    private Long entityCategoryId;

    /**
     * 实体分类类别
     */
    private Integer entityCategoryType;

    /**
     * 0:错误:1:正确
     */
    private Boolean isCorrect;

    /**
     * 错误原因
     */
    private List<ErrorReasonDTO> errorReasons;

    public Long getEntityCategoryId() {
        return entityCategoryId;
    }

    public void setEntityCategoryId(Long entityCategoryId) {
        this.entityCategoryId = entityCategoryId;
    }

    public Integer getEntityCategoryType() {
        return entityCategoryType;
    }

    public void setEntityCategoryType(Integer entityCategoryType) {
        this.entityCategoryType = entityCategoryType;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public List<ErrorReasonDTO> getErrorReasons() {
        return errorReasons;
    }

    public void setErrorReasons(List<ErrorReasonDTO> errorReasons) {
        this.errorReasons = errorReasons;
    }
}
