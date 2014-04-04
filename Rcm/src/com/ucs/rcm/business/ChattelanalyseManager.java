package com.ucs.rcm.business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.ucs.rcm.business.bo.Chattelanalysebo;
import com.ucs.rcm.pdrdataUtils.ChoseFaultType;
import com.ucs.rcm.reqobj.ReqgetAllEquipmentTree;
import com.ucs.rcm.reqobj.ReqgetChattelanalyseList;
import com.ucs.rcm.reqobj.ReqgetEquipmentName;
import com.ucs.rcm.reqobj.ReqgetEquipmentSubComponent;
import com.ucs.rcm.reqobj.ReqgetEquipmentTree;
import com.ucs.rcm.reqobj.ReqgetEquipmentinfo;
import com.ucs.rcm.reqobj.ReqgetFaultSubComponent;
import com.ucs.rcm.reqobj.ReqgetFaultTree;
import com.ucs.rcm.reqobj.ReqgetFaultinfo;
import com.ucs.rcm.reqobj.ReqgetOperateindexList;
import com.ucs.rcm.reqobj.ReqgetSystemName;
import com.ucs.rcm.reqobj.ReqgetTendencyList;
import com.ucs.rcm.reqobj.Reqgetmapview;
import com.ucs.rcm.reqobj.ReqgetstationFaultTree;
import com.ucs.rcm.reqobj.ReqsearchEquipmentInfo;
import com.ucs.rcm.reqobj.ReqshowOneStationsubComponent;
import com.ucs.rcm.responseObj.FaultType;
import com.ucs.rcm.responseObj.ResOperateindexList;
import com.ucs.rcm.responseObj.ResChattelanalyseList;
import com.ucs.rcm.responseObj.ResEquipmentName;
import com.ucs.rcm.responseObj.ResEquipmentSubComponentName;
import com.ucs.rcm.responseObj.ResFaultSubComponentName;
import com.ucs.rcm.responseObj.ResMapView;
import com.ucs.rcm.responseObj.ResTendencyList;
import com.ucs.rcm.responseObj.ResSystemName;
import com.ucs.rcm.responseObj.Subcomponent;
import com.ucs.rcm.responseObj.SubcomponentInfo;
import com.ucs.rcm.ztree.allStationEquipmentTree.GetChattelanalyseEquipmentTree;
import com.ucs.rcm.ztree.allStationEquipmentTree.ResAllEquipmentTree;
import com.ucs.rcm.ztree.strategyDesign.ResFaultZTree;
import com.ucs.rcm.ztree.strategyDesign.ResZTree;
import com.ucs.rcm.ztree.strategyDesign.ZTreegetEquipment;
import com.ucs.rcm.ztree.strategyDesign.ZTreegetFault;
import com.ucs.rcm.ztree.warningEquipmentTree.GetOneStationEquipmentree;
import com.ucs.rcm.ztree.warningEquipmentTree.ResOneStationEquipmentTree;
import com.ucs.rcm.ztree.warningFaultTree.GetStationFaultTree;
import com.ucs.rcm.ztree.warningFaultTree.ResFaultList;

/**实件分析*/
@Controller

public class ChattelanalyseManager {
	static Logger log = Logger.getLogger(ChattelanalyseManager.class);
	Gson gson=new Gson();
	/**健康指数-设施指数*/
	@RequestMapping(value="/Manage/getChattelanalyseList")
	@ResponseBody
	public ResChattelanalyseList getChattelanalyseList(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getChattelanalyseList");
		Chattelanalysebo bo = new Chattelanalysebo();
		ReqgetChattelanalyseList query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetChattelanalyseList.class);
		ResChattelanalyseList r = bo.getChattelanalyses(query);
		
		log.info("健康指数-设施指数返回给前台的数据+"+gson.toJson(r));
		return r;
	}
	//运营指数
	@RequestMapping(value="/Manage/getOperateindexList")
	@ResponseBody
	public ResOperateindexList getOperateindexList(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getOperateindexList");
		Gson gson=new Gson();
		Chattelanalysebo bo = new Chattelanalysebo();
		ReqgetOperateindexList query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetOperateindexList.class);
		return bo.getOperateindexList(query); 
	}
	/**调用接口与卡斯柯获取在途试图**/
	public ResMapView getmapview(Reqgetmapview query){
		//return Chattelanalysebo.getOperateindexList(query);
		
		//TODO
		ResMapView map=new ResMapView();
		map.setMapURL("http://pic17.nipic.com/20111017/8621548_172349630163_2.jpg");
		return map;
		
	}
	
	/***趋势分析 模拟数据*/
	@RequestMapping(value="/Manage/getTendencyList")
	@ResponseBody
	public ResTendencyList getTendencyList(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getTendencyList********");
		
		Chattelanalysebo bo = new Chattelanalysebo();
		ReqgetTendencyList query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetTendencyList.class);
		
		return bo.getTendencyList_old(query);
	}
	
	/***从卡斯柯数据库获取数据 显示趋势分析***/
	/*@RequestMapping(value="/Manage/getTendencyList")
	@ResponseBody
	public ResTendencyList getTendencyList(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getTendencyList********从卡斯柯数据库获取数据 显示趋势分析");
		Gson gson=new Gson();
		Chattelanalysebo bo = new Chattelanalysebo();
		ReqgetTendencyList query=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetTendencyList.class);
		return bo.getTendencyList(query);
	}*/
	
	
	
	
	//设备按条件查询 
	public static Subcomponent getEquipmentinfo(ReqgetEquipmentinfo req){
		if(((req.getSystemName()!=null)&&(req.getEquipmentName()!=null)&&(req.getSubcomponent()!=null))||req.getId()!=0){
			Chattelanalysebo bo = new Chattelanalysebo();
			return bo.getEquipmentinfo(req);
		}else{
			return null;
		}
	}
	//故障条件查询
	@RequestMapping(value="/Manage/getFaultinfo")
	@ResponseBody
	public FaultType getFaultinfo(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getFaultinfo");
		Gson gson=new Gson();
		Chattelanalysebo bo = new Chattelanalysebo();
		ReqgetFaultinfo req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetFaultinfo.class);
		if(((req.getSystemName()!=null)&&(req.getEquipmentName()!=null)&&(req.getSubcomponent()!=null))||(req.getId()!=0)){
			return bo.getFaultTypeinfo(req);
		}else if(req.getFaultType()!=null){
			return bo.getFaultTypeinfo(req);
		}else{
			return null;
		}
	}
	
	
	/**ZTree 设备树*/
	public ResZTree getEquipmentTree(ReqgetEquipmentTree req){
		ZTreegetEquipment ze = new ZTreegetEquipment();
		return ze.getEquipmentTree(req);
	}
	/**ZTree 故障树**/
	@RequestMapping(value="/Manage/getFaultTree")
	@ResponseBody
	public ResFaultZTree getFaultTree(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getFaultTree");
		Gson gson=new Gson();
		ZTreegetFault bo = new ZTreegetFault();
		ReqgetFaultTree req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetFaultTree.class);
		return bo.getFaultTree(req);
	}
	
	
	
	/**实件分析的全车站的设备树*/
	@RequestMapping(value="/Manage/getAllEquipmentTree")
	@ResponseBody
	public ResAllEquipmentTree getAllEquipmentTree(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getAllEquipmentTree");
		Gson gson=new Gson();
		GetChattelanalyseEquipmentTree bo = new GetChattelanalyseEquipmentTree();
		ReqgetAllEquipmentTree req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetAllEquipmentTree.class);
		return bo.getAllEquipmentTree(req);
	}
	
	/**实件分析全车站按照条件查询指定部件信息**/
	@RequestMapping(value="/Manage/searchEquipmentInfo")
	@ResponseBody
	public SubcomponentInfo searchEquipmentInfo(HttpServletRequest request,HttpServletResponse response ){
		log.info("/Manage/searchEquipmentInfo");
		Gson gson=new Gson();
		Chattelanalysebo bo = new Chattelanalysebo();
		ReqsearchEquipmentInfo req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqsearchEquipmentInfo.class);
		if((req.getId()!=0)||((req.getStationName()!=null)&&(req.getSystemName()!=null)&&(req.getComponent()!=null)&&(req.getSubComponent()!=null))){
			return bo.searchEquipmentInfo(req);
		}else{
			return null;
		}
	}
	
	
	/**告警管理中所用到的设备树，根据不同的车站显示不同的设备*/
	@RequestMapping(value="/Manage/showOneStationsubComponent")
	@ResponseBody
	public ResOneStationEquipmentTree showOneStationsubComponent(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/showOneStationsubComponent");
		Gson gson=new Gson();
		GetOneStationEquipmentree bo = new GetOneStationEquipmentree();
		ReqshowOneStationsubComponent req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqshowOneStationsubComponent.class);
		if(req.getStationName()!=null&&req.getSystemName()!=null&&req.getSubComponentId()!=null){
			return bo.getonestationEquipmentTree(req);
		}else{
			return null;
		}
	}
	
	/**告警管理中所用到的故障树，*/
	@RequestMapping(value="/Manage/getstationFaultTree")
	@ResponseBody
	public ResFaultList getstationFaultTree(HttpServletRequest request,HttpServletResponse response){
		log.info("/Manage/getstationFaultTree");
		Gson gson=new Gson();
		GetStationFaultTree bo = new GetStationFaultTree();
		ReqgetstationFaultTree req=gson.fromJson(ReqUtils.readreqtoObject(request), ReqgetstationFaultTree.class);
		if((req.getEquipmentId()!=null)&&(req.getEquipmentId().trim().length()>0)){
			log.warn("**************************操作用户："+req.getUser());
			
			if((req.getUser()!=null)&&(req.getUser().trim().equals("user3"))){
				if(req.getFlag().trim().equals("Yes")){
					log.warn("开始寻找故障原因");
					String type=ChoseFaultType.getFaultType(req);
					req.setFaultType(type);
					log.warn("************"+type+"**************");
				}
			}
			
			
			log.warn("*******故障原因寻找结束------开始定位故障树****************");
			return bo.getstationFaultTree(req);
			
		}else{
			return null;
		}
		
		
		
		
	}
	
	
	
	
	
	//查询条件options的读取。从数据库读取写到页面
	//options
	public ResEquipmentName getEquipmentName(ReqgetEquipmentName req){
		Chattelanalysebo bo = new Chattelanalysebo();
		return bo.findequipmentName(req);
	}
	public ResSystemName getSystemName(ReqgetSystemName req){
		Chattelanalysebo bo = new Chattelanalysebo();
		return bo.findsystemName(req);
	}
	public ResEquipmentSubComponentName getEquipmentSubComponent(ReqgetEquipmentSubComponent req){
		Chattelanalysebo bo = new Chattelanalysebo();
		return bo.findEquipmentSubComponentName(req);
	}
	public ResFaultSubComponentName getFaultSubComponent(ReqgetFaultSubComponent req){
		Chattelanalysebo bo = new Chattelanalysebo();
		return bo.findFaultSubComponentName(req);
	}
}
