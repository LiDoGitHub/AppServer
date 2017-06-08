package com.gjyl.appserver.pojo;

import java.util.List;

public class HotSearchResult {

	//医生,科室,疾病
	private List<Doctor> doctors;
	
	private List<Section> sections;
	
	private List<DiseaseLibraryWithBLOBs> diseases;

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public List<DiseaseLibraryWithBLOBs> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<DiseaseLibraryWithBLOBs> diseases) {
		this.diseases = diseases;
	}
	
}
