package com.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.inter_face.ManageInterface;
import com.pojo.AdminInfo;
import com.pojo.AllUserInfo;
import com.pojo.UserInfo;

@Repository("ManageDao")
public class ManageDao implements ManageInterface{
	Logger log=Logger.getLogger(ManageDao.class);
	
	
	public UserInfo getuserMain() {
		UserInfo info=new UserInfo();
		return info;
	}

	public AdminInfo getadminMain() {
		AdminInfo info=new AdminInfo();
		return info;
	}

	public AllUserInfo getAllUserInfo() {
		AllUserInfo info=new AllUserInfo();
		UserInfo i=new UserInfo();
		info.setUserInfo(i);
		info.setList(Lists.newArrayList("游泳","钓鱼","晒太阳"));
		return info;
	}

	public List<String> getSelect() {
		List<String> list=Lists.newArrayList("牛顿","马里奥","矮星斯坦");
		return list;
	}

}
