package com.gjyl.appserver.service;

import java.util.List;

import com.gjyl.appserver.pojo.Calendar;

public interface CalendarCService {

	List<Calendar> getCalendarList(String userid, String date);

	Calendar getCalenDarInfo(String userid, String date);

}
