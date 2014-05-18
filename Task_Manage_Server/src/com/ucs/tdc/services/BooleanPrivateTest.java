package com.ucs.tdc.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BooleanPrivateTest {
	
	static boolean flag=false;
	
	@RequestMapping(value="/getBooleanPrivateTest1")
	@ResponseBody
	public boolean getBooleanPrivateTest1(){
		System.out.println("getBooleanPrivateTest1获取域中的值"+flag);
		flag=true;
		System.out.println("getBooleanPrivateTest1修改以后域的值"+flag);
		return flag;
	}
	
	@RequestMapping(value="/getBooleanPrivateTest2")
	@ResponseBody
	public boolean getBooleanPrivateTest2(){
		System.out.println("getBooleanPrivateTest2获取域中的值"+flag);
		flag=false;
		System.out.println("getBooleanPrivateTest2修改以后域的值"+flag);
		return flag;
	}
}
