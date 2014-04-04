package com.ucs.business.object;

import java.util.Date;

public class ProjectInfo {
	private Integer id;//项目id
	private String projectName="";//项目名称
	private String projectState="";//项目状态
	private String projectRemark="";//备注
	private String projectType="";
	private Date createProjectDate;
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
	public String getprojectState() {
		return projectState;
	}
	public void setprojectState(String projectState) {
		this.projectState = projectState;
	}
	public String getprojectType() {
		return projectType;
	}
	public void setprojectType(String projectType) {
		this.projectType = projectType;
	}

	public String getprojectRemark() {
		return projectRemark;
	}
	public void setprojectRemark(String projectRemark) {
		this.projectRemark = projectRemark;
	}
	public Date getcreateProjectDate() {
		return createProjectDate;
	}
	public void setcreateProjectDate(Date createProjectDate) {
		this.createProjectDate = createProjectDate;
	}


}
