package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Registration;

import java.util.List;

public interface RegistrationMapper {
    int deleteByPrimaryKey(String regid);

    int insert(Registration record);

    int insertSelective(Registration record);

    Registration selectByPrimaryKey(String regid);

    int updateByPrimaryKeySelective(Registration record);

    int updateByPrimaryKey(Registration record);

	int addRegUser(Registration registration);

    //分页获取挂号信息
    List<Registration> getRegByPage(Integer page);

    //挂号详情
    Registration getRegById(String regid);

    //总量
    Integer getTotal();

    //我的预约
    List<Registration> getMyRegistration(String userid);

    //所有预约列表
    List<Registration> getRegList(String date);

    void updateRegComStatus(String regid);
}