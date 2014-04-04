package com.ucs.business.object;

import java.util.Date;

public class TaskParticulars {
	private Integer id;
	private String projectName="";//项目名称
	private String taskDetail="";
	private float finishHouse;
	private String userName="";//姓名 
	private Date commitDare;//填写报告的时间
	private String farmatDate;
	public String getFarmatDate() {
		return farmatDate;
	}
	public void setFarmatDate(String farmatDate) {
		this.farmatDate = farmatDate;
	}
	public Integer getid() {
		return id;
	}
	public void setid(Integer id) {
		this.id = id;
	}
	public String getprojectName() {
		return projectName;
	}
	public void setprojectName(String projectName) {
		this.projectName = projectName;
	}
	public float getfinishHouse() {
		return finishHouse;
	}
	public void setfinishHouse(float finishHouse) {
		this.finishHouse = finishHouse;
	}
	
	public String gettaskDetail() {
		return taskDetail;
	}
	public void settaskDetail(String taskDetail) {
		this.taskDetail = taskDetail;
	}
	public String getuserName() {
		return userName;
	}
	public void setuserName(String userName) {
		this.userName = userName;
	}
	public Date getcommitDare() {
		return commitDare;
	}
	public void setcommitDare(Date commitDare) {
		this.commitDare = commitDare;
	}

}
