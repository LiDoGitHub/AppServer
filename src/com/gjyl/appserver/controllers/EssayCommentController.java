package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.EssayComment;
import com.gjyl.appserver.service.ECommentService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/ecomment")
public class EssayCommentController {

	@Resource
	private ECommentService ecommentService;

	/**
	 * 评论列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/getEComments")
	public void getEComments(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String eid = request.getParameter("eid");
		List<EssayComment> list = ecommentService.getCommentByEid(eid);
		response.getWriter().write(JSON.toJSONString(list));
	}

	/**
	 * 新增评论,回复评论
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/addEComment")
	public void addEComment(HttpServletRequest request,HttpServletResponse response) throws Exception{
		EssayComment comment = new EssayComment();
		DateConverter dc=new DateConverter();
		dc.setPattern("yyyy-MM-dd HH:mm:ss");
		ConvertUtils.register(dc, Date.class);
		BeanUtils.populate(comment,request.getParameterMap());
		if (!comment.getEctime().equals("")&&comment.getEctime()!=null) {
			Boolean rst = ecommentService.addEComment(comment);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write("error");
		}
	}
}
