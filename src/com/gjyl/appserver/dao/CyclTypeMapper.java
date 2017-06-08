package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.CyclType;

import java.util.List;

public interface CyclTypeMapper {

    int insert(CyclType record);

    CyclType selectByPrimaryKey(String typeid);

    int updateByPrimaryKey(CyclType record);

    //分类列表
	List<CyclType> getAllTypes();

    //新增文章分类
    int insertSelective(CyclType record);

    //文章分类详情
    CyclType getTypeById(String id);

    //更新
    int updateByPrimaryKeySelective(CyclType record);

    //删除文章分类
    int deleteByPrimaryKey(String typeid);
}