package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.DiseaseLibraryMapper;
import com.gjyl.appserver.dao.SectionMapper;
import com.gjyl.appserver.pojo.DiseaseLibraryWithBLOBs;
import com.gjyl.appserver.pojo.Section;
import com.gjyl.appserver.service.SectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sectionService")
public class SectionServiceImpl implements SectionService {

	@Resource
	private SectionMapper mapper;
	@Resource
	private DiseaseLibraryMapper disDao;
	
	public List<Section> getSecList() {
		List<Section> secList = mapper.getSecList();
		for (Section section : secList) {
			List<DiseaseLibraryWithBLOBs> disList = disDao.getDiseaseList(section.getSectionid());
			section.setDisList(disList);
		}
		return secList;
	}

	public List<Section> getSections() {

		return mapper.getSecList();
	}

	public Section getSectionInfo(String id) {
		return mapper.getSectionById(id);
	}

	public Boolean addSection(Section section) {
		int rst=mapper.insertSelective(section);
		if (rst>0){
			return true;
		}
		return false;
	}

	public Boolean delSection(String secid) {
		int rst=mapper.deleteByPrimaryKey(secid);
		if (rst>0){
			return true;
		}
		return false;
	}

	public Boolean updateSection(Section section) {
		int rst=mapper.updateByPrimaryKeySelective(section);
		if (rst>0){
			return true;
		}
		return false;
	}

	public Boolean addSecFromExcel(List<Object> list) {

		int rst=mapper.executeBatch(list);
		if (rst>0){
			return true;
		}
		return false;
	}


}
