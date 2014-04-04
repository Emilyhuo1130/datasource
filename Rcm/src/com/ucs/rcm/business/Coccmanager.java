package com.ucs.rcm.business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ucs.rcm.business.bo.Coccmanagerbo;
import com.ucs.rcm.business.bo.Page;
import com.ucs.rcm.pojo.RateOfwas;
import com.ucs.rcm.pojo.WarningIndex;
import com.ucs.rcm.reqobj.CoccReqForNofinishedWarnings;
import com.ucs.rcm.reqobj.ReqgetIds;


@Controller
public class Coccmanager {
	static Logger log = Logger.getLogger(Coccmanager.class);
	Gson gson = new Gson();
	//显示cocc页面未处理的告警信息
	
	@RequestMapping(value="/Manage/getNofinishedWaringList")
	@ResponseBody
	public WarningIndex getNofinishedWarnings(HttpServletRequest request , HttpServletResponse response){
		Page page=gson.fromJson(ReqUtils.readreqtoObject(request), Page.class);
		log.info("******************************"+gson.toJson(page));
		Coccmanagerbo co = new Coccmanagerbo();
		WarningIndex wx = co.getNoFinishedWarning(page);
		log.info("****wx=**********"+gson.toJson(wx)+"********");
		return wx;
	}
	
	//获得未处理 确认中  和 已处理的告警数量。
	@RequestMapping(value="/Manage/getDifNumOfWaring")
	@ResponseBody
	public RateOfwas getNumOfDifWars(HttpServletRequest request , HttpServletResponse response){
		Coccmanagerbo co = new Coccmanagerbo();
		RateOfwas r = co.getNumOfDifWars();
		return r;
	}
	
	
	
	
	
	
}
