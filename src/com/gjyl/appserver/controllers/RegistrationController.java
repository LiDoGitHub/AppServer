package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.AppUser;
import com.gjyl.appserver.pojo.Registration;
import com.gjyl.appserver.service.RegistrationService;
import com.gjyl.appserver.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Resource
	private RegistrationService registrationService;
	@Resource
	private UserService userService;

	/**
	 * 医生端添加挂号信息
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
}
