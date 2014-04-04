package com.cxf.interFace;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.cxf.pojo.AdminInfo;
import com.cxf.pojo.User;

@WebService
public interface Hello {
	public String sayHello(@WebParam(targetNamespace="http://interFace.cxf.com/")String object);
	public String sayHelloNo();
	public void sayHelloWold();
	public User getUserInfo(@WebParam(targetNamespace="http://interFace.cxf.com/")String username);
	public void getinfo();
	
}
