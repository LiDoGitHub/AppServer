package com.gjyl.appserver.pojo;

import java.util.Date;
import java.util.UUID;

public class Cases {
    private String caseid;

    private String casename;

    private String sectionid;

    private String secname;

    private String hospital;

    private String hospitalname;

    private String docid;

    private String docname;

    private String memo;

    private String userid;

    private Date casetime;

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid == null ? null : caseid.trim();
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename == null ? null : casename.trim();
    }

    public String getSectionid() {
        return sectionid;
    }

    public void setSectionid(String sectionid) {
        this.sectionid = sectionid == null ? null : sectionid.trim();
    }

    public String getSecname() {
        return secname;
    }

    public void setSecname(String secname) {
        this.secname = secname == null ? null : secname.trim();
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital == null ? null : hospital.trim();
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname == null ? null : hospitalname.trim();
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid == null ? null : docid.trim();
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname == null ? null : docname.trim();
    }

    public String getMemo() {
        return memo;
    }
    
    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getCasetime() {
        return casetime;
    }

    public void setCasetime(Date casetime) {
        this.casetime = casetime;
    }

	public Cases() {
		this.caseid=UUID.randomUUID().toString().replace("-", "");
	}
}