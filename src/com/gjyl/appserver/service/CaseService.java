package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.Cases;

import java.util.List;

public interface CaseService {

	List<Cases> getMyCases(String userid);

	Boolean addCases(Cases cases);

	Cases getCaseInfo(String id);

	Boolean deleteCase(String id);

	Boolean updateCases(Cases cases);

}
