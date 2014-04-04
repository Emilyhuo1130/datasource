package com.pojo;

/**
 * AdminInfo entity. @author MyEclipse Persistence Tools
 */

public class AdminInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userName;
	private String userPw;

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