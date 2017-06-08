package com.gjyl.appserver.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gjyl.appserver.dao.RegistrationMapper;
import com.gjyl.appserver.pojo.Registration;
import com.gjyl.appserver.service.RegistrationService;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

	@Resource
	private RegistrationMapper dao;

	public Boolean addRegUser(Registration registration) {
		
		return false;
	}
}
