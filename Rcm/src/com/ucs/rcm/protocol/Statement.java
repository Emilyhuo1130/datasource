package com.ucs.rcm.protocol;

import net.sf.json.JSONObject;

import com.ucs.rcm.business.bo.Page;
import com.ucs.rcm.business.bo.Statementbo;
import com.ucs.rcm.reqobj.CountofHistorywarningQueryObj;
import com.ucs.rcm.reqobj.Equipment_recordQueryObj;
import com.ucs.rcm.reqobj.FormsOperateindexQueryObj;
import com.ucs.rcm.reqobj.HistorywarningQueryObj;
import com.ucs.rcm.reqobj.HttpRequest;
import com.ucs.rcm.reqobj.PlanWarningQueryObj;
import com.ucs.rcm.reqobj.Reportforms_Equipment_indexQueryObj;
import com.ucs.rcm.reqobj.Reportforms_requisitionQueryObj;
import com.ucs.rcm.reqobj.ReqgetEquipment_record;
import com.ucs.rcm.reqobj.ReqgetFormsOperateindexList;
import com.ucs.rcm.reqobj.ReqgetReportforms_Equipment_index;
import com.ucs.rcm.reqobj.ReqgetReportforms_KPI;
import com.ucs.rcm.reqobj.ReqgetReportforms_KPIQueryObj;
import com.ucs.rcm.reqobj.ReqgetReportforms_requisition;
import com.ucs.rcm.reqobj.ReqgetcountofPlanhistoryWarning;
import com.ucs.rcm.reqobj.ReqgetcountofhistoryWarning;
import com.ucs.rcm.reqobj.ReqgethistoryWarningList;

public class Statement {

	/**
	 * @param args
	 */
	Statementbo bo = new Statementbo();
	public  void main(String[] args) {
		// TODO Auto-generated method stub
		getStatementgaojingWarning();
		
		/**健康指数分析表--运营指数*/
		//getFormsOperateindexList();
		
		
		/**主动维保通知单**/
		//getReportforms_requisition();
		
		
		/**设备履历表***/
		//getEquipment_record();
		
		/***健康指数分析表-设施指数**/
		//getReportforms_Equipment_index();
		
		
		/**KPI**/
		//getReportforms_KPI();
		
		/**历史告警、预警对设备告警次数的统计***/
		//getcountofhistoryWarning();
		
		/**计划修历史统计***/
		//getcountofPlanhistoryWarning();
		
	}

	@SuppressWarnings("unused")
	private  void getcountofPlanhistoryWarning() {
		HttpRequest req = new HttpRequest();
		req.setType("StatementManager");
		req.setMethod("getcountofPlanhistoryWarning");
		ReqgetcountofPlanhistoryWarning reqgetWaringList = new ReqgetcountofPlanhistoryWarning();
		Page page = new Page();
		page.setPageCount(5);
		page.setPageNo(1);
		reqgetWaringList.setPage(page);

		PlanWarningQueryObj query = new PlanWarningQueryObj();
		
		reqgetWaringList.setQuery(query);
		req.setReq(reqgetWaringList);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		
		result = JSONObject.fromObject( bo.getcountofPlanhistoryWarning(reqgetWaringList)).toString();
		System.out.println(result);
		
	}

	private  void getcountofhistoryWarning() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		req.setType("StatementManager");
		req.setMethod("getcountofhistoryWarning");
		ReqgetcountofhistoryWarning reqgetWaringList = new ReqgetcountofhistoryWarning();
		Page page = new Page();
		page.setPageCount(5);
		page.setPageNo(1);
		reqgetWaringList.setPage(page);

		CountofHistorywarningQueryObj query = new CountofHistorywarningQueryObj();
		query.setWarningType("故障预警");
		reqgetWaringList.setQuery(query);
		req.setReq(reqgetWaringList);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		
		result = JSONObject.fromObject( bo.getcountofhistoryWarning(reqgetWaringList)).toString();
		System.out.println(result);
	}

	private  void getReportforms_KPI() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		req.setType("StatementManager");
		req.setMethod("getReportforms_KPI");
		ReqgetReportforms_KPI reqgetWaringList = new ReqgetReportforms_KPI();
		Page page = new Page();
		page.setPageCount(5);
		page.setPageNo(1);
		reqgetWaringList.setPage(page);

		ReqgetReportforms_KPIQueryObj query = new ReqgetReportforms_KPIQueryObj();
		
		reqgetWaringList.setQuery(query);
		req.setReq(reqgetWaringList);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		
		result = JSONObject.fromObject( bo.getReportforms_KPI(reqgetWaringList)).toString();
		System.out.println(result);
	}

	private  void getReportforms_Equipment_index() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		req.setType("StatementManager");
		req.setMethod("getReportforms_Equipment_index");
		ReqgetReportforms_Equipment_index reqgetWaringList = new ReqgetReportforms_Equipment_index();
		Page page = new Page();
		page.setPageCount(5);
		page.setPageNo(1);
		reqgetWaringList.setPage(page);

		Reportforms_Equipment_indexQueryObj query = new Reportforms_Equipment_indexQueryObj();
		
		reqgetWaringList.setQuery(query);
		req.setReq(reqgetWaringList);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		
		result = JSONObject.fromObject( bo.getReportforms_Equipment_index(reqgetWaringList)).toString();
		System.out.println(result);
	}

	private  void getEquipment_record() {
		HttpRequest req = new HttpRequest();
		req.setType("StatementManager");
		req.setMethod("getEquipment_record");
		ReqgetEquipment_record reqgetWaringList = new ReqgetEquipment_record();
		Page page = new Page();
		page.setPageCount(5);
		page.setPageNo(1);
		reqgetWaringList.setPage(page);

		Equipment_recordQueryObj query = new Equipment_recordQueryObj();
		
		reqgetWaringList.setQuery(query);
		req.setReq(reqgetWaringList);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		
		result = JSONObject.fromObject( bo.getEquipment_record(reqgetWaringList)).toString();
		System.out.println(result);
		
	}

	private  void getReportforms_requisition() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		req.setType("getReportforms_requisition");
		req.setMethod("getFormsOperateindexList");
		ReqgetReportforms_requisition reqgetWaringList = new ReqgetReportforms_requisition();
		Page page = new Page();
		page.setPageCount(5);
		page.setPageNo(1);
		reqgetWaringList.setPage(page);

		Reportforms_requisitionQueryObj query = new Reportforms_requisitionQueryObj();
		
		reqgetWaringList.setQuery(query);
		req.setReq(reqgetWaringList);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		
		result = JSONObject.fromObject( bo.getReportforms_requisition(reqgetWaringList)).toString();
		System.out.println(result);
	}

	private  void getFormsOperateindexList() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		req.setType("StatementManager");
		req.setMethod("getFormsOperateindexList");
		ReqgetFormsOperateindexList reqgetWaringList = new ReqgetFormsOperateindexList();
		Page page = new Page();
		page.setPageCount(5);
		page.setPageNo(2);
		reqgetWaringList.setPage(page);

		FormsOperateindexQueryObj query = new FormsOperateindexQueryObj();
		
		reqgetWaringList.setQuery(query);
		req.setReq(reqgetWaringList);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		
		result = JSONObject.fromObject( bo.getFormsOperateindexList(reqgetWaringList)).toString();
		System.out.println(result);
	}

	private  void getStatementgaojingWarning() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		req.setType("StatementManager");
		req.setMethod("gethistoryWarningList");
		ReqgethistoryWarningList reqgetWaringList = new ReqgethistoryWarningList();
		Page page = new Page();
		page.setPageCount(5);
		page.setPageNo(1);
		reqgetWaringList.setPage(page);

		HistorywarningQueryObj query = new HistorywarningQueryObj();
		query.setWarningType("故障预警");
		query.setComponent("隧道风机通风系统");
		reqgetWaringList.setQuery(query);
		req.setReq(reqgetWaringList);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		
		result = JSONObject.fromObject( bo.gethistoryWarningList(reqgetWaringList)).toString();
		System.out.println(result);
		
	}
	

}
