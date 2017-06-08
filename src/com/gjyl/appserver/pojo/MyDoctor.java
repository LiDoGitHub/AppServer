package com.gjyl.appserver.pojo;

import java.util.UUID;

public class MyDoctor {
    private String mydrid;

    private String userid;

    private String doctorid;

    private String memo;
    
    private Doctor doctor;

    public String getMydrid() {
        return mydrid;
    }

    public void setMydrid(String mydrid) {
        this.mydrid = mydrid == null ? null : mydrid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid == null ? null : doctorid.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public MyDoctor() {
		this.mydrid=UUID.randomUUID().toString().replace("-", "");
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
    
}