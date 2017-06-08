package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Lecture;

import java.util.List;

public interface LectureMapper {
    int deleteByPrimaryKey(String lecid);

    int insert(Lecture record);

    //新增视频
    int insertSelective(Lecture record);

    //视频详情
    Lecture selectByPrimaryKey(String lecid);

    //更新视频信息
    int updateByPrimaryKeySelective(Lecture record);

    int updateByPrimaryKey(Lecture record);

    //获取所有
	List<Lecture> getAllLectures();
}