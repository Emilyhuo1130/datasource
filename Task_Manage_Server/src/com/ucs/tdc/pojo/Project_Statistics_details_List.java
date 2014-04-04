package com.ucs.tdc.pojo;

import java.util.List;

public class Project_Statistics_details_List {
	private List<Project_Statistics_details> detailsList;//详情集合
	private Float total;//总计
	private int totalpages;//共几页
	
	public int getTotalpages() {
		return totalpages;
	}
	public void setTotalpages(int totalpages) {
		this.totalpages = totalpages;
	}
	public List<Project_Statistics_details> getDetailsList() {
		return detailsList;
	}
	public void setDetailsList(List<Project_Statistics_details> detailsList) {
		this.detailsList = detailsList;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	
	
}
