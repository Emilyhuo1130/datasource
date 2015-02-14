package com.test.aop.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.aop.bean.UserInfo;
import com.test.aop.service.IAOPTest;
@Controller
public class AOPTestController {
	Logger log=Logger.getLogger(getClass());
	@Resource
	IAOPTest aop;
	@ResponseBody
	@RequestMapping(value="/Aop1")
	public String AopTest1(){
		aop.AopTest1();
		return "--";
	}
	
	@ResponseBody
	@RequestMapping(value="/Aop2")
	public String AopTest2(){
		UserInfo info = new UserInfo();
		aop.AopTest2(info, "99iii");
		return "--";
	}
	
	@ResponseBody
	@RequestMapping(value="/Aop3")
	public String AopTest3(){
		try{
			aop.AopTest3();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "--";
	}
}
