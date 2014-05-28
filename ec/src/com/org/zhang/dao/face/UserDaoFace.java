package com.org.zhang.dao.face;

import com.org.zhang.pojo.UsersInfo;

public interface UserDaoFace {

	boolean verifyUserName(String userName);

	boolean addUserInfo(UsersInfo user);

}
