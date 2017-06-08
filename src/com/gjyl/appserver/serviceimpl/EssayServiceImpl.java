package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.*;
import com.gjyl.appserver.pojo.AppUser;
import com.gjyl.appserver.pojo.Essay;
import com.gjyl.appserver.pojo.EssayAgree;
import com.gjyl.appserver.service.EssayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("essayService")
public class EssayServiceImpl implements EssayService {

	@Resource
	private EssayMapper dao;
	@Resource
	private EssayAgreeMapper eaDao;
	@Resource
	private MyFocusesMapper focDao;
	@Resource
	private AppUserMapper userDao;

	private static Integer pageSize=20;

	public Boolean publishEssay(Essay essay) {

		int rst=dao.publishEssay(essay);

		if (rst>0){
			return true;
		}
		return false;
	}

	public Boolean deleteEssayById(String id) {

		int rst=dao.deleteByPrimaryKey(id);
		if (rst>0){
			return  true;
		}
		return false;
	}

	//分页显示所有说说
	public List<Essay> getAllEssaiesByPage(String pageNum,String userid) {

		List<Essay> list = dao.getAllEssaiesByPage(Integer.valueOf(pageNum)*pageSize);
		for (Essay essay:list) {
			Map<String,String> map=new HashMap<>();
			map.put("pubUserId",essay.getUserid());
			map.put("userid",userid);
			//是否关注
			int rst= focDao.isExist(map);
			if (rst>0){
				essay.setIsfocus(true);
			}else {
				essay.setIsfocus(false);
			}
			//是否点赞
			map.clear();
			map.put("eid",essay.getEid());
			map.put("userid",userid);
			int result = eaDao.isExist(map);
			if (result>0){
				essay.setIsagree(true);
			}else {
				essay.setIsagree(false);
			}
			//发布说说的用户信息
			AppUser user = userDao.getUserById(essay.getUserid());
			if (user!=null&&user.getName()!=null&&(!user.getName().equals(""))) {
				essay.setUser(user);
			}
		}
		return list;
	}

	public Essay getEssayInfo(String id) {
		//浏览一次,增加一次访问量
		dao.updateETImes(id);
		return  dao.getEssayInfo(id);
	}

	//新增点赞用户
	public Boolean agreeWithEssay(EssayAgree ea) {
		//Essay的点赞人数的增加由触发器完成
		int rst=eaDao.insertSelective(ea);
		if (rst>0){
			return  true;
		}
		return false;
	}

	//取消点赞
	public Boolean disAgreeWithEssay(String userid, String eid) {
		Map<String,String> map=new HashMap<>();
		map.put("userid",userid);
		map.put("eid",eid);
		int rst=eaDao.disAgreeWithEssay(map);
		if (rst>0){
			return  true;
		}
		return false;
	}

	//我的关注
	public List<Essay> getFocusEssayByPage(String pageNum, String userid) {
		List<String> focUsers=focDao.getMyFocus(userid);//关注的用户列表
		if (focUsers.size()>0) {
			Map<String, Object> map = new HashMap<>();
			map.put("pageNum", Integer.valueOf(pageNum)*pageSize);
			map.put("list", focUsers);
			List<Essay> list = dao.getFocusEssayByPage(map);
			for (Essay essay : list) {
				//是否点赞
				Map<String,String> map1=new HashMap<>();
				map1.put("eid",essay.getEid());
				map1.put("userid",userid);
				int result = eaDao.isExist(map1);
				if (result>0){
					essay.setIsagree(true);
				}else {
					essay.setIsagree(false);
				}
				//发布说说的用户信息
				AppUser user = userDao.getUserById(essay.getUserid());
				if (user.getName() != null && (!user.getName().equals(""))) {
					essay.setUser(user);
				}
			}
			return list;
		}else {
			return null;
		}
	}

	@Override
	public Integer getMaxPage() {
		int total= dao.getTotalNum();
		return (int) Math.ceil(total / pageSize);
	}

	@Override
	public Integer getFocusMaxPage(String userid) {
		List<String> focUsers=focDao.getMyFocus(userid);//关注的用户列表
		if (focUsers.size()>0) {
			int total = dao.getFocusTotalNum(focUsers);
			return (int)Math.ceil(total/pageSize);
		}
		return 0;
	}

	public List<Essay> getUserEssaies(String userid,Integer pageNum) {
		Map<String,Object> map=new HashMap<>();
		map.put("userid",userid);
		map.put("pageNum",pageNum*pageSize);
		List<Essay> list = dao.getUserEssaies(map);
		if (list!=null&&list.size()>0){
			for (Essay es : list) {
				AppUser user = userDao.getUserById(es.getUserid());
				es.setUser(user);
			}
		}
		return list;
	}


}
