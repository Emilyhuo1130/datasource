package com.ucs.rcm.protocol;

import net.sf.json.JSONObject;

import com.ucs.rcm.business.bo.Messagebo;
import com.ucs.rcm.reqobj.HttpRequest;
import com.ucs.rcm.reqobj.ReqgetMessageList;
import com.ucs.rcm.reqobj.Reqmessage;

public class Message {
	public static void main(String[] args) {
		//getmessagebywarningId();
		
		
		usersavemessage();
	}

	private static void usersavemessage() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("message");
		json.setType("MessageManager");
		Reqmessage reqs=new Reqmessage();
		reqs.setWarningId("2");
		reqs.setStarMessage("开始操作的消息主体");
		reqs.setStarTime("2013-08-28 08:33:05");
		reqs.setEndMessage("结束操作的消息主体");
		reqs.setEndTime("2013-08-28 08:35:05");
		reqs.setFromuser("2");
		
		json.setReq(reqs);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);

	
		result = JSONObject.fromObject(Messagebo.saveMessage(reqs)).toString();
		System.out.println(result);
		
	}

	private static void getmessagebywarningId() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getMessageList");
		json.setType("MessageManager");
		ReqgetMessageList reqs=new ReqgetMessageList();
		reqs.setUserName("user2");
		reqs.setWarningId("2");
		json.setReq(reqs);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);

	
		result = JSONObject.fromObject(Messagebo.getmessageList(reqs)).toString();
		System.out.println(result);
	}
	
}
