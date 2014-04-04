package com.ucs.rcm.business;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class ReqUtils {
	static Logger log = Logger.getLogger(ReqUtils.class);
	public static String readreqtoObject(HttpServletRequest request){
		String req=null;
		try {
			StringBuffer inputString = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			String s = reader.readLine();
			while (s != null) {
				inputString.append(s);
				s = reader.readLine();
			}
			req=inputString.toString();
			log.info("读取请求参数: " + req);
			
		}catch(Exception e){
			e.printStackTrace();
			log.info(e+"获取请求参数失败");
		}
		return req;
	}
}
