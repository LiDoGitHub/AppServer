package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Feedback;

import java.util.List;

public interface FeedbackMapper {
    int deleteByPrimaryKey(String feedid);

    int insert(Feedback record);

    //新增反馈
    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(String feedid);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

    //分页获取反馈
    List<Feedback> getFeedbackByPage(int i);

    //总页数
    Integer getTotalNum();
}