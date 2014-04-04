package com.ucs.rcm.responseObj;

public class MaintainStatistics {
	private int id;
	private String systemName;//所属系统
	private String equipmentName;//资产名称
	private String noFaultDays;//平均无故障时间
	private String statisticsDays;//平均维修时间
	private String statisticsInterval;//平均维修间隔时间
	private String realityMistiming;//计划维修工时数与实际差
	private String month;//月份
	private String updateDay;//更新时间
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
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getNoFaultDays() {
		return noFaultDays;
	}
	public void setNoFaultDays(String noFaultDays) {
		this.noFaultDays = noFaultDays;
	}
	public String getStatisticsDays() {
		return statisticsDays;
	}
	public void setStatisticsDays(String statisticsDays) {
		this.statisticsDays = statisticsDays;
	}
	public String getStatisticsInterval() {
		return statisticsInterval;
	}
	public void setStatisticsInterval(String statisticsInterval) {
		this.statisticsInterval = statisticsInterval;
	}
	public String getRealityMistiming() {
		return realityMistiming;
	}
	public void setRealityMistiming(String realityMistiming) {
		this.realityMistiming = realityMistiming;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getUpdateDay() {
		return updateDay;
	}
	public void setUpdateDay(String updateDay) {
		this.updateDay = updateDay;
	}
	
	
}
