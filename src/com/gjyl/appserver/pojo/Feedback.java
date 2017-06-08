package com.gjyl.appserver.pojo;

import java.util.UUID;

public class Feedback {
    private String feedid;

    private String content;

    private String ftime;

    private String userid;

    private AppUser user;

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getFeedid() {
        return feedid;
    }

    public void setFeedid(String feedid) {
        this.feedid = feedid == null ? null : feedid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime == null ? null : ftime.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

	public Feedback() {
		this.feedid=UUID.randomUUID().toString().replace("-", "");
	}
    
}