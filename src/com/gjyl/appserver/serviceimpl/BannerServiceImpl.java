package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.BannerMapper;
import com.gjyl.appserver.dao.CyclopediaMapper;
import com.gjyl.appserver.pojo.Banner;
import com.gjyl.appserver.service.BannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("bannerService")
public class BannerServiceImpl implements BannerService {

	@Resource
	private BannerMapper dao;
	@Resource
	private CyclopediaMapper cyclopediaMapper;
	
	public List<Banner> getHomeBanners() {
		return dao.getBannersByCate(1);
	}

	public List<Banner> getDisBanners() {
		return dao.getBannersByCate(2);
	}

	public Boolean addBanner(Banner banner) {
		int rst=dao.insertSelective(banner);
		return rst > 0 ? true : false;
	}

	public Banner getBanByCyclId(String cyclid) {
		return dao.getBanByCyclId(cyclid);
	}


	public Banner getBanById(String banid) {
		return dao.selectByPrimaryKey(banid);
	}

	public Boolean updateBanner(Banner banner) {
		int rst=dao.updateByPrimaryKeySelective(banner);
		if (rst>0)
			return true;
		return false;
	}

	public Boolean deleteBanners(String[] ids) {
		Map<String,String[]> map=new HashMap<>();
		map.put("list",ids);
		int rst=dao.deleteBanners(map);
		if (rst>0)
			return true;
		return false;
	}
}
