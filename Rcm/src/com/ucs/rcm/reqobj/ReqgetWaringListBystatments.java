package com.ucs.rcm.reqobj;

import com.ucs.rcm.business.bo.Page;

public class ReqgetWaringListBystatments {
	private Page page;
	private String statments;
	private String warningType;
	
	

	public String getWarningType() {
		return warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getStatments() {
		return statments;
	}

	public void setStatments(String statments) {
		this.statments = statments;
	}
	
}
