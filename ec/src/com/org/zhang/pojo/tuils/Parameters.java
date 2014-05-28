package com.org.zhang.pojo.tuils;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.org.zhang.controller.UserController;


public class Parameters {
	/*public  static <T> T  toPojo(T object,HttpServletRequest req){
		
	}*/
	private final static Gson gson=new Gson();
	Logger log=Logger.getLogger(Parameters.class);
	@SuppressWarnings("unchecked")
	public   <T> T  toPojo(T object,HttpServletRequest req){
		log.info(gson.toJson(req.getParameterMap()));
		return req.getParameterMap().size()>0?(T)gson.fromJson(gson.toJson(req.getParameterMap()).replace("[", "").replace("]", ""), object.getClass()):null;
	}
	
	
}
