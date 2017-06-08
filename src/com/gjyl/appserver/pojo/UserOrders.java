package com.gjyl.appserver.pojo;

import java.util.Date;
import java.util.UUID;

public class UserOrders {
    private String orderid;

    private String orderstatus;

    private Date ordertime;

    private String userid;

    private String memp;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus == null ? null : orderstatus.trim();
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getMemp() {
        return memp;
    }

    public void setMemp(String memp) {
        this.memp = memp == null ? null : memp.trim();
    }

    public UserOrders() {
        this.orderid= UUID.randomUUID().toString().replace("-","");
    }
}