package com.gjyl.appserver.serviceimpl;

import com.gjyl.appserver.dao.CyclTypeMapper;
import com.gjyl.appserver.pojo.CyclType;
import com.gjyl.appserver.service.CyclTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("/cyclTypeService")
public class CyclTypeServiceImpl implements CyclTypeService {
	
	@Resource
	private CyclTypeMapper dao;

	public List<CyclType> getAllTypes() {
		
		return dao.getAllTypes();
	}

	public Boolean addCyclType(CyclType type) {
		int rst=dao.insertSelective(type);
		if (rst>0){
			return true;
		}
		return false;
	}

	public CyclType getTypeById(String id) {
		return dao.getTypeById(id);
	}

	public Boolean updateCyclType(CyclType type) {
		int rst=dao.updateByPrimaryKeySelective(type);
		if (rst>0){
			return true;
		}
		return false;
	}

	public Boolean deleCyclTypeById(String id) {
		int rst=dao.deleteByPrimaryKey(id);
		if (rst>0){
			return true;
		}
		return false;
	}


}
