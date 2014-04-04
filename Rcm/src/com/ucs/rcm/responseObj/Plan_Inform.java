package com.ucs.rcm.responseObj;

public class Plan_Inform {
	  private int Id;
	  private String maintain_id;
	  private String equipmentId;
	  private String equipmentname;
	  private String subsystemName;
	  private String systemName;
	  private String lineNo;//线
	  private String stationName;//车站名
	  private String warningType;//告警类型  计划预警
	  private String warningTypeLevel;//维修等级  小修 中修 大修
	  private String maintaininfo;//维修内容
	  private String next_maintainDate;//下次维修时间
	  private String statments;//当前状态  是否维修完成  0 未完成 1完成
	  private String maintain_period;//维修周期
	  
	public String getMaintain_id() {
		return maintain_id;
	}
	public void setMaintain_id(String maintain_id) {
		this.maintain_id = maintain_id;
	}
	public String getSubsystemName() {
		return subsystemName;
	}
	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
	}
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
