package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Cyclopedia;

import java.util.List;
import java.util.Map;

public interface CyclopediaMapper {
    int deleteByPrimaryKey(String cyclopediaid);

    int insert(Cyclopedia record);

    Cyclopedia selectByPrimaryKey(String cyclopediaid);
    
    //随机获取两个文章
    List<Cyclopedia> getRandomAtr();

    //分页获取文章
	List<Cyclopedia> getCyclByPage(Integer pageNum);

	//文章详情
	Cyclopedia getCyclInfo(String cyclId);

	//所有文章
	List<Cyclopedia> getAllCyclopedias();
	
	//新增文章
	Integer addCycl(Cyclopedia cyclopedia);

	//删除文章
	int delCyclopedia(String cycId);
	//根据分类获取文章
	List<Cyclopedia> getCyclByType(Map<String, Object> map);
	
	//更新
	int updateCycl(Cyclopedia cyclopedia);

	//获取热门文章
	List<Cyclopedia> getHotCycl();

	//总数
    Integer getTotalNum();
}