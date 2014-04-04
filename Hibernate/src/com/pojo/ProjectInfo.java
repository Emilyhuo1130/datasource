package com.pojo;

import java.util.Date;

/**
 * ProjectInfo entity. @author MyEclipse Persistence Tools
 */

public class ProjectInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String projectName;
	private String projectState;
	private String projectType;
	private String projectRemark;
	private Date createProjectDate;

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