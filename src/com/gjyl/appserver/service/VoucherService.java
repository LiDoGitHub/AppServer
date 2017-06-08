package com.gjyl.appserver.service;

import java.util.List;

import com.gjyl.appserver.pojo.UserVoucher;

public interface VoucherService {

	List<UserVoucher> getMyVoucher(String userId);

	Boolean getShareVoucher(UserVoucher userVoucher);

	Boolean updateVoucher(List<String> vouList);

}
