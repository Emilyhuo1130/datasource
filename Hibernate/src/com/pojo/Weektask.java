package com.pojo;

import java.util.Date;

/**
 * Weektask entity. @author MyEclipse Persistence Tools
 */

public class Weektask implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userAccount;
	private String projectName;
	private String weekTask;
	private Integer weekNo;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public Weektask() {
	}

	/** full constructor */
	public Weektask(String userAccount, String projectName, String weekTask,
			Integer weekNo, Date updateTime) {
		this.userAccount = userAccount;
		this.projectName = projectName;
		this.weekTask = weekTask;
		this.weekNo = weekNo;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getWeekTask() {
		return this.weekTask;
	}

	public void setWeekTask(String weekTask) {
		this.weekTask = weekTask;
	}

	public Integer getWeekNo() {
		return this.weekNo;
	}

	public void setWeekNo(Integer weekNo) {
		this.weekNo = weekNo;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}