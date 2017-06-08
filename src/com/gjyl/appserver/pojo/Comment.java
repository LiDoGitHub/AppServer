package com.gjyl.appserver.pojo;

import java.util.UUID;

public class Comment {
    private String commentid;

    private String doctorid;

    private String userid;

    private String ctime;

    private String content;
    
    private Doctor doctor;
    
    private AppUser appUser;

    public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid == null ? null : commentid.trim();
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

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public Comment() {
		this.commentid=UUID.randomUUID().toString().replace("-", "");
	}

	public String toString() {
		return "Comment [commentid=" + commentid + ", doctorid=" + doctorid
				+ ", userid=" + userid + ", ctime=" + ctime + ", content="
				+ content + ", doctor=" + doctor + ", appUser=" + appUser + "]";
	}
}