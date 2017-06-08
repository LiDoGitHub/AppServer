package com.gjyl.appserver.dao;

import java.util.List;
import java.util.Map;

import com.gjyl.appserver.pojo.Calendar;

public interface CalendarMapper {
    int deleteByPrimaryKey(String id);

    int insert(Calendar record);

    int insertSelective(Calendar record);

    Calendar selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Calendar record);

    int updateByPrimaryKey(Calendar record);

    List<Calendar> getCalendarList(Map<String, String> map);
    
    List<Calendar> getCalendarDateList(Map<String, String> map);

	Calendar getCalendarInfo(String userid, String date);
}