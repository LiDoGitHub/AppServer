package com.gjyl.appserver.pojo;

import java.util.UUID;

public class Launch {
    private String launchid;

    private String img;

    private String laundate;

    public String getLaunchid() {
        return launchid;
    }

    public void setLaunchid(String launchid) {
        this.launchid = launchid == null ? null : launchid.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getLaundate() {
        return laundate;
    }

    public void setLaundate(String laundate) {
        this.laundate = laundate == null ? null : laundate.trim();
    }

	public Launch() {
		this.launchid=UUID.randomUUID().toString().replace("-", "");
	}
    
}