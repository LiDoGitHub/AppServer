package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.AppUser;
import com.gjyl.appserver.pojo.Doctor;
import com.gjyl.appserver.pojo.Registration;
import com.gjyl.appserver.pojo.Section;
import com.gjyl.appserver.service.DoctorService;
import com.gjyl.appserver.service.RegistrationService;
import com.gjyl.appserver.service.SectionService;
import com.gjyl.appserver.service.UserService;
import com.gjyl.appserver.utils.DateUtils;
import com.gjyl.appserver.utils.SMSUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Resource
	private RegistrationService registrationService;
	@Resource
	private DoctorService doctorService;
	@Resource
	private UserService userService;
	@Resource
	private SectionService sectionService;

	/**
	 * 医生端添加挂号信息,功能变更,已弃用
	 * @param city:城市
	 * @param section:科室
	 * @param reservationdate:挂号时间
	 * @param name:姓名
	 * @param gender:性别
	 * @param age:年龄
	 * @param phone:手机号
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@Deprecated
	@RequestMapping(value="/addRegUser")
	public void addRegUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		Registration registration = new Registration();
		//订单号 start
		String orderCode = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		registration.setOrdercode(orderCode);
		//订单号 end
		BeanUtils.populate(registration, request.getParameterMap());
		if (registration.getPhone() != null && !registration.getPhone().equals("")) {
			AppUser user = userService.GetUserByPhone(registration.getPhone());
			if (user.getUserid() != null && !user.getUserid().equals("")) {// 用户已存在,绑定用户ID
				registration.setUserid(user.getUserid());
			} else {// 不存在用户
				AppUser appUser = new AppUser();
				BeanUtils.populate(appUser, request.getParameterMap());
				if (appUser.getPhone() != null && !appUser.getPhone().equals("")) {// 新增用户
					Boolean result = userService.addUser(appUser);
					if (result) {// 新增用户成功,绑定用户ID
						registration.setUserid(appUser.getUserid());
					}
				}
			}
		}
		Boolean result = registrationService.addRegUser(registration);
		response.getWriter().write(JSON.toJSONString(result));
//		return (JSON) JSON.toJSON(result);
	}

	/**
	 * 新增挂号
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/addRegistration")
	public void addRegistration(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		Registration registration = new Registration();
		DateConverter dc=new DateConverter();
		ConvertUtils.register(dc,Date.class);
		dc.setPattern("yyyy-MM-dd HH:mm:ss");
		BeanUtils.populate(registration,request.getParameterMap());
		if (registration.getName()!=null&&!registration.getName().equals("")){
			//获取医生所属科室
			Doctor drInfo = doctorService.getDrInfo(registration.getDoctorid());
			registration.setDocname(drInfo.getName());
			//设置科室信息
			Section section = sectionService.getSectionByName(drInfo.getSection());
			registration.setSectionid(section.getSectionid());
			registration.setSection(section.getName());
			//新增挂号
			Boolean rst = registrationService.addRegUser(registration);
			if (rst){
				//发送短信
				StringBuilder msg = new StringBuilder();
				msg.append("【儿医天使】您已成功提交了" + drInfo.getSection() + ",")
						.append(drInfo.getName() + "医生，")
						.append(DateUtils.getDateStr(registration.getReservationdate()))
						.append("的预约挂号，稍后会有我们的工作人员给您致电确认信息。谢谢，联系电话028-85056688");
				Boolean isSend = SMSUtils.SendMsg(registration.getPhone(), msg.toString());
				if (isSend){
					response.getWriter().write(JSON.toJSONString("success"));//挂号成功,短信发送成功
				}else {
					response.getWriter().write(JSON.toJSONString("msgError"));//挂号成功,短信发送失败
				}
			}else {//挂号失败
				response.getWriter().write(JSON.toJSONString(rst));
			}
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 获取挂号列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getRegistartions",method = RequestMethod.GET)
	public void getRegistartions(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		String pageNum = request.getParameter("pageNum");
		if (pageNum!=null&&!pageNum.equals("")) {
			List<Registration> list= registrationService.getRegByPage(Integer.valueOf(pageNum));
			Integer total= registrationService.getTotal();
			Map<String,Object> map=new HashMap<>();
			map.put("list",list);
			map.put("total",total);
			response.getWriter().write(JSON.toJSONString(map));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}


	/**
	 * 挂号详情
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getRegDetail",method = RequestMethod.GET)
	public void getRegDetail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		String regid = request.getParameter("regid");
		Registration registration = registrationService.getRegById(regid);
		List<Section> secList = sectionService.getSecList();
		Map<String,Object>map=new HashMap<>();
		map.put("registration",registration);
		map.put("secList",secList);
		response.getWriter().write(JSON.toJSONString(map));
	}

	/**
	 * 我的预约
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMyRegistration")
	public void getMyRegistration(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String userid = request.getParameter("userid");
		if (userid!=null&&!userid.equals("")) {
			List<Registration> list = registrationService.getMyRegistration(userid);
			response.getWriter().write(JSON.toJSONString(list));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 修改挂号信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/editRegistration",method = RequestMethod.POST)
	public void editRegistration(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		String regid = request.getParameter("regid");
		Registration registration= registrationService.getRegById(regid);
		if (registration.getName()!=null&&!registration.getName().equals("")){
			Date oldDate=registration.getReservationdate();
			String oldSecName=registration.getDoctor().getSection();
			String oldDocName=registration.getDoctor().getName();
			DateConverter dc=new DateConverter();
			ConvertUtils.register(dc,Date.class);
			dc.setPattern("yyyy-MM-dd HH:mm:ss");
			BeanUtils.populate(registration,request.getParameterMap());
			Boolean rst = registrationService.updateRegistration(registration);
			if (rst){//更新成功,根据状态发送短信
				Boolean isSend;
				if (registration.getStatus()!=null&&registration.getStatus().equals("0")){//修改挂号信息
					StringBuilder sb=new StringBuilder();
					sb.append("儿医天使】您提交的预约")
							.append(oldSecName + "," + oldDocName + ",")
							.append(DateUtils.getDateStr(oldDate) + "的预约挂号,已修改为")
							.append(registration.getDoctor().getSection() + ","
									+ registration.getDoctor().getName() + ","
									+ DateUtils.getDateStr(registration.getReservationdate())+ ",")
							.append("请按时前往就诊，就诊地址:成都天使儿童医院，成都市武候区人民南路四段46号（桐梓林地铁B出口旁）。" +
									"如有疑问请联系电话028-85056688，谢谢！");
					isSend = SMSUtils.SendMsg(registration.getPhone(), sb.toString());
				}else if (registration.getStatus()!=null&&registration.getStatus().equals("1")){//已确认
					StringBuilder sb=new StringBuilder();
					sb.append("儿医天使】您已成功预约")
							.append(registration.getDoctor().getSection() + ","
									+ registration.getDoctor().getName() + ","
									+ DateUtils.getDateStr(registration.getReservationdate()) + ",")
							.append("请按时前往就诊，就诊地址:成都天使儿童医院，成都市武候区人民南路四段46号（桐梓林地铁B出口旁）。" +
									"如有疑问请联系电话028-85056688，谢谢！");
					isSend = SMSUtils.SendMsg(registration.getPhone(), sb.toString());
				}else if(registration.getStatus()!=null&&registration.getStatus().equals("2")){//取消预约
					StringBuilder sb=new StringBuilder();
					sb.append("【儿医天使】您提交的预约")
							.append(registration.getDoctor().getSection() + ","
									+ registration.getDoctor().getName() + ","
									+ DateUtils.getDateStr(registration.getReservationdate()))
							.append("的预约挂号已取消。如有疑问请联系电话028-85056688，谢谢！");
					isSend = SMSUtils.SendMsg(registration.getPhone(), sb.toString());
				}else {
					response.getWriter().write(JSON.toJSONString("statusError"));
					return;
				}
				if (isSend){//短信发送成功
					response.getWriter().write(JSON.toJSONString("success"));
				}else {//短信发送失败
					response.getWriter().write(JSON.toJSONString("msgError"));
				}
			}else {//更新失败
				response.getWriter().write(JSON.toJSONString("false"));
			}
		}else {//参数不齐
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

}
