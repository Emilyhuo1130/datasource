package com.ucs.tdc.pojo;

import java.util.Date;

/**
 * TaskParticulars entity. @author MyEclipse Persistence Tools
 */

public class TaskParticulars implements java.io.Serializable {
	private Integer id;
	private String projectName;
	private Float finishHouse;//所用工时浮点数据库支持一位小数点  22.2
	private String userName;//工作人员
	private String taskDetail;//任务详情
	private Date commitDare;//修改或者提交日期
	
	private String farmatDate;//日期格式化
	// Constructors

	public String getFarmatDate() {
		return farmatDate;
	}

	public void setFarmatDate(String farmatDate) {
		this.farmatDate = farmatDate;
	}

	/** default constructor */
	public TaskParticulars() {
	}

	/** minimal constructor */
	public TaskParticulars(String projectName, Float finishHouse,
			String userName, Date commitDare) {
		this.projectName = projectName;
		this.finishHouse = finishHouse;
		this.userName = userName;
		this.commitDare = commitDare;
	}

	/** full constructor */
	public TaskParticulars(String projectName, Float finishHouse,
			String userName, String taskDetail, Date commitDare) {
		this.projectName = projectName;
		this.finishHouse = finishHouse;
		this.userName = userName;
		this.taskDetail = taskDetail;
		this.commitDare = commitDare;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Float getFinishHouse() {
		return this.finishHouse;
	}

	public void setFinishHouse(Float finishHouse) {
		this.finishHouse = finishHouse;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTaskDetail() {
		return this.taskDetail;
	}

	public void setTaskDetail(String taskDetail) {
		this.taskDetail = taskDetail;
	}

	public Date getCommitDare() {
		return this.commitDare;
	}

	public void setCommitDare(Date commitDare) {
		this.commitDare = commitDare;
	}

}