package com.gjyl.appserver.pojo;

import java.util.UUID;

public class VersionUpdate {
    private String verid;

    private String version;

    private String memo;

    public String getVerid() {
        return verid;
    }

    public void setVerid(String verid) {
        this.verid = verid == null ? null : verid.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public VersionUpdate() {
		this.verid=UUID.randomUUID().toString().replace("-", "");
	}
    
}