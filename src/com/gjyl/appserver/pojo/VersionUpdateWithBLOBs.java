package com.gjyl.appserver.pojo;

public class VersionUpdateWithBLOBs extends VersionUpdate {
    private String downloadpath;

    private String updatelog;

    public String getDownloadpath() {
        return downloadpath;
    }

    public void setDownloadpath(String downloadpath) {
        this.downloadpath = downloadpath == null ? null : downloadpath.trim();
    }

    public String getUpdatelog() {
        return updatelog;
    }

    public void setUpdatelog(String updatelog) {
        this.updatelog = updatelog == null ? null : updatelog.trim();
    }

	public VersionUpdateWithBLOBs() {
		super();
	}
}