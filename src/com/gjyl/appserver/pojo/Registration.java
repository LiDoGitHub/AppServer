package com.gjyl.appserver.pojo;

import java.util.Date;
import java.util.UUID;

public class Registration {
    private String regid;

    private String city;

    private String section;

    private String title;

    private Date reservationdate;

    private String name;

    private String gender;

    private String age;

    private String phone;

    private String content;

    private String money;

    private String ordercode;

    private String status;

    private Date createtime;

    private String userid;

    private String orderStatusid;

    private String sectionid;

    private String titleid;

    private String doctorid;

    private Integer isexpert;

    private String docname;

    private Doctor doctor;

    private Integer hassent;

    private Integer iscommented;

    public Integer getIscommented() {
        return iscommented;
    }

    public void setIscommented(Integer iscommented) {
        this.iscommented = iscommented;
    }

    public Integer getHassent() {
        return hassent;
    }

    public void setHassent(Integer hassent) {
        this.hassent = hassent;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public Integer getIsexpert() {
        return isexpert;
    }

    public void setIsexpert(Integer isexpert) {
        this.isexpert = isexpert;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid == null ? null : regid.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getReservationdate() {
        return reservationdate;
    }

    public void setReservationdate(Date reservationdate) {
        this.reservationdate = reservationdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getOrderStatusid() {
		return orderStatusid;
	}

	public void setOrderStatusid(String orderStatusid) {
		this.orderStatusid = orderStatusid;
	}

	public String getSectionid() {
        return sectionid;
    }

    public void setSectionid(String sectionid) {
        this.sectionid = sectionid == null ? null : sectionid.trim();
    }

    public String getTitleid() {
        return titleid;
    }

    public void setTitleid(String titleid) {
        this.titleid = titleid == null ? null : titleid.trim();
    }

	public Registration() {
		this.regid=UUID.randomUUID().toString().replace("-", "");
        this.createtime=new Date();
    }
    
}