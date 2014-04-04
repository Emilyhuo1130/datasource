package com.ucs.rcm.reqobj;

import com.ucs.rcm.business.bo.Page;

public class ReqgetEquipment_record {
	private Page page;//page
	private Equipment_recordQueryObj query;//req信息
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Equipment_recordQueryObj getQuery() {
		return query;
	}
	public void setQuery(Equipment_recordQueryObj query) {
		this.query = query;
	}
	
}
