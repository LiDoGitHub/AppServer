package com.gjyl.appserver.dao;

import java.util.List;

import com.gjyl.appserver.pojo.HotSearch;

public interface HotSearchMapper {
    int deleteByPrimaryKey(String sid);

    int insert(HotSearch record);

    int insertSelective(HotSearch record);

    HotSearch selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(HotSearch record);

    int updateByPrimaryKey(HotSearch record);

	List<HotSearch> getHotSearch();

	HotSearch getHotSearchByContent(String content);

	int updateSTimes(HotSearch search);

	int addSearch(HotSearch search);

}