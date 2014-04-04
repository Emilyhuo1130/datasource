package com.pojo;

import java.util.List;

public class AllUserInfo {
	private UserInfo userInfo;
	private List<String> list;
	private String name="钓鱼";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	
}
