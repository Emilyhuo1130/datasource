package com.ucs.rcm.business;
/**策略定制和影响分析放一块*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ucs.rcm.business.bo.Strategybo;
import com.ucs.rcm.reqobj.ReqfindFaultInfoByID;
import com.ucs.rcm.reqobj.Reqgetimpactanalysttree;
import com.ucs.rcm.reqobj.RequpdateFaultInfo;
import com.ucs.rcm.responseObj.FaultType;
import com.ucs.rcm.responseObj.ResInfo;
import com.ucs.rcm.ztree.impactAnalyst.GetimpactAnalystTree;
import com.ucs.rcm.ztree.impactAnalyst.Resshuck;

@Controller
public class StrategyManager {
	static Logger log = Logger.getLogger(WarningManager.class);
	/**策略定制和影响分析的保存按钮*/
	@RequestMapping(value="/Manage/updateFaultInfo")
	@ResponseBody
	public ResInfo updateFaultInfo(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/updateFaultInfo");
		Gson gson=new Gson();
		RequpdateFaultInfo req=gson.fromJson(ReqUtils.readreqtoObject(request), RequpdateFaultInfo.class);
		Strategybo bo=new Strategybo();
		if(req.getId()!=0){
			return bo.updateFaultInfo(req);
		}else{
			return null;
		}
	}
	/***策略定制和影响分析的撤销按钮*/
	@RequestMapping(value="/Manage/findFaultInfoByID")
	@ResponseBody
	public FaultType findFaultInfoByID(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/findFaultInfoByID");
		Gson gson=new Gson();
		ReqfindFaultInfoByID req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqfindFaultInfoByID.class);
		Strategybo bo=new Strategybo();
		if(req.getId()!=0){
			return bo.findFaultInfoByFaultType(req);
			
		}else{
			return null;
		}
		
	}
	
	
	/***影响分析的树*/
	@RequestMapping(value="/Manage/getimpactanalysttree")
	@ResponseBody
	public static Resshuck getimpactanalysttree(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getimpactanalysttree");
		Gson gson=new Gson();
		Reqgetimpactanalysttree req=gson.fromJson(ReqUtils.readreqtoObject(request), Reqgetimpactanalysttree.class);
		GetimpactAnalystTree bo=new GetimpactAnalystTree();
		return bo.getimpactanalysttree(req);
	}

}
