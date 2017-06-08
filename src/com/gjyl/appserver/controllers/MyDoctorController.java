package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.MyDoctor;
import com.gjyl.appserver.service.MyDoctorService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 我的医生
 * @author LiD
 *
 */
@Controller
@RequestMapping("/myDoctor")
public class MyDoctorController {

	@Resource
	private MyDoctorService myDrService;

	/**
	 * 我的医生
	 * @param userId:用户ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getMyDoctor")
	public void getMyDoctor(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String userId = request.getParameter("userid");
		List<MyDoctor> list = myDrService.getMyDoctor(userId);
		response.getWriter().write(JSON.toJSONString(list));
//		return (JSON) JSON.toJSON(list);
	}
	/**
	 * 添加我的医生
	 * @param userid:用户ID
	 * @param doctorid:医生ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addMyDoctor")
	public void addMyDoctor(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		MyDoctor myDoctor = new MyDoctor();
		BeanUtils.populate(myDoctor,request.getParameterMap());
		if (myDoctor.getUserid()!=null&&!myDoctor.getUserid().equals("")) {
			Boolean result = myDrService.addMyDoctor(myDoctor);
			response.getWriter().write(JSON.toJSONString(result));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
//		return (JSON) JSON.toJSON(result);
	}

	/**
	 * 删除我的医生,取消收藏
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/delMyDoctor")
	public void delMyDoctor(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String docid = request.getParameter("docid");
		String userid = request.getParameter("userid");
		if (docid!=null&&userid!=null){
			Boolean rst=myDrService.delMyDoctor(docid,userid);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}
}
