package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.AppUserMapper;
import com.gjyl.appserver.pojo.AppUser;
import com.gjyl.appserver.service.UserService;
import com.gjyl.appserver.utils.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private AppUserMapper mapper;
	
	public AppUser GetUserById(String id) {
		
		return mapper.getUserById(id);
	}

	public AppUser GetUserByPhone(String phone) {
		
		return mapper.getUserByPhone(phone);
	}

	public Boolean addUser(AppUser user) {
		user.setPassword(MD5Utils.md5(user.getPassword()));
		int result= mapper.addUser(user);
		if (result>0) {
			return true;
			
		}else {
			return false;
		}
	}

	public Boolean updateUser(AppUser user) {
		int result = mapper.updateUserByPhone(user);
		if (result>0) {
			return true;
		}else {
			return false;
		}
	}

	public AppUser umLogin(String uid) {
		return mapper.umLogin(uid);
	}

	public List<AppUser> getAllUsers() {

		return mapper.getAllUsers();
	}

	public Boolean deleteUser(String userid) {
		int rst=mapper.deleteByPrimaryKey(userid);
		if (rst>0)
			return true;
		return false;
	}
}
