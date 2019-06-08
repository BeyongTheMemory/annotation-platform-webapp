package com.hongying.service.dto;

import com.hongying.enums.RelationEnum;
import com.hongying.enums.TypeEnum;

import java.util.Date;

/**
 * entity_category
 * @author 
 */
public class EntityCategoryDTO {
    private Long id;

    private String entity;

    private String category;

    /**
     * 0:is instance of
     * @see RelationEnum
     */
    private Integer relation;

    private String wikiUrl;

    /**
     * 0:标识单个对应关系是否正确
     * @see TypeEnum
     */
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}