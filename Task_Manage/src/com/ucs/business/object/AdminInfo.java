package com.ucs.business.object;

public class AdminInfo {
	private Integer id;
	private String userName;
	private String userPw;
	public Integer id() {
		return id;
	}
	public void setid(Integer id) {
		this.id = id;
	}
	public String getName() {
		return userName;
	}
	public void setName(String name) {
		this.userName = name;
	}
	public String getPassword() {
		return userPw;
	}
	public void setPassword(String password) {
		this.userPw = password;
	}

}
