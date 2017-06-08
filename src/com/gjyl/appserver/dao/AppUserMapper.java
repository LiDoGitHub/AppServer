package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.AppUser;

import java.util.List;

public interface AppUserMapper {
    //删除用户
    int deleteByPrimaryKey(String userid);

    int insert(AppUser record);

    int insertSelective(AppUser record);

    //通过ID获取用户信息
    AppUser getUserById(String userid);
    
    //根据手机号,获取用户信息
    AppUser getUserByPhone(String phone);

    //新增用户
	int addUser(AppUser user);
	//更新用户
	int updateUserByPhone(AppUser user);

	//友盟登录
	AppUser umLogin(String uid);

    //关注用户
    List<AppUser> getFocusUsers(List<String> myFocus);

    //全部用户
    List<AppUser> getAllUsers();
}