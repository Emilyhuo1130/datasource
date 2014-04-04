package com.ucs.rcm.business;

/**主要包
 * 1.括首页告警信息的逻辑处理
 * 2.user1,user2,user3的告警管理的处理，显示各个用户能待处理的告警信息，
 * 单击详情后进入处理界面，包括核实和终止流程等操作
 * **/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ucs.rcm.afterfromServices.MainPage_Refresh;
import com.ucs.rcm.business.bo.Equipment;
import com.ucs.rcm.business.bo.WarningBo;
import com.ucs.rcm.pojo.Warning;
import com.ucs.rcm.reqobj.ReqcommitPlanWaringById;
import com.ucs.rcm.reqobj.ReqgetDataShow;
import com.ucs.rcm.reqobj.ReqgetEquipmentAllInfo;
import com.ucs.rcm.reqobj.ReqgetEquipmentName;
import com.ucs.rcm.reqobj.ReqgetFaultInfo_user2ById;
import com.ucs.rcm.reqobj.ReqgetInfoFromFlag;
import com.ucs.rcm.reqobj.ReqgetPlanWaringList;
import com.ucs.rcm.reqobj.ReqgetSystemName;
import com.ucs.rcm.reqobj.ReqgetWaringListBystatments;
import com.ucs.rcm.reqobj.ReqgetcountofNeedtoDO;
import com.ucs.rcm.reqobj.ReqgetthisDayLineHealth;
import com.ucs.rcm.reqobj.Reqinsertinform_user4;
import com.ucs.rcm.reqobj.ReqgetIds;
import com.ucs.rcm.reqobj.ReqgetWarningCounting;
import com.ucs.rcm.reqobj.ReqgetWaringList;
import com.ucs.rcm.reqobj.ReqgetLineHealth;
import com.ucs.rcm.reqobj.ReqsetInfoFromFlag;
import com.ucs.rcm.reqobj.ReqstopFlow;
import com.ucs.rcm.reqobj.RequpdateFaultInfo_user2ById;
import com.ucs.rcm.reqobj.ReqwarningOperate;
import com.ucs.rcm.reqobj.ReqinsertFaultInfo_user2;
import com.ucs.rcm.responseObj.ResDataShowList;
import com.ucs.rcm.responseObj.ResDayLineHealth;
import com.ucs.rcm.responseObj.ResEquipmentName;
import com.ucs.rcm.responseObj.ResFaultInfo_user2;
import com.ucs.rcm.responseObj.ResInfo;
import com.ucs.rcm.responseObj.ResLineHealth;
import com.ucs.rcm.responseObj.ResNeeddoOfCount;
import com.ucs.rcm.responseObj.ResPlanWaringList;
import com.ucs.rcm.responseObj.ResSystemName;
import com.ucs.rcm.responseObj.ResWaringList;
import com.ucs.rcm.responseObj.ResWarningCounting;


@Controller
public class WarningManager {
	static Logger log = Logger.getLogger(WarningManager.class);
	WarningBo wb = new WarningBo();
	
	
	/**
	 * @param args
	 */
	//传进来的参数时json中的req主要包括page和query  都包含在以下的ReqgetWaringList中
	/**获取所有信息*/
	@RequestMapping(value="/Manage/getWaringList")
	@ResponseBody
	public ResWaringList getWaringList(HttpServletRequest request,HttpServletResponse response) {
		log.info("/Manage/getWaringList");
		Gson gson=new Gson();
		
		ReqgetWaringList query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetWaringList.class);
		log.info(query.getPage());
		WarningBo bo=new WarningBo();
		return bo.getWarnins(query);
	}
	
	
	/***时间到了生成计划修预警**/
	public static void createPlanWarning(){
		new WarningBo().createPlanWarning();
	}
	/***获取计划修预警(包括未处理的和历史的 未处理的状态为-0 处理过的为1)***/
	@RequestMapping(value="/Manage/getPlanWaringList")
	@ResponseBody
	public ResPlanWaringList getPlanWaringList(HttpServletRequest request,HttpServletResponse response) {
		log.info("/Manage/getPlanWaringList");
		Gson gson=new Gson();
		
		ReqgetPlanWaringList query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetPlanWaringList.class);
		WarningBo bo=new WarningBo();
		return bo.getPlanWaringList(query);
	}
	/*** 确定    修改计划修的状态，发送计划修通知单****/
	@RequestMapping(value="/Manage/commitPlanWaringById")
	@ResponseBody
	public ResInfo commitPlanWaringById(HttpServletRequest request,HttpServletResponse response) {
		log.info("/Manage/commitPlanWaringById");
		Gson gson=new Gson();
		ReqcommitPlanWaringById query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqcommitPlanWaringById.class);
		WarningBo bo=new WarningBo();
		return bo.commitPlanWaringById(query);
	}
	/***每一个小时取一次的一条线的健康值**/
	public void readHealrhLevel(){
		new WarningBo().readHealrhLevel();
	}
	/***首页中的某条线的一天的健康状态***/
	public ResDayLineHealth getthisDayLineHealth(ReqgetthisDayLineHealth query) {
		return wb.getthisDayLineHealth(query);
	}
	
	/****当前故障告警、预警告警、计划修告警当前未被处理的有几条，需要有数字醒目表示。**/
	public ResNeeddoOfCount getcountofNeedtoDO(ReqgetcountofNeedtoDO req){
		return wb.getcountofNeedtoDO();
	}
	//List<TendencyInfo> 
	
	/**首页底下3张图的显示**/
	@RequestMapping(value="/Manage/getWarningCounting")
	@ResponseBody
	public ResWarningCounting  getWarningCounting(HttpServletRequest request,HttpServletResponse response) {
		log.info("/Manage/getWarningCounting");
		Gson gson=new Gson();
		ReqgetWarningCounting req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetWarningCounting.class);
		WarningBo bo=new WarningBo();
		return bo.getWarningCounting(req);
	}
	
	/**状态处理，处理警告状态*/
	@RequestMapping(value="/Manage/warningOperate")
	@ResponseBody
	public ResInfo warningOperate(HttpServletRequest request,HttpServletResponse response) {
		log.info("/Manage/warningOperate");
		Gson gson=new Gson();
		WarningBo bo=new WarningBo();
		ReqwarningOperate reqwarningOperate=gson.fromJson(ReqUtils.readreqtoObject(request), ReqwarningOperate.class);
		if((reqwarningOperate.getIds()!=null)&&(reqwarningOperate.getIds().length>0)){
			log.info("****reqwarningOperate=************"+gson.toJson(reqwarningOperate)+"**********************");
			return bo.update(reqwarningOperate);
		}else{
			return null;
		}

	}
	
	
	/***以下是 4个用户的 告警管理的  详情页面的操作*/
	
	
	/**根据id来获取信息  处理选中的告警*/
	@RequestMapping(value="/Manage/getIds")
	@ResponseBody
	public Warning getIds(HttpServletRequest request,HttpServletResponse response) {
		log.info("/Manage/getIds");
		Gson gson=new Gson();
		ReqgetIds req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetIds.class);
		if(req.getId()>0){
			return wb.getWarningInfoById(req);
		}else{
			return null;
		}
	}
	
	/****推演时显示追忆数据**/
	public ResDataShowList getDataShow(ReqgetDataShow req) {
		if(req.getEquipmentId().substring(6,11).equals("08.01")){
			//return WarningBo.getDataShow(req);
			Gson gson=new Gson();
			//测试
			String ss="{'listInfo':[{'source':[35.8,36.8,37.8,38.8,39.8,38.8],'name':'定位直流电压'},{'source':[35.8,36.8,37.8,38.8,39.8,38.8],'name':'定位交流电压'}]}";

			ResDataShowList info=gson.fromJson(ss, ResDataShowList.class);
			return info;
		}else{
			ResDataShowList info=new ResDataShowList();
			return info;
		}
	}
	
	
	/**根据状态 0：未处理  1：确认  2：核实  3：审定  和告警类型  来在4个 操作页面山显示不同状态的结果 */
	//入参树0 ，1,2,3
	@RequestMapping(value="/Manage/getWaringListBystatments")
	@ResponseBody
	public ResWaringList getWaringListBystatments(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getWaringListBystatments");
		Gson gson=new Gson();
		WarningBo bo=new WarningBo();
		ReqgetWaringListBystatments req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetWaringListBystatments.class);
		if(req.getStatments()!=null){
			return bo.findWaringListBystatments(req);
		}else{
			return null;
		}
	}
	
	
	
	
	/**user1鼠标悬停显示设备的信息
	 * 
	 * ****/
	@RequestMapping(value="/Manage/getEquipmentAllInfo")
	@ResponseBody
	public Equipment getEquipmentAllInfo(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getEquipmentAllInfo");
		Gson gson=new Gson();
		WarningBo bo=new WarningBo();
		ReqgetEquipmentAllInfo req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetEquipmentAllInfo.class);
			return bo.getEquipmentAllInfo(req);
	}
	
	
	/**user2 副表  user2点击核实，插入核实信息到表faultInfo_user2*/
	@RequestMapping(value="/Manage/insertFaultInfo_user2")
	@ResponseBody
	public ResInfo insertFaultInfo_user2(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/insertFaultInfo_user2");
		Gson gson=new Gson();
		ReqinsertFaultInfo_user2 req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqinsertFaultInfo_user2.class);
		if(req.getId()!=0){
			return wb.insertFault_user2(req);
		}else{
			return null;
		}
	}
	
	/**因为warning中的id和faultInfo_user2表中的id是一样的，user3点击详情获取warning的id，
	 * 通过id找到faultInfo_user2 中的数据，显示到详情中，等待审定
	 * user4通用*/
	@RequestMapping(value="/Manage/getFaultInfo_user2ById")
	@ResponseBody
	public ResFaultInfo_user2 getFaultInfo_user2ById(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getFaultInfo_user2ById");
		Gson gson=new Gson();
		WarningBo bo=new WarningBo();
		ReqgetFaultInfo_user2ById req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetFaultInfo_user2ById.class);
		if(req.getId()!=0){
			return bo.getFaultInfo_user2ById(req);
		}else{
			return null;
		}
	}
	/**user3进行审定  点击审定按钮    和终止流程按钮*/
	@RequestMapping(value="/Manage/updateFaultInfo_user2ById")
	@ResponseBody
	public ResInfo updateFaultInfo_user2ById(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/updateFaultInfo_user2ById");
		Gson gson=new Gson();
		WarningBo bo=new WarningBo();
		RequpdateFaultInfo_user2ById req=gson.fromJson(ReqUtils.readreqtoObject(request), RequpdateFaultInfo_user2ById.class);
		if((req.getId()!=0)&&(req.getOpinion().length()>4)){
			return bo.updateFaultInfo_user2ById(req);
		}else{
			return null;
		}
	}
	
	/***user4发通知单 通知单的id就是warning的id 和user2表的id一致
	 * 
	 * 通过接口 向维保中心发送通知单
	 * */
	@RequestMapping(value="/Manage/insertinform_user4")
	@ResponseBody
	public ResInfo insertinform_user4(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/insertinform_user4");
		Gson gson=new Gson();
		WarningBo bo=new WarningBo();
		Reqinsertinform_user4 req=gson.fromJson(ReqUtils.readreqtoObject(request), Reqinsertinform_user4.class);
		if(req.getId()!=0){
			return bo.insertinform_user4(req);
			/*ResInfo info=new ResInfo();
			info.setInfo("sucess");
			return info;*/
		}else{
			return null;
		}
	}
	
	
	
	
	
	
	/**告警管理user2的终止流程按钮*/
	@RequestMapping(value="/Manage/stopFlow")
	@ResponseBody
	public ResInfo stopFlow(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/stopFlow");
		Gson gson=new Gson();
		WarningBo bo=new WarningBo();
		ReqstopFlow req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqstopFlow.class);
		if(req.getId()!=0){
			return bo.stopFlow(req);
		}else{
			return null;
		}
	}
	/**告警管理的  user2,user3的 消息提示*//*
	public ResInfo message(Reqmessage req){
		return WarningBo.saveMessage(req);
	}
	*//**告警管理的user1的消息提示*//*
	public ResInfo confirmorcancel(Reqconfirmorcancel req){
		return WarningBo.saveuser1Message(req);
	}*/
	
	/**当有数据入库时，前台定时发请求，查看是否有数据入库**/
	@RequestMapping(value="/Manage/getInfoFromFlag")
	@ResponseBody
	public ResInfo getInfoFromFlag(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getInfoFromFlag");
		Gson gson=new Gson();
		MainPage_Refresh bo=new MainPage_Refresh();
		ReqgetInfoFromFlag req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetInfoFromFlag.class);
		return bo.getInfoRromFlag(req);
	}
	/**如果有数据入库，前端刷新后再次向我请求，将标志位设置为false*/
	@RequestMapping(value="/Manage/setInfoFromFlag")
	@ResponseBody
	public ResInfo setInfoFromFlag(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/setInfoFromFlag");
		Gson gson=new Gson();
		MainPage_Refresh bo=new MainPage_Refresh();
		ReqsetInfoFromFlag req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqsetInfoFromFlag.class);
		return bo.setInfoRromFlag(req);
	}
 
	
	/**返回线路健康状况*/
	@RequestMapping(value="/Manage/getLineHealth")
	@ResponseBody
	public ResLineHealth getLineHealth(HttpServletRequest request,HttpServletResponse response) {
		log.info("/Manage/getLineHealth");
		Gson gson=new Gson();
		WarningBo bo=new WarningBo();
		ReqgetLineHealth req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetLineHealth.class);
		return bo.getLineHealth(req);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	
	
	/** 前台的select options 查询按钮内容后台生成*/
	public ResSystemName getSystemName(ReqgetSystemName req){
		return wb.findsystemName(req);
	}
	
	public ResEquipmentName getEquipmentName(ReqgetEquipmentName req){
		return wb.findequipmentName(req);
	}

}
