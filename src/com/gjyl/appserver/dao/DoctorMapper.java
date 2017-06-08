package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Doctor;

import java.util.List;
import java.util.Map;

public interface DoctorMapper {
    int deleteByPrimaryKey(String doctorid);

    int insert(Doctor record);

    Doctor selectByPrimaryKey(String doctorid);

    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKeyWithBLOBs(Doctor record);

    List<Doctor> getRandomDr();

	List<Doctor> getDrList();

	Doctor getDrInfo(String doctorid);

	//联想查询用
	List<String> getDocInfoByContent(String content);

	//热搜用
	List<Doctor> getDoctorByContent(String content);

    //Excel导入
    int executeBatch(List<Object> list);

    //更新医生头像
    int updateDocIcon(Map<String, String> map);

    //新增医生
    int insertSelective(Doctor record);

    //科室下医生
    List<Doctor> getDocBySection(String secName);

    //当日坐诊
    List<Doctor> getTodayDoctor(Map<String, String> map);
}