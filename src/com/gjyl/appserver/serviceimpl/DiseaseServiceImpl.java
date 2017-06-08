package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.DiseaseLibraryMapper;
import com.gjyl.appserver.dao.DoctorMapper;
import com.gjyl.appserver.pojo.DiseaseLibraryWithBLOBs;
import com.gjyl.appserver.pojo.Doctor;
import com.gjyl.appserver.service.DiseaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("diseaseService")
public class DiseaseServiceImpl implements DiseaseService {

	@Resource
	private DiseaseLibraryMapper dao;
	@Resource
	private DoctorMapper docDao;
	private static Integer PageSize=20;//每页数量

	public List<DiseaseLibraryWithBLOBs> getDiseaseList(String sectionId) {
		
		return dao.getDiseaseList(sectionId);
	}

	public DiseaseLibraryWithBLOBs getDiseaseById(String disId) {
		//获取疾病+医生的信息
		DiseaseLibraryWithBLOBs disease = dao.getDiseaseById(disId);
		if (disease!=null&&disease.getDoctorid()!=null) {
			Doctor doctor = docDao.getDrInfo(disease.getDoctorid());
			disease.setDoctor(doctor);
		}
		return disease;
	}

	public List<DiseaseLibraryWithBLOBs> getAllDiseases(String pageNum) {
		Integer page=Integer.valueOf(pageNum)*PageSize;
		return dao.getAllDiseases(Integer.valueOf(page));
	}

	public List<DiseaseLibraryWithBLOBs> getCommonDis() {
		return dao.getCommonDis();
	}

	public Boolean updateDisease(DiseaseLibraryWithBLOBs disease) {
		int result=dao.updateDisease(disease);
		if (result>0) {
			return true;
		}
		return false;
	}

	public Boolean addDisease(DiseaseLibraryWithBLOBs dlwb) {
		int rst=dao.insertSelective(dlwb);
		if (rst>0)
			return true;
		return false;
	}

	public Boolean delDiseaseById(String disid) {
		int rst=dao.deleteByPrimaryKey(disid);
		if (rst>0)
			return true;
		return false;
	}

	public Integer getTotalNum() {
		return dao.getTotalNum();
	}
}
