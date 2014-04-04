package com.ucs.rcm.protocol;
import net.sf.json.JSONObject;

import com.ucs.rcm.business.bo.Page;
import com.ucs.rcm.business.bo.WarningBo;
import com.ucs.rcm.reqobj.HttpRequest;
import com.ucs.rcm.reqobj.PlanWarningQueryObj;
import com.ucs.rcm.reqobj.ReqcommitPlanWaringById;
import com.ucs.rcm.reqobj.Reqconfirmorcancel;
import com.ucs.rcm.reqobj.ReqgetEquipmentAllInfo;
import com.ucs.rcm.reqobj.ReqgetFaultInfo_user2ById;
import com.ucs.rcm.reqobj.ReqgetLineHealth;
import com.ucs.rcm.reqobj.ReqgetPlanWaringList;
import com.ucs.rcm.reqobj.ReqgetWaringList;
import com.ucs.rcm.reqobj.ReqgetWaringListBystatments;
import com.ucs.rcm.reqobj.ReqgetWarningCounting;
import com.ucs.rcm.reqobj.ReqgetcountofNeedtoDO;
import com.ucs.rcm.reqobj.ReqgetthisDayLineHealth;
import com.ucs.rcm.reqobj.ReqinsertFaultInfo_user2;
import com.ucs.rcm.reqobj.Reqinsertinform_user4;
import com.ucs.rcm.reqobj.Reqmessage;
import com.ucs.rcm.reqobj.ReqstopFlow;
import com.ucs.rcm.reqobj.RequpdateFaultInfo_user2ById;
import com.ucs.rcm.reqobj.ReqwarningOperate;
import com.ucs.rcm.reqobj.WarningQueryObj;

public class Warnings {

	/**
	 * @param args
	 */
	WarningBo wb = new WarningBo();
	public  void main(String[] args) {

		// 获取警告列表
		//getWaringList();
		
		//getPlanWaringList();
		// TODO Auto-generated method stub
		
		
/*		1	严重
		2	危险
		3	良好
		4	健康*/

		//getLineHealth();

		//getWarningCounting();
		

		
		
		
		
		/**告警管理操作页面*/
		//修改后 0：未处理   1：确认   2：核实   3:审定    4：已处理   5  取消
		//1,2,3，统称为确认中
		//warningOperate();
		
		//获取相对应的状态0,1,2,3的告警记录
		//getWarningListBystatments();
		
	
		
		//终止按钮操作
		//stopFlow();
		
		//getWaringListById();
		
		
		
		//user2,user3保存告警中的消息
		//savemessage();
		
		//user1保存告警中的消息
		//saveuser1message();
		
		
		//根据user3 user4根据id从副表中获取数据，
		//getFaultInfo_user2ById();
		
		//user2进行核实操作
		//insertFaultInfo_user2();
		
		//user3的审定按钮
		//updateFaultInfo_user2ById();
		
		//user4发通知单
		//insertinform_user4();
		
		//getEquipmentAllInfo();
		
		
		//计划修发通知单
		//commitPlanWaringById();
		/***首页中的某条线的一天的健康状态***/
		//getthisDayLineHealth();
		
		
		/****当前故障告警、预警告警、计划修告警当前未被处理的有几条，需要有数字醒目表示。**/
		getcountofNeedtoDO();
	}

	private  void getcountofNeedtoDO() {
		HttpRequest req = new HttpRequest();
		req.setType("WarningManager");
		req.setMethod("getcountofNeedtoDO");
		ReqgetcountofNeedtoDO reqgetWaringList = new ReqgetcountofNeedtoDO();
		
		req.setReq(reqgetWaringList);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);
		result = JSONObject.fromObject( wb.getcountofNeedtoDO()).toString();
		System.out.println(result);
		
	}

	private  void getthisDayLineHealth() {
		HttpRequest req = new HttpRequest();
		req.setType("WarningManager");
		req.setMethod("getthisDayLineHealth");
		ReqgetthisDayLineHealth reqgetWaringList = new ReqgetthisDayLineHealth();
		reqgetWaringList.setLineNo("10");
		req.setReq(reqgetWaringList);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		
		result = JSONObject.fromObject( wb.getthisDayLineHealth(reqgetWaringList)).toString();
		System.out.println(result);
		
	}

	private  void commitPlanWaringById() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		req.setType("WarningManager");
		req.setMethod("commitPlanWaringById");
		ReqcommitPlanWaringById reqgetWaringList = new ReqcommitPlanWaringById();
		
		reqgetWaringList.setEquipmentId("10.29.08.01.001");
		reqgetWaringList.setId("1");
		req.setReq(reqgetWaringList);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		
		result = JSONObject.fromObject( wb.commitPlanWaringById(reqgetWaringList)).toString();
		System.out.println(result);
	}

	private  void getPlanWaringList() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		req.setType("WarningManager");
		req.setMethod("getPlanWaringList");
		ReqgetPlanWaringList reqgetWaringList = new ReqgetPlanWaringList();
		Page page = new Page();
		page.setPageCount(10);
		page.setPageNo(2);
		reqgetWaringList.setPage(page);

		PlanWarningQueryObj query = new PlanWarningQueryObj();
		query.setStatments("0");
		reqgetWaringList.setQuery(query);
		req.setReq(reqgetWaringList);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		
		result = JSONObject.fromObject( wb.getPlanWaringList(reqgetWaringList)).toString();
		System.out.println(result);
	}

	private  void getEquipmentAllInfo() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		
		ReqgetEquipmentAllInfo me=new ReqgetEquipmentAllInfo();
		me.setEquipmentId("11111");
		
		req.setReq(me);
		req.setMethod("getEquipmentAllInfo");
		req.setType("WarningManager");
		String result;
		result =JSONObject.fromObject(req).toString();
		System.out.println(result);
		
		result=JSONObject.fromObject(wb.getEquipmentAllInfo(me)).toString();
		System.out.println(result);
	}

	private  void saveuser1message() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		
		Reqconfirmorcancel me=new Reqconfirmorcancel();
		me.setMessage("操作员小黑，对XXXXXXX的进行了确认");
		me.setTime("2001-02-09 12:12:12");
		
		req.setReq(me);
		req.setMethod("confirmorcancel");
		req.setType("WarningManager");
		String result;
		result =JSONObject.fromObject(req).toString();
		System.out.println(result);
		
		result=JSONObject.fromObject(wb.saveuser1Message(me)).toString();
		System.out.println(result);
	}

	private  void insertinform_user4() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		Reqinsertinform_user4 user2 = new Reqinsertinform_user4();
		String result;
		
		user2.setId(2);
		user2.setJobNumber("112233");
		user2.setOperator("张三");
		user2.setStarTime("2012-09-08 22:12:12");
		user2.setEquipmentId("12121");
		user2.setEquipmentName("道岔");
		user2.setSystemName("信号系统");
		user2.setSubSystemName("轨旁设备");
		user2.setWarningTypeLevel("1");
		user2.setFultDescription("道岔无表示报警");
		user2.setLineNo("10");
		user2.setStationName("南京东路");
		user2.setHealthLevel("2");
		user2.setFaultCause("故障原因");
		user2.setInfluence("故障影响");
		user2.setMainTenancePolicy("维修策略");
		
		req.setMethod("insertinform_user4");
		req.setType("WarningManager");
	
		req.setReq(user2);
		result = JSONObject.fromObject(req).toString();
		System.out.println(result);
		
		result = JSONObject.fromObject(wb.insertinform_user4(user2)).toString();
		System.out.println(result);
		
	}

	private  void updateFaultInfo_user2ById() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		RequpdateFaultInfo_user2ById user2ById = new RequpdateFaultInfo_user2ById();
		String result;
		user2ById.setId(2);
		user2ById.setOpinion("同意||同意||同意");
		req.setType("WarningManager");
		req.setMethod("updateFaultInfo_user2ById");
		req.setReq(user2ById);
		result = JSONObject.fromObject(req).toString();
		System.out.println(result);
		
		result = JSONObject.fromObject(wb.updateFaultInfo_user2ById(user2ById)).toString();
		System.out.println(result);
		
		
	}

	private  void getFaultInfo_user2ById() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		ReqgetFaultInfo_user2ById user2ById = new ReqgetFaultInfo_user2ById();
		String result;
		user2ById.setId(2);
		req.setType("WarningManager");
		req.setMethod("getFaultInfo_user2ById");
		req.setReq(user2ById);
		result = JSONObject.fromObject(req).toString();
		System.out.println(result);
		
		result = JSONObject.fromObject(wb.getFaultInfo_user2ById(user2ById)).toString();
		System.out.println(result);
		
	}

	

	private  void stopFlow() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		String result;
		ReqstopFlow stop=new ReqstopFlow();
		stop.setUsername("user3");
		stop.setId(2);
		stop.setStatments("2");
		req.setType("WarningManager");
		req.setMethod("stopFlow");
		req.setReq(stop);
		result = JSONObject.fromObject(req).toString();
		System.out.println(result);
		
		result = JSONObject.fromObject(wb.stopFlow(stop)).toString();
		System.out.println(result);
		
	}

	private  void getWarningListBystatments() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		String result;
	
		ReqgetWaringListBystatments statments=new ReqgetWaringListBystatments();
		
		Page page = new Page();
		page.setPageNo(2);
		page.setPageCount(10);
		statments.setPage(page);
		statments.setStatments("0");
		statments.setWarningType("故障告警");
		
		
		req.setType("WarningManager");
		req.setMethod("getWaringListBystatments");
		req.setReq(statments);
		result = JSONObject.fromObject(req).toString();
		System.out.println(result);
 
		result = JSONObject.fromObject(wb.findWaringListBystatments(statments)).toString();
		System.out.println(result);
		
	}

	private  void insertFaultInfo_user2() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		
		ReqinsertFaultInfo_user2 user2=new ReqinsertFaultInfo_user2();
		user2.setId(2);
		user2.setEquipmentid("12121");
		user2.setEquipmentname("道岔");
		user2.setSystemname("信号系统");
		user2.setWarningType("故障告警");
		user2.setWarningTypeLevel("1");
		user2.setFultDescription("道岔无表示报警");
		user2.setLineNo("10");
		user2.setStationName("南京东路");
		user2.setWarningDate("2012-09-08 22:12:12");
		user2.setFaultCause("故障原因");
		user2.setInfluence("故障影响");
		user2.setMaintenancePolicy("维修策略");
		
		
		req.setReq(user2);
		req.setMethod("insertFaultInfo_user2");
		req.setType("WarningManager");
		String result;
		result =JSONObject.fromObject(req).toString();
		System.out.println(result);
		
		result=JSONObject.fromObject(wb.insertFault_user2(user2)).toString();
		System.out.println(result);
	}

	private  void savemessage() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		
		Reqmessage me=new Reqmessage();
		me.setStarMessage("操作员小黑，开始对XXXXXXX的核实流程");
		me.setStarTime("2001-02-09 12:12:12");
		me.setEndMessage("操作员小黑，终止对XXXXXXX的核实流程");
		me.setEndTime("2001-02-09 12:14:13");
		req.setReq(me);
		req.setMethod("message");
		req.setType("WarningManager");
		String result;
		result =JSONObject.fromObject(req).toString();
		System.out.println(result);
		
		result=JSONObject.fromObject(wb.saveMessage(me)).toString();
		System.out.println(result);
	}

	private  void warningOperate() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		String result;
	
		ReqwarningOperate reqwarningOperate = new ReqwarningOperate();
		int[] ids = { 32, 22, 33 };
		reqwarningOperate.setIds(ids);
		reqwarningOperate.setAction("cancel");
		req.setType("WarningManager");
		req.setMethod("warningOperate");
		req.setReq(reqwarningOperate);
		result = JSONObject.fromObject(req).toString();
		System.out.println(result);
 
		result = JSONObject.fromObject(wb.update(reqwarningOperate)).toString();
		System.out.println(result);

		
	}

	private  void getWarningCounting() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		String result  ;

		req.setType("WarningManager");
		req.setMethod("getWarningCounting");
		ReqgetWarningCounting reqs = new ReqgetWarningCounting();
		reqs.setReqString("get");
		req.setReq(reqs);
		result = JSONObject.fromObject(req).toString();
		System.out.println(result);

	
		result = JSONObject.fromObject(wb.getWarningCounting(reqs)).toString();
		System.out.println(result);

	}

	private  void getLineHealth() {
		// TODO Auto-generated method stub

		HttpRequest req = new HttpRequest();
		String result  ;

		ReqgetLineHealth reqGetlineHealth = new ReqgetLineHealth();
		reqGetlineHealth.setReqString("getAll");
		req.setType("WarningManager");
		req.setMethod("getLineHealth");
		req.setReq(reqGetlineHealth);
		result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		result = JSONObject.fromObject(wb.getLineHealth(reqGetlineHealth)).toString();
		System.out.println(result);


	}

	private  void getWaringList() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		req.setType("WarningManager");
		req.setMethod("getWaringList");
		ReqgetWaringList reqgetWaringList = new ReqgetWaringList();
		Page page = new Page();
		page.setPageCount(10);
		page.setPageNo(1);
		reqgetWaringList.setPage(page);

		WarningQueryObj query = new WarningQueryObj();
		
		query.setStartDate("1980-1-1");
		query.setEndDate("2014-1-1");
		query.setWarningType("故障告警");
		reqgetWaringList.setQuery(query);
		req.setReq(reqgetWaringList);

		String result = JSONObject.fromObject(req).toString();
		System.out.println(result);

		
		result = JSONObject.fromObject( wb.getWarnins(reqgetWaringList)).toString();
		System.out.println(result);

		

	}

}
