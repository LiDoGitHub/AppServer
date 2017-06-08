package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.Feedback;

import java.util.List;

/**
 * Created by LiD on 2017/6/7.
 */
public interface FeedbackService {
    Boolean addFeedback(Feedback feedback);

    List<Feedback> getFeedbackByPage(Integer integer);

    Boolean delFeedbackById(String fbid);

    Integer getTotalNum();
}
