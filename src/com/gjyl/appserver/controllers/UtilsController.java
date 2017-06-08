package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value="/utils")
public class UtilsController {

	static String ACCESS_KEY = "jKxoI6ZkySxBy0cmjHFv9Ek1nU-6zHBTqUd0aRmD";
	static String SECRET_KEY = "SWLtLJfsVxk3GqUpEvnJ7V7Ca_miOvmce3Z3Ke1g";
	static String bucketname = "usericon";
	static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	@RequestMapping(value={"/getToken"},method=RequestMethod.GET)
	public void GetToken(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(JSON.toJSONString(auth.uploadToken(bucketname)));
//		return (JSON) JSON.toJSON(auth.uploadToken(bucketname));
	}

	@RequestMapping(value="/getLaunchImg")
	public void getLaunchImg(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(JSON.toJSONString(""));
//		return (JSON) JSON.toJSON("");
	}
}
