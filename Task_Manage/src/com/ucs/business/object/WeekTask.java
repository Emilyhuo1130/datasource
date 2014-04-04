package com.ucs.business.object;

import java.util.Date;

public class WeekTask {
	private Integer id;
	private String projectName;//项目名称
	private String userAccount;//账号
	private String weekTask;//周计划任务
	private String weekNo;//周号
	private Date updateTime;//时间
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getWeekTask() {
		return weekTask;
	}
	public void setWeekTask(String weekTask) {
		this.weekTask = weekTask;
	}
	public String getWeekNo() {
		return weekNo;
	}
	public void setWeekNo(String weekNo) {
		this.weekNo = weekNo;
	}


}
