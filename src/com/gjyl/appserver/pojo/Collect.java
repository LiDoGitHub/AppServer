package com.gjyl.appserver.pojo;

import java.util.UUID;

public class Collect {
    private String id;

    private String userid;

    private String cyclopediaid;

    private String ctime;

    private Cyclopedia cyclopedia;

    public Cyclopedia getCyclopedia() {
        return cyclopedia;
    }

    public void setCyclopedia(Cyclopedia cyclopedia) {
        this.cyclopedia = cyclopedia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getCyclopediaid() {
        return cyclopediaid;
    }

    public void setCyclopediaid(String cyclopediaid) {
        this.cyclopediaid = cyclopediaid == null ? null : cyclopediaid.trim();
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

	public Collect() {
		this.id=UUID.randomUUID().toString().replace("-", "");
	}

    public String toString() {
        return "Collect{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", cyclopediaid='" + cyclopediaid + '\'' +
                ", ctime='" + ctime + '\'' +
                '}';
    }
}