package com.ucs.rcm.reqobj;

public class PlanWarningQueryObj {
	private String warningTypeLevel;//警告类型
	private String systemName;//所属系统
	private String lineNo;//线路号
	private String stationName;//站点
	private String equipmentname;//资产名称
	private String startDate;//开始日期
	private String endDate;//结束日期
	private String statments;
	
	
	
	
	public String getStatments() {
		return statments;
	}
	public void setStatments(String statments) {
		this.statments = statments;
	}
	public String getWarningTypeLevel() {
		return warningTypeLevel;
	}
	public void setWarningTypeLevel(String warningTypeLevel) {
		this.warningTypeLevel = warningTypeLevel;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
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
	public String getEquipmentname() {
		return equipmentname;
	}
	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
