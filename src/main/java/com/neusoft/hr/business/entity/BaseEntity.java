package com.neusoft.hr.business.entity;

public class BaseEntity {
    protected Long id;
    protected String delFlag;//逻辑删除标记

    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
