package com.ucs.rcm.responseObj;

import java.util.List;



public class ResPlanWaringList {
	private List<PlanWarning> planWarningList;//所有的警告信息
	private String totalCount;//总记录数
	private String totalPage;//总页数
	public List<PlanWarning> getPlanWarningList() {
		return planWarningList;
	}
	public void setPlanWarningList(List<PlanWarning> planWarningList) {
		this.planWarningList = planWarningList;
	}
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
	
}
