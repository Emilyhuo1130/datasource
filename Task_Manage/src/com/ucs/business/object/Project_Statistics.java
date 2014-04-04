package com.ucs.business.object;

public class Project_Statistics {
	private String projectName="";//项目名
	private String starTime;//开始时间
	private String endTime;//结束时间
	private Float useDays;//工时统计
	public String getprojectName() {
		return projectName;
	}
	public void setprojectName(String projectName) {
		this.projectName = projectName;
	}
	public String getstarTime() {
		return starTime;
	}
	public void setstartTime(String starTime) {
		this.starTime = starTime;
	}
	public String getendTime() {
		return endTime;
	}
	public void setendTime(String endTime) {
		this.endTime = endTime;
	}
	public Float getuseDays() {
		return useDays;
	}
	public void setuseDays(Float useDays) {
		this.useDays = useDays;
	}

}
