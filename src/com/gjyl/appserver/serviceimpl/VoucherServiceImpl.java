package com.gjyl.appserver.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gjyl.appserver.dao.UserVoucherMapper;
import com.gjyl.appserver.pojo.UserVoucher;
import com.gjyl.appserver.service.VoucherService;

@Service("voucherService")
public class VoucherServiceImpl implements VoucherService {
	
	@Resource
	private UserVoucherMapper dao;

	//我的代金券
	public List<UserVoucher> getMyVoucher(String userId) {
		
		return dao.getMyVoucher(userId);
	}

	//分享得券
	public Boolean getShareVoucher(UserVoucher userVoucher) {
		LocalDate date = LocalDate.now().plusDays(15);
		userVoucher.setEndtime(date.toString());
		int result = dao.addShareVoucher(userVoucher);
		if (result>0) {
			return true;
		}
		return false;
	}

	//使用代金券
	@Transactional
	public Boolean updateVoucher(List<String> vouList) {
		//批处理
		int result=dao.updateBatch(vouList);
		if (result>0) {
			return true;
		}
		return false;
	}

}
