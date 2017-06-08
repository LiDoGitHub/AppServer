package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.AppUserMapper;
import com.gjyl.appserver.dao.FeedbackMapper;
import com.gjyl.appserver.pojo.AppUser;
import com.gjyl.appserver.pojo.Feedback;
import com.gjyl.appserver.service.FeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by LiD on 2017/6/7.
 */
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    private FeedbackMapper dao;
    @Resource
    private AppUserMapper userDao;

    private Integer pageSize=20;

    @Override
    public Boolean addFeedback(Feedback feedback) {
        int rst = dao.insertSelective(feedback);
        return rst > 0 ? true : false;
    }

    @Override
    public List<Feedback> getFeedbackByPage(Integer pageNum) {

        List<Feedback> list = dao.getFeedbackByPage(pageSize * pageNum);
        for (Feedback fb : list) {
            if (fb.getUserid()!=null&&!fb.getUserid().equals("")){
                AppUser user = userDao.getUserById(fb.getUserid());
                fb.setUser(user);
            }
        }
        return list;
    }

    @Override
    public Boolean delFeedbackById(String fbid) {
        int rst = dao.deleteByPrimaryKey(fbid);
        return rst > 0 ? true : false;
    }

    @Override
    public Integer getTotalNum() {
        return dao.getTotalNum();
    }
}
