package com.server;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e.UserInfo;
import com.google.gson.Gson;
import com.interFace.HibernateTestInterface;

@Controller
public class HibrenateTest {
	@Resource
	private HibernateTestInterface dao;
	
	Logger log=Logger.getLogger(HibrenateTest.class);
	@RequestMapping(value="/testhibernate",method=RequestMethod.GET)
	@ResponseBody
	public Object test(HttpServletRequest req,HttpServletResponse res){
		Object obj=dao.test();
		Gson gson=new Gson();
		log.info(gson.toJson(obj));
		return obj;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	@ResponseBody
	public Object update(HttpServletRequest req,HttpServletResponse res){
		Object obj=dao.test();
		Gson gson=new Gson();
		UserInfo info=(UserInfo)obj;
		info.setUserPw("999999");
		boolean b=dao.update(info);
		Object obj2=dao.test();
		return obj2;
	}
	
}
