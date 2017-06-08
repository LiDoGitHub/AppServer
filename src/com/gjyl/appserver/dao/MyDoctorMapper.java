package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.MyDoctor;

import java.util.List;
import java.util.Map;

public interface MyDoctorMapper {
    int deleteByPrimaryKey(String mydrid);

    int insert(MyDoctor record);

    int insertSelective(MyDoctor record);

    MyDoctor selectByPrimaryKey(String mydrid);

    int updateByPrimaryKeySelective(MyDoctor record);

    int updateByPrimaryKey(MyDoctor record);

	List<MyDoctor> getMyDoctor(String userId);

	int addMyDoctor(MyDoctor myDoctor);

    //医生是否收藏
    int isCollected(Map<String, String> map);

    //删除我的医生(取消收藏)
    int delMyDoctor(Map<String, String> map);
}