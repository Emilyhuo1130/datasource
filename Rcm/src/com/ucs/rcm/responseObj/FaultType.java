package com.ucs.rcm.responseObj;

public class FaultType {
	private int id;
	private String componentid; //equipmentTree表中component的id
	private String equipmentName;//资产名称
	private String equipmentId;//资产id
	private String systemName;//所属系统
	private String equipmentDescription;//详情
	private String type;//告警类型
	private String fultDescription;//故障描述
	private String faultCause;//故障原因
	private String influence;//运营影响
	private String maintenancePolicy;//维修策略
	private String subcomponent;//部件名称
	
	
	
	public String getSubcomponent() {
		return subcomponent;
	}
	public void setSubcomponent(String subcomponent) {
		this.subcomponent = subcomponent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getComponentid() {
		return componentid;
	}
	public void setComponentid(String componentid) {
		this.componentid = componentid;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentDescription() {
		return equipmentDescription;
	}
	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}
	public String getFultDescription() {
		return fultDescription;
	}
	public void setFultDescription(String fultDescription) {
		this.fultDescription = fultDescription;
	}
	public String getFaultCause() {
		return faultCause;
	}
	public void setFaultCause(String faultCause) {
		this.faultCause = faultCause;
	}
	public String getInfluence() {
		return influence;
	}
	public void setInfluence(String influence) {
		this.influence = influence;
	}
	public String getMaintenancePolicy() {
		return maintenancePolicy;
	}
	public void setMaintenancePolicy(String maintenancePolicy) {
		this.maintenancePolicy = maintenancePolicy;
	}
	
	
}
