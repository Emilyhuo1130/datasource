package com.ucs.rcm.protocol;

import net.sf.json.JSONObject;

import com.ucs.rcm.business.bo.Informbo;
import com.ucs.rcm.business.bo.Page;
import com.ucs.rcm.reqobj.HttpRequest;
import com.ucs.rcm.reqobj.InformQueryObj;
import com.ucs.rcm.reqobj.MaintainQueryObj;
import com.ucs.rcm.reqobj.PlanInformQueryObj;
import com.ucs.rcm.reqobj.Plan_TableQueryObj;
import com.ucs.rcm.reqobj.ReqgetInformList;
import com.ucs.rcm.reqobj.ReqgetMaintain_couple_back;
import com.ucs.rcm.reqobj.ReqgetPlanInformList;
import com.ucs.rcm.reqobj.Reqgetmaintain_plan_table;

public class Inform {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//维保通知单
		//getInfromList();
		//维修反馈记录
		//getweixiufankui();
		
		//计划修的通知单
		//getPlanInformList();
		
		getmaintain_plan_table();
	}
	
	private static void getmaintain_plan_table() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		req.setType("InformManager");
		req.setMethod("getmaintain_plan_table");
		Reqgetmaintain_plan_table reqL = new Reqgetmaintain_plan_table();
		Page page = new Page();
		page.setPageCount(5);
		page.setPageNo(1);
		reqL.setPage(page);
		Plan_TableQueryObj query = new Plan_TableQueryObj();
		reqL.setQuery(query);
		req.setReq(reqL);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);
		Informbo bo = new Informbo();
		result = JSONObject.fromObject( bo.getmaintain_plan_table(reqL)).toString();
		System.out.println(result);
	}

	private static void getPlanInformList() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		req.setType("InformManager");
		req.setMethod("getPlanInformList");
		ReqgetPlanInformList reqL = new ReqgetPlanInformList();
		Page page = new Page();
		page.setPageCount(5);
		page.setPageNo(1);
		reqL.setPage(page);
		PlanInformQueryObj query = new PlanInformQueryObj();
		reqL.setQuery(query);
		req.setReq(reqL);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);
		Informbo bo = new Informbo();
		result = JSONObject.fromObject( bo.getPlanInformList(reqL)).toString();
		System.out.println(result);
	}

	private static void getweixiufankui() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		req.setType("InformManager");
		req.setMethod("getMaintain_couple_back");
		ReqgetMaintain_couple_back reqL = new ReqgetMaintain_couple_back();
		Page page = new Page();
		page.setPageCount(5);
		page.setPageNo(1);
		reqL.setPage(page);

		MaintainQueryObj query = new MaintainQueryObj();
		
		reqL.setQuery(query);
		req.setReq(reqL);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		Informbo bo = new Informbo();
		result = JSONObject.fromObject( bo.getMaintain_couple_back(reqL)).toString();
		System.out.println(result);
	}

	public static void getInfromList(){
		HttpRequest req = new HttpRequest();
		req.setType("InformManager");
		req.setMethod("getInformList");
		ReqgetInformList reqL = new ReqgetInformList();
		Page page = new Page();
		page.setPageCount(10);
		page.setPageNo(1);
		reqL.setPage(page);

		InformQueryObj query = new InformQueryObj();
		
		reqL.setQuery(query);
		req.setReq(reqL);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		
		result = JSONObject.fromObject( Informbo.getInformList(reqL)).toString();
		System.out.println(result);

	}
	

}
