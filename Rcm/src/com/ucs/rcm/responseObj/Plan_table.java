package com.ucs.rcm.responseObj;

public class Plan_table {
	  private String equipmentId;
	  private String equipmentname;
	  private String systemName;
	  private String lineNo;//线
	  private String stationName;//车站名
	  private String warningTypeLevel;//维修等级  小修 中修 大修
	  private String maintaininfo;//维修内容
	  private String lastTime_maintainDate;//下次维修时间
	  private String productDate;//
	  private String maintain_period;//维修周期
	  
	  
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
	public String getMaintain_period() {
		return maintain_period;
	}
	public void setMaintain_period(String maintain_period) {
		this.maintain_period = maintain_period;
	}
	  
}
