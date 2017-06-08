package com.gjyl.appserver.pojo;

import java.util.UUID;

public class DiseaseLibrary {
    private String disid;

    private String name;

    private String bio;

    private String usericon;

    private String username;

    private String doctoricon;

    private String doctorname;

    private String sectionid;
    
    private String secname;

    private String doctorid;
    
    private String docname;

    private Integer iscommon;
    
    public Integer getIscommon() {
		return iscommon;
	}

	public void setIscommon(Integer iscommon) {
		this.iscommon = iscommon;
	}

	public String getSecname() {
		return secname;
	}

	public void setSecname(String secname) {
		this.secname = secname;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getDisid() {
        return disid;
    }

    public void setDisid(String disid) {
        this.disid = disid == null ? null : disid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio == null ? null : bio.trim();
    }

    public String getUsericon() {
        return usericon;
    }

    public void setUsericon(String usericon) {
        this.usericon = usericon == null ? null : usericon.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getDoctoricon() {
        return doctoricon;
    }

    public void setDoctoricon(String doctoricon) {
        this.doctoricon = doctoricon == null ? null : doctoricon.trim();
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname == null ? null : doctorname.trim();
    }

    public String getSectionid() {
        return sectionid;
    }

    public void setSectionid(String sectionid) {
        this.sectionid = sectionid == null ? null : sectionid.trim();
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid == null ? null : doctorid.trim();
    }

	public DiseaseLibrary() {
		this.disid=UUID.randomUUID().toString().replace("-", "");
	}
    
    
}