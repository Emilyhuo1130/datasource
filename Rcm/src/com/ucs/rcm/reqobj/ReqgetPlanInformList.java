package com.ucs.rcm.reqobj;

import com.ucs.rcm.business.bo.Page;

public class ReqgetPlanInformList {
	private Page page;//page
	private PlanInformQueryObj query;//req信息
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public PlanInformQueryObj getQuery() {
		return query;
	}
	public void setQuery(PlanInformQueryObj query) {
		this.query = query;
	}
	
}
