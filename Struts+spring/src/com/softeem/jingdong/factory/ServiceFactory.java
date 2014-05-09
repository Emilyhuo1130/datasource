package com.softeem.jingdong.factory;

import com.my.strutsTest.service.UsersService;
import com.my.strutsTest.service.impl.UsersServiceImpl;

public class ServiceFactory {
	
	//获取 UsersService 实例
	public static UsersService getUsersServiceInstance(){
		return new UsersServiceImpl();
	}
	

}
