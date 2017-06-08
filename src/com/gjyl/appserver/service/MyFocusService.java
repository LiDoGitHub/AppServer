package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.AppUser;
import com.gjyl.appserver.pojo.MyFocuses;

import java.util.List;

/**
 * Created by LiD on 2017/4/18.
 */
public interface MyFocusService {
    List<AppUser> getMyFocuses(String userid);

    Boolean addMyFocus(MyFocuses myFocus);

    Boolean delMyFocus(String focUserId, String userid);
}
