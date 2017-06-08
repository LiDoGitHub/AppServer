package com.gjyl.appserver.pojo;

import java.util.UUID;

public class Cyclopedia {
    private String cyclopediaid;

    private String icon;

    private String cover;

    private String title;

    private String ctime;

    private Integer collectcount;

    private String content;
    
    private String typeid;
    
    private Integer readtimes;

    private CyclType type;

    private Banner banner;

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public CyclType getType() {
        return type;
    }

    public void setType(CyclType type) {
        this.type = type;
    }

    public Integer getReadtimes() {
		return readtimes;
	}

	public void setReadtimes(Integer readtimes) {
		this.readtimes = readtimes;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getCyclopediaid() {
        return cyclopediaid;
    }

    public void setCyclopediaid(String cyclopediaid) {
        this.cyclopediaid = cyclopediaid == null ? null : cyclopediaid.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }

    public Integer getCollectcount() {
        return collectcount;
    }

    public void setCollectcount(Integer collectcount) {
        this.collectcount = collectcount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public Cyclopedia() {
		this.cyclopediaid=UUID.randomUUID().toString().replace("-", "");
	}

    @Override
    public String toString() {
        return "Cyclopedia{" +
                "cyclopediaid='" + cyclopediaid + '\'' +
                ", icon='" + icon + '\'' +
                ", cover='" + cover + '\'' +
                ", title='" + title + '\'' +
                ", ctime='" + ctime + '\'' +
                ", collectcount=" + collectcount +
                ", content='" + content + '\'' +
                ", typeid='" + typeid + '\'' +
                ", readtimes=" + readtimes +
                '}';
    }
}