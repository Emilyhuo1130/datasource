package com.ucs.rcm.responseObj;

public class ResReportforms_KPI_Info {
	private int id;
	private String systemName;
	private String subsystemName;
	private String lineNo;
	private String stationName;
	private String component;
	private String safety_production_days;//安全生产天数
	private String force_stop_days;//强迫停运天数
	private String noplan_stop_days;//非计划停运天数
	private String no_fault_days;//无故障时间
	private String key_equipment_repair_time;//关键设备平均修复时间
	private String key_technology_repair_time;//平均技术对象平局修复时间
	private String physical_variance;//计划维修时间与实际差
	private String updateTime;//更新时间
	
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getSafety_production_days() {
		return safety_production_days;
	}

	public void setSafety_production_days(String safety_production_days) {
		this.safety_production_days = safety_production_days;
	}

	public String getForce_stop_days() {
		return force_stop_days;
	}

	public void setForce_stop_days(String force_stop_days) {
		this.force_stop_days = force_stop_days;
	}

	public String getNoplan_stop_days() {
		return noplan_stop_days;
	}

	public void setNoplan_stop_days(String noplan_stop_days) {
		this.noplan_stop_days = noplan_stop_days;
	}

	public String getNo_fault_days() {
		return no_fault_days;
	}

	public void setNo_fault_days(String no_fault_days) {
		this.no_fault_days = no_fault_days;
	}

	public String getKey_equipment_repair_time() {
		return key_equipment_repair_time;
	}

	public void setKey_equipment_repair_time(String key_equipment_repair_time) {
		this.key_equipment_repair_time = key_equipment_repair_time;
	}

	public String getKey_technology_repair_time() {
		return key_technology_repair_time;
	}

	public void setKey_technology_repair_time(String key_technology_repair_time) {
		this.key_technology_repair_time = key_technology_repair_time;
	}

	public String getPhysical_variance() {
		return physical_variance;
	}

	public void setPhysical_variance(String physical_variance) {
		this.physical_variance = physical_variance;
	}
	
	
	
}
