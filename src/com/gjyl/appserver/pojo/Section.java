package com.gjyl.appserver.pojo;

import java.util.List;
import java.util.UUID;

public class Section {
    private String sectionid;

    private String name;

    private String icon1;

    private String icon2;

    private String memo;
    
    private List<DiseaseLibraryWithBLOBs> disList;

    public List<DiseaseLibraryWithBLOBs> getDisList() {
		return disList;
	}

	public void setDisList(List<DiseaseLibraryWithBLOBs> disList) {
		this.disList = disList;
	}

	public String getSectionid() {
        return sectionid;
    }

    public void setSectionid(String sectionid) {
        this.sectionid = sectionid == null ? null : sectionid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon1() {
        return icon1;
    }

    public void setIcon1(String icon1) {
        this.icon1 = icon1 == null ? null : icon1.trim();
    }

    public String getIcon2() {
        return icon2;
    }

    public void setIcon2(String icon2) {
        this.icon2 = icon2 == null ? null : icon2.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public Section() {
		this.sectionid=UUID.randomUUID().toString().replace("-", "");
	}
    
}