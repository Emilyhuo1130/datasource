package com.ucs.rcm;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.ucs.rcm.webservice.WebServiceUtil;


public class SystemStart implements ServletContextListener {
	static Logger log = Logger.getLogger(SystemStart.class);
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println("*************开始注册到卡斯柯**************");
		
		//注册到卡斯柯服务
		//WebServiceUtil.reg();
		
		//获取站名列表
						

	}

}
