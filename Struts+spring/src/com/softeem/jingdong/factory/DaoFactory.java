package com.softeem.jingdong.factory;

import com.my.strutsTest.dao.UsersDao;
import com.my.strutsTest.dao.impl.UserDaoImpl;



/**
 * 静态工厂 ， 生产对象
 * @author Administrator
 *
 */
public class DaoFactory {
	
	//获取 UsersDao 实例
	public static UsersDao getUsersDaoInstance(){
		return new UserDaoImpl();
	}
	
	
	
}
