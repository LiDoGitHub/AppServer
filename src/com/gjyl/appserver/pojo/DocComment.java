package com.gjyl.appserver.pojo;

import java.util.UUID;

public class DocComment {
    private String comid;

    private String content;

    private String memo;

    private String doctorid;

    private String healthid;

    private Doctor doctor;
    
    public String getComid() {
        return comid;
    }

    public void setComid(String comid) {
        this.comid = comid == null ? null : comid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid == null ? null : doctorid.trim();
    }

	public String getHealthid() {
		return healthid;
	}

	public void setHealthid(String healthid) {
		this.healthid = healthid;
	}

	public DocComment() {
		this.comid=UUID.randomUUID().toString().replace("-", "");
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
    
}