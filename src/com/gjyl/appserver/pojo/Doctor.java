package com.gjyl.appserver.pojo;

import java.util.UUID;

public class Doctor {
    private String doctorid;        //ID

    private String name;            //姓名

    private String username;        //用户名

    private String password;        //密码

    private String icon;            //头像

    private String title;           //职称

    private String sectionid;       //科室ID

    private String section;         //科室名称

    private String hospital;        //所属医院

    private String chatcost;        //图文问诊费用

    private String callcost;        //电话问诊费用

    private String seniority;       //资历

    private Integer commentcount;   //评论次数

    private String city;            //城市

    private String titleid;         //职称ID
    
    private Integer isexpert;       //是否是专家

    private Integer canonline;      //是否可以在线问诊

    private String bio;             //简介

    private String adept;           //擅长

    private DocArrangement arrangement;//医生排班

    private Boolean isCollected;    //是否收藏

    public Boolean getCollected() {
        return isCollected;
    }

    public void setCollected(Boolean collected) {
        isCollected = collected;
    }

    public DocArrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(DocArrangement arrangement) {
        this.arrangement = arrangement;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAdept() {
        return adept;
    }

    public void setAdept(String adept) {
        this.adept = adept;
    }

    public Integer getCanonline() {
        return canonline;
    }

    public void setCanonline(Integer canonline) {
        this.canonline = canonline;
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
        this.doctorid = doctorid == null ? null : doctorid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSectionid() {
        return sectionid;
    }

    public void setSectionid(String sectionid) {
        this.sectionid = sectionid == null ? null : sectionid.trim();
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section == null ? null : section.trim();
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital == null ? null : hospital.trim();
    }

    public String getChatcost() {
        return chatcost;
    }

    public void setChatcost(String chatcost) {
        this.chatcost = chatcost == null ? null : chatcost.trim();
    }

    public String getCallcost() {
        return callcost;
    }

    public void setCallcost(String callcost) {
        this.callcost = callcost == null ? null : callcost.trim();
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority == null ? null : seniority.trim();
    }

    public Integer getCommentcount() {
		return commentcount;
	}

	public void setCommentcount(Integer commentcount) {
		this.commentcount = commentcount;
	}

	public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getTitleid() {
        return titleid;
    }

    public void setTitleid(String titleid) {
        this.titleid = titleid == null ? null : titleid.trim();
    }

	public Doctor() {
		this.doctorid=UUID.randomUUID().toString().replace("-", "");
	}

	@Override
	public String toString() {
		return "Doctor [doctorid=" + doctorid + ", name=" + name
				+ ", username=" + username + ", password=" + password
				+ ", icon=" + icon + ", title=" + title + ", sectionid="
				+ sectionid + ", section=" + section + ", hospital=" + hospital
				+ ", chatcost=" + chatcost + ", callcost=" + callcost
				+ ", seniority=" + seniority + ", commentcount=" + commentcount
				+ ", city=" + city + ", titleid=" + titleid + "]";
	}
}