package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.EssayComment;

import java.util.List;

public interface ECommentService {

    List<EssayComment> getCommentByEid(String eid);

    Boolean addEComment(EssayComment comment);
}
