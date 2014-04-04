package com.ucs.tdc.pojo;
/***项目统计*/
public class Project_Statistics {
	private String projectName;//项目名
	private String starTime;//
	private String  endTime;//
	private Float useDays;//共用了多少天
	
	

	public Float getUseDays() {
		return useDays;
	}
	public void setUseDays(Float useDays) {
		this.useDays = useDays;
	}
	public String getStarTime() {
		return starTime;
	}
	public void setStarTime(String starTime) {
		this.starTime = starTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
	
}
