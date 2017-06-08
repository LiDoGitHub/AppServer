package com.gjyl.appserver.dao;

import java.util.List;

import com.gjyl.appserver.pojo.Hot;

public interface HotMapper {
    int deleteByPrimaryKey(String hotid);

    int insert(Hot record);

    int insertSelective(Hot record);

    Hot selectByPrimaryKey(String hotid);

    int updateByPrimaryKeySelective(Hot record);

    int updateByPrimaryKey(Hot record);
    
    List<Hot> getAllArticles();
}