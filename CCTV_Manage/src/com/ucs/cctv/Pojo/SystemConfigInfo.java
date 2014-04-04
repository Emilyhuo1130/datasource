package com.ucs.cctv.Pojo;

public class SystemConfigInfo {
	private double cpuOccupancy;//cpu占有率不得高于这个数
	private double flowRate;//流量进出不得高于这个数
	private double totalDiskIndex;//总磁盘容量不得低于这个数
	private double perDiskIndex;//每块磁盘的容量不得低于这个数
	
	
	
	public double getCpuOccupancy() {
		return cpuOccupancy;
	}
	public void setCpuOccupancy(double cpuOccupancy) {
		this.cpuOccupancy = cpuOccupancy;
	}
	public double getFlowRate() {
		return flowRate;
	}
	public void setFlowRate(double flowRate) {
		this.flowRate = flowRate;
	}
	public double getTotalDiskIndex() {
		return totalDiskIndex;
	}
	public void setTotalDiskIndex(double totalDiskIndex) {
		this.totalDiskIndex = totalDiskIndex;
	}
	public double getPerDiskIndex() {
		return perDiskIndex;
	}
	public void setPerDiskIndex(double perDiskIndex) {
		this.perDiskIndex = perDiskIndex;
	}
	
}
