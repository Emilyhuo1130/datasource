package com.ucs.rcm.responseObj;

import java.util.List;

public class RescountPlanWaringList {
	private List<CountPlanWarningstatistics> historyList;
	private String totalCount;//总记录数
	private String totalPage;//总页数
	public List<CountPlanWarningstatistics> getHistoryList() {
		return historyList;
	}
	public void setHistoryList(List<CountPlanWarningstatistics> historyList) {
		this.historyList = historyList;
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
