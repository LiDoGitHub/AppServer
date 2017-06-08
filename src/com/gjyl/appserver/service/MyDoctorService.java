package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.MyDoctor;

import java.util.List;

public interface MyDoctorService {

	List<MyDoctor> getMyDoctor(String userId);

	Boolean addMyDoctor(MyDoctor myDoctor);

    Boolean isCollected(String docId, String userid);

    Boolean delMyDoctor(String docid, String userid);
}
