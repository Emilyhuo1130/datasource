package com.ucs.rcm.business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ucs.rcm.business.bo.Informbo;
import com.ucs.rcm.pojo.MaintainCoupleBack;
import com.ucs.rcm.reqobj.ReqgetIds;
import com.ucs.rcm.reqobj.ReqgetInformList;
import com.ucs.rcm.reqobj.ReqgetMaintain_couple_back;
import com.ucs.rcm.reqobj.ReqgetPlanInformList;
import com.ucs.rcm.reqobj.Reqgetmaintain_plan_table;
import com.ucs.rcm.responseObj.ResInformList;
import com.ucs.rcm.responseObj.ResPlanInformList;
import com.ucs.rcm.responseObj.Res_maintain_couple_back;
import com.ucs.rcm.responseObj.Res_plan_tableList;

/** 通知单 页面*/ 
@Controller
public class InformManager {
	static Logger log = Logger.getLogger(WarningManager.class);
	Gson gson = new Gson();
	/***维保通知单*/
	@RequestMapping(value="/Manage/getInformList")
	@ResponseBody
	public ResInformList getInformList(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getInformList");
		Gson gson=new Gson();
		ReqgetInformList query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetInformList.class);
		Informbo bo=new Informbo();
		return bo.getInformList(query);
	}
	/***计划修维修规程表***/
	@RequestMapping(value="/Manage/getmaintain_plan_table")
	@ResponseBody
	public Res_plan_tableList getmaintain_plan_table(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getmaintain_plan_table");
		Gson gson=new Gson();
		Reqgetmaintain_plan_table query=gson.fromJson(ReqUtils.readreqtoObject(request), Reqgetmaintain_plan_table.class);
		Informbo bo=new Informbo();
		return bo.getmaintain_plan_table(query);
	}
	/***返回计划修维保通知单**/
	@RequestMapping(value="/Manage/getPlanInformList")
	@ResponseBody
	public ResPlanInformList getPlanInformList(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getPlanInformList");
		Gson gson=new Gson();
		ReqgetPlanInformList query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetPlanInformList.class);
		Informbo bo=new Informbo();
		return bo.getPlanInformList(query);
	}
	
	/***维修反馈记录*/
	@RequestMapping(value="/Manage/getMaintain_couple_back")
	@ResponseBody
	public Res_maintain_couple_back getMaintain_couple_back(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getMaintain_couple_back");
		Gson gson=new Gson();
		ReqgetMaintain_couple_back req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetMaintain_couple_back.class);
		log.info("******维修反馈记录req=******"+gson.toJson(req)+"******************");
		Informbo bo=new Informbo();
		return bo.getMaintain_couple_back(req);
	}
	

	/***根据id查询维修反馈记录详情**/
	@RequestMapping(value="/Manage/getMaintainDescById")
	@ResponseBody
	public MaintainCoupleBack getMaintenceDesById(HttpServletRequest request,HttpServletResponse response){
	Informbo bo = new Informbo();
	ReqgetIds req = gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetIds.class);
	int id = req.getId();
	MaintainCoupleBack m  = bo.getMaintenDescById(id);
	
	return m;
}
	
	
	
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	