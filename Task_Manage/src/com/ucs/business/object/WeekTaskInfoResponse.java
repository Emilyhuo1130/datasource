package com.ucs.business.object;

import java.util.List;

public class WeekTaskInfoResponse {
	private List<WeekTask> weektaskList;
	private int totalPage;
	public List<WeekTask> getWeektaskList() {
		return weektaskList;
	}
	public void setWeektaskList(List<WeekTask> weektaskList) {
		this.weektaskList = weektaskList;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
