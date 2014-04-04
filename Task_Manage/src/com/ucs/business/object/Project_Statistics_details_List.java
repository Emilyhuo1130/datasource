package com.ucs.business.object;

import java.util.List;

public class Project_Statistics_details_List {
	private List<Project_Statistics_details> detailsList;//详情集合
	private float total;//总计
	private int totalpages;
	public List<Project_Statistics_details> getdetailsList() {
		return detailsList;
	}
	public void setdetailsList(List<Project_Statistics_details> detailsList) {
		this.detailsList = detailsList;
	}
	public float gettotal() {
		return total;
	}
	public void settotal(float total) {
		this.total = total;
	}
	public int gettotalpages() {
		return totalpages;
	}
	public void settotalpages(int totalpages) {
		this.totalpages = totalpages;
	}

}
