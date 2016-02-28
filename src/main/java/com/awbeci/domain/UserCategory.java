package com.awbeci.domain;

import java.util.Date;
import java.util.List;

public class UserCategory {
    private String id;
    private String uid;
    private String pid;
    private String name;
    private int depth;
    private Integer sortNo;
    private Date createDt;
    private Date updateDt;
    private List<UserSites> userSiteses;

    public List<UserSites> getUserSiteses() {
        return userSiteses;
    }

    public void setUserSiteses(List<UserSites> userSiteses) {
        this.userSiteses = userSiteses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
