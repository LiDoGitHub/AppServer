package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.Doctor;
import com.gjyl.appserver.pojo.Section;
import com.gjyl.appserver.service.DoctorService;
import com.gjyl.appserver.service.MyDoctorService;
import com.gjyl.appserver.service.SectionService;
import com.gjyl.appserver.utils.ExcelUtil;
import com.gjyl.appserver.utils.FileUploadUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Resource
	private DoctorService doctorService;
	@Resource
	private SectionService sectionService;
	@Resource
	private MyDoctorService myDoctorService;

	/**
	 * 主页随机获取三名医生
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getRanDomDr")
	public void getRandomDr(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		List<Doctor> list = doctorService.getRandomDr();
		response.getWriter().write(JSON.toJSONString(list));
//		return (JSON) JSON.toJSON(list);
	}

	/**
	 * 获取医生列表,后台共用
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getDrList")
	public void getDrList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		List<Doctor> list = doctorService.getDrList();
		response.getWriter().write(JSON.toJSONString(list));
	}

	/**
	 * 医生详情
	 * @throws Exception
	 */
	@RequestMapping(value="/getDrInfo")
	public void getDrInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String docId = request.getParameter("docId");
		String userid = request.getParameter("userid");
		if (docId!=null&&!docId.equals("")&&userid!=null&&!userid.equals("")) {
			Doctor doctor = doctorService.getDrInfo(docId);
			if (doctor.getDoctorid()!=null&&!doctor.getDoctorid().equals("")){
				Boolean isCollected = myDoctorService.isCollected(docId, userid);
				doctor.setCollected(isCollected);
			}else {
				doctor.setCollected(false);
			}
			response.getWriter().write(JSON.toJSONString(doctor));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 当日坐诊医生
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getTodayDoctor")
	public void getTodayDoctor(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		List<Doctor> list=doctorService.getTodayDoctor();
		response.getWriter().write(JSON.toJSONString(list));
	}

	/**
	 * 导入Excel表格
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getDataFromExcel",method = RequestMethod.POST)
	public void getDataFromExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		List<Object> list = ExcelUtil.getDataFromExcel(request, Doctor.class);
		if (list!=null&&list.size()>0){
			Boolean rst = doctorService.executeBatch(list);
			response.getWriter().write(JSON.toJSONString(Boolean.TRUE));
		}else {
			response.getWriter().write(JSON.toJSONString(Boolean.FALSE));
		}
	}

	/**
	 * 更新医生信息,,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateDocInfo")
	public void updateDocInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String docid = request.getParameter("docid");
		if (docid!=null&&!docid.equals("")) {
			Doctor doctor = doctorService.getDrInfo(docid);
			BeanUtils.populate(doctor, request.getParameterMap());
			Boolean rst = doctorService.updateDocInfo(doctor);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 医生详情,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getDocInfo")
	public void getDocInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String docId = request.getParameter("docid");
		if (docId!=null&&!docId.equals("")) {
			Doctor doctor = doctorService.getDrInfo(docId);
			List<Section> sections = sectionService.getSecList();
			Map<String,Object> map=new HashMap<>();
			map.put("doctor",doctor);
			map.put("section",sections);
			response.getWriter().write(JSON.toJSONString(map));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 更新医生头像,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateDocIcon")
	public void updateDocIcon(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String docid = request.getParameter("docid");
		List<String> pathList = FileUploadUtils.uploadImage(request);
		if (pathList!=null&&pathList.size()==1){
			Boolean rst= doctorService.updateDocIcon(docid,pathList.get(0));
			if (rst){
				response.getWriter().write(JSON.toJSONString(pathList.get(0)));
			}else {
				response.getWriter().write(JSON.toJSONString("failed"));
			}
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 新增医生,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/addDoctor")
	public void addDoctor(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		Doctor doctor = new Doctor();
		BeanUtils.populate(doctor,request.getParameterMap());
		if (doctor.getName()!=null&&!doctor.getName().equals("")){
			Boolean rst = doctorService.addDoctor(doctor);
			response.getWriter().write(JSON.toJSONString(rst));
		}
	}

	/**
	 * 科室下医生,后台用
	 * @param secName:科室名称
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getDocBySection")
	public void getDocBySection(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String secName = request.getParameter("secName");
		if (secName!=null&&!secName.equals("")) {
			List<Doctor> list = doctorService.getDocBySection(secName);
			response.getWriter().write(JSON.toJSONString(list));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}
}
