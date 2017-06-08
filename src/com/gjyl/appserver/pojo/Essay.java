package com.gjyl.appserver.pojo;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Essay {
    private String eid;

    private String econtent;

    private String eimages;

    private Integer etimes;

    private Integer eagrees;

    private Integer ecommontcount;

    private String ememo;

    private String userid;

    private Date epubtime;

    private Boolean isfocus;

    private AppUser user;

    private Boolean isagree;

    private List<EssayComment> essayCommentList;

    public List<EssayComment> getEssayCommentList() {
        return essayCommentList;
    }

    public void setEssayCommentList(List<EssayComment> essayCommentList) {
        this.essayCommentList = essayCommentList;
    }

    public Boolean getIsagree() {
        return isagree;
    }

    public void setIsagree(Boolean isagree) {
        this.isagree = isagree;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
    }

    public String getEcontent() {
        return econtent;
    }

    public void setEcontent(String econtent) {
        this.econtent = econtent == null ? null : econtent.trim();
    }

    public String getEimages() {
        return eimages;
    }

    public void setEimages(String eimages) {
        this.eimages = eimages == null ? null : eimages.trim();
    }

    public Integer getEtimes() {
        return etimes;
    }

    public void setEtimes(Integer etimes) {
        this.etimes = etimes;
    }

    public Integer getEagrees() {
        return eagrees;
    }

    public void setEagrees(Integer eagrees) {
        this.eagrees = eagrees;
    }

    public Integer getEcommontcount() {
        return ecommontcount;
    }

    public void setEcommontcount(Integer ecommontcount) {
        this.ecommontcount = ecommontcount;
    }

    public String getEmemo() {
        return ememo;
    }

    public void setEmemo(String ememo) {
        this.ememo = ememo == null ? null : ememo.trim();
    }

    public Date getEpubtime() {
        return epubtime;
    }

    public void setEpubtime(Date epubtime) {
        this.epubtime = epubtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Boolean getIsfocus() {
        return isfocus;
    }

    public void setIsfocus(Boolean isfocus) {
        this.isfocus = isfocus;
    }

    public Essay() {
		this.eid=UUID.randomUUID().toString().replace("-", "");
	}

    public String toString() {
        return "Essay{" +
                "eid='" + eid + '\'' +
                ", econtent='" + econtent + '\'' +
                ", eimages='" + eimages + '\'' +
                ", etimes=" + etimes +
                ", eagrees=" + eagrees +
                ", ecommontcount=" + ecommontcount +
                ", ememo='" + ememo + '\'' +
                ", userid='" + userid + '\'' +
                ", epubtime=" + epubtime +
                '}';
    }
}