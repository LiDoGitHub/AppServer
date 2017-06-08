package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.AppUser;

import java.util.List;


public interface UserService {
	
	AppUser GetUserById(String id);
	
	AppUser GetUserByPhone(String phone);

	Boolean addUser(AppUser user);

	Boolean updateUser(AppUser user);

	AppUser umLogin(String uid);

    List<AppUser> getAllUsers();

    Boolean deleteUser(String userid);
}
