package com.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FormTest {
	Logger log=Logger.getLogger(FormTest.class);
	
	@RequestMapping(value="/testform")
	@ResponseBody
	public boolean testform(HttpServletRequest req,HttpServletResponse res){
		String username = req.getParameter("username");
		String passwd = req.getParameter("userpassword");
		if(username!=null&&passwd!=null){
			log.info(username+"   "+passwd);
		}else{
			log.info("没有收到请求");
		}
		return true;
	}
}
