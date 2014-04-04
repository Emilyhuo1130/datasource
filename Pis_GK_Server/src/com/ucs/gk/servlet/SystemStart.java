package com.ucs.gk.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ucs.gk.utils.WOLStartAndDownSetting;
public class SystemStart extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(SystemStart.class);
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		/***设置唤醒和关闭时间**/
	
		WOLStartAndDownSetting.openTimer();
		WOLStartAndDownSetting.modifyFileDate();

	}
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dopost");
	}
	
	

}
