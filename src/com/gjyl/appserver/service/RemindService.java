package com.gjyl.appserver.service;

import java.util.List;

import com.gjyl.appserver.pojo.Remind;

public interface RemindService {

	List<Remind> getRemind(String userid, String startDate);

	Boolean addRemind(Remind remind);

	Boolean updateRemind(Remind remind);

	Remind getRemindById(String id);

	Boolean deleteRemind(String id);

}
