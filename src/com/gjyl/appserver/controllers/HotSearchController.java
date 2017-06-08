package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.HotSearch;
import com.gjyl.appserver.pojo.HotSearchResult;
import com.gjyl.appserver.service.HotSearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/hotSearch")
public class HotSearchController {

	@Resource
	private HotSearchService hotSearchService;
	/**
	 * 热门搜索
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getHotSearch",method=RequestMethod.POST)
	public void getHotSearch(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		List<HotSearch> list= hotSearchService.getHotSearch();
		response.getWriter().write(JSON.toJSONString(list));
	}

	/**
	 * 搜索结果
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/getSearchRst",method=RequestMethod.POST)
	public void getSearchRst(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String content = request.getParameter("content").trim();
		if (content!=null&&!content.equals("")) {
			HotSearchResult rst = hotSearchService.getSearchRst(content);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("null"));
		}
	}


	/**
	 * 联想查询联想列表
	 * @param content:搜索内容
	 * @throws Exception
	 */
	@RequestMapping(value="/getRelativeSearch",method=RequestMethod.POST)
	public void getRelativeSearch(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String content = request.getParameter("content");
		List<String> relative= hotSearchService.getRelativeSearch(content);
		response.getWriter().write(JSON.toJSONString(relative));
	}


}
