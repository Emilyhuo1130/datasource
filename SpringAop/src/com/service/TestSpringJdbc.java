package com.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inter_face.TestSpringJdbcIInterface;

@Controller
public class TestSpringJdbc {
	Logger log=Logger.getLogger(TestSpringJdbc.class);
	@Resource
	private TestSpringJdbcIInterface testJdbcdao;
	
	@RequestMapping(value="/testJdbc",method=RequestMethod.GET)
	@ResponseBody
	public Object testJdbc(HttpServletRequest req,HttpServletResponse res){
		return testJdbcdao.testSpringJdbc();
	}
}
