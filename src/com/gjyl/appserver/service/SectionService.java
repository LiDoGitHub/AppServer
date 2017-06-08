package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.Section;

import java.util.List;

public interface SectionService {

	List<Section> getSecList();

	List<Section> getSections();

	Section getSectionInfo(String id);

    Boolean addSection(Section section);

	Boolean delSection(String secid);

	Boolean updateSection(Section section);

    Boolean addSecFromExcel(List<Object> list);
}
