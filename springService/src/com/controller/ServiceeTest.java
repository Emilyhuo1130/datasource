package com.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.UserInfo;
import com.service.face.ServiceTestFace;

@Controller
public class ServiceeTest {
	@Resource
	private ServiceTestFace service;
	Logger log=Logger.getLogger(ServiceeTest.class);
	@RequestMapping(value="/test",method=RequestMethod.GET)
	@ResponseBody
	public boolean test() {
		log.info("进入controller");
		UserInfo info=new UserInfo("test",33,"555555");
		try {
			return service.addAndUpdate(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
