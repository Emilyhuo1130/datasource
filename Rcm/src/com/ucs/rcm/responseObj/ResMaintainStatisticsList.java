package com.ucs.rcm.responseObj;

import java.util.List;

public class ResMaintainStatisticsList {
	private String totalCount;//总记录数
	private String totalPage;//总页数
	private List<MaintainStatistics> maintainStatisticsList;
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public List<MaintainStatistics> getMaintainStatisticsList() {
		return maintainStatisticsList;
	}
	public void setMaintainStatisticsList(
			List<MaintainStatistics> maintainStatisticsList) {
		this.maintainStatisticsList = maintainStatisticsList;
	}
	
	
}
