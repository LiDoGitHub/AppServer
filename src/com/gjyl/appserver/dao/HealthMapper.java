package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Health;

public interface HealthMapper {
    int deleteByPrimaryKey(String healthid);

    int insert(Health record);

    int insertSelective(Health record);

    Health selectByPrimaryKey(String healthid);

    int updateByPrimaryKeySelective(Health record);

    int updateByPrimaryKey(Health record);
}