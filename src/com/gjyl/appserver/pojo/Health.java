package com.gjyl.appserver.pojo;

import java.util.UUID;

public class Health {
    private String healthid;

    private String createdate;

    private String tag;

    private String content;

    private String userid;

    public String getHealthid() {
        return healthid;
    }

    public void setHealthid(String healthid) {
        this.healthid = healthid == null ? null : healthid.trim();
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate == null ? null : createdate.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

	public Health() {
		this.healthid=UUID.randomUUID().toString().replace("-", "");
	}
    
    
}