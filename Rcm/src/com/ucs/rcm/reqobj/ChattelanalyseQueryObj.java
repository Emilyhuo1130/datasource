package com.ucs.rcm.reqobj;
/**实件分析传入参数*/
public class ChattelanalyseQueryObj {
	
	private String equipmentname;//资产名称
	private String systemName;//所属系统
	private String lineNo;//线号
	private String stationName;
	private String statments;//状态  
	private String healthIndex;//健康指数
	private String startDate;//开始日期
	private String endDate;//结束日期

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
	
	public String getStatments() {
		return statments;
	}
	public void setStatments(String statments) {
		this.statments = statments;
	}
	
	public String getHealthIndex() {
		return healthIndex;
	}
	public void setHealthIndex(String healthIndex) {
		this.healthIndex = healthIndex;
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
