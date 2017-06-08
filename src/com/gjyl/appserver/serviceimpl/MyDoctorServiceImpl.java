package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.DoctorMapper;
import com.gjyl.appserver.dao.MyDoctorMapper;
import com.gjyl.appserver.pojo.Doctor;
import com.gjyl.appserver.pojo.MyDoctor;
import com.gjyl.appserver.service.MyDoctorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("myDrService")
public class MyDoctorServiceImpl implements MyDoctorService {

	@Resource
	private MyDoctorMapper dao;

	@Resource
	private DoctorMapper docDao;
	public List<MyDoctor> getMyDoctor(String userId) {

		List<MyDoctor> list = dao.getMyDoctor(userId);
		for (MyDoctor doctor : list) {
			Doctor info = docDao.getDrInfo(doctor.getDoctorid());
			doctor.setDoctor(info);
		}
		return list;
	}
	public Boolean addMyDoctor(MyDoctor myDoctor) {
		int result= dao.addMyDoctor(myDoctor);
		if (result>0) {
			return true;
		}
		return false;
	}

	public Boolean isCollected(String docId, String userid) {
		Map<String,String> map=new HashMap<>();
		map.put("docid",docId);
		map.put("userid",userid);
		int rst=dao.isCollected(map);
		return rst > 0 ? true : false;
	}

	public Boolean delMyDoctor(String docid, String userid) {
		Map<String,String> map=new HashMap<>();
		map.put("docid",docid);
		map.put("userid",userid);
		int rst=dao.delMyDoctor(map);
		return rst > 0 ? true : false;
	}
}
