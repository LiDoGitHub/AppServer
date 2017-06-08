package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.Cyclopedia;

import java.util.List;

public interface CyclopediaService {

	List<Cyclopedia> getRandomArt();

	List<Cyclopedia> getCyclByPage(Integer pageNum);

	Cyclopedia getCyclInfo(String cyclId);

	List<Cyclopedia> getAllCyclopedias();

	Boolean addCycl(Cyclopedia cyclopedia);

	Boolean delCyclopedia(String cycId);

	List<Cyclopedia> getCyclByType(String typeId, String page);

	List<Cyclopedia> getHotCycl();

    Boolean updateCyclopedia(Cyclopedia cyclopedia);

	List<Cyclopedia> getCyclListByPage(Integer integer);

	Integer getTotalNum();
}
