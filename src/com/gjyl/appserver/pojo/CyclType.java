package com.gjyl.appserver.pojo;

import java.util.UUID;

public class CyclType {
    private String typeid;

    private String typename;

    private Integer memo;

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public Integer getMemo() {
        return memo;
    }

    public void setMemo(Integer memo) {
        this.memo = memo;
    }

    public CyclType() {
        this.typeid= UUID.randomUUID().toString().replace("-","");
    }

    @Override
	public String toString() {
		return "CyclType [typeid=" + typeid + ", typename=" + typename
				+ ", memo=" + memo + "]";
	}
    
}