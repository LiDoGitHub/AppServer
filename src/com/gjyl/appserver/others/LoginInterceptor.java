package com.gjyl.appserver.others;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LiD on 2017/6/21.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        response.setContentType("text/json;charset=utf-8");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "*");
//        response.setHeader("Access-Control-Max-Age", "3600");

        StringBuffer url = request.getRequestURL();
        Boolean isLogged=false;
        if (!"login.html".equals(getRequestPage(url.toString()))) {
            Cookie[] cookies = request.getCookies();
            System.out.println("Cookie中的值:\n");
            if (cookies != null) {
                for (Cookie c : cookies) {
                    //System.out.println(URLDecoder.decode(c.getName(), "UTF-8") + ":" + URLDecoder.decode(c.getValue(), "UTF-8"));
                    if (c.getName()!=null&&"user".equals(c.getName())){
                        isLogged=true;
                    }
                }
                if (!isLogged){
                    System.out.println("未登录,跳转...");
                    response.sendRedirect("/AppServer/login.html");
                    return false;
                }
            }
        }
        return true;
    }

    private String  getRequestPage(String url){
        int lastIndex = url.lastIndexOf("/");
        if (lastIndex==url.length()-1){
            return "login.html";
        }else {
            String substring = url.substring(lastIndex+1, url.length());
            return substring;
        }
    }

}
