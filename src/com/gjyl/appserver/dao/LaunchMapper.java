package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Launch;

public interface LaunchMapper {
    int deleteByPrimaryKey(String launchid);

    int insert(Launch record);

    int insertSelective(Launch record);

    Launch selectByPrimaryKey(String launchid);

    int updateByPrimaryKeySelective(Launch record);

    int updateByPrimaryKey(Launch record);
}