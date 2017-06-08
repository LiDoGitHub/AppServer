package com.gjyl.appserver.pojo;

import java.util.Date;
import java.util.UUID;

public class EssayAgree {
    private String agreeid;

    private String eid;

    private String userid;

    private Date agreetime;

    private String memo;

    private AppUser user;

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getAgreeid() {
        return agreeid;
    }

    public void setAgreeid(String agreeid) {
        this.agreeid = agreeid == null ? null : agreeid.trim();
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Date getAgreetime() {
        return agreetime;
    }

    public void setAgreetime(Date agreetime) {
        this.agreetime = agreetime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public EssayAgree() {
        this.agreeid= UUID.randomUUID().toString().replace("-","");
    }
}