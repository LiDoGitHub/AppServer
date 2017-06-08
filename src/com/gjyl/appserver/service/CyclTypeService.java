package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.CyclType;

import java.util.List;

public interface CyclTypeService {

	List<CyclType> getAllTypes();

    Boolean addCyclType(CyclType type);

    CyclType getTypeById(String id);

    Boolean updateCyclType(CyclType type);

    Boolean deleCyclTypeById(String id);
}
