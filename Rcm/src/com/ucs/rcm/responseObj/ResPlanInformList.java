package com.ucs.rcm.responseObj;

import java.util.List;

import com.ucs.rcm.pojo.InformPlan;

public class ResPlanInformList {
	private List<InformPlan> planinformList;
	private String totalCount;//总记录数
	private String totalPage;//总页数
	public List<InformPlan> getPlaninformList() {
		return planinformList;
	}
	public void setPlaninformList(List<InformPlan> planinformList) {
		this.planinformList = planinformList;
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
