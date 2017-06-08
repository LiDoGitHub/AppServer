package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.MyFocuses;

import java.util.List;
import java.util.Map;

public interface MyFocusesMapper {
    int deleteByPrimaryKey(String focusid);

    int insert(MyFocuses record);



    MyFocuses selectByPrimaryKey(String focusid);

    int updateByPrimaryKeySelective(MyFocuses record);

    int updateByPrimaryKey(MyFocuses record);

    //是否关注
    int isExist(Map<String, String> map);

    //我关注的用户
    List<String> getMyFocus(String userid);

    //添加我的关注
    int insertSelective(MyFocuses record);

    //取消关注
    int delMyFocus(Map<String, String> map);
}