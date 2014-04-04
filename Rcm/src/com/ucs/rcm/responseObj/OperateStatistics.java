package com.ucs.rcm.responseObj;

public class OperateStatistics {
	private int id;
	private String lineNo;
	private String safetyDays;//安全运营天数
	private String offstreamDays;//强迫停运天数
	private String noPlanoffstreamDays;//非计划停运天数
	private String month;//月份
	private String updateDay;//更新时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getSafetyDays() {
		return safetyDays;
	}
	public void setSafetyDays(String safetyDays) {
		this.safetyDays = safetyDays;
	}
	public String getOffstreamDays() {
		return offstreamDays;
	}
	public void setOffstreamDays(String offstreamDays) {
		this.offstreamDays = offstreamDays;
	}
	public String getNoPlanoffstreamDays() {
		return noPlanoffstreamDays;
	}
	public void setNoPlanoffstreamDays(String noPlanoffstreamDays) {
		this.noPlanoffstreamDays = noPlanoffstreamDays;
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
