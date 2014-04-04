/**磁盘容量*/
package com.ucs.cctv.Response;

import java.util.List;

import com.ucs.cctv.Pojo.DiskInfo;

public class DiskSizeResponse {

	List<DiskInfo> diskList;
	private int totalPage;
	private double totalSurplusCapacity;
    private double perDiskCapacityLess;

	
	public double getPerDiskCapacityLess() {
		return perDiskCapacityLess;
	}
	public void setPerDiskCapacityLess(double perDiskCapacityLess) {
		this.perDiskCapacityLess = perDiskCapacityLess;
	}
	public double getTotalSurplusCapacity() {
		return totalSurplusCapacity;
	}
	public void setTotalSurplusCapacity(double totalSurplusCapacity) {
		this.totalSurplusCapacity = totalSurplusCapacity;
	}
	public List<DiskInfo> getDiskList() {
		return diskList;
	}
	public void setDiskList(List<DiskInfo> diskList) {
		this.diskList = diskList;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
