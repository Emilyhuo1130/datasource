package com.ucs.rcm.responseObj;

import java.util.List;

public class ResOperateStatisticsList {
	private String totalCount;//总记录数
	private String totalPage;//总页数
	private List<OperateStatistics> operateStatisticsList;
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
	public List<OperateStatistics> getOperateStatisticsList() {
		return operateStatisticsList;
	}
	public void setOperateStatisticsList(
			List<OperateStatistics> operateStatisticsList) {
		this.operateStatisticsList = operateStatisticsList;
	}
	
	
	
	
	
}
