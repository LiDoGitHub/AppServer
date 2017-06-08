package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.CyclType;
import com.gjyl.appserver.service.CyclTypeService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/cyclType")
public class CyclTypeController {

	@Resource
	private CyclTypeService cyclTypeService;

	/**
	 * 获取分类
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getAllTypes",method=RequestMethod.GET)
	public void getTypes(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		List<CyclType> types = cyclTypeService.getAllTypes();
		response.getWriter().write(JSON.toJSONString(types));
	}

	/**
	 * 新增文章分类,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/addCyclType",method = RequestMethod.POST)
	public void addCyclType(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		CyclType type=new CyclType();
		BeanUtils.populate(type,request.getParameterMap());
		if (type.getTypename()!=null&&(!type.getTypename().equals(""))){
			Boolean rst = cyclTypeService.addCyclType(type);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 编辑文章分类,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/editCyclType",method = RequestMethod.POST)
	public void editCyclType(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String id=request.getParameter("typeid");
		CyclType type= cyclTypeService.getTypeById(id);
		BeanUtils.populate(type,request.getParameterMap());
		Boolean rst = cyclTypeService.updateCyclType(type);
		response.getWriter().write(JSON.toJSONString(rst));
	}

	/**
	 * 删除分类,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleCyclType",method = RequestMethod.POST)
	public void deleCyclType(HttpServletRequest request,HttpServletResponse response) throws  Exception{
		response.setContentType("text/json;charset=utf-8");
		String id=request.getParameter("typeid");
		Boolean rst=cyclTypeService.deleCyclTypeById(id);
		response.getWriter().write(JSON.toJSONString(rst));
	}

	/**
	 * 文章分类详情
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getTypeInfo",method = RequestMethod.GET)
	public  void getTypeInfo(HttpServletRequest request,HttpServletResponse response) throws  Exception{
		response.setContentType("text/json;charset=utf-8");
		String id=request.getParameter("typeid");
		CyclType type= cyclTypeService.getTypeById(id);
		response.getWriter().write(JSON.toJSONString(type));
	}
}
