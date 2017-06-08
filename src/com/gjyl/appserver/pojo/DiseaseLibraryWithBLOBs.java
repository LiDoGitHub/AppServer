package com.gjyl.appserver.pojo;

public class DiseaseLibraryWithBLOBs extends DiseaseLibrary {

    private String userputquestion;

    private String doctoranswerquestion;

    private String symptom;

    private String cure;

    private String prompt;
    
    private Doctor doctor;

    public String getUserputquestion() {
        return userputquestion;
    }

    public void setUserputquestion(String userputquestion) {
        this.userputquestion = userputquestion == null ? null : userputquestion.trim();
    }

    public String getDoctoranswerquestion() {
        return doctoranswerquestion;
    }

    public void setDoctoranswerquestion(String doctoranswerquestion) {
        this.doctoranswerquestion = doctoranswerquestion == null ? null : doctoranswerquestion.trim();
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom == null ? null : symptom.trim();
    }

    public String getCure() {
        return cure;
    }

    public void setCure(String cure) {
        this.cure = cure == null ? null : cure.trim();
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt == null ? null : prompt.trim();
    }

	public DiseaseLibraryWithBLOBs() {
		super();
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String toString() {
		return "DiseaseLibraryWithBLOBs [userputquestion=" + userputquestion
				+ ", doctoranswerquestion=" + doctoranswerquestion
				+ ", symptom=" + symptom + ", cure=" + cure + ", prompt="
				+ prompt + ", doctor=" + doctor + "]";
	}
	
	
	
}