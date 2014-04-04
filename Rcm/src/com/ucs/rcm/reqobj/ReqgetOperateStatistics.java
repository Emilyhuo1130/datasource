package com.ucs.rcm.reqobj;

import com.ucs.rcm.business.bo.Page;


public class ReqgetOperateStatistics {
	private String reqString;
	private Page page;
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getReqString() {
		return reqString;
	}

	public void setReqString(String reqString) {
		this.reqString = reqString;
	}
	
	
}
