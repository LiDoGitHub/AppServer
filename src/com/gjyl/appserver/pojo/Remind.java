package com.gjyl.appserver.pojo;

import java.util.Date;
import java.util.UUID;

public class Remind {
    private String remindid;

    private Date reminddate;

    private String time1;

    private String time2;

    private String time3;

    private String content1;

    private String content2;

    private String content3;

    private Date addtime;

    private String userid;
    
    private Date enddate;

    public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getRemindid() {
        return remindid;
    }

    public void setRemindid(String remindid) {
        this.remindid = remindid == null ? null : remindid.trim();
    }

    public Date getReminddate() {
		return reminddate;
	}

	public void setReminddate(Date reminddate) {
		this.reminddate = reminddate;
	}

	public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1 == null ? null : time1.trim();
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2 == null ? null : time2.trim();
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3 == null ? null : time3.trim();
    }

    public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2 == null ? null : content2.trim();
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3 == null ? null : content3.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

	public Remind() {
		this.remindid=UUID.randomUUID().toString().replace("-", "");
	}

	public String toString() {
		return "Remind [remindid=" + remindid + ", reminddate=" + reminddate
				+ ", time1=" + time1 + ", time2=" + time2 + ", time3=" + time3
				+ ", content1=" + content1 + ", content2=" + content2
				+ ", content3=" + content3 + ", addtime=" + addtime
				+ ", userid=" + userid + ", enddate=" + enddate + "]";
	}
	
	
	
}