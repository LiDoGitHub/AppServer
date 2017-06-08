package com.gjyl.appserver.dao;

import java.util.List;

import com.gjyl.appserver.pojo.UserVoucher;

public interface UserVoucherMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserVoucher record);

    int insertSelective(UserVoucher record);

    UserVoucher selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserVoucher record);

    int updateByPrimaryKey(UserVoucher record);

	List<UserVoucher> getMyVoucher(String userId);

	//新增分享获得的代金券
	int addShareVoucher(UserVoucher userVoucher);

	int updateBatch(List<String> vouList);
}