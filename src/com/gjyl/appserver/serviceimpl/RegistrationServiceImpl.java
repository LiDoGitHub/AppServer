package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.DoctorMapper;
import com.gjyl.appserver.dao.RegistrationMapper;
import com.gjyl.appserver.dao.SectionMapper;
import com.gjyl.appserver.pojo.Doctor;
import com.gjyl.appserver.pojo.Registration;
import com.gjyl.appserver.service.RegistrationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

	@Resource
	private RegistrationMapper dao;
	@Resource
	private DoctorMapper docDao;
	@Resource
	private SectionMapper secDao;

	private Integer pageSize=20;

	public Boolean addRegUser(Registration registration) {
		int rst = dao.addRegUser(registration);
		return rst > 0 ? true : false;
	}

	@Override
	public List<Registration> getRegByPage(Integer pageNum) {
		List<Registration> list=dao.getRegByPage(pageNum*pageSize);
		/*for (Registration reg : list) {
			if (reg.getDoctorid()!=null&&!reg.getDoctorid().equals("")){//设置医生信息
				Doctor doctor = docDao.getDrInfo(reg.getDoctorid());
				reg.setDoctor(doctor);
			}
			*//*if (reg.getSectionid()!=null&&!reg.getSectionid().equals("")){//设置科室信息
				reg.setSection(secDao.getSectionById(reg.getSectionid()).getName());
			}*//*
		}*/
		return list;
	}

	@Override
	public Registration getRegById(String regid) {
		Registration registration = dao.getRegById(regid);
		if (registration.getDoctorid()!=null&&!registration.getDoctorid().equals("")){
			Doctor doctor = docDao.getDrInfo(registration.getDoctorid());
			registration.setDoctor(doctor);
		}
		/*if (registration.getSectionid()!=null&&!registration.getSectionid().equals("")){
			Section section = secDao.getSectionById(registration.getSectionid());
			registration.setSection(section.getName());
		}*/
		return registration;
	}

	@Override
	public Boolean updateRegistration(Registration registration) {
		int rst = dao.updateByPrimaryKeySelective(registration);
		return rst > 0 ? true : false;
	}

	@Override
	public Integer getTotal() {

		return dao.getTotal();
	}

	@Override
	public List<Registration> getMyRegistration(String userid) {
		List<Registration> list = dao.getMyRegistration(userid);
		for (Registration reg : list) {
			if (reg.getDoctorid()!=null&&!reg.getDoctorid().equals("")){
				Doctor doctor = docDao.getDrInfo(reg.getDoctorid());
				reg.setDoctor(doctor);
			}
		}
		return list;
	}

	@Override
	public List<Registration> getRegList() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = format.format(date);
		return dao.getRegList(dateStr);
	}

	@Override
	public void updateRegComStatus(String regid) {
		dao.updateRegComStatus(regid);
	}
}
