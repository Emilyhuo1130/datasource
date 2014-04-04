package com.ucs.rcm.reqobj;

import com.ucs.rcm.business.bo.Page;

public class ReqgetReportforms_KPI {
	private Page page;//page
	private ReqgetReportforms_KPIQueryObj query;//req信息
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public ReqgetReportforms_KPIQueryObj getQuery() {
		return query;
	}
	public void setQuery(ReqgetReportforms_KPIQueryObj query) {
		this.query = query;
	}
	
}
