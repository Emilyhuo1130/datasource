package com.ucs.rcm.business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ucs.rcm.business.bo.IndexAnalysisbo;
import com.ucs.rcm.reqobj.ReqgetMaintainStatistics;
import com.ucs.rcm.reqobj.ReqgetOneSysmaintai;
import com.ucs.rcm.reqobj.ReqgetOperateStatistics;
import com.ucs.rcm.reqobj.ReqgetoneLineAllOperateStatistics;
import com.ucs.rcm.reqobj.ReqgetoneLineOperateStatistics;
import com.ucs.rcm.responseObj.ResMaintainStatisticsList;
import com.ucs.rcm.responseObj.ResOneLineOperateStatisticsList;
import com.ucs.rcm.responseObj.ResOneSysmaintaiList;
import com.ucs.rcm.responseObj.ResOperateStatisticsList;

/**指标分析*/
@Controller
public class IndexAnalysisManager {
	static Logger log = Logger.getLogger(WarningManager.class);
	/**运营统计指标*/
	@RequestMapping(value="/Manage/getOperateStatistics")
	@ResponseBody
	public ResOperateStatisticsList getOperateStatistics(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getOperateStatistics");
		Gson gson=new Gson();
		ReqgetOperateStatistics query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetOperateStatistics.class);
		IndexAnalysisbo bo=new IndexAnalysisbo();
		return bo.getOperateStatistics(query);
	}
	
	/**维修统计指标*/
	@RequestMapping(value="/Manage/getMaintainStatistics")
	@ResponseBody
	public  ResMaintainStatisticsList getMaintainStatistics(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getMaintainStatistics");
		Gson gson=new Gson();
		ReqgetMaintainStatistics query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetMaintainStatistics.class);
		IndexAnalysisbo bo=new IndexAnalysisbo();
		return bo.getMaintainStatistics(query);
	}
	
	/**某一条线的 一年的运营统计指标**/
	@RequestMapping(value="/Manage/getoneLineOperateStatistics")
	@ResponseBody
	public ResOneLineOperateStatisticsList getoneLineOperateStatistics(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getoneLineOperateStatistics");
		Gson gson=new Gson();
		ReqgetoneLineOperateStatistics query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetoneLineOperateStatistics.class);
		IndexAnalysisbo bo=new IndexAnalysisbo();
		return bo.getoneLineOperateStatistics(query);
	}
	
	/**某一条线的   所统计的数据库的运营统计指标**/
	public ResOneLineOperateStatisticsList getoneLineAllOperateStatistics(ReqgetoneLineAllOperateStatistics query){
		return IndexAnalysisbo.getoneLineAllOperateStatistics(query);
	}
	
	/**维修统计指标 --------图*/
	@RequestMapping(value="/Manage/getOneSysmaintai")
	@ResponseBody
	public ResOneSysmaintaiList getOneSysmaintai(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getOneSysmaintai");
		Gson gson=new Gson();
		ReqgetOneSysmaintai query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetOneSysmaintai.class);
		IndexAnalysisbo bo=new IndexAnalysisbo();
		return bo.getOneSysmaintai(query);
	}
}
