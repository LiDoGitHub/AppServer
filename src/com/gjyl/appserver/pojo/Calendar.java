package com.gjyl.appserver.pojo;

import java.util.UUID;

public class Calendar {
    private String id;

    private String cdate;

    private String regid;

    private String remindid;

    private String userid;

    private String healthid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate == null ? null : cdate.trim();
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid == null ? null : regid.trim();
    }

    public String getRemindid() {
        return remindid;
    }

    public void setRemindid(String remindid) {
        this.remindid = remindid == null ? null : remindid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

	public String getHealthid() {
		return healthid;
	}

	public void setHealthid(String healthid) {
		this.healthid = healthid;
	}

	public Calendar() {
		this.id=UUID.randomUUID().toString().replace("-", "");
	}
    
}