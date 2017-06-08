package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.Doctor;

import java.util.List;


public interface DoctorService {

	List<Doctor> getRandomDr();

	List<Doctor> getDrList();

	Doctor getDrInfo(String docId);

    Boolean executeBatch(List<Object> list);

    Boolean updateDocInfo(Doctor doctor);

	Boolean updateDocIcon(String docid, String s);

    Boolean addDoctor(Doctor doctor);

    List<Doctor> getDocBySection(String secName);

    List<Doctor> getTodayDoctor();
}
