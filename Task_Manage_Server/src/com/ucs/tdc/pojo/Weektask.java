package com.ucs.tdc.pojo;

import java.util.Date;

/**
 * Weektask entity. @author MyEclipse Persistence Tools
 */

public class Weektask implements java.io.Serializable {
	private Integer id;
	private String userAccount;//
	private String projectName;//
	private String weekTask;//周计划任务
	private Integer weekNo;//周号
	private Date updateTime;//提交或者修改周计划的时间
	private String fromatDate;//日期格式化
	

	// Constructors

	public String getFromatDate() {
		return fromatDate;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public void setFromatDate(String fromatDate) {
		this.fromatDate = fromatDate;
	}

	/** default constructor */
	public Weektask() {
	}

	/** full constructor */
	public Weektask(String weekTask, Integer weekNo, Date updateTime) {
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