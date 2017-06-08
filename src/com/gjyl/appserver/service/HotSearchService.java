package com.gjyl.appserver.service;

import java.util.List;

import com.gjyl.appserver.pojo.HotSearch;
import com.gjyl.appserver.pojo.HotSearchResult;

public interface HotSearchService {

	List<HotSearch> getHotSearch();

	List<String> getRelativeSearch(String content);

	HotSearchResult getSearchRst(String content);


}
