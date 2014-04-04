package com.ucs.rcm.reqobj;

import com.ucs.rcm.business.bo.Page;

public class ReqgetReportforms_requisition {
	private Page page;//page
	private Reportforms_requisitionQueryObj query;//req信息
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Reportforms_requisitionQueryObj getQuery() {
		return query;
	}
	public void setQuery(Reportforms_requisitionQueryObj query) {
		this.query = query;
	}
	
}
