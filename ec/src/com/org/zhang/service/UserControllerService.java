package com.org.zhang.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.zhang.dao.face.UserDaoFace;
import com.org.zhang.pojo.UsersInfo;
import com.org.zhang.service.face.UserControllerFace;
@Service
public class UserControllerService implements UserControllerFace{
	@Resource
	private UserDaoFace dao;
	@Override
	public boolean public_verifyUserName(String userName) {
		if(userName.toUpperCase().equals("ADMIN")||userName.toUpperCase().equals("ADMINISTRATOR")){
			return true;
		}
		return  dao.verifyUserName(userName);
			
		
	}
	@Override
	public boolean addUserInfo(UsersInfo user) {
		return dao.addUserInfo(user);
	}

}
