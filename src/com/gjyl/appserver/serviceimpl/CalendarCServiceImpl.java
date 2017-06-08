package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.CalendarMapper;
import com.gjyl.appserver.pojo.Calendar;
import com.gjyl.appserver.service.CalendarCService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("calendarService")
public class CalendarCServiceImpl implements CalendarCService {
	@Resource
	private CalendarMapper dao;

	public List<Calendar> getCalendarList(String userid, String date) {
		Map<String, String>map=new HashMap<>();
		map.put("userid", userid);
		map.put("cdate", date);
		return dao.getCalendarList(map);
	}

	public Calendar getCalenDarInfo(String userid, String date) {
		Map<String, String>map=new HashMap<>();
		map.put("userid", userid);
		map.put("cdate", date);
		return dao.getCalendarInfo(userid,date);
	}
}
