package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.DocArrangementMapper;
import com.gjyl.appserver.dao.DoctorMapper;
import com.gjyl.appserver.pojo.DocArrangement;
import com.gjyl.appserver.pojo.Doctor;
import com.gjyl.appserver.service.DoctorService;
import com.gjyl.appserver.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {

	@Resource
	private DoctorMapper mapper;
	@Resource
	private DocArrangementMapper arrDao;
	
	public List<Doctor> getRandomDr(){
		return mapper.getRandomDr();
	}

	public List<Doctor> getDrList() {
		
		return mapper.getDrList();
	}

	public Doctor getDrInfo(String docId) {
		Doctor doctor = mapper.getDrInfo(docId);
		DocArrangement arrangement= arrDao.getArrangeByDocId(docId);
		if (arrangement!=null) {
			doctor.setArrangement(arrangement);
		}
		return doctor;
	}

	public Boolean executeBatch(List<Object> list) {
		int rst=mapper.executeBatch(list);
		if (rst>0){
			return true;
		}
		return false;
	}

	public Boolean updateDocInfo(Doctor doctor) {
		int rst=mapper.updateByPrimaryKeySelective(doctor);
		if (rst>0)
			return true;
		return false;
	}

	public Boolean updateDocIcon(String docid, String s) {
		Map<String,String> map=new HashMap<>();
		map.put("docid",docid);
		map.put("path",s);
		int rst = mapper.updateDocIcon(map);
		if (rst>0)
			return true;
		return false;
	}

	public Boolean addDoctor(Doctor doctor) {
		int rst=mapper.insertSelective(doctor);
		if (rst>0)
			return true;
		return false;
	}

	public List<Doctor> getDocBySection(String secName) {

		return mapper.getDocBySection(secName);
	}

	public List<Doctor> getTodayDoctor() {
		String weekDay = DateUtils.getWeekDay();
		Map<String,String> map=new HashMap<>();
		map.put("weekDay",weekDay);
		return mapper.getTodayDoctor(map);
	}
}
