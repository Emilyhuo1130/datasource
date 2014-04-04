package com.cxf.server;

import javax.jws.WebService;

import com.cxf.interFace.Hello;
import com.cxf.pojo.AdminInfo;
import com.cxf.pojo.User;
@WebService()
public class HelloDao implements Hello {
	public String sayHello(String object ) {
		return "你好： "+object;
	}
	public void sayHelloWold(){
		System.out.println("helloWold");
	}
	
	public User getUserInfo(String username) {
		// TODO Auto-generated method stub
		User info=new User();
		info.setName(username);info.setAge(22);
		return info;
	}
	public void getinfo() {
		// TODO Auto-generated method stub
		
	}
	public String sayHelloNo() {
		// TODO Auto-generated method stub
		return "helloNo";
	}
	
}
