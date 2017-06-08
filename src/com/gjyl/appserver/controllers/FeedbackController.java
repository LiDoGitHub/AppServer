package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.Feedback;
import com.gjyl.appserver.service.FeedbackService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiD on 2017/6/7.
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @Resource
    private FeedbackService feedbackService;

    /**
     * 新增反馈
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/addFeedback")
    public void addFeedback(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/json;charset=utf-8");
        Feedback feedback = new Feedback();
        DateConverter dc=new DateConverter();
        ConvertUtils.register(dc,Date.class);
        dc.setPattern("yyyy-MM-dd HH:mm:ss");
        BeanUtils.populate(feedback,request.getParameterMap());
        if (feedback.getContent()!=null&&!feedback.getContent().equals("")){
            Boolean rst= feedbackService.addFeedback(feedback);
            response.getWriter().write(JSON.toJSONString(rst));
        }else {
            response.getWriter().write(JSON.toJSONString("error"));
        }
    }

    /**
     * 意见反馈列表,后台用
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/getFeedbacks")
    public void getFeedbacks(HttpServletRequest request,HttpServletResponse response) throws Exception{
        response.setContentType("text/json;charset=utf-8");
        String pageNum = request.getParameter("pageNum");
        if (pageNum!=null&&!pageNum.equals("")){
            List<Feedback> list=feedbackService.getFeedbackByPage(Integer.valueOf(pageNum));
            Integer total= feedbackService.getTotalNum();
            Map<String,Object> map=new HashMap<>();
            map.put("list",list);
            map.put("total",total);
            response.getWriter().write(JSON.toJSONString(map));
        }else {
            response.getWriter().write(JSON.toJSONString("error"));
        }
    }

    /**
     * 删除意见反馈
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/delFeedback")
    public void delFeedback(HttpServletRequest request,HttpServletResponse response) throws Exception{
        response.setContentType("text/json;charset=utf-8");
        String fbid = request.getParameter("fbid");
        if (fbid!=null&&!fbid.equals("")){
            Boolean rst = feedbackService.delFeedbackById(fbid);
            response.getWriter().write(JSON.toJSONString(rst));
        }else {
            response.getWriter().write(JSON.toJSONString("error"));
        }
    }
}
