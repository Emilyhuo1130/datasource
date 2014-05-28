package com.org.zhang.service.face;

import com.org.zhang.pojo.UsersInfo;

public interface UserControllerFace {

	boolean public_verifyUserName(String userName);

	boolean addUserInfo(UsersInfo user);
	
}
