package com.ucs.rcm.responseObj;

public class Reportforms_requisitionInfo {
	private int id;
	private String componentId;
	private String systemName;
	private String subsystemName;
	private String lineNo;
	private String stationName;
	private String component;
	private String requisitionNo;//通知单编号
	private String requisitionstatments;//通知单状态
	private String sendTime;//通知单发送时间
	private String maintainTime;//维修时间
	private String maintainDepartment;//维修部门
	private String maintainPerson;//维修人员
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getSubsystemName() {
		return subsystemName;
	}
	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getRequisitionNo() {
		return requisitionNo;
	}
	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}
	public String getRequisitionstatments() {
		return requisitionstatments;
	}
	public void setRequisitionstatments(String requisitionstatments) {
		this.requisitionstatments = requisitionstatments;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getMaintainTime() {
		return maintainTime;
	}
	public void setMaintainTime(String maintainTime) {
		this.maintainTime = maintainTime;
	}
	public String getMaintainDepartment() {
		return maintainDepartment;
	}
	public void setMaintainDepartment(String maintainDepartment) {
		this.maintainDepartment = maintainDepartment;
	}
	public String getMaintainPerson() {
		return maintainPerson;
	}
	public void setMaintainPerson(String maintainPerson) {
		this.maintainPerson = maintainPerson;
	}
	
}
