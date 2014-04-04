package com.ucs.tdc.pojo;

/**
 * AdminInfo entity. @author MyEclipse Persistence Tools
 */

public class AdminInfo implements java.io.Serializable {

	// Fields

	private Integer id;//id
	private String userName;//管理员登陆账号
	private String userPw;//管理员登陆密码

	// Constructors

	/** default constructor */
	public AdminInfo() {
	}

	/** full constructor */
	public AdminInfo(String userName, String userPw) {
		this.userName = userName;
		this.userPw = userPw;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPw() {
		return this.userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

}