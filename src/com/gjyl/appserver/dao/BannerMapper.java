package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Banner;

import java.util.List;
import java.util.Map;

public interface BannerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);
    
    List<Banner> getBannersByCate(Integer categoryCode);

    Banner getBanByCyclId(String cyclopediaid);

    //批量删除
    int deleteBanners(Map<String, String[]> map);
}