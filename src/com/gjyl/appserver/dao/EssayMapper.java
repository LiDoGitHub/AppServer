package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Essay;

import java.util.List;
import java.util.Map;

public interface EssayMapper {

    int insert(Essay record);

    int insertSelective(Essay record);

    Essay selectByPrimaryKey(String eid);

    int updateByPrimaryKeySelective(Essay record);

    int updateByPrimaryKey(Essay record);

    //分页显示
    List<Essay> getAllEssaiesByPage(Integer pageNum);

    //发布内容
    int publishEssay(Essay essay);

    //删除
    int deleteByPrimaryKey(String eid);

    //内容详情
    Essay getEssayInfo(String eid);

    //更新浏览次数
    int updateETImes(String eid);

    //我的关注
    List<Essay> getFocusEssayByPage(Map<String, Object> map);

    //总数
    int getTotalNum();

    //关注总数
    int getFocusTotalNum(List<String> list);

    //某一用户发布的说说
    List<Essay> getUserEssaies(Map<String, Object> map);
}