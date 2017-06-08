package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.Banner;

import java.util.List;

public interface BannerService {

	List<Banner> getHomeBanners();

	List<Banner> getDisBanners();

	Boolean addBanner(Banner banner);

	Banner getBanByCyclId(String banid);

	Banner getBanById(String banid);

	Boolean updateBanner(Banner banner);

    Boolean deleteBanners(String[] ids);
}
