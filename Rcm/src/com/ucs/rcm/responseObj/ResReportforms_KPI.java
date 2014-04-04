package com.ucs.rcm.responseObj;

import java.util.List;

public class ResReportforms_KPI {
	
	private List<ResReportforms_KPI_Info> infoList;
	private String totalCount;//总记录数
	private String totalPage;//总页数
	public List<ResReportforms_KPI_Info> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<ResReportforms_KPI_Info> infoList) {
		this.infoList = infoList;
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
