package com.gjyl.appserver.dao;

import com.gjyl.appserver.pojo.Inquiry;

public interface InquiryMapper {
    int deleteByPrimaryKey(String inqid);

    int insert(Inquiry record);

    int insertSelective(Inquiry record);

    Inquiry selectByPrimaryKey(String inqid);

    int updateByPrimaryKeySelective(Inquiry record);

    int updateByPrimaryKey(Inquiry record);
}