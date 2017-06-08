package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.AppUser;
import com.gjyl.appserver.pojo.Cyclopedia;
import com.gjyl.appserver.service.CyclopediaService;
import com.gjyl.appserver.service.UserService;
import com.gjyl.appserver.utils.DateUtils;
import com.gjyl.appserver.utils.FileUploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 图片上传
 * @author LiD
 *
 */
@Controller
@RequestMapping("/upload")
public class FileUpload {

	@Resource
	private CyclopediaService cyclopediaService;
	@Resource
	private UserService userService;

	/**
	 * 图片上传
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ImgUpload",method=RequestMethod.POST)
	public void imgUpload(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
//		String prePath = FileUploadUtils.getPrePath(request);
		//封装数据
		Cyclopedia cyclopedia = new Cyclopedia();
		cyclopedia.setContent(request.getParameter("content"));
		cyclopedia.setTitle(request.getParameter("title"));
		List<String> list = FileUploadUtils.uploadImage(request);
		if (list.size()==2) {
			cyclopedia.setIcon(list.get(0));
			cyclopedia.setCover(list.get(1));
		}
		cyclopedia.setCtime(DateUtils.getNowDateStr());
		cyclopedia.setTypeid(request.getParameter("typeid"));
		if (cyclopedia.getIcon()!=null&&cyclopedia.getCover()!=null) {
			//图片已保存,才存储数据
			Boolean result = cyclopediaService.addCycl(cyclopedia);
//			return (JSON) JSON.toJSON(result);
			response.getWriter().write(JSON.toJSONString(result));
		}else {
//			return (JSON) JSON.toJSON(Boolean.FALSE);
			response.getWriter().write(JSON.toJSONString(Boolean.FALSE));
		}
	}


	/**
	 * 上传头像
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadIcon")
	public void uploadIcon(HttpServletRequest request,HttpServletResponse response) throws Exception {

		String userid = request.getParameter("userid");
		AppUser user = userService.GetUserById(userid);

		List<String> list = FileUploadUtils.uploadImage(request);
		if (list.size()==1) {
			user.setIcon(list.get(0));
		}
		System.out.println(user.toString());
		Boolean result = userService.updateUser(user);
		if (result) {//成功,返回新的用户信息
			response.getWriter().write(JSON.toJSONString(user));
		}else {
			response.getWriter().write(JSON.toJSONString(result));
		}
	}

	/**
	 * 图片上传,返回路径,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadFile")
	public void uploadFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		List<String> list = FileUploadUtils.uploadImage(request);
		if (list!=null&&list.size()==1){
			System.out.println("文件路径: "+list.get(0));
			response.getWriter().write(JSON.toJSONString(list.get(0)));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}
}