package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * FaultinfoUser2 entity. @author MyEclipse Persistence Tools
 */

public class FaultinfoUser2 implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String equipmentid;
	private String equipmentname;
	private String systemname;
	private String warningType;
	private String warningTypeLevel;
	private String fultDescription;
	private String lineNo;
	private String stationName;
	private String warningDate;
	private String faultCause;
	private String influence;
	private String mainTenancePolicy;
	private String operator;
	private String statments;
	private String fromuser;
	private String equipmentDescription;
	private String opinion;
	private String maintenanceId;
	private String warningId;
	private Float mileage;
	private String subsystemName;
	
	
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMainTenancePolicy() {
		return mainTenancePolicy;
	}

	public void setMainTenancePolicy(String mainTenancePolicy) {
		this.mainTenancePolicy = mainTenancePolicy;
	}

	

	// Constructors

	public FaultinfoUser2(String maintenanceId, String subsystemName) {
		super();
		this.maintenanceId = maintenanceId;
		this.subsystemName = subsystemName;
	}

	/** default constructor */
	public FaultinfoUser2() {
	}

	/** minimal constructor */
	public FaultinfoUser2(String equipmentid, String equipmentname,
			String systemname, String warningType, String warningTypeLevel,
			String fultDescription, String lineNo, String stationName,
			String warningDate, String faultCause, String influence,
			String mainTenancePolicy, String statments, String fromuser) {
		this.equipmentid = equipmentid;
		this.equipmentname = equipmentname;
		this.systemname = systemname;
		this.warningType = warningType;
		this.warningTypeLevel = warningTypeLevel;
		this.fultDescription = fultDescription;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.warningDate = warningDate;
		this.faultCause = faultCause;
		this.influence = influence;
		this.mainTenancePolicy = mainTenancePolicy;
		this.statments = statments;
		this.fromuser = fromuser;
	}

	/** full constructor */
	public FaultinfoUser2(String equipmentid, String equipmentname,
			String systemname, String warningType, String warningTypeLevel,
			String fultDescription, String lineNo, String stationName,
			String warningDate, String faultCause, String influence,
			String mainTenancePolicy, String statments, String fromuser,
			String equipmentDescription, String opinion, String maintenanceId,
			String warningId, Float mileage, String subsystemName) {
		this.equipmentid = equipmentid;
		this.equipmentname = equipmentname;
		this.systemname = systemname;
		this.warningType = warningType;
		this.warningTypeLevel = warningTypeLevel;
		this.fultDescription = fultDescription;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.warningDate = warningDate;
		this.faultCause = faultCause;
		this.influence = influence;
		this.mainTenancePolicy = mainTenancePolicy;
		this.statments = statments;
		this.fromuser = fromuser;
		this.equipmentDescription = equipmentDescription;
		this.opinion = opinion;
		this.maintenanceId = maintenanceId;
		this.warningId = warningId;
		this.mileage = mileage;
		this.subsystemName = subsystemName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEquipmentid() {
		return this.equipmentid;
	}

	public void setEquipmentid(String equipmentid) {
		this.equipmentid = equipmentid;
	}

	public String getEquipmentname() {
		return this.equipmentname;
	}

	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}

	public String getSystemname() {
		return this.systemname;
	}

	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}

	public String getWarningType() {
		return this.warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	public String getWarningTypeLevel() {
		return this.warningTypeLevel;
	}

	public void setWarningTypeLevel(String warningTypeLevel) {
		this.warningTypeLevel = warningTypeLevel;
	}

	public String getFultDescription() {
		return this.fultDescription;
	}

	public void setFultDescription(String fultDescription) {
		this.fultDescription = fultDescription;
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

	public String getWarningDate() {
		return this.warningDate;
	}

	public void setWarningDate(String warningDate) {
		this.warningDate = warningDate;
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

	public String getStatments() {
		return this.statments;
	}

	public void setStatments(String statments) {
		this.statments = statments;
	}

	public String getFromuser() {
		return this.fromuser;
	}

	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}

	public String getEquipmentDescription() {
		return this.equipmentDescription;
	}

	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}

	public String getOpinion() {
		return this.opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getMaintenanceId() {
		return this.maintenanceId;
	}

	public void setMaintenanceId(String maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public String getWarningId() {
		return this.warningId;
	}

	public void setWarningId(String warningId) {
		this.warningId = warningId;
	}

	public Float getMileage() {
		return this.mileage;
	}

	public void setMileage(Float mileage) {
		this.mileage = mileage;
	}

	public String getSubsystemName() {
		return this.subsystemName;
	}

	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
	}

}