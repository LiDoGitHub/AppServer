package com.gjyl.appserver.service;

import java.util.List;

import com.gjyl.appserver.pojo.DocComment;

public interface DocCommentService {

	List<DocComment> getHealthComment(String healthId);

	Boolean addDocComment(DocComment docComment);

}
