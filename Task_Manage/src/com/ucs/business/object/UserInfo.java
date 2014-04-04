package com.ucs.business.object;

public class UserInfo {
	private Integer id;
	private String userName="";//姓名
	private String userAccount="";//用户名
	private String userPw="";//用户密码
	private String phoneNumber="";//用户电话
	public Integer getid() {
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
	public String getuserAccount() {
		return userAccount;
	}
	public void setuserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getPassword() {
		return userPw;
	}
	public void setPassword(String password) {
		this.userPw = password;
	}
	public String getphoneNumber() {
		return phoneNumber;
	}
	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
