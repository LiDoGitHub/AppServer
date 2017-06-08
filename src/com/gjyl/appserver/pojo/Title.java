package com.gjyl.appserver.pojo;

import java.util.UUID;

public class Title {
    private String titleid;

    private String name;

    private String memo;

    public String getTitleid() {
        return titleid;
    }

    public void setTitleid(String titleid) {
        this.titleid = titleid == null ? null : titleid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public Title() {
		this.titleid=UUID.randomUUID().toString().replace("-", "");
	}
    
}