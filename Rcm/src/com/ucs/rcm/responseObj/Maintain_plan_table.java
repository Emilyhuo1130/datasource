package com.ucs.rcm.responseObj;
//计划修设备规程信息
public class Maintain_plan_table {
	  private int Id;
	  private String equipmentId;
	  private String equipmentname;
	  private String subsystemName;
	  private String systemName;
	  private String lineNo;
	  private String stationName;
	  private String warningType;
	  private String warningTypeLevel;
	  private String maintaininfo;
	  private String lastTime_maintainDate;
	  private String productDate;
	  private String next_maintainDate;
	  private String statments;
	  private String maintain_period;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentname() {
		return equipmentname;
	}
	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}
	public String getSubsystemName() {
		return subsystemName;
	}
	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
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
	public String getWarningType() {
		return warningType;
	}
	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
	public String getWarningTypeLevel() {
		return warningTypeLevel;
	}
	public void setWarningTypeLevel(String warningTypeLevel) {
		this.warningTypeLevel = warningTypeLevel;
	}
	public String getMaintaininfo() {
		return maintaininfo;
	}
	public void setMaintaininfo(String maintaininfo) {
		this.maintaininfo = maintaininfo;
	}
	public String getLastTime_maintainDate() {
		return lastTime_maintainDate;
	}
	public void setLastTime_maintainDate(String lastTime_maintainDate) {
		this.lastTime_maintainDate = lastTime_maintainDate;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	public String getNext_maintainDate() {
		return next_maintainDate;
	}
	public void setNext_maintainDate(String next_maintainDate) {
		this.next_maintainDate = next_maintainDate;
	}
	public String getStatments() {
		return statments;
	}
	public void setStatments(String statments) {
		this.statments = statments;
	}
	public String getMaintain_period() {
		return maintain_period;
	}
	public void setMaintain_period(String maintain_period) {
		this.maintain_period = maintain_period;
	}
	  
}
