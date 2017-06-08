package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.UserVoucher;
import com.gjyl.appserver.service.VoucherService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/voucher")
public class VoucherController {

	@Resource
	private VoucherService voucherService;

	/**
	 * 我的代金券
	 * @param userId:用户ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getMyVoucher")
	public void getMyVoucher(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String userId = request.getParameter("userId");
		List<UserVoucher> list = voucherService.getMyVoucher(userId);
		response.getWriter().write(JSON.toJSONString(list));
//		return (JSON) JSON.toJSON(list);
	}

	/**
	 * 分享赠送代金券
	 * @throws Exception
	 */
	@RequestMapping(value="/getShareVoucher")
	public void getShareVoucher(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		UserVoucher userVoucher = new UserVoucher();
		BeanUtils.populate(userVoucher, request.getParameterMap());
		System.out.println("UserVoucher.............\n" + userVoucher);
		Boolean result = voucherService.getShareVoucher(userVoucher);
//		 return (JSON) JSON.toJSON(result);
		response.getWriter().write(JSON.toJSONString(result));
	}
	/**
	 * 使用代金券
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/useVoucher")
	public void useVoucher(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		String[] vouIds = request.getParameter("vouIds").toString().split(",");
		List<String> vouList=new ArrayList<String>();
		if (vouIds.length>0) {
			for (int i = 0; i < vouIds.length; i++) {
				vouList.add(vouIds[i]);
			}
		}
		Boolean result = voucherService.updateVoucher(vouList);
//		return (JSON) JSON.toJSON(result);
		response.getWriter().write(JSON.toJSONString(result));
	}

}
