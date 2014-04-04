package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * InformUser4 entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class InformUser4 implements java.io.Serializable {

	// Fields

	private Integer id;
	private String jobNumber;
	private String operator;
	private String starTime;
	private String lineNo;
	private String stationName;
	private String systemname;
	private String subSystemname;
	private String equipmentname;
	private String equipmentId;
	private String warningTypeLevel;
	private String healthLevel;
	private String fultDescription;
	private String faultCause;
	private String influence;
	private String maintenancePolicy;
	private String equipmentDescription;
	private String warningType;
	private String statments;
	private Float mileage;

	// Constructors

	/** default constructor */
	public InformUser4() {
	}

	/** minimal constructor */
	public InformUser4(String jobNumber, String starTime, String lineNo,
			String stationName, String systemname, String subSystemname,
			String equipmentname, String equipmentId, String warningTypeLevel,
			String healthLevel, String fultDescription, String faultCause,
			String influence, String maintenancePolicy,
			String equipmentDescription, String warningType, String statments) {
		this.jobNumber = jobNumber;
		this.starTime = starTime;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.systemname = systemname;
		this.subSystemname = subSystemname;
		this.equipmentname = equipmentname;
		this.equipmentId = equipmentId;
		this.warningTypeLevel = warningTypeLevel;
		this.healthLevel = healthLevel;
		this.fultDescription = fultDescription;
		this.faultCause = faultCause;
		this.influence = influence;
		this.maintenancePolicy = maintenancePolicy;
		this.equipmentDescription = equipmentDescription;
		this.warningType = warningType;
		this.statments = statments;
	}

	/** full constructor */
	public InformUser4(String jobNumber, String operator, String starTime,
			String lineNo, String stationName, String systemname,
			String subSystemname, String equipmentname, String equipmentId,
			String warningTypeLevel, String healthLevel,
			String fultDescription, String faultCause, String influence,
			String maintenancePolicy, String equipmentDescription,
			String warningType, String statments, Float mileage) {
		this.jobNumber = jobNumber;
		this.operator = operator;
		this.starTime = starTime;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.systemname = systemname;
		this.subSystemname = subSystemname;
		this.equipmentname = equipmentname;
		this.equipmentId = equipmentId;
		this.warningTypeLevel = warningTypeLevel;
		this.healthLevel = healthLevel;
		this.fultDescription = fultDescription;
		this.faultCause = faultCause;
		this.influence = influence;
		this.maintenancePolicy = maintenancePolicy;
		this.equipmentDescription = equipmentDescription;
		this.warningType = warningType;
		this.statments = statments;
		this.mileage = mileage;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getStarTime() {
		return this.starTime;
	}

	public void setStarTime(String starTime) {
		this.starTime = starTime;
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

	public String getSystemname() {
		return this.systemname;
	}

	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}

	public String getSubSystemname() {
		return this.subSystemname;
	}

	public void setSubSystemname(String subSystemname) {
		this.subSystemname = subSystemname;
	}

	public String getEquipmentname() {
		return this.equipmentname;
	}

	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}

	public String getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getWarningTypeLevel() {
		return this.warningTypeLevel;
	}

	public void setWarningTypeLevel(String warningTypeLevel) {
		this.warningTypeLevel = warningTypeLevel;
	}

	public String getHealthLevel() {
		return this.healthLevel;
	}

	public void setHealthLevel(String healthLevel) {
		this.healthLevel = healthLevel;
	}

	public String getFultDescription() {
		return this.fultDescription;
	}

	public void setFultDescription(String fultDescription) {
		this.fultDescription = fultDescription;
	}

	public String getFaultCause() {
		return this.faultCause;
	}

	public void setFaultCause(String faultCause) {
		this.faultCause = faultCause;
	}

	public String getInfluence() {
		return this.influence;
	}

	public void setInfluence(String influence) {
		this.influence = influence;
	}

	public String getMaintenancePolicy() {
		return this.maintenancePolicy;
	}

	public void setMaintenancePolicy(String maintenancePolicy) {
		this.maintenancePolicy = maintenancePolicy;
	}

	public String getEquipmentDescription() {
		return this.equipmentDescription;
	}

	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}

	public String getWarningType() {
		return this.warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	public String getStatments() {
		return this.statments;
	}

	public void setStatments(String statments) {
		this.statments = statments;
	}

	public Float getMileage() {
		return this.mileage;
	}

	public void setMileage(Float mileage) {
		this.mileage = mileage;
	}

}