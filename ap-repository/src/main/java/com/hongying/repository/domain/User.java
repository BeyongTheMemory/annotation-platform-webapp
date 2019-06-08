package com.hongying.repository.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * user
 * @author 
 */
public class User  {
    private Long id;

    private String name;

    private String password;

    private Date cteatedAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCteatedAt() {
        return cteatedAt;
    }

    public void setCteatedAt(Date cteatedAt) {
        this.cteatedAt = cteatedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}