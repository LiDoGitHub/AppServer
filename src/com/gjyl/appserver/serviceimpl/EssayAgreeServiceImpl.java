package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.AppUserMapper;
import com.gjyl.appserver.dao.EssayAgreeMapper;
import com.gjyl.appserver.pojo.AppUser;
import com.gjyl.appserver.pojo.EssayAgree;
import com.gjyl.appserver.service.EssayAgreeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by LiD on 2017/4/21.
 */
@Service("essayAgreeService")
public class EssayAgreeServiceImpl implements EssayAgreeService {

    @Resource
    private EssayAgreeMapper dao;
    @Resource
    private AppUserMapper userDao;

    @Override
    public List<EssayAgree> getEssayAgreeList(String eid) {
        List<EssayAgree> list= dao.getAgreeByEid(eid);
        for (EssayAgree ea : list) {
            AppUser user = userDao.getUserById(ea.getUserid());
            ea.setUser(user);
        }
        return list;
    }

}
