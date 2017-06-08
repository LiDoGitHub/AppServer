package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.Collect;

import java.util.List;

public interface CollectService {

	Boolean collectCycl(Collect collect);

	Boolean cancleCollect(String userId, String cyclId);

	Boolean isCollected(String userId, String cyclId);

	List<Collect> getCollectByUserId(String userId);
}
