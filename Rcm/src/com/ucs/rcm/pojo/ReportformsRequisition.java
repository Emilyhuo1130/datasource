package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * ReportformsRequisition entity. @author MyEclipse Persistence Tools
 */

public class ReportformsRequisition implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String componentId;
	private String systemName;
	private String subsystemName;
	private String lineNo;
	private String stationName;
	private String component;
	private String requisitionNo;
	private String requisitionstatments;
	private String sendTime;
	private String maintainTime;
	private String maintainDepartment;
	private String maintainPerson;

	// Constructors

	/** default constructor */
	public ReportformsRequisition() {
	}

	/** minimal constructor */
	public ReportformsRequisition(String sendTime) {
		this.sendTime = sendTime;
	}

	/** full constructor */
	public ReportformsRequisition(String componentId, String systemName,
			String subsystemName, String lineNo, String stationName,
			String component, String requisitionNo,
			String requisitionstatments, String sendTime,
			String maintainTime, String maintainDepartment,
			String maintainPerson) {
		this.componentId = componentId;
		this.systemName = systemName;
		this.subsystemName = subsystemName;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.component = component;
		this.requisitionNo = requisitionNo;
		this.requisitionstatments = requisitionstatments;
		this.sendTime = sendTime;
		this.maintainTime = maintainTime;
		this.maintainDepartment = maintainDepartment;
		this.maintainPerson = maintainPerson;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComponentId() {
		return this.componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSubsystemName() {
		return this.subsystemName;
	}

	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
	}

	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getStationName() {
		return this.stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getComponent() {
		return this.component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getRequisitionNo() {
		return this.requisitionNo;
	}

	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}

	public String getRequisitionstatments() {
		return this.requisitionstatments;
	}

	public void setRequisitionstatments(String requisitionstatments) {
		this.requisitionstatments = requisitionstatments;
	}

	public String getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMaintainTime() {
		return this.maintainTime;
	}

	public void setMaintainTime(String maintainTime) {
		this.maintainTime = maintainTime;
	}

	public String getMaintainDepartment() {
		return this.maintainDepartment;
	}

	public void setMaintainDepartment(String maintainDepartment) {
		this.maintainDepartment = maintainDepartment;
	}

	public String getMaintainPerson() {
		return this.maintainPerson;
	}

	public void setMaintainPerson(String maintainPerson) {
		this.maintainPerson = maintainPerson;
	}

}