package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * MaintainCoupleBack entity. @author MyEclipse Persistence Tools
 */

public class MaintainCoupleBack implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private Integer id;//0
	private String componentId;//部件id
	private String componentName;//资产名称 0
	private String systemName;//系统名称
	private String equipmentDescription;//设备详情0
	private String lineNo;//线号0
	private String stationName;//车站名称 0
	private String repairsDate;//维修日期0
	private String changeEquipment;//更换部件0
	
	private String maintainInfo;//维修内容0
	private String maintainResult;//维修结果0
	private String maintencePolicy;//维修策略0
	
	private String maintainPerson;//维修人员0
	private String checkPerson;//检验人员0
	private String remark;//备注0
	private String warningType;//告警类型 0
	//新增字段 
	private String warningInfo;//告警内容0
	private String warningTypeLevel;//告警等级
	private String warningDate;//告警时间
	private String maintenceCode;//维修建议单号 1
	private String faultCause;//故障可能原因 1
	private String opinions;//审定意见1
	private String faultImpact;//故障影响1  23
	
	
	
	public String getWarningTypeLevel() {
		return warningTypeLevel;
	}

	public void setWarningTypeLevel(String warningTypeLevel) {
		this.warningTypeLevel = warningTypeLevel;
	}

	public String getWarningDate() {
		return warningDate;
	}

	public void setWarningDate(String warningDate) {
		this.warningDate = warningDate;
	}
	
	public String getFaultImpact() {
		return faultImpact;
	}

	public void setFaultImpact(String faultImpact) {
		this.faultImpact = faultImpact;
	}
	
	// Constructors

	public String getOpinions() {
		return opinions;
	}

	public void setOpinions(String opinions) {
		this.opinions = opinions;
	}

	public String getFaultCause() {
		return faultCause;
	}

	public void setFaultCause(String faultCause) {
		this.faultCause = faultCause;
	}

	public String getMaintenceCode() {
		return maintenceCode;
	}

	public void setMaintenceCode(String maintenceCode) {
		this.maintenceCode = maintenceCode;
	}

	public String getWarningInfo() {
		return warningInfo;
	}

	public void setWarningInfo(String warningInfo) {
		this.warningInfo = warningInfo;
	}

	public String getMaintencePolicy() {
		return maintencePolicy;
	}

	public void setMaintencePolicy(String maintencePolicy) {
		this.maintencePolicy = maintencePolicy;
	}

	/** default constructor */
	public MaintainCoupleBack() {
	}

	/** full constructor */
	public MaintainCoupleBack(String componentId, String componentName,
			String systemName, String equipmentDescription, String lineNo,
			String stationName, String repairsDate, String changeEquipment,
			String maintainInfo, String maintainResult, String maintainPerson,
			String checkPerson, String remark, String warningType) {
		this.componentId = componentId;
		this.componentName = componentName;
		this.systemName = systemName;
		this.equipmentDescription = equipmentDescription;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.repairsDate = repairsDate;
		this.changeEquipment = changeEquipment;
		this.maintainInfo = maintainInfo;
		this.maintainResult = maintainResult;
		this.maintainPerson = maintainPerson;
		this.checkPerson = checkPerson;
		this.remark = remark;
		this.warningType = warningType;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComponentId() {
		return this.componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentName() {
		return this.componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getEquipmentDescription() {
		return this.equipmentDescription;
	}

	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
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

	public String getRepairsDate() {
		return this.repairsDate;
	}

	public void setRepairsDate(String repairsDate) {
		this.repairsDate = repairsDate;
	}

	public String getChangeEquipment() {
		return this.changeEquipment;
	}

	public void setChangeEquipment(String changeEquipment) {
		this.changeEquipment = changeEquipment;
	}

	public String getMaintainInfo() {
		return this.maintainInfo;
	}

	public void setMaintainInfo(String maintainInfo) {
		this.maintainInfo = maintainInfo;
	}

	public String getMaintainResult() {
		return this.maintainResult;
	}

	public void setMaintainResult(String maintainResult) {
		this.maintainResult = maintainResult;
	}

	public String getMaintainPerson() {
		return this.maintainPerson;
	}

	public void setMaintainPerson(String maintainPerson) {
		this.maintainPerson = maintainPerson;
	}

	public String getCheckPerson() {
		return this.checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWarningType() {
		return this.warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

}