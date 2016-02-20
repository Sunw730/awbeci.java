package com.awbeci.domain;


import java.util.Date;

public class UserDynamic {
    private String id;
    private String uid;
    private String objId;
    //1:网址
    private String objType;
    //1：添加 ，2：编辑，3：删除
    private String action;
    private String content;
    private Date createDt;
    private Date updateDt;

    private User user;

    private UserSites userSites;

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

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserSites getUserSites() {
        return userSites;
    }

    public void setUserSites(UserSites userSites) {
        this.userSites = userSites;
    }
}
