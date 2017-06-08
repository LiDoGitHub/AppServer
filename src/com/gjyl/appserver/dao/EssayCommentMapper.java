package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.EssayComment;

import java.util.List;

public interface EssayCommentMapper {
    int deleteByPrimaryKey(String ecid);

    int insert(EssayComment record);



    EssayComment selectByPrimaryKey(String ecid);

    int updateByPrimaryKeySelective(EssayComment record);

    int updateByPrimaryKey(EssayComment record);

    //评论列表
    List<EssayComment> getCommentByEid(String eid);

    //添加评论
    int insertSelective(EssayComment record);
}