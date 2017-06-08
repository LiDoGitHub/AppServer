package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.Lecture;
import com.gjyl.appserver.service.LectureService;
import com.gjyl.appserver.utils.FileUploadUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/lecture")
public class LectureController {

	@Resource
	private LectureService lectureService;
	/**
	 * 获取所有视频列表,后台共用
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getAllLectures")
	public void getAllLectures(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		List<Lecture> list= lectureService.getAllLectures();
//		return (JSON) JSON.toJSON(list);
		response.getWriter().write(JSON.toJSONString(list));
	}

	/**
	 * 上传新视频,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/addLecture")
	public void addLecture(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		List<String> path = FileUploadUtils.uploadImage(request);
		if (path.size()>0) {
			Lecture lecture = new Lecture();
			lecture.setVideo(path.get(0));
			BeanUtils.populate(lecture, request.getParameterMap());
			if (lecture.getVideo() != null && !lecture.getVideo().equals("")) {
				Boolean rst = lectureService.addLecture(lecture);
				response.getWriter().write(JSON.toJSONString(rst));
			} else {
				response.getWriter().write(JSON.toJSONString("error"));
			}
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 视频详情,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/lecInfo")
	public void lecInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String lecid = request.getParameter("lecid");
		if (lecid!=null&&!lecid.equals("")){
			Lecture lecture= lectureService.getLectureById(lecid);
			response.getWriter().write(JSON.toJSONString(lecture));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 更新视频信息,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateLecture")
	public void updateLecture(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String lecid = request.getParameter("lecid");
		if (lecid!=null&&!lecid.equals("")) {
			Lecture lecture = lectureService.getLectureById(lecid);
			BeanUtils.populate(lecture,request.getParameterMap());
			List<String> path = FileUploadUtils.uploadImage(request);
			if (path.size()>0){
				lecture.setVideo(path.get(0));
			}
			 Boolean rst=lectureService.updateLecture(lecture);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 删除视频信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteLecture")
	public void deleteLecture(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String lecid = request.getParameter("lecid");
		if (lecid!=null&&!lecid.equals("")){
			Boolean rst = lectureService.deleteLecById(lecid);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("eooro"));
		}
	}
}
