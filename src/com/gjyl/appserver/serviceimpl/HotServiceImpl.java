package com.gjyl.appserver.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gjyl.appserver.dao.HotMapper;
import com.gjyl.appserver.pojo.Hot;
import com.gjyl.appserver.service.HotService;

/**
 * 获取所有热门文章
 * @author LiD
 *
 */
@Service("hotService")
public class HotServiceImpl implements HotService {

	@Resource
	private HotMapper mapper;
	
	public List<Hot> getHotArticles() {
		
		return mapper.getAllArticles();
	}

}
