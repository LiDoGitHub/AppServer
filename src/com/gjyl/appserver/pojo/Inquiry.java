package com.gjyl.appserver.pojo;

import java.util.UUID;

public class Inquiry {
    private String inqid;

    private String doctorid;

    private String userid;

    private String ordertime;

    public String getInqid() {
        return inqid;
    }

    public void setInqid(String inqid) {
        this.inqid = inqid == null ? null : inqid.trim();
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid == null ? null : doctorid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime == null ? null : ordertime.trim();
    }

	public Inquiry() {
		this.inqid=UUID.randomUUID().toString().replace("-", "");
	}
    
}