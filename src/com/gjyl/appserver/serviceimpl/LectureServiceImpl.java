package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.LectureMapper;
import com.gjyl.appserver.pojo.Lecture;
import com.gjyl.appserver.service.LectureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("lectureService")
public class LectureServiceImpl implements LectureService {

	@Resource
	private LectureMapper dao;

	public List<Lecture> getAllLectures() {
		return dao.getAllLectures();
	}

	public Boolean addLecture(Lecture lecture) {
		int rst=dao.insertSelective(lecture);
		return rst > 0 ? true : false;
	}

	public Lecture getLectureById(String lecid) {
		return dao.selectByPrimaryKey(lecid);
	}


	public Boolean updateLecture(Lecture lecture) {
		int rst=dao.updateByPrimaryKeySelective(lecture);
		return rst>0?true:false;
	}

	public Boolean deleteLecById(String lecid) {
		int rst=dao.deleteByPrimaryKey(lecid);
		return rst>0?true:false;
	}


}
