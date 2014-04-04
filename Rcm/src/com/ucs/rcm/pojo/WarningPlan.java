package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * WarningPlan entity. @author MyEclipse Persistence Tools
 */

public class WarningPlan implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String equipmentId;
	private String equipmentname;
	private String subsystemName;
	private String systemName;
	private String lineNo;
	private String stationName;
	private String warningType;
	private String warningTypeLevel;
	private String warninginfo;
	private String warningDate;
	private String statments;

	// Constructors

	/** default constructor */
	public WarningPlan() {
	}

	/** full constructor */
	public WarningPlan(String equipmentId, String equipmentname,
			String subsystemName, String systemName, String lineNo,
			String stationName, String warningType, String warningTypeLevel,
			String warninginfo, String warningDate, String statments) {
		this.equipmentId = equipmentId;
		this.equipmentname = equipmentname;
		this.subsystemName = subsystemName;
		this.systemName = systemName;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.warningType = warningType;
		this.warningTypeLevel = warningTypeLevel;
		this.warninginfo = warninginfo;
		this.warningDate = warningDate;
		this.statments = statments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getWarninginfo() {
		return this.warninginfo;
	}

	public void setWarninginfo(String warninginfo) {
		this.warninginfo = warninginfo;
	}

	public String getWarningDate() {
		return this.warningDate;
	}

	public void setWarningDate(String warningDate) {
		this.warningDate = warningDate;
	}

	public String getStatments() {
		return this.statments;
	}

	public void setStatments(String statments) {
		this.statments = statments;
	}

}