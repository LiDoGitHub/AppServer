package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.Remind;
import com.gjyl.appserver.service.RemindService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/remind")
public class RemindController {

	@Resource
	private RemindService remindService;

	/**
	 * 获取我的用药提醒
	 * @param userid:用户ID
	 * @param startDate:开始时间
	 * @throws Exception 回写异常
	 */
	@RequestMapping(value="/getRemind",method=RequestMethod.POST)
	public void getRemind(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String userid = request.getParameter("userid");
		String startDate= request.getParameter("startDate");
		List<Remind> list= remindService.getRemind(userid,startDate);
		response.getWriter().write(JSON.toJSONString(list));
	}

	/**
	 * 获取详情
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getRemindById",method=RequestMethod.POST)
	public void getRemindById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String id = request.getParameter("remindid");
		Remind remind= remindService.getRemindById(id);
		response.getWriter().write(JSON.toJSONString(remind));
	}

	/**
	 * 添加用药提醒
	 * @param request:一个提醒实体对象
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/addRemind", method = RequestMethod.POST)
	public void addRemind(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		Remind remind = new Remind();
		// 注册处理日期的转换器
		DateConverter dc=new DateConverter();
		dc.setPattern("yyyy-MM-dd HH:mm:ss");
		ConvertUtils.register(dc, Date.class);
		BeanUtils.populate(remind, request.getParameterMap());
		System.out.println(remind);
		if ((!remind.getContent1().equals(""))) {
			Boolean rst = remindService.addRemind(remind);
			response.getWriter().write(JSON.toJSONString(rst));
		} else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 编辑提醒
	 * @param request:一个提醒实体对象
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/editRemind",method=RequestMethod.POST)
	public void editRemind(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String remindId = request.getParameter("remindid");
		Remind remind = remindService.getRemindById(remindId);
		// 注册处理日期的转换器
		DateConverter dc=new DateConverter();
		dc.setPattern("yyyy-MM-dd HH:mm:ss");
		ConvertUtils.register(dc, Date.class);
		BeanUtils.populate(remind, request.getParameterMap());
		Boolean rst = remindService.updateRemind(remind);
		response.getWriter().write(JSON.toJSONString(rst));
	}

	/**
	 * 删除提醒
	 * @param remindid:提醒ID
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteRemind",method = RequestMethod.POST)
	public void deleteRemind(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("remindid");
		Boolean rst=remindService.deleteRemind(id);
		response.getWriter().write(JSON.toJSONString(rst));
	}
}
