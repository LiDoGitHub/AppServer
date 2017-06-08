package com.gjyl.appserver.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gjyl.appserver.dao.DiseaseLibraryMapper;
import com.gjyl.appserver.dao.DoctorMapper;
import com.gjyl.appserver.dao.HotSearchMapper;
import com.gjyl.appserver.dao.SectionMapper;
import com.gjyl.appserver.pojo.DiseaseLibraryWithBLOBs;
import com.gjyl.appserver.pojo.Doctor;
import com.gjyl.appserver.pojo.HotSearch;
import com.gjyl.appserver.pojo.HotSearchResult;
import com.gjyl.appserver.pojo.Section;
import com.gjyl.appserver.service.HotSearchService;

@Service("hotSearchService")
public class HotSearchServiceImpl implements HotSearchService {

	@Resource
	private HotSearchMapper dao;
	@Resource
	private DoctorMapper docDao;
	@Resource
	private DiseaseLibraryMapper disDao;
	@Resource
	private  SectionMapper secDao;

	public List<HotSearch> getHotSearch() {
		
		return dao.getHotSearch();
	}

	public List<String> getRelativeSearch(String content) {
		List<String> docs= docDao.getDocInfoByContent(content);
		List<String> sections=secDao.getSectionByContent(content);
		List<String> disList=disDao.getDisInfoByContent(content);
		List<String> list=new ArrayList<String>();
		if (docs.size()>0) {
			list.addAll(docs);
		}
		if (sections.size()>0) {
			list.addAll(sections);
		}
		if (disList.size()>0) {
			list.addAll(disList);
		}
		return list;
	}

	//搜索结果
	public HotSearchResult getSearchRst(String content) {
		HotSearchResult result = new HotSearchResult();
		HotSearch search= dao.getHotSearchByContent(content);
		if (search!=null) {//更新搜索次数
			search.setStimes(search.getStimes()+1);
			int rst= dao.updateSTimes(search);
			if (rst>0) {
				System.out.println("热搜更新成功.................");
			}
		}else {//添加搜索词汇
			search=new HotSearch();
			search.setStimes(1);
			search.setScontent(content);
			search.setSdate(new Date());
			int rst= dao.addSearch(search);
			if (rst>0) {
				System.out.println("热搜新增成功.................");
			}
		}
		//进行相关搜索...
		List<Doctor> docList=docDao.getDoctorByContent(content);
		List<Section> secList = secDao.getSecByContent(content);
		List<DiseaseLibraryWithBLOBs> disList=disDao.getDisByContent(content);
		result.setDoctors(docList);
		result.setSections(secList);
		result.setDiseases(disList);
		return result;
	}
	
	
}
