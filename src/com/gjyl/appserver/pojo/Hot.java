package com.gjyl.appserver.pojo;

import java.util.UUID;

public class Hot {
    private String hotid;

    private String title;

    private String site;

    private String memo;

    public String getHotid() {
        return hotid;
    }

    public void setHotid(String hotid) {
        this.hotid = hotid == null ? null : hotid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public Hot() {
		this.hotid=UUID.randomUUID().toString().replace("-", "");
	}
    
}