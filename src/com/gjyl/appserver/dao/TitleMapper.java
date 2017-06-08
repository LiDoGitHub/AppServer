package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Title;

public interface TitleMapper {
    int deleteByPrimaryKey(String titleid);

    int insert(Title record);

    int insertSelective(Title record);

    Title selectByPrimaryKey(String titleid);

    int updateByPrimaryKeySelective(Title record);

    int updateByPrimaryKey(Title record);
}