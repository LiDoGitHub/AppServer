package com.gjyl.appserver.pojo;

import java.util.Date;
import java.util.UUID;

public class Lecture {
    private String lecid;

    private String title;

    private String cover;

    private String video;

    private String memo;

    private String lduration;

    private Date ltime;

    private Integer agrees;

    public String getLecid() {
        return lecid;
    }

    public void setLecid(String lecid) {
        this.lecid = lecid == null ? null : lecid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getLduration() {
        return lduration;
    }

    public void setLduration(String lduration) {
        this.lduration = lduration == null ? null : lduration.trim();
    }

    public Date getLtime() {
        return ltime;
    }

    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }

    public Integer getAgrees() {
        return agrees;
    }

    public void setAgrees(Integer agrees) {
        this.agrees = agrees;
    }

    public Lecture() {
        this.lecid= UUID.randomUUID().toString().replace("-","");
        this.ltime=new Date();
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "lecid='" + lecid + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", video='" + video + '\'' +
                ", memo='" + memo + '\'' +
                ", lduration='" + lduration + '\'' +
                ", ltime=" + ltime +
                ", agrees=" + agrees +
                '}';
    }
}