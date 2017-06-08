package com.gjyl.appserver.pojo;

import java.util.Date;
import java.util.UUID;

public class EssayComment {
    private String ecid;

    private String eccontent;

    private String ecuserid;

    private String ecusername;

    private AppUser ecUser;//评论用户

    private Date ectime;

    private String replyid;//评论ID即回复哪一条说说的评论

    private String ressayid;//说说ID

    private AppUser replyUser;//回复用户

    private String ruserid;

    private String rusername;

    private String rcontent;

    private Date rtime;

    private String memo;

    public AppUser getEcUser() {
        return ecUser;
    }

    public void setEcUser(AppUser ecUser) {
        this.ecUser = ecUser;
    }

    public AppUser getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(AppUser replyUser) {
        this.replyUser = replyUser;
    }

    public String getEcid() {
        return ecid;
    }

    public void setEcid(String ecid) {
        this.ecid = ecid == null ? null : ecid.trim();
    }

    public String getEccontent() {
        return eccontent;
    }

    public void setEccontent(String eccontent) {
        this.eccontent = eccontent == null ? null : eccontent.trim();
    }

    public String getEcuserid() {
        return ecuserid;
    }

    public void setEcuserid(String ecuserid) {
        this.ecuserid = ecuserid == null ? null : ecuserid.trim();
    }

    public String getEcusername() {
        return ecusername;
    }

    public void setEcusername(String ecusername) {
        this.ecusername = ecusername == null ? null : ecusername.trim();
    }

    public Date getEctime() {
        return ectime;
    }

    public void setEctime(Date ectime) {
        this.ectime = ectime;
    }

    public String getReplyid() {
        return replyid;
    }

    public void setReplyid(String replyid) {
        this.replyid = replyid == null ? null : replyid.trim();
    }

    public String getRessayid() {
        return ressayid;
    }

    public void setRessayid(String ressayid) {
        this.ressayid = ressayid == null ? null : ressayid.trim();
    }

    public String getRuserid() {
        return ruserid;
    }

    public void setRuserid(String ruserid) {
        this.ruserid = ruserid == null ? null : ruserid.trim();
    }

    public String getRusername() {
        return rusername;
    }

    public void setRusername(String rusername) {
        this.rusername = rusername == null ? null : rusername.trim();
    }

    public String getRcontent() {
        return rcontent;
    }

    public void setRcontent(String rcontent) {
        this.rcontent = rcontent == null ? null : rcontent.trim();
    }

    public Date getRtime() {
        return rtime;
    }

    public void setRtime(Date rtime) {
        this.rtime = rtime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public EssayComment() {
		this.ecid=UUID.randomUUID().toString().replace("-", "");
	}
    
    
    
    
}