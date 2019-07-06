package com.like.weblog.weblog.model;

import sun.util.calendar.LocalGregorianCalendar;

import java.util.Date;

public class User {
    private long acountId;
    private  String name;
    private  String token;
    private  long gmtCreate;
    private  long gmtModified;
    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public long getAcountId() {
        return acountId;
    }

    public void setAcountId(long acountId) {
        this.acountId = acountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(long gmtModified) {
        this.gmtModified = gmtModified;
    }
}
