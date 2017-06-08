package com.gjyl.appserver.pojo;

import java.util.UUID;

public class UserVoucher {
    private String id;

    private String userid;

    private Integer voutype;

    private String endtime;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getVoutype() {
        return voutype;
    }

    public void setVoutype(Integer voutype) {
        this.voutype = voutype;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public UserVoucher() {
		this.id=UUID.randomUUID().toString().replace("-", "");
	}

	public String toString() {
		return "UserVoucher [id=" + id + ", userid=" + userid + ", voutype="
				+ voutype + ", endtime=" + endtime + ", status=" + status + "]";
	}
	
}