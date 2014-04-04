package com.ucs.rcm.responseObj;

import java.util.List;

public class ResInformList {
	private List<Informinfo> informList;
	private String totalCount;//总记录数
	private String totalPage;//总页数

	public List<Informinfo> getInformList() {
		return informList;
	}

	public void setInformList(List<Informinfo> informList) {
		this.informList = informList;
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
