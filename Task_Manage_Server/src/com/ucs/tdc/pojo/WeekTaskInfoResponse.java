package com.ucs.tdc.pojo;

import java.util.List;

public class WeekTaskInfoResponse {
	private List<Weektask> weektaskList;
	private int totalPage;
	public List<Weektask> getWeektaskList() {
		return weektaskList;
	}
	public void setWeektaskList(List<Weektask> weektaskList) {
		this.weektaskList = weektaskList;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
