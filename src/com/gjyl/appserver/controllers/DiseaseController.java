package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.DiseaseLibraryWithBLOBs;
import com.gjyl.appserver.service.DiseaseService;
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
@RequestMapping("/disease")
public class DiseaseController {

	@Resource
	private DiseaseService diseaseService;

	/**
	 * 后台用,获取所有疾病
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getAllDiseases")
	public void getAllDisease(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String pageNum = request.getParameter("pageNum");
		if (pageNum!=null&&!pageNum.equals("")) {
			List<DiseaseLibraryWithBLOBs> list = diseaseService.getAllDiseases(pageNum);
			Integer total=diseaseService.getTotalNum();
			Map<String,Object> map=new HashMap<>();
			map.put("list",list);
			map.put("totalNum",total);
			response.getWriter().write(JSON.toJSONString(map));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}
	/**
	 * 常见疾病
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getCommonDis",method=RequestMethod.GET)
	public void getCommonDis(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		List<DiseaseLibraryWithBLOBs> list= diseaseService.getCommonDis();
		response.getWriter().write(JSON.toJSONString(list));
	}

	/**
	 * 设置疾病是否常见,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 * @throws
	 */
	@RequestMapping(value="/setIsCommon")
	public void setIsCommon(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String disid = request.getParameter("disid");
		if (disid != null && !disid.equals("")) {
			DiseaseLibraryWithBLOBs disease = diseaseService.getDiseaseById(disid);
			BeanUtils.populate(disease, request.getParameterMap());
			if (disease.getDisid() != null && disease.getIscommon() != null) {
				Boolean result = diseaseService.updateDisease(disease);
				response.getWriter().write(JSON.toJSONString(result));
			} else {
				response.getWriter().write(JSON.toJSONString("error"));
			}
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 科室下疾病列表
	 * @param sectionId:科室ID
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	@RequestMapping(value="/getDiseaseList")
	public void getDiseaseList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String sectionId = request.getParameter("sectionId");
		List<DiseaseLibraryWithBLOBs> list = diseaseService.getDiseaseList(sectionId);
		response.getWriter().write(JSON.toJSONString(list));
//		return (JSON) JSON.toJSON(list);
	}

	/**
	 * 疾病详情,后台共用
	 * @param diseaseId:疾病ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getDiseaseById")
	public void getDiseaseById(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String disId = request.getParameter("diseaseId");
		DiseaseLibraryWithBLOBs disease = diseaseService.getDiseaseById(disId);
		response.getWriter().write(JSON.toJSONString(disease));
//		return (JSON) JSON.toJSON(disease);
	}

	/**
	 * 新增疾病,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/addDisease")
	public void addDisease(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		DiseaseLibraryWithBLOBs dlwb = new DiseaseLibraryWithBLOBs();
		BeanUtils.populate(dlwb,request.getParameterMap());
		if (dlwb.getName()!=null&&!dlwb.getName().equals("")){
			Boolean rst = diseaseService.addDisease(dlwb);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 删除疾病,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/delDisease")
	public void delDisease(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String disid = request.getParameter("disid");
		if (disid!=null&&!disid.equals("")){
			Boolean rst = diseaseService.delDiseaseById(disid);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 编辑疾病,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/editDisease")
	public void editDisease(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String disid = request.getParameter("disid");
		if (disid!=null&&!disid.equals("")) {
			DiseaseLibraryWithBLOBs disease = diseaseService.getDiseaseById(disid);
			BeanUtils.populate(disease, request.getParameterMap());
			Boolean rst = diseaseService.updateDisease(disease);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}
}
