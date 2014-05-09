package com.my.strutsTest.service.impl;

import com.my.strutsTest.dao.UsersDao;
import com.my.strutsTest.entity.UsersBean;
import com.my.strutsTest.service.UsersService;

public class UsersServiceImpl implements UsersService{

	private UsersDao usersDao;
	
	

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}



	public UsersBean login(UsersBean usersBean) {
		return usersDao.login(usersBean);
		
	}

}
