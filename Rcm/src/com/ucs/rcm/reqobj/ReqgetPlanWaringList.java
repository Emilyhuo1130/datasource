package com.ucs.rcm.reqobj;

import com.ucs.rcm.business.bo.Page;

public class ReqgetPlanWaringList {
	private Page page;//page
	private PlanWarningQueryObj query;//req信息
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public PlanWarningQueryObj getQuery() {
		return query;
	}
	public void setQuery(PlanWarningQueryObj query) {
		this.query = query;
	}
	
}
