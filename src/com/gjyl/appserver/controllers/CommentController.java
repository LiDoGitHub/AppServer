package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.Comment;
import com.gjyl.appserver.pojo.DocComment;
import com.gjyl.appserver.service.CommentService;
import com.gjyl.appserver.service.DocCommentService;
import com.gjyl.appserver.service.RegistrationService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Resource
	private CommentService commentService;
	@Resource
	private DocCommentService docComService;
	@Resource
	private RegistrationService registrationService;
	/**
	 * 获取用户对医生的评价
	 * @throws Exception:异常
	 */
	@RequestMapping(value="/getUserComment")
	public void getUserComment(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//response.setContentType("text/json;charset=utf-8");
		String docId = request.getParameter("docId");
		if (docId!=null&&!docId.equals("")) {
			List<Comment> list = commentService.getUserComment(docId);
			response.getWriter().write(JSON.toJSONString(list));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
//		return (JSON) JSON.toJSON(list);
	}

	/**
	 * 用户对医生评价
	 * @throws Exception
	 */
	@RequestMapping(value="/addComment")
	public void addComment(HttpServletRequest request,HttpServletResponse response) throws  Exception {
		//response.setContentType("text/json;charset=utf-8");
		Comment comment = new Comment();
		String regid = request.getParameter("regid");
		BeanUtils.populate(comment, request.getParameterMap());
		if (comment.getContent() != null && (!comment.getContent().equals(""))) {
			Boolean result = commentService.addComment(comment);
			if (result&&regid!=null&&!regid.equals("")){
				registrationService.updateRegComStatus(regid);
			}
			response.getWriter().write(JSON.toJSONString(result));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 根据healthId获取医生对健康状况的评价
	 * @throws Exception
	 */
	@RequestMapping(value="/getHealthComment")
	public void getHealthComment(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//response.setContentType("text/json;charset=utf-8");
		String healthId = request.getParameter("healthId");
		if (healthId!=null&&!healthId.equals("")) {
			List<DocComment> list = docComService.getHealthComment(healthId);
			response.getWriter().write(JSON.toJSONString(list));
//		return (JSON) JSON.toJSON(list);
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 医生对健康状况进行评价
	 * @param doctorid:医生ID
	 * @param healthid:健康ID
	 * @param content:评价内容
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@RequestMapping(value="/addDocComment")
	public void addDocComment(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//response.setContentType("text/json;charset=utf-8");
		DocComment docComment = new DocComment();
		BeanUtils.populate(docComment, request.getParameterMap());
		if (docComment.getDoctorid()!=null&&!docComment.getDoctorid().equals("")&&docComment.getHealthid()!=null&&!docComment.getHealthid().equals("")) {
			Boolean result = docComService.addDocComment(docComment);
			response.getWriter().write(JSON.toJSONString(result));
//		return (JSON) JSON.toJSON(result);
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}
}
