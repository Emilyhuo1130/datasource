package com.ucs.rcm.responseObj;

import java.util.List;

public class Res_plan_tableList {
	private List<Plan_table> Plan_tableList;
	private String totalCount;//总记录数
	private String totalPage;//总页数
	public List<Plan_table> getPlan_tableList() {
		return Plan_tableList;
	}
	public void setPlan_tableList(List<Plan_table> plan_tableList) {
		Plan_tableList = plan_tableList;
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
