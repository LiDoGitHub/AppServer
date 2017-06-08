package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.Essay;
import com.gjyl.appserver.pojo.EssayAgree;

import java.util.List;

public interface EssayService {

	Boolean publishEssay(Essay essay);

    Boolean deleteEssayById(String id);

    List<Essay> getAllEssaiesByPage(String pageNum, String userid);

    Essay getEssayInfo(String id);

    Boolean agreeWithEssay(EssayAgree ea);

    Boolean disAgreeWithEssay(String userid, String eid);

    List<Essay> getFocusEssayByPage(String pageNum, String userid);

    Integer getMaxPage();

    Integer getFocusMaxPage(String userid);

    List<Essay> getUserEssaies(String userid, Integer pageNum);
}
