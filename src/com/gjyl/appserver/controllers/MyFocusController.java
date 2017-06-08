package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.AppUser;
import com.gjyl.appserver.pojo.MyFocuses;
import com.gjyl.appserver.service.MyFocusService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by LiD on 2017/4/19.
 */
@Controller
@RequestMapping("focus")
public class MyFocusController {

    @Resource
    private MyFocusService myfocueService;

    /**
     * 我关注的用户
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getMyFocus")
    public void getMyFocus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json;charset=utf-8");
        String userid = request.getParameter("userid");
        if (userid!=null&&!userid.equals("")) {
            List<AppUser> list = myfocueService.getMyFocuses(userid);
            response.getWriter().write(JSON.toJSONString(list));
        }else {
            response.getWriter().write(JSON.toJSONString("error"));
        }
    }

    /**
     * 添加关注用户
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/addMyFocus")
    public void addMyFocus(HttpServletRequest request,HttpServletResponse response) throws Exception{
        MyFocuses myFocus = new MyFocuses();
        BeanUtils.populate(myFocus,request.getParameterMap());
        Boolean rst = myfocueService.addMyFocus(myFocus);
        response.getWriter().write(JSON.toJSONString(rst));
    }

    /**
     * 取消关注
     * @param focUserId:被关注人ID
     * @param userid:用户ID
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/delMyFocus")
    public void delMyFocus(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String focUserId = request.getParameter("focuserid");
        String userid = request.getParameter("userid");
        Boolean rst=myfocueService.delMyFocus(focUserId,userid);
        response.getWriter().write(JSON.toJSONString(rst));
    }
}
