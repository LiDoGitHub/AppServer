package com.gjyl.appserver.service;

import java.util.List;

import com.gjyl.appserver.pojo.Comment;

public interface CommentService {

	public List<Comment> getUserComment(String docId);

	public Boolean addComment(Comment comment);
	

}
