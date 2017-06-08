package com.gjyl.appserver.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gjyl.appserver.dao.CasesMapper;
import com.gjyl.appserver.pojo.Cases;
import com.gjyl.appserver.service.CaseService;

@Service("caseService")
public class CaseServiceImpl implements CaseService {

	@Resource
	private CasesMapper dao;

	public List<Cases> getMyCases(String userid) {

		return dao.getMyCases(userid);
	}

	public Boolean addCases(Cases cases) {
		int rst= dao.addCases(cases);
		if (rst>0) {
			return true;
		}
		return false;
	}

	public Cases getCaseInfo(String id) {

		return dao.getCaseInfo(id);
	}

	public Boolean deleteCase(String id) {
		int rst=dao.deleteByPrimaryKey(id);
		if (rst>0) {
			return true;
		}
		return false;
	}

	public Boolean updateCases(Cases cases) {
		int i=dao.updateByPrimaryKeySelective(cases);
		if (i>0) {
			return true;
		}
		return false;
	}
}
