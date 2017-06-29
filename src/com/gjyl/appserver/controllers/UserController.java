package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.AppUser;
import com.gjyl.appserver.service.UserService;
import com.gjyl.appserver.utils.MD5Utils;
import com.gjyl.appserver.utils.MsgCodeUtils;
import com.gjyl.appserver.utils.RedisUtil;
import com.gjyl.appserver.utils.SMSUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	/**
	 * 根据用户ID获取用户信息
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getUserById")
	public void getUserById(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//response.setContentType("text/json;charset=utf-8");
		String userId = request.getParameter("userid");
		if (userId!=null&&!userId.equals("")) {
			AppUser user = userService.GetUserById(userId);
			response.getWriter().write(JSON.toJSONString(user));
//		return (JSON) JSON.toJSON(user);
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}
	/**
	 * 根据手机号,获取用户信息
	 * @param phone:手机号
	 * @param password:密码
	 * @return 用户信息
	 * @throws IOException
	 */
	@RequestMapping(value="/Login")
	public void loginWithPwd(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=utf-8");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password").toUpperCase();
		if (phone!=null&&(!phone.equals(""))&&(!password.equals(""))) {
			AppUser user = userService.GetUserByPhone(phone);
			if (user!= null) {
				if (MD5Utils.md5(password).equals(user.getPassword())) {
					response.getWriter().print(JSON.toJSONString(user));
//					return (JSON) JSON.toJSON(user);
//					return user;
//					return JSON.toJSONString(user).replace("\\", "");
				}else {//密码不正确
					response.getWriter().print(JSON.toJSONString("pwdError"));
//					return (JSON) JSON.toJSON("pwdError");
//					return "pwdError";

				}
			}else {//用户名不正确
				//无用户信息
				response.getWriter().print(JSON.toJSONString("phoneError"));
//				return (JSON) JSON.toJSON("phoneError");
//				return "phoneError";
			}
		}else {
			//参数不全
			response.getWriter().print(JSON.toJSONString("error"));
//			return (JSON) JSON.toJSON("error");
//			return "error";
		}
	}


	/**
	 * 获取验证码
	 * @param phone:手机号
	 * @return 是否发送成功
	 * @throws Exception:数据解析异常
	 */
	@RequestMapping(value="/getVerification")
	public void getVerification(HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		//response.setContentType("text/json;charset=utf-8");
		String phone = request.getParameter("phone");
		if (phone != null && (!phone.equals("")) && phone.length() == 11) {
			String msgCode = MsgCodeUtils.RandomCode();
			if ("ok".equals(RedisUtil.set(phone, msgCode).toLowerCase())) {// redis本地缓存
				System.out.println(phone+"的验证码:"+msgCode);
				String msg="您好,您的验证码为:"+msgCode+"。10分钟内有效!【儿医天使】";
				if (SMSUtils.SendMsg(phone,msg)) {// 短信发送成功
					response.getWriter().write(JSON.toJSONString("success"));
//					return (JSON) JSON.toJSON("success");
				} else {// 短信发送失败,清除缓存
					RedisUtil.set(phone, "");
					response.getWriter().write(JSON.toJSONString("failed"));
//					return (JSON) JSON.toJSON("failed");
				}
			} else {// redis本地缓存设置失败
				response.getWriter().write(JSON.toJSONString("error"));
//				return (JSON) JSON.toJSON("error");
			}
		} else {
			response.getWriter().write(JSON.toJSONString("phoneError"));
//			return (JSON) JSON.toJSON("phoneError");
		}
	}

	/**
	 * 一键登录
	 * @param phone:手机号
	 * @param code:短信收到的验证码
	 * @return 登录状态
	 */
	@Deprecated
	@RequestMapping(value="/oneLogin")
	public @ResponseBody Object oneKeyLogin(HttpServletRequest request) {
		String phone = request.getParameter("phone");
		String msgCode=request.getParameter("code");
		if (msgCode!=null&&(!msgCode.equals(""))&&msgCode.equals(RedisUtil.get(phone))) {
			AppUser user = userService.GetUserByPhone(phone);
			if (user==null) {//未注册用户,自动注册
				user=new AppUser();
				user.setPhone(phone);
				user.setPassword(MD5Utils.md5("123456"));//默认密码为123456
				Boolean result = userService.addUser(user);
				if (result) {
					return JSON.toJSON(user);//自动注册成功
				}else {
					return JSON.toJSON("failed");//自动注册失败
				}
			}else {
				user.setPassword("");//清空密码,不返回密码
				return JSON.toJSON(user);
			}
		}else {
			//验证码错误
			return JSON.toJSON("codeError");
		}
	}


	/**
	 * 用户注册
	 * @param request
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@RequestMapping(value="/registUser")
	public void registUser(HttpServletRequest request,HttpServletResponse response) throws  Exception{
		//response.setContentType("text/json;charset=utf-8");
		String phone = request.getParameter("phone");
		String msgCode=request.getParameter("code");
		if (msgCode!=null&&(!msgCode.equals(""))&&msgCode.equals(RedisUtil.get(phone))) {//验证验证码
			AppUser user = new AppUser();
			BeanUtils.populate(user, request.getParameterMap());
			System.out.println("注册用户信息\n" + user.toString());
			if (user.getPhone() != null && (!user.getPhone().equals(""))) {
				Boolean result = userService.addUser(user);
				response.getWriter().write(JSON.toJSONString(result));
//			return (JSON) JSON.toJSON(result);
			} else {
				response.getWriter().write(JSON.toJSONString("phoneError"));
//			return (JSON) JSON.toJSON("phoneError");
			}
		}else {
			response.getWriter().write(JSON.toJSONString("codeError"));
		}
	}


	/**
	 * 重置密码
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/resetPwd")
	public void resetPassword(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//response.setContentType("text/json;charset=utf-8");
		String phone = request.getParameter("phone");
		String msgCode = request.getParameter("code");
		String pwd = request.getParameter("password");
		if (msgCode != null && (!msgCode.equals(""))
				&& msgCode.equals(RedisUtil.get(phone))) {
			AppUser user = userService.GetUserByPhone(phone);
			if (user!=null) {
				user.setPassword(MD5Utils.md5(pwd));
				Boolean result = userService.updateUser(user);
				response.getWriter().write(JSON.toJSONString(result));
//				return (JSON) JSON.toJSON(result);
			}else {
				response.getWriter().write(JSON.toJSONString("nouser"));
//				return (JSON) JSON.toJSON("nouser");
			}
		}else {
			response.getWriter().write(JSON.toJSONString("codeError"));
//			return (JSON) JSON.toJSON("codeError");
		}
	}

	/**
	 * 更新用户信息
	 * @param request
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@RequestMapping(value="/updateUser")
	public void updateUser(HttpServletRequest request,HttpServletResponse response) throws  Exception{
		//response.setContentType("text/json;charset=utf-8");
		AppUser user = userService.GetUserById(request.getParameter("userid"));
		if (user!=null&&user.getPhone()!=null) {
			BeanUtils.populate(user, request.getParameterMap());
			Boolean result = userService.updateUser(user);
			response.getWriter().write(JSON.toJSONString(result));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 友盟登录
	 * @param uid:友盟返回的用户id,对应QQ,微信,微博
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/umLogin")
	public void UMLogin(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//response.setContentType("text/json;charset=utf-8");
		String uid = request.getParameter("uid");
		AppUser user= userService.umLogin(uid);
		if (user!=null) {
			System.out.println("umLogin...........\n"+user.toString());
			response.getWriter().write(JSON.toJSONString(user));
//			return (JSON) JSON.toJSON(user);
		}else {
			response.getWriter().write(JSON.toJSONString(Boolean.FALSE));
//			return (JSON) JSON.toJSON("FALSE");
		}

	}


	/**
	 * 三方登录绑定手机号
	 * @param phone:手机号
	 * @param name:昵称
	 * @param gender:性别
	 * @param icon:头像
	 * @param uid:三方登录唯一标识
	 * @param icon:头像
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@RequestMapping(value="/bindPhone")
	public void bindPhone(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//response.setContentType("text/json;charset=utf-8");
		String phone = request.getParameter("phone");
		String msgCode = request.getParameter("code");
		AppUser isExist = userService.GetUserByPhone(phone);
		if (isExist == null) {
			if (msgCode != null && (!msgCode.equals(""))
					&& msgCode.equals(RedisUtil.get(phone))) {
				AppUser user = new AppUser();
				BeanUtils.populate(user, request.getParameterMap());
				user.setPassword(MD5Utils.md5("123456"));
				System.out.println("绑定手机用户信息:\n" + user.toString());
				Boolean result = userService.addUser(user);
				if (result) {//成功,返回用户信息
					response.getWriter().write(JSON.toJSONString(user));
//					return (JSON) JSON.toJSON(user);
				} else {//绑定失败
					response.getWriter().write(JSON.toJSONString(Boolean.FALSE));
//					return (JSON) JSON.toJSON(Boolean.FALSE);
				}
			} else {//验证码错误
				response.getWriter().write(JSON.toJSONString("codeError"));
//				return (JSON) JSON.toJSON("codeError");
			}
		} else {//手机号已被绑定
			response.getWriter().write(JSON.toJSONString("used"));
//			return (JSON) JSON.toJSON("used");
		}
	}

	/**
	 * 所有用户列表,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserList")
	public void getUserList(HttpServletRequest request,HttpServletResponse response) throws  Exception{
		//response.setContentType("text/json;charset=utf-8");
		List<AppUser> list= userService.getAllUsers();
		response.getWriter().write(JSON.toJSONString(list));
	}


	/**
	 * 设置是否为管理员
	 * @param userid:用户ID
	 * @param role:用户角色
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/setManager")
	public void setManager(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//response.setContentType("text/json;charset=utf-8");
		String userid = request.getParameter("userid");
		if (userid!=null&&!userid.equals("")) {
			AppUser user = userService.GetUserById(userid);
			BeanUtils.populate(user, request.getParameterMap());
			Boolean rst = userService.updateUser(user);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 新增用户,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/addUser")
	public void addUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//response.setContentType("text/json;charset=utf-8");
		AppUser user=new AppUser();
		BeanUtils.populate(user,request.getParameterMap());
		if (user.getPhone() != null && !user.getPhone().equals("")) {
			Boolean rst = userService.addUser(user);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("error"));
		}
	}

	/**
	 * 删除用户,后台用
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/delUser")
	public void delUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//response.setContentType("text/json;charset=utf-8");
		String userid = request.getParameter("userid");
		if (userid!=null&&!userid.equals("")){
			Boolean rst= userService.deleteUser(userid);
			response.getWriter().write(JSON.toJSONString(rst));
		}else {
			response.getWriter().write(JSON.toJSONString("Error"));
		}
	}

}
