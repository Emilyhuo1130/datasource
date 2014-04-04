package com.ucs.rcm.responseObj;

import java.util.List;

public class ResReportforms_requisition {
	
	
	private String totalCount;//总记录数
	private String totalPage;//总页数
	private List<Reportforms_requisitionInfo> requisitionList;
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
	public List<Reportforms_requisitionInfo> getRequisitionList() {
		return requisitionList;
	}
	public void setRequisitionList(List<Reportforms_requisitionInfo> requisitionList) {
		this.requisitionList = requisitionList;
	}
	
}
