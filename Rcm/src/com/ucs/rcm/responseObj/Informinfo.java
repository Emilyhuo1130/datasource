package com.ucs.rcm.responseObj;

public class Informinfo {
	private int id;
	private String jobNumber;//工单号
	private String operator;//操作员
	private String starTime;//开始时间
	private String LineNo;//线号
	private String stationName;//站名
	private String systemName;//所属系统
	private String subSystemName;//子系统
	private String equipmentName;//资产名称
	private String equipmentId;//资产ID
	private String warningTypeLevel;//告警等级
	private String healthLevel;//健康等级
	private String fultDescription;//故障描述
	private String faultCause;//故障原因
	private String influence;//故障影响
	private String mainTenancePolicy;//维修优化
	private String equipmentDescription;//部件描述
	private String warningType;//告警类型
	
	public String getWarningType() {
		return warningType;
	}
	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
	public String getEquipmentDescription() {
		return equipmentDescription;
	}
	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getStarTime() {
		return starTime;
	}
	public void setStarTime(String starTime) {
		this.starTime = starTime;
	}
	public String getLineNo() {
		return LineNo;
	}
	public void setLineNo(String lineNo) {
		LineNo = lineNo;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getSubSystemName() {
		return subSystemName;
	}
	public void setSubSystemName(String subSystemName) {
		this.subSystemName = subSystemName;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getWarningTypeLevel() {
		return warningTypeLevel;
	}
	public void setWarningTypeLevel(String warningTypeLevel) {
		this.warningTypeLevel = warningTypeLevel;
	}
	public String getHealthLevel() {
		return healthLevel;
	}
	public void setHealthLevel(String healthLevel) {
		this.healthLevel = healthLevel;
	}
	public String getFultDescription() {
		return fultDescription;
	}
	public void setFultDescription(String fultDescription) {
		this.fultDescription = fultDescription;
	}
	public String getFaultCause() {
		return faultCause;
	}
	public void setFaultCause(String faultCause) {
		this.faultCause = faultCause;
	}
	public String getInfluence() {
		return influence;
	}
	public void setInfluence(String influence) {
		this.influence = influence;
	}
	public String getMainTenancePolicy() {
		return mainTenancePolicy;
	}
	public void setMainTenancePolicy(String mainTenancePolicy) {
		this.mainTenancePolicy = mainTenancePolicy;
	}
	
}
