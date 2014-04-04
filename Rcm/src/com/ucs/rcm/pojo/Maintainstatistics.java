package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * Maintainstatistics entity. @author MyEclipse Persistence Tools
 */

public class Maintainstatistics implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String systemName;
	private String equipmentName;
	private String noFaultDays;
	private String statisticsDays;
	private String statisticsInterval;
	private String realityMistiming;
	private String month;
	private String updateDay;

	// Constructors

	/** default constructor */
	public Maintainstatistics() {
	}

	/** full constructor */
	public Maintainstatistics(String systemName, String equipmentName,
			String noFaultDays, String statisticsDays,
			String statisticsInterval, String realityMistiming, String month,
			String updateDay) {
		this.systemName = systemName;
		this.equipmentName = equipmentName;
		this.noFaultDays = noFaultDays;
		this.statisticsDays = statisticsDays;
		this.statisticsInterval = statisticsInterval;
		this.realityMistiming = realityMistiming;
		this.month = month;
		this.updateDay = updateDay;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getEquipmentName() {
		return this.equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getNoFaultDays() {
		return this.noFaultDays;
	}

	public void setNoFaultDays(String noFaultDays) {
		this.noFaultDays = noFaultDays;
	}

	public String getStatisticsDays() {
		return this.statisticsDays;
	}

	public void setStatisticsDays(String statisticsDays) {
		this.statisticsDays = statisticsDays;
	}

	public String getStatisticsInterval() {
		return this.statisticsInterval;
	}

	public void setStatisticsInterval(String statisticsInterval) {
		this.statisticsInterval = statisticsInterval;
	}

	public String getRealityMistiming() {
		return this.realityMistiming;
	}

	public void setRealityMistiming(String realityMistiming) {
		this.realityMistiming = realityMistiming;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getUpdateDay() {
		return this.updateDay;
	}

	public void setUpdateDay(String updateDay) {
		this.updateDay = updateDay;
	}

}