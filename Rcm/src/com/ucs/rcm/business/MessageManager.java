package com.ucs.rcm.business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ucs.rcm.business.bo.Messagebo;
import com.ucs.rcm.reqobj.Reqconfirmorcancel;
import com.ucs.rcm.reqobj.ReqgetMessageList;
import com.ucs.rcm.reqobj.Reqmessage;
import com.ucs.rcm.responseObj.ResInfo;
import com.ucs.rcm.responseObj.ResMessageList;
@Controller
public class MessageManager {
	static Logger log = Logger.getLogger(WarningManager.class);
	/***告警管理的消息来自上一个用户的操作提示*/
	@RequestMapping(value="/Manage/getMessageList")
	@ResponseBody
	public ResMessageList getMessageList(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getMessageList");
		Gson gson=new Gson();
		ReqgetMessageList req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetMessageList.class);
		Messagebo bo=new Messagebo();
		return bo.getmessageList(req);
	}
	
	
	
	
	
	/**告警管理的  user2,user3,的 消息提示插入信息*/
	@RequestMapping(value="/Manage/message")
	@ResponseBody
	public ResInfo message(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/message");
		Gson gson=new Gson();
		Reqmessage req=gson.fromJson(ReqUtils.readreqtoObject(request), Reqmessage.class);
		Messagebo bo=new Messagebo();
		return bo.saveMessage(req);
	}
	/**告警管理的user1的消息提示插入信息*/
	@RequestMapping(value="/Manage/confirmorcancel")
	@ResponseBody
	public ResInfo confirmorcancel(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/confirmorcancel");
		Gson gson=new Gson();
		Reqconfirmorcancel req=gson.fromJson(ReqUtils.readreqtoObject(request), Reqconfirmorcancel.class);
		Messagebo bo=new Messagebo();
		return bo.saveuser1Message(req);
	}
	
}
