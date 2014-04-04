package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * ReportformsKpi entity. @author MyEclipse Persistence Tools
 */

public class ReportformsKpi implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String systemName;
	private String subsystemName;
	private String lineNo;
	private String stationName;
	private String component;
	private String safetyProductionDays;
	private String forceStopDays;
	private String noplanStopDays;
	private String noFaultDays;
	private String keyEquipmentRepairTime;
	private String keyTechnologyRepairTime;
	private String physicalVariance;
	private String updateTime;

	// Constructors

	/** default constructor */
	public ReportformsKpi() {
	}

	/** minimal constructor */
	public ReportformsKpi(String updateTime) {
		this.updateTime = updateTime;
	}

	/** full constructor */
	public ReportformsKpi(String systemName, String subsystemName,
			String lineNo, String stationName, String component,
			String safetyProductionDays, String forceStopDays,
			String noplanStopDays, String noFaultDays,
			String keyEquipmentRepairTime, String keyTechnologyRepairTime,
			String physicalVariance, String updateTime) {
		this.systemName = systemName;
		this.subsystemName = subsystemName;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.component = component;
		this.safetyProductionDays = safetyProductionDays;
		this.forceStopDays = forceStopDays;
		this.noplanStopDays = noplanStopDays;
		this.noFaultDays = noFaultDays;
		this.keyEquipmentRepairTime = keyEquipmentRepairTime;
		this.keyTechnologyRepairTime = keyTechnologyRepairTime;
		this.physicalVariance = physicalVariance;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSafetyProductionDays() {
		return this.safetyProductionDays;
	}

	public void setSafetyProductionDays(String safetyProductionDays) {
		this.safetyProductionDays = safetyProductionDays;
	}

	public String getForceStopDays() {
		return this.forceStopDays;
	}

	public void setForceStopDays(String forceStopDays) {
		this.forceStopDays = forceStopDays;
	}

	public String getNoplanStopDays() {
		return this.noplanStopDays;
	}

	public void setNoplanStopDays(String noplanStopDays) {
		this.noplanStopDays = noplanStopDays;
	}

	public String getNoFaultDays() {
		return this.noFaultDays;
	}

	public void setNoFaultDays(String noFaultDays) {
		this.noFaultDays = noFaultDays;
	}

	public String getKeyEquipmentRepairTime() {
		return this.keyEquipmentRepairTime;
	}

	public void setKeyEquipmentRepairTime(String keyEquipmentRepairTime) {
		this.keyEquipmentRepairTime = keyEquipmentRepairTime;
	}

	public String getKeyTechnologyRepairTime() {
		return this.keyTechnologyRepairTime;
	}

	public void setKeyTechnologyRepairTime(String keyTechnologyRepairTime) {
		this.keyTechnologyRepairTime = keyTechnologyRepairTime;
	}

	public String getPhysicalVariance() {
		return this.physicalVariance;
	}

	public void setPhysicalVariance(String physicalVariance) {
		this.physicalVariance = physicalVariance;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}