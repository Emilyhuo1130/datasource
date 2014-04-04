package com.testAop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class TestAop {
	Logger log=Logger.getLogger(TestAop.class);
	@RequestMapping(value="/testAop",method=RequestMethod.GET)
	@ResponseBody
	public String getstring(HttpServletRequest req,HttpServletResponse res){
		log.info("测试log4j");
		return "------------";
	}
}
