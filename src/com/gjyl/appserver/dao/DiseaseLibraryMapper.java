package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.DiseaseLibraryWithBLOBs;

import java.util.List;

public interface DiseaseLibraryMapper {


    DiseaseLibraryWithBLOBs selectByPrimaryKey(String disid);

    int updateDisease(DiseaseLibraryWithBLOBs record);

	List<DiseaseLibraryWithBLOBs> getDiseaseList(String sectionId);

	DiseaseLibraryWithBLOBs getDiseaseById(String disId);

	List<DiseaseLibraryWithBLOBs> getAllDiseases(Integer pageNum);

	List<DiseaseLibraryWithBLOBs> getCommonDis();

	//联想搜索
	List<String> getDisInfoByContent(String content);

	//搜索结果
	List<DiseaseLibraryWithBLOBs> getDisByContent(String content);

	//新增疾病
	int insertSelective(DiseaseLibraryWithBLOBs record);

	//删除疾病
	int deleteByPrimaryKey(String disid);

	//总量
	Integer getTotalNum();
}