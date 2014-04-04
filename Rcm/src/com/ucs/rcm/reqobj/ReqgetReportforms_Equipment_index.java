package com.ucs.rcm.reqobj;

import com.ucs.rcm.business.bo.Page;

public class ReqgetReportforms_Equipment_index {
	private Page page;//page
	private Reportforms_Equipment_indexQueryObj query;//req信息
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Reportforms_Equipment_indexQueryObj getQuery() {
		return query;
	}
	public void setQuery(Reportforms_Equipment_indexQueryObj query) {
		this.query = query;
	}
	
	
}
