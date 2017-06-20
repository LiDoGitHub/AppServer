package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.utils.SignatureUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by LiD on 2017/6/20.
 */

@Controller
@RequestMapping("/singnature")
public class SingnatureController {

    /**
     * 获取极光签名
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/getSingnature")
    public void getSingnature(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        Map<String, Object> map = SignatureUtil.getSignature();
        response.getWriter().write(JSON.toJSONString(map));
    }

}
