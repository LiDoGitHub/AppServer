package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.Banner;
import com.gjyl.appserver.pojo.Cyclopedia;
import com.gjyl.appserver.pojo.Hot;
import com.gjyl.appserver.service.BannerService;
import com.gjyl.appserver.service.CyclopediaService;
import com.gjyl.appserver.service.HotService;
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
@RequestMapping("/banner")
public class BannerController {

	@Resource
	private BannerService bannerService;
	@Resource
	private HotService hotService;
	@Resource
	private CyclopediaService cyclopediaService;

	/**
	 * 获取主页的轮播图
	 * @throws Exception
	 */
	@RequestMapping(value="/getHomeBanners")
	public void getHomeBanners(HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		List<Banner> list = bannerService.getHomeBanners();
//		return (JSON) JSON.toJSON(banners);
		response.getWriter().write(JSON.toJSONString(list));
//		return banners;
	}

	/**
	 * 疾病库轮播图
	 * @throws Exception
	 */
	@RequestMapping(value="/getDisBanners")
	public void getDisBanners(HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		List<Banner> banners = bannerService.getDisBanners();
		response.getWriter().write(JSON.toJSONString(banners));
//		return (JSON) JSON.toJSON(banners);
	}

	/**
	 * 获取所有热点文章
	 * @throws Exception
	 */
	@RequestMapping(value="/getAllHots",method=RequestMethod.GET)
	public void getHotArticles(HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		List<Hot> articles = hotService.getHotArticles();
		response.getWriter().write(JSON.toJSONString(articles));
//		return (JSON) JSON.toJSON(articles);

	}

	/**
	 * 横幅列表,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBannerList")
	public void getBannerList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String pageNum = request.getParameter("pageNum");
		if (pageNum==null||pageNum.equals("")){
			pageNum="0";
		}
		List<Cyclopedia> list = cyclopediaService.getCyclListByPage(Integer.valueOf(pageNum));
		Map<String,Object> map=new HashMap<>();
		Integer total=cyclopediaService.getTotalNum();
		double maxPage = Math.ceil(total / 20.0);
		map.put("list",list);
		map.put("total",maxPage);
		response.getWriter().write(JSON.toJSONString(map));
	}

	/**
	 * 更换/添加Banner,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/editBanner")
	public void editBanner(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String banid = request.getParameter("banid");
		Banner banner=null;
		if (banid!=null&&!banid.equals("")) {
			banner= bannerService.getBanById(banid);
		}
		if (banner==null){
			banner=new Banner();
		}
		BeanUtils.populate(banner,request.getParameterMap());
		Boolean rst;
		if (banner.getCover()!=null){
			if (banid!=null&&!banid.equals("")) {
				rst=bannerService.updateBanner(banner);
			}else {
				rst = bannerService.addBanner(banner);
			}
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * banner详情,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBanner")
	public void getBanner(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String cyclid = request.getParameter("cyclid");
		if (cyclid!=null&&!cyclid.equals("")) {
			Banner banner= bannerService.getBanByCyclId(cyclid);
			response.getWriter().write(JSON.toJSONString(banner));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 批量删除
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/delBanners")
	public void delBanners(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/json;charset=utf-8");
		String banids = request.getParameter("banids");
		if (banids!=null&&!banids.equals("")) {
			String[] ids = banids.split(",");
			if (ids.length > 0) {
				Boolean rst = bannerService.deleteBanners(ids);
				response.getWriter().write(JSON.toJSONString(rst));
			}
		}else {
			response.getWriter().write("error");
		}
	}
}
