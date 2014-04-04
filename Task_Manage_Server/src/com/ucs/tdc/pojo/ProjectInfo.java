package com.ucs.tdc.pojo;

import java.util.Date;

/**
 * ProjectInfo entity. @author MyEclipse Persistence Tools
 */

public class ProjectInfo implements java.io.Serializable {
	private Integer id;
	private String projectName;//项目名称
	private String projectState;//项目状态
	private String projectType;//项目类型
	private String projectRemark;//备注
	private Date createProjectDate;//创建项目的时间

	// Constructors

	/** default constructor */
	public ProjectInfo() {
	}

	/** minimal constructor */
	public ProjectInfo(String projectName, String projectState,
			String projectRemark) {
		this.projectName = projectName;
		this.projectState = projectState;
		this.projectRemark = projectRemark;
	}

	/** full constructor */
	public ProjectInfo(String projectName, String projectState,
			String projectType, String projectRemark, Date createProjectDate) {
		this.projectName = projectName;
		this.projectState = projectState;
		this.projectType = projectType;
		this.projectRemark = projectRemark;
		this.createProjectDate = createProjectDate;
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

	public String getProjectState() {
		return this.projectState;
	}

	public void setProjectState(String projectState) {
		this.projectState = projectState;
	}

	public String getProjectType() {
		return this.projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectRemark() {
		return this.projectRemark;
	}

	public void setProjectRemark(String projectRemark) {
		this.projectRemark = projectRemark;
	}

	public Date getCreateProjectDate() {
		return this.createProjectDate;
	}

	public void setCreateProjectDate(Date createProjectDate) {
		this.createProjectDate = createProjectDate;
	}

}