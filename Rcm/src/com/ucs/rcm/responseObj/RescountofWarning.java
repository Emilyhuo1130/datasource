package com.ucs.rcm.responseObj;

import java.util.List;

public class RescountofWarning {
	private List<HistoryWarningstatistics> historyList;
	private String totalCount;//总记录数
	private String totalPage;//总页数
	public List<HistoryWarningstatistics> getHistoryList() {
		return historyList;
	}
	public void setHistoryList(List<HistoryWarningstatistics> historyList) {
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
