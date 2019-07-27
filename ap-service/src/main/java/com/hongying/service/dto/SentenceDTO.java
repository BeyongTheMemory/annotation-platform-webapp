package com.hongying.service.dto;

import java.io.Serializable;

/**
 * sentence
 * @author 
 */
public class SentenceDTO implements Serializable {
    private Long id;

    private String content;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}