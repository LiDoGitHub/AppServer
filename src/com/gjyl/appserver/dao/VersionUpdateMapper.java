package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.VersionUpdate;
import com.gjyl.appserver.pojo.VersionUpdateWithBLOBs;

public interface VersionUpdateMapper {
    int deleteByPrimaryKey(String verid);

    int insert(VersionUpdateWithBLOBs record);

    int insertSelective(VersionUpdateWithBLOBs record);

    VersionUpdateWithBLOBs selectByPrimaryKey(String verid);

    int updateByPrimaryKeySelective(VersionUpdateWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(VersionUpdateWithBLOBs record);

    int updateByPrimaryKey(VersionUpdate record);
}