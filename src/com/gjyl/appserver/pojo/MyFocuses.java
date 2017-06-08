package com.gjyl.appserver.pojo;

import java.util.UUID;

public class MyFocuses {
    private String focusid;

    private String fuserid;

    private String username;

    private String focusedid;

    private String focusedname;

    private String memo;

    public String getFocusid() {
        return focusid;
    }

    public void setFocusid(String focusid) {
        this.focusid = focusid == null ? null : focusid.trim();
    }

    public String getFuserid() {
        return fuserid;
    }

    public void setFuserid(String fuserid) {
        this.fuserid = fuserid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getFocusedid() {
        return focusedid;
    }

    public void setFocusedid(String focusedid) {
        this.focusedid = focusedid == null ? null : focusedid.trim();
    }

    public String getFocusedname() {
        return focusedname;
    }

    public void setFocusedname(String focusedname) {
        this.focusedname = focusedname == null ? null : focusedname.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public MyFocuses() {
        this.focusid= UUID.randomUUID().toString().replace("-","");
    }
}