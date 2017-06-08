package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Collect;

import java.util.List;
import java.util.Map;

public interface CollectMapper {
    int deleteByPrimaryKey(String id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

	int collectCycl(Collect collect);

	int isExists(Map<String, String> map);

	List<Collect> getCollectByUserId(String userId);

    int cancleCollect(Map<String, String> map);
}