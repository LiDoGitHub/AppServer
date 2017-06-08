package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Cases;

import java.util.List;

public interface CasesMapper {

    int insert(Cases record);

    int insertSelective(Cases record);

    Cases selectByPrimaryKey(String caseid);

    int updateByPrimaryKey(Cases record);

    //列表
	List<Cases> getMyCases(String userid);
	//添加
	int addCases(Cases cases);
    //编辑
    int updateByPrimaryKeySelective(Cases record);
    //删除
    int deleteByPrimaryKey(String caseid);
	//详情
	Cases getCaseInfo(String id);
}