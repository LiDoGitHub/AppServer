package com.gjyl.appserver.controllers;

import com.alibaba.fastjson.JSON;
import com.gjyl.appserver.pojo.DocArrangement;
import com.gjyl.appserver.service.DocArrService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by LiD on 2017/4/17.
 */
@Controller
@RequestMapping("/docArrangement")
public class DocArrangeController {

    @Resource
    private DocArrService docArrService;

    /**
     * 医生排班信息
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/getDocArrange")
    public void getDocArrange(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/json;charset=utf-8");
        String docid = request.getParameter("docid");
        if (docid!=null&&!docid.equals("")) {
            DocArrangement arrangement = docArrService.getDocArrByDocId(docid);
            response.getWriter().write(JSON.toJSONString(arrangement));
        }else {
            response.getWriter().write(JSON.toJSONString("error"));
        }
    }

    /**
     * 编辑排班
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/editDocArrange")
    public void editDocArrange(HttpServletRequest request,HttpServletResponse response) throws Exception{
        response.setContentType("text/json;charset=utf-8");
        String arrid = request.getParameter("arrid");
        if (arrid!=null&&!arrid.equals("")) {
            DocArrangement arr = docArrService.getArrById(arrid);
            if(arr==null){//没有排班,新增排班
                DocArrangement arrangement = new DocArrangement();
                arrangement.setArrid(arrid);
                BeanUtils.populate(arrangement,request.getParameterMap());
                Boolean rst = docArrService.addDocArrangement(arrangement);
                response.getWriter().write(JSON.toJSONString(rst));
            }else {//已有排班,更新排班
                BeanUtils.populate(arr,request.getParameterMap());
                Boolean rst = docArrService.updateDocArrangement(arr);
                response.getWriter().write(JSON.toJSONString(rst));
            }
        }else {
            response.getWriter().write(JSON.toJSONString("error"));
        }
    }
}
