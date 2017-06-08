package com.gjyl.appserver.dao;

import java.util.List;

import com.gjyl.appserver.pojo.DocComment;

public interface DocCommentMapper {
    int deleteByPrimaryKey(String comid);

    int insert(DocComment record);

    int insertSelective(DocComment record);

    DocComment selectByPrimaryKey(String comid);

    int updateByPrimaryKeySelective(DocComment record);

    int updateByPrimaryKey(DocComment record);

	List<DocComment> getHealthComment(String healthId);

	int addDocComment(DocComment docComment);
}