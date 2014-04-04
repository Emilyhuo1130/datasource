package com.ucs.rcm.responseObj;

import java.util.List;

import com.ucs.rcm.pojo.MaintainCoupleBack;

public class Res_maintain_couple_back {
	
	  
	  private List<MaintainCoupleBack> backInfoList;
	  private String totalCount;//总记录数
	  private String totalPage;//总页数
	public List<MaintainCoupleBack> getBackInfoList() {
		return backInfoList;
	}
	public void setBackInfoList(List<MaintainCoupleBack> backInfoList) {
		this.backInfoList = backInfoList;
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
