package com.ucs.rcm.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class ToolUtils{
	/**
	 * 
	 */
	Logger log;

	/**读取application的路径*/
	
	
	/**格式化日期方法*/
	public static String formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = sdf.format(date);
		return dateStr;
	}

}
