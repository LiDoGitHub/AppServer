package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Registration;

public interface RegistrationMapper {
    int deleteByPrimaryKey(String regid);

    int insert(Registration record);

    int insertSelective(Registration record);

    Registration selectByPrimaryKey(String regid);

    int updateByPrimaryKeySelective(Registration record);

    int updateByPrimaryKey(Registration record);

	int addRegUser(Registration registration);
}