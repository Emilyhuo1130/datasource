package com.ucs.rcm.business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ucs.rcm.business.bo.Statementbo;
import com.ucs.rcm.reqobj.ReqgetEquipment_record;
import com.ucs.rcm.reqobj.ReqgetFormsOperateindexList;
import com.ucs.rcm.reqobj.ReqgetReportforms_Equipment_index;
import com.ucs.rcm.reqobj.ReqgetReportforms_KPI;
import com.ucs.rcm.reqobj.ReqgetReportforms_requisition;
import com.ucs.rcm.reqobj.ReqgetcountofPlanhistoryWarning;
import com.ucs.rcm.reqobj.ReqgetcountofhistoryWarning;
import com.ucs.rcm.reqobj.ReqgethistoryWarningList;
import com.ucs.rcm.responseObj.ResReportforms_Equipment_indexList;
import com.ucs.rcm.responseObj.ResEquipment_recordList;
import com.ucs.rcm.responseObj.ResOperateindexList;
import com.ucs.rcm.responseObj.ResReportforms_KPI;
import com.ucs.rcm.responseObj.ResReportforms_requisition;
import com.ucs.rcm.responseObj.RescountPlanWaringList;
import com.ucs.rcm.responseObj.RescountofWarning;
import com.ucs.rcm.responseObj.ReshistoryWarningList;

/**报表管理*/
@Controller
public class StatementManager {
	static Logger log = Logger.getLogger(WarningManager.class);
	Statementbo bo = new Statementbo();
	/***告警历史统计表*/
	/**预警历史统计表*/  /****查找某个设备的历史告警详情 入参设备名称  时间，车站，线号***/
	@RequestMapping(value="/Manage/gethistoryWarningList")
	@ResponseBody
	public ReshistoryWarningList gethistoryWarningList(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/gethistoryWarningList");
		Gson gson=new Gson();
		ReqgethistoryWarningList req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgethistoryWarningList.class);
		Statementbo bo=new Statementbo();
		return  bo.gethistoryWarningList(req);
	}
	/**历史告警、预警对设备告警次数的统计***/
	@RequestMapping(value="/Manage/getcountofhistoryWarning")
	@ResponseBody
	public RescountofWarning getcountofhistoryWarning(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getcountofhistoryWarning");
		Gson gson=new Gson();
		ReqgetcountofhistoryWarning req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetcountofhistoryWarning.class);
		Statementbo bo=new Statementbo();
		return  bo.getcountofhistoryWarning(req);
	}
	/**计划修历史统计***/
	public RescountPlanWaringList getcountofPlanhistoryWarning(ReqgetcountofPlanhistoryWarning req){
		return  bo.getcountofPlanhistoryWarning(req);
	}
	
	/**健康指数分析表--设施指数*/
	@RequestMapping(value="/Manage/getReportforms_Equipment_index")
	@ResponseBody
	public ResReportforms_Equipment_indexList getReportforms_Equipment_index (HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getReportforms_Equipment_index");
		Gson gson=new Gson();
		ReqgetReportforms_Equipment_index query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetReportforms_Equipment_index.class);
		Statementbo bo=new Statementbo();
		return  bo.getReportforms_Equipment_index(query);
	}
	
	
	/**健康指数分析表--运营指数*/
	@RequestMapping(value="/Manage/getFormsOperateindexList")
	@ResponseBody
	public ResOperateindexList getFormsOperateindexList(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getFormsOperateindexList");
		Gson gson=new Gson();
		ReqgetFormsOperateindexList req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetFormsOperateindexList.class);
		Statementbo bo=new Statementbo();
		return  bo.getFormsOperateindexList(req);
	}
	
	/**KPI分析表*/
	@RequestMapping(value="/Manage/getReportforms_KPI")
	@ResponseBody
	public ResReportforms_KPI getReportforms_KPI(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getReportforms_KPI");
		Gson gson=new Gson();
		ReqgetReportforms_KPI req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetReportforms_KPI.class);
		Statementbo bo=new Statementbo();
		return  bo.getReportforms_KPI(req);
	}
	
	
	/**主动维保通知单*/
	@RequestMapping(value="/Manage/getReportforms_requisition")
	@ResponseBody
	public ResReportforms_requisition getReportforms_requisition(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getReportforms_requisition");
		Gson gson=new Gson();
		ReqgetReportforms_requisition req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetReportforms_requisition.class);
		Statementbo bo=new Statementbo();
		return  bo.getReportforms_requisition(req);
	}
	
	/**设备履历表*/
	@RequestMapping(value="/Manage/getEquipment_record")
	@ResponseBody
	public ResEquipment_recordList getEquipment_record(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getEquipment_record");
		Gson gson=new Gson();
		ReqgetEquipment_record query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetEquipment_record.class);
		Statementbo bo=new Statementbo();
		return bo.getEquipment_record(query);
	}
}
