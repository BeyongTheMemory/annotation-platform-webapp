package com.hongying.repository.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * user_feedback
 * @author 
 */
public class UserFeedback {
    private Long id;

    private Long userId;

    private Long entityCategoryId;

    /**
     * 实体分类类别
     */
    private Integer entityCategoryType;

    /**
     * 0:错误:1:正确
     */
    private Boolean isCorrect;

    private String errorReason;

    private Date updatedAt;

    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}