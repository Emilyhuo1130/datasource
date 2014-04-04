package com.ucs.rcm.responseObj;

public class ResFaultInfo_user2 {
	private int id; //id  和warning里的id相对应
	private String suggest_Number=null;//维修建议单号
	private String equipmentid;//资产编号
	private String equipmentname;//资产名称
	private String systemname;//所属系统
	private String warningType;//告警类型
	private String warningTypeLevel;//告警等级
	private String fultDescription;//告警内容（故障描述）
	private String LineNo;//线号
	private String stationName;//站名
	private String warningDate;//告警日期
	private String faultCause;//故障原因
	private String influence;//故障影响
	private String maintenancePolicy;//维修策略（优化维修）
	private String opinion;//审定意见
	private String statments;//状态
	private String fromuser;//来自哪个用户
	private String maintenanceId;//维保用的资产编码.user4用
	private String subsystemName;//子系统
	
	
	
	public String getSubsystemName() {
		return subsystemName;
	}
	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
	}
	public String getSuggest_Number() {
		return suggest_Number;
	}
	public void setSuggest_Number(String suggest_Number) {
		this.suggest_Number = suggest_Number;
	}
	public String getMaintenanceId() {
		return maintenanceId;
	}
	public void setMaintenanceId(String maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	public String getFromuser() {
		return fromuser;
	}
	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}
	public String getStatments() {
		return statments;
	}
	public void setStatments(String statments) {
		this.statments = statments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEquipmentid() {
		return equipmentid;
	}
	public void setEquipmentid(String equipmentid) {
		this.equipmentid = equipmentid;
	}
	public String getEquipmentname() {
		return equipmentname;
	}
	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}
	public String getSystemname() {
		return systemname;
	}
	public void setSystemname(String systemname) {
		this.systemname = systemname;
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
	public String getFultDescription() {
		return fultDescription;
	}
	public void setFultDescription(String fultDescription) {
		this.fultDescription = fultDescription;
	}
	public String getLineNo() {
		return LineNo;
	}
	public void setLineNo(String lineNo) {
		LineNo = lineNo;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getWarningDate() {
		return warningDate;
	}
	public void setWarningDate(String warningDate) {
		this.warningDate = warningDate;
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
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
}
