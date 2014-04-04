package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * MaintainPlanTable entity. @author MyEclipse Persistence Tools
 */

public class MaintainPlanTable implements java.io.Serializable {

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
	private String maintaininfo;
	private String lastTimeMaintainDate;
	private String productDate;
	private String nextMaintainDate;
	private String maintainPeriod;

	// Constructors

	/** default constructor */
	public MaintainPlanTable() {
	}

	/** full constructor */
	public MaintainPlanTable(String equipmentId, String equipmentname,
			String subsystemName, String systemName, String lineNo,
			String stationName, String warningType, String warningTypeLevel,
			String maintaininfo, String lastTimeMaintainDate,
			String productDate, String nextMaintainDate,
			String maintainPeriod) {
		this.equipmentId = equipmentId;
		this.equipmentname = equipmentname;
		this.subsystemName = subsystemName;
		this.systemName = systemName;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.warningType = warningType;
		this.warningTypeLevel = warningTypeLevel;
		this.maintaininfo = maintaininfo;
		this.lastTimeMaintainDate = lastTimeMaintainDate;
		this.productDate = productDate;
		this.nextMaintainDate = nextMaintainDate;
		this.maintainPeriod = maintainPeriod;
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

	public String getMaintaininfo() {
		return this.maintaininfo;
	}

	public void setMaintaininfo(String maintaininfo) {
		this.maintaininfo = maintaininfo;
	}

	public String getLastTimeMaintainDate() {
		return this.lastTimeMaintainDate;
	}

	public void setLastTimeMaintainDate(String lastTimeMaintainDate) {
		this.lastTimeMaintainDate = lastTimeMaintainDate;
	}

	public String getProductDate() {
		return this.productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	public String getNextMaintainDate() {
		return this.nextMaintainDate;
	}

	public void setNextMaintainDate(String nextMaintainDate) {
		this.nextMaintainDate = nextMaintainDate;
	}

	public String getMaintainPeriod() {
		return this.maintainPeriod;
	}

	public void setMaintainPeriod(String maintainPeriod) {
		this.maintainPeriod = maintainPeriod;
	}

}