package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.AppUserMapper;
import com.gjyl.appserver.dao.EssayCommentMapper;
import com.gjyl.appserver.pojo.AppUser;
import com.gjyl.appserver.pojo.EssayComment;
import com.gjyl.appserver.service.ECommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("ecommentService")
public class ECommentServiceImpl implements ECommentService {

    @Resource
    private EssayCommentMapper dao;
    @Resource
    private AppUserMapper userDao;

    @Override
    public List<EssayComment> getCommentByEid(String eid) {
        List<EssayComment> comments = dao.getCommentByEid(eid);
        for (EssayComment ec : comments) {
            if (ec.getEcuserid() != null && (!ec.getEcuserid().equals(""))) {
                //评论用户信息
                AppUser ecUser = userDao.getUserById(ec.getEcuserid());
                ec.setEcUser(ecUser);
            }
            if (ec.getRuserid()!=null&&(!ec.getRuserid().equals(""))){
                //回复用户信息
                AppUser user = userDao.getUserById(ec.getRuserid());
                ec.setReplyUser(user);
            }
        }
        return comments;
    }

    @Override
    public Boolean addEComment(EssayComment comment) {
        int rst=dao.insertSelective(comment);
        if (rst>0){
            return true;
        }
        return false;
    }
}
