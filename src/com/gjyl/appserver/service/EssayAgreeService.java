package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.EssayAgree;

import java.util.List;

/**
 * Created by LiD on 2017/4/21.
 */
public interface EssayAgreeService {
    List<EssayAgree> getEssayAgreeList(String eid);

}
