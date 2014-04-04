package com.Logindao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.dao.ManageDao;
import com.inter_face.LoginInterface;
import com.inter_face.ManageInterface;
import com.pojo.AdminInfo;
import com.pojo.UserInfo;

@Repository("LoginDao")
public class LoginDao implements LoginInterface {
	Logger log=Logger.getLogger(ManageDao.class);
	public boolean login(String name, String password) {
		// TODO Auto-generated method stub
		log.info("登录账号："+name+"      登录密码："+password);
		return true;
	}
}
	


