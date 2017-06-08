package com.gjyl.appserver.serviceimpl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gjyl.appserver.dao.RemindMapper;
import com.gjyl.appserver.pojo.Remind;
import com.gjyl.appserver.service.RemindService;
import com.gjyl.appserver.utils.DateUtils;

@Service("remindService")
public class RemindServiceImpl implements RemindService {

	@Resource
	private RemindMapper dao;

	public List<Remind> getRemind(String userid, String startDate) {
		Map<String, String>map=new HashMap<String, String>();
		map.put("userid", userid);
		map.put("startDate", startDate);
		return dao.getRemind(map);
	}

	@Transactional
	public Boolean addRemind(Remind remind) {
		Map<String, Object> map=new HashMap<String, Object>();
		int num=0;
		try {
			num = DateUtils.getMinusDate(remind.getReminddate(),remind.getEnddate());
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		int rst=0;
		System.out.println("需新增总量......................"+num);
		for (int i = 0; i < num; i++) {
			map.put("reminddate", DateUtils.getNextDate(remind.getReminddate()));
			map.put("userid", remind.getUserid());
			//插入前,判断是否已存在
			int exists= dao.isExist(map);
			if (exists>0) {
				return false;
			}
			remind.setRemindid(UUID.randomUUID().toString().replace("-", ""));
			remind.setReminddate(DateUtils.getNextDate(remind.getReminddate()));
			 int result = dao.addRemind(remind);
			 if (result>0) {
				rst++;
			}
		}
		System.out.println("已插入总量......................"+rst);
		if (rst!=num) {
			return false;
		}else {
			return true;
		}
	}

	public Boolean updateRemind(Remind remind) {
		int rst=dao.updateRemind(remind);
		if (rst>0) {
			return true;
		}
		return false;
	}

	public Remind getRemindById(String id) {
		return dao.getRemindById(id);
	}

	public Boolean deleteRemind(String id) {
		int rst=dao.deleteRemind(id);
		if (rst>0) {
			return true;
		}
		return false;
	}
}
