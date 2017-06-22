package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.Registration;

import java.util.List;

public interface RegistrationService {

	Boolean addRegUser(Registration registration);

    List<Registration> getRegByPage(Integer integer);

    Registration getRegById(String regid);

    Boolean updateRegistration(Registration registration);

    Integer getTotal();

    List<Registration> getMyRegistration(String userid);

    List<Registration> getRegList();

    void updateRegComStatus(String regid);
}
