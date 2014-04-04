package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * InformPlan entity. @author MyEclipse Persistence Tools
 */

public class InformPlan implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String maintainId;
	private String equipmentId;
	private String equipmentname;
	private String subsystemName;
	private String systemName;
	private String lineNo;
	private String stationName;
	private String warningType;
	private String warningTypeLevel;
	private String maintaininfo;
	private String nextMaintainDate;
	private String statments;
	private String maintainPeriod;

	// Constructors

	/** default constructor */
	public InformPlan() {
	}

	/** full constructor */
	public InformPlan(String maintainId, String equipmentId,
			String equipmentname, String subsystemName, String systemName,
			String lineNo, String stationName, String warningType,
			String warningTypeLevel, String maintaininfo,
			String nextMaintainDate, String statments, String maintainPeriod) {
		this.maintainId = maintainId;
		this.equipmentId = equipmentId;
		this.equipmentname = equipmentname;
		this.subsystemName = subsystemName;
		this.systemName = systemName;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.warningType = warningType;
		this.warningTypeLevel = warningTypeLevel;
		this.maintaininfo = maintaininfo;
		this.nextMaintainDate = nextMaintainDate;
		this.statments = statments;
		this.maintainPeriod = maintainPeriod;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMaintainId() {
		return this.maintainId;
	}

	public void setMaintainId(String maintainId) {
		this.maintainId = maintainId;
	}

	public String getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentname() {
		return this.equipmentname;
	}

	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}

	public String getSubsystemName() {
		return this.subsystemName;
	}

	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
	}

	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
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

	public String getMaintaininfo() {
		return this.maintaininfo;
	}

	public void setMaintaininfo(String maintaininfo) {
		this.maintaininfo = maintaininfo;
	}

	public String getNextMaintainDate() {
		return this.nextMaintainDate;
	}

	public void setNextMaintainDate(String nextMaintainDate) {
		this.nextMaintainDate = nextMaintainDate;
	}

	public String getStatments() {
		return this.statments;
	}

	public void setStatments(String statments) {
		this.statments = statments;
	}

	public String getMaintainPeriod() {
		return this.maintainPeriod;
	}

	public void setMaintainPeriod(String maintainPeriod) {
		this.maintainPeriod = maintainPeriod;
	}

}