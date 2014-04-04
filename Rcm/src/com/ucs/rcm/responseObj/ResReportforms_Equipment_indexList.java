package com.ucs.rcm.responseObj;

import java.util.List;

public class ResReportforms_Equipment_indexList {
	private List<Reportforms_Equipment_indexListInfo> infoList;
	private String totalCount;//总记录数
	private String totalPage;//总页数
	public List<Reportforms_Equipment_indexListInfo> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<Reportforms_Equipment_indexListInfo> infoList) {
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
