package com.gjyl.appserver.pojo;

import java.util.Date;
import java.util.UUID;

public class HotSearch {
    private String sid;

    private String scontent;

    private Date sdate;

    private Integer stimes;

    private String memo;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getScontent() {
        return scontent;
    }

    public void setScontent(String scontent) {
        this.scontent = scontent == null ? null : scontent.trim();
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Integer getStimes() {
        return stimes;
    }

    public void setStimes(Integer stimes) {
        this.stimes = stimes;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public HotSearch() {
		this.sid=UUID.randomUUID().toString().replace("-", "");
	}
    
}