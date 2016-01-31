package com.awbeci.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SiteStatus {
    private String id;

    private int type;

    private String content;

    @DateTimeFormat(pattern="yyyy年MM月dd日")
    @JsonFormat(pattern="yyyy年MM月dd日",timezone = "GMT+8")
    private Date createDt;

    @DateTimeFormat(pattern="yyyy年MM月dd日")
    @JsonFormat(pattern="yyyy年MM月dd日",timezone = "GMT+8")
    private Date updateDt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }
}
