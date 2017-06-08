package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Section;

import java.util.List;

public interface SectionMapper {
    int deleteByPrimaryKey(String sectionid);

    int insert(Section record);

    int insertSelective(Section record);

    Section selectByPrimaryKey(String sectionid);
    //更新科室
    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);

	List<Section> getSecList();

	//联想搜索
	List<String> getSectionByContent(String content);
	//搜索结果相关
	List<Section> getSecByContent(String content);

    //科室详情,后台用
    Section getSectionById(String secid);

    //批量插入
    int executeBatch(List<Object> list);
}