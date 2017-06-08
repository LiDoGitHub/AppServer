package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.AppUserMapper;
import com.gjyl.appserver.dao.MyFocusesMapper;
import com.gjyl.appserver.pojo.AppUser;
import com.gjyl.appserver.pojo.MyFocuses;
import com.gjyl.appserver.service.MyFocusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiD on 2017/4/18.
 */

@Service("myfocueService")
public class MyFocusServiceImpl implements MyFocusService {

    @Resource
    private MyFocusesMapper dao;
    @Resource
    private AppUserMapper userDao;

    public List<AppUser> getMyFocuses(String userid) {

        List<String> myFocus = dao.getMyFocus(userid);
        if (myFocus.size()>0) {
            return userDao.getFocusUsers(myFocus);
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public Boolean addMyFocus(MyFocuses myFocus) {
        int rst=dao.insertSelective(myFocus);
        if (rst>0){
            return true;
        }
        return false;
    }

    public Boolean delMyFocus(String focUserId, String userid) {
        Map<String,String> map=new HashMap<>();
        map.put("focUserId",focUserId);
        map.put("userid",userid);
        int rst=dao.delMyFocus(map);
        if (rst>0){
            return true;
        }
        return false;
    }
}
