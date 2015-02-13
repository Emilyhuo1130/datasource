package com.ces.tcm.buyer.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ces.tcm.bean.UserInfo;
import com.ces.tcm.buyer.dao.IGoodsDao;
import com.ces.tcm.buyer.service.IGoodService;
@Service("GoodService")
@Transactional
public class GoodService implements IGoodService{
	@Resource
	private IGoodsDao gooddao;
	public boolean addAndUpdate(UserInfo info) {
		boolean b=gooddao.addAndUpdate(info);
		return b;
	}

}
