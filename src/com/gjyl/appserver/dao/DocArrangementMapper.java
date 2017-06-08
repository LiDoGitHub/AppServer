package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.DocArrangement;

public interface DocArrangementMapper {
    int deleteByPrimaryKey(String arrid);

    int insert(DocArrangement record);

    //新增
    int insertSelective(DocArrangement record);

    //查询
    DocArrangement selectByPrimaryKey(String arrid);

    //更新
    int updateByPrimaryKeySelective(DocArrangement record);

    int updateByPrimaryKey(DocArrangement record);

    //查询
    DocArrangement getArrangeByDocId(String docId);
}