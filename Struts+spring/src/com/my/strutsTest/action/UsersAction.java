package com.my.strutsTest.action;

import com.my.strutsTest.entity.UsersBean;
import com.my.strutsTest.service.UsersService;
import com.opensymphony.xwork2.ActionSupport;

public class UsersAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private UsersService usersService;
	private UsersBean user;
	
	public String login(){
		System.out.println(name+"    :"+password);
		
		user=usersService.login(new UsersBean(name,password));
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	public UsersService getUsersService() {
		return usersService;
	}










	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}










	public UsersBean getUser() {
		return user;
	}

	public void setUser(UsersBean user) {
		this.user = user;
	}
	
}
