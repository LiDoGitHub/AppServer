package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.*;
import com.gjyl.appserver.service.ECommentService;
import com.gjyl.appserver.service.EssayAgreeService;
import com.gjyl.appserver.service.EssayService;
import com.gjyl.appserver.utils.FileUploadUtils;
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
@RequestMapping(value="/essay")
public class EssayController {

	@Resource
	private EssayService essayService;
	@Resource
	private EssayAgreeService essayAgreeService;
	@Resource
	private ECommentService ecommentService;

	/**
	 * 成长树文章列表
	 * @param pageNum:页码
	 * @param response
	 */
	@RequestMapping(value="/getAllEssaiesByPage")
	public void getAllEssaies(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String pageNum = request.getParameter("pageNum");
		String userid = request.getParameter("userid");
		if (pageNum!=null&&!pageNum.equals("")&&userid!=null&&!userid.equals("")) {
			List<Essay> list = essayService.getAllEssaiesByPage(pageNum, userid);
			EssayResult result = new EssayResult();
			result.setList(list);
			result.setMaxPage(essayService.getMaxPage());
			response.getWriter().write(JSON.toJSONString(result));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 我的关注列表,分页显示
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMyFocusEssay",method = RequestMethod.GET)
	public  void getMyFocusEssay(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String pageNum = request.getParameter("pageNum");
		String userid = request.getParameter("userid");
		List<Essay> list= essayService.getFocusEssayByPage(pageNum,userid);
		EssayResult result = new EssayResult();
		result.setList(list);
		result.setMaxPage(essayService.getFocusMaxPage(userid));
		response.getWriter().write(JSON.toJSONString(result));
	}

	/**
	 * 成长树文章详情
	 * @param eid:文章ID
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEssayInfo")
	public void getEssayInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String id = request.getParameter("eid");
		Essay essay = essayService.getEssayInfo(id);
		List<EssayAgree> essayAgreeList = essayAgreeService.getEssayAgreeList(essay.getEid());
		List<EssayComment> essayCommentList=ecommentService.getCommentByEid(essay.getEid());
		ECDetailResult result = new ECDetailResult();
		result.setEssay(essay);
		result.setEssayAgreeList(essayAgreeList);
		result.setEssayCommentList(essayCommentList);
		response.getWriter().write(JSON.toJSONString(result));
	}

	/**
	 * 发布成长树文章
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/publishEssay",method = RequestMethod.POST)
	public void publishEssay(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		Essay essay = new Essay();
		Date now = new Date();
		BeanUtils.populate(essay, request.getParameterMap());
		List<String> paths = FileUploadUtils.uploadImage(request);
		if (paths.size()>0) {
			String imgPath="";
			for (String path : paths) {
				System.out.println("图片路径:\n"+path);
				imgPath += path + ";";
			}
			essay.setEimages(imgPath);
		}
		essay.setEpubtime(now);
		Boolean rst=essayService.publishEssay(essay);
		response.getWriter().write(JSON.toJSONString(rst));
	}

	/**
	 * 删除成长树文章
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteEssay",method = RequestMethod.GET)
	public void deleteEssay(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String id= request.getParameter("eid");
		Boolean rst= essayService.deleteEssayById(id);
		response.getWriter().write(JSON.toJSONString(rst));
	}

	/**
	 * 点赞
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/agreeWithEssay",method = RequestMethod.POST)
	public void agreeWithEssay(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		EssayAgree ea = new EssayAgree();
		DateConverter dc=new DateConverter();
		ConvertUtils.register(dc,Date.class);
		dc.setPattern("yyyy-MM-dd HH:mm:ss");
		BeanUtils.populate(ea, request.getParameterMap());
		Boolean rst = essayService.agreeWithEssay(ea);
		response.getWriter().write(JSON.toJSONString(rst));
	}

	/**
	 * 取消点赞
	 * @param userid:用户ID
	 * @param eid:说说ID
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/disAgreeWithEssay",method = RequestMethod.POST)
	public void disAgreeWithEssay(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String userid=request.getParameter("userid");
		String eid=request.getParameter("eid");
		Boolean rst = essayService.disAgreeWithEssay(userid, eid);
		response.getWriter().write(JSON.toJSONString(rst));
	}

	/**
	 * 某一用户发布的说说
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserEssaies")
	public void getUserEssaies(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String userid = request.getParameter("userid");
		String pageNum = request.getParameter("pageNum");
		List<Essay> list=essayService.getUserEssaies(userid,Integer.valueOf(pageNum));
		response.getWriter().write(JSON.toJSONString(list));
	}
}
