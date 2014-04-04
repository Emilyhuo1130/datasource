package com.ucs.rcm.protocol;

import net.sf.json.JSONObject;

import com.ucs.rcm.business.bo.IndexAnalysisbo;
import com.ucs.rcm.business.bo.Page;
import com.ucs.rcm.reqobj.HttpRequest;
import com.ucs.rcm.reqobj.ReqgetMaintainStatistics;
import com.ucs.rcm.reqobj.ReqgetOneSysmaintai;
import com.ucs.rcm.reqobj.ReqgetOperateStatistics;
import com.ucs.rcm.reqobj.ReqgetoneLineAllOperateStatistics;
import com.ucs.rcm.reqobj.ReqgetoneLineOperateStatistics;

/***指标分析*/
public class IndexAnalysis {
	public static void main(String[] args) {
		//运营统计指标
		//getOperateStatistics();
		
		//维修统计指标
		//getMaintainStatistics();
		
		
		//图
		//getoneLineOperateStatistics();
		
		
		//维修统计指标图
		//getOneSysmaintai();
		
		
		getoneLineAllOperateStatistics();
		
	}

	private static void getoneLineAllOperateStatistics() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getoneLineAllOperateStatistics");
		json.setType("IndexAnalysisManager");
		ReqgetoneLineAllOperateStatistics reqs=new ReqgetoneLineAllOperateStatistics();
		reqs.setLineNo("10");
		
		json.setReq(reqs);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);

	
		result = JSONObject.fromObject(IndexAnalysisbo.getoneLineAllOperateStatistics(reqs)).toString();
		System.out.println(result);
	}

	private static void getOneSysmaintai() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getOneSysmaintai");
		json.setType("IndexAnalysisManager");
		ReqgetOneSysmaintai reqs=new ReqgetOneSysmaintai();
		
		
		json.setReq(reqs);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);

	
		result = JSONObject.fromObject(IndexAnalysisbo.getOneSysmaintai(reqs)).toString();
		System.out.println(result);
	}

	private static void getoneLineOperateStatistics() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getoneLineOperateStatistics");
		json.setType("IndexAnalysisManager");
		ReqgetoneLineOperateStatistics reqs=new ReqgetoneLineOperateStatistics();
		
		reqs.setLineNo("10");
		json.setReq(reqs);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);

	
		result = JSONObject.fromObject(IndexAnalysisbo.getoneLineOperateStatistics(reqs)).toString();
		System.out.println(result);
	}

	private static void getMaintainStatistics() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getMaintainStatistics");
		json.setType("IndexAnalysisManager");
		ReqgetMaintainStatistics reqs=new ReqgetMaintainStatistics();
		Page page=new Page();
		page.setPageCount(10);
		page.setPageNo(1);
		reqs.setPage(page);
		
		reqs.setReqString("getAll");
		json.setReq(reqs);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);

	
		result = JSONObject.fromObject(IndexAnalysisbo.getMaintainStatistics(reqs)).toString();
		System.out.println(result);
		
	}

	private static void getOperateStatistics() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getOperateStatistics");
		json.setType("IndexAnalysisManager");
		ReqgetOperateStatistics reqs=new ReqgetOperateStatistics();
		Page page=new Page();
		page.setPageCount(10);
		page.setPageNo(1);
		reqs.setPage(page);
		
		reqs.setReqString("getAll");
		json.setReq(reqs);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);

	
		result = JSONObject.fromObject(IndexAnalysisbo.getOperateStatistics(reqs)).toString();
		System.out.println(result);
		
	}
	
}
