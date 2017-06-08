package com.gjyl.appserver.service;

import com.gjyl.appserver.pojo.DocArrangement;

/**
 * Created by LiD on 2017/4/27.
 */
public interface DocArrService {

    DocArrangement getDocArrByDocId(String docid);

    DocArrangement getArrById(String arrid);

    Boolean addDocArrangement(DocArrangement arrangement);

    Boolean updateDocArrangement(DocArrangement arr);
}
