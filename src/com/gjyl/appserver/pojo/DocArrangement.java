package com.gjyl.appserver.pojo;

import java.util.UUID;

public class DocArrangement {
    private String arrid;

    private Integer monday;

    private Integer tuesday;

    private Integer wensday;

    private Integer thursday;

    private Integer friday;

    private Integer saturday;

    private Integer sunday;

    private String memo;

    private String docid;

    private Doctor doctor;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getArrid() {
        return arrid;
    }

    public void setArrid(String arrid) {
        this.arrid = arrid == null ? null : arrid.trim();
    }

    public Integer getMonday() {
        return monday;
    }

    public void setMonday(Integer monday) {
        this.monday = monday;
    }

    public Integer getTuesday() {
        return tuesday;
    }

    public void setTuesday(Integer tuesday) {
        this.tuesday = tuesday;
    }

    public Integer getWensday() {
        return wensday;
    }

    public void setWensday(Integer wensday) {
        this.wensday = wensday;
    }

    public Integer getThursday() {
        return thursday;
    }

    public void setThursday(Integer thursday) {
        this.thursday = thursday;
    }

    public Integer getFriday() {
        return friday;
    }

    public void setFriday(Integer friday) {
        this.friday = friday;
    }

    public Integer getSaturday() {
        return saturday;
    }

    public void setSaturday(Integer saturday) {
        this.saturday = saturday;
    }

    public Integer getSunday() {
        return sunday;
    }

    public void setSunday(Integer sunday) {
        this.sunday = sunday;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid == null ? null : docid.trim();
    }

    public DocArrangement() {
        this.arrid= UUID.randomUUID().toString().replace("-","");
    }
}