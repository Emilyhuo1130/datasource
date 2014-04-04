package com.ucs.tdc;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ucs.tdc.protocol.Adminprotocol;



public class ProjectListener implements ServletContextListener {
	static Logger log = Logger.getLogger(ProjectListener.class);

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent config) {
		// TODO Auto-generated method stub
		log.info(config.getServletContext().getRealPath("/"));
		System.setProperty("user.dir", config.getServletContext().getRealPath("/"));
		PropertyConfigurator.configure(System.getProperty("user.dir")+"WEB-INF/log4j.properties") ;
		log.info(System.getProperty("user.dir"));
	}
}
