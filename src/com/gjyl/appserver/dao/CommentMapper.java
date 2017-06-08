package com.gjyl.appserver.dao;

import java.util.List;

import com.gjyl.appserver.pojo.Comment;

public interface CommentMapper {
    int deleteByPrimaryKey(String commentid);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String commentid);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

	List<Comment> getUserComment(String doctorId);

	int addComment(Comment comment);
	
	
}