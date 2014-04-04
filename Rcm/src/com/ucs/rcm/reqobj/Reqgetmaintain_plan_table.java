package com.ucs.rcm.reqobj;

import com.ucs.rcm.business.bo.Page;

public class Reqgetmaintain_plan_table {
	private Page page;//page
	private Plan_TableQueryObj query;//req信息
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Plan_TableQueryObj getQuery() {
		return query;
	}
	public void setQuery(Plan_TableQueryObj query) {
		this.query = query;
	}
	
}
