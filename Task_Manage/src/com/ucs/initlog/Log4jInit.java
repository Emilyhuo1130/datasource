package com.ucs.initlog;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@SuppressWarnings("serial")
public class Log4jInit extends HttpServlet {
	public static Logger logger = Logger.getLogger(Log4jInit.class);
	public Log4jInit(){
		
	}
	public void init(ServletConfig config) throws ServletException {
		String rootUrl = config.getServletContext().getRealPath("/");
		String file = config.getInitParameter("log4j");
		String filePath = rootUrl+file;
		Properties props = new Properties();
		try {
			FileInputStream istream = new FileInputStream(filePath);
			props.load(istream);
			istream.close();
			String logFile = rootUrl +props.getProperty("log4j.appender.file.File");
			logger.info(logFile);
			props.setProperty("log4j.appender.file.File", logFile);
			PropertyConfigurator.configure(props);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Could not read configuration file [" + filePath + "].");  
				System.out.println("Ignoring configuration file [" + filePath + "].");
				e.printStackTrace();
			}
		
	}

}
