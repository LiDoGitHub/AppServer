package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.Section;
import com.gjyl.appserver.service.SectionService;
import com.gjyl.appserver.utils.ExcelUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/section")
public class SectionController {

	@Resource
	private SectionService sectionService;

	/**
	 * 科室下所有疾病
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getSecList")
	public void getSecList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		List<Section> secList = sectionService.getSecList();
//		return (JSON) JSON.toJSON(secList);
		response.getWriter().write(JSON.toJSONString(secList));
	}

	/**
	 * 所有科室,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSections")
	public void getSections(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		List<Section> list=sectionService.getSections();
		response.getWriter().write(JSON.toJSONString(list));
	}

	/**
	 * 科室详情,后台用
	 * @param secid:科室ID
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSectionInfo")
	public void getSectionInfo(HttpServletRequest request,HttpServletResponse response)throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String id= request.getParameter("secid");
		Section section= sectionService.getSectionInfo(id);
		response.getWriter().write(JSON.toJSONString(section));
	}

	/**
	 * 新增科室
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSection")
	public void addSection(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		Section section=new Section();
		BeanUtils.populate(section,request.getParameterMap());
		if ((!section.getName().equals(""))){
			Boolean rst = sectionService.addSection(section);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 删除科室
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/delSection")
	public void delSection(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String secid=request.getParameter("secid");
		Boolean rst=sectionService.delSection(secid);
		response.getWriter().write(JSON.toJSONString(rst));
	}

	/**
	 * 更新科室信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSection")
	public void updateSection(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String secId=request.getParameter("secid");
		Section section= sectionService.getSectionInfo(secId);
		BeanUtils.populate(section,request.getParameterMap());
		Boolean rst = sectionService.updateSection(section);
		response.getWriter().write(JSON.toJSONString(rst));
	}

	/**
	 * 从Excel导入科室
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getDataFromExcel")
	public void getDataFromExcel(HttpServletRequest request,HttpServletResponse response) throws  Exception{
		response.setContentType("text/json;charset=utf-8");
		List<Object> list = ExcelUtil.getDataFromExcel(request, Section.class);
		if (list!=null&&list.size()>0) {
			Boolean rst = sectionService.addSecFromExcel(list);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}
}
