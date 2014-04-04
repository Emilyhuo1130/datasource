package com.ucs.cctv.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.poifs.eventfilesystem.POIFSReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ucs.cctv.Pojo.OperateLog;

public class ToolUtils{
	/**
	 * 
	 */

	/**读取application的路径*/
	public static ApplicationContext  getPathOfapplicationXML() {
	//	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		String path = POIFSReader.class.getResource("/").getPath();
		String websiteURL = (path.replace("/build/classes", "").replace("%20"," ").replace("classes/", "") ).replaceFirst("/", "");
		ApplicationContext context = new FileSystemXmlApplicationContext(websiteURL+"/applicationContext.xml");
		return context;
	}
	/**格式化日期方法*/
	public static String formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = sdf.format(date);
		return dateStr;
	}
	/**日志的set封装方法*/
	public static OperateLog wrapSetMathod(String operatorAccount,
									Date operateTime,String operateType,String remark,String operatorFormatDate){
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateType(operateType);
		operateLog.setOperatorAccount(operatorAccount);
		operateLog.setOperatorFormatDate(operatorFormatDate);
		operateLog.setRemark(remark);
		operateLog.setOperateTime(operateTime);
		return operateLog;
	}
}
