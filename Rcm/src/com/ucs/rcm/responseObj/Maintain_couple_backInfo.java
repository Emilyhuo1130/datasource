package com.ucs.rcm.responseObj;

public class Maintain_couple_backInfo {
	  private int Id;
	  private String componentId;
	  private String componentName;
	  private String systemName;
	  private String equipmentDescription;
	  private String lineNo;
	  private String  stationName;
	  private String  repairsDate;//报修日期
	  private String changeEquipment;//更换部件
	  private String  maintainInfo;//维修内容
	  private String  maintainResult;//维修结果
	  private String  maintainPerson;//维修人
	  private String  checkPerson;//验收人
	  private String remark;//备注
	  private String warningType;
	  //新增字段  告警内容 和维修策略
	  private String warningInfo;
	  private String maintencePolicy;//
	  
	public String getWarningnfo() {
		return warningInfo;
	}
	public void setWarningnfo(String warningnfo) {
		this.warningInfo = warningnfo;
	}
	public String getMaintencePolicy() {
		return maintencePolicy;
	}
	public void setMaintencePolicy(String maintencePolicy) {
		this.maintencePolicy = maintencePolicy;
	}
	public String getWarningType() {
		return warningType;
	}
	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
	public String getChangeEquipment() {
		return changeEquipment;
	}
	public void setChangeEquipment(String changeEquipment) {
		this.changeEquipment = changeEquipment;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getEquipmentDescription() {
		return equipmentDescription;
	}
	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
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
	public String getRepairsDate() {
		return repairsDate;
	}
	public void setRepairsDate(String repairsDate) {
		this.repairsDate = repairsDate;
	}
	public String getMaintainInfo() {
		return maintainInfo;
	}
	public void setMaintainInfo(String maintainInfo) {
		this.maintainInfo = maintainInfo;
	}
	public String getMaintainResult() {
		return maintainResult;
	}
	public void setMaintainResult(String maintainResult) {
		this.maintainResult = maintainResult;
	}
	public String getMaintainPerson() {
		return maintainPerson;
	}
	public void setMaintainPerson(String maintainPerson) {
		this.maintainPerson = maintainPerson;
	}
	public String getCheckPerson() {
		return checkPerson;
	}
	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	  
}
