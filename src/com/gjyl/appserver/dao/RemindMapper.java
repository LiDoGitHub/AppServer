package com.gjyl.appserver.dao;

import java.util.List;
import java.util.Map;

import com.gjyl.appserver.pojo.Remind;

public interface RemindMapper {

    int insert(Remind record);

    int insertSelective(Remind record);

    Remind selectByPrimaryKey(String remindid);

    int updateByPrimaryKeySelective(Remind record);

    int updateByPrimaryKey(Remind record);

    //获取用要提醒
	List<Remind> getRemind(Map<String, String> map);

	//添加用药提醒
	int addRemind(Remind remind);
	//是否存在
	int isExist(Map<String, Object> map);
	//修改提醒
	int updateRemind(Remind remind);

	//详情
	Remind getRemindById(String remindid);

	//删除
	int deleteRemind(String remindid);
}