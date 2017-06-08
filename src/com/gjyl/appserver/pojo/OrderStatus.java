package com.gjyl.appserver.pojo;

import java.util.UUID;

public class OrderStatus {
    private String statusid;

    private String status;

    private String description;

    private String memo;

    public String getStatusid() {
        return statusid;
    }

    public void setStatusid(String statusid) {
        this.statusid = statusid == null ? null : statusid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public OrderStatus() {
		this.statusid=UUID.randomUUID().toString().replace("-", "");
	}
    
}