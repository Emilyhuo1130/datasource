package com.ucs.rcm.protocol;

import net.sf.json.JSONObject;

import com.ucs.rcm.business.ChattelanalyseManager;
import com.ucs.rcm.business.bo.Chattelanalysebo;
import com.ucs.rcm.business.bo.Page;
import com.ucs.rcm.reqobj.ChattelanalyseQueryObj;
import com.ucs.rcm.reqobj.HttpRequest;
import com.ucs.rcm.reqobj.OperateindexQueryObj;
import com.ucs.rcm.reqobj.ReqgetAllEquipmentTree;
import com.ucs.rcm.reqobj.ReqgetChattelanalyseList;
import com.ucs.rcm.reqobj.ReqgetEquipmentName;
import com.ucs.rcm.reqobj.ReqgetEquipmentSubComponent;
import com.ucs.rcm.reqobj.ReqgetEquipmentinfo;
import com.ucs.rcm.reqobj.ReqgetFaultSubComponent;
import com.ucs.rcm.reqobj.ReqgetOperateindexList;
import com.ucs.rcm.reqobj.ReqgetSystemName;
import com.ucs.rcm.reqobj.ReqgetTendencyList;
import com.ucs.rcm.reqobj.ReqshowOneStationsubComponent;
import com.ucs.rcm.ztree.allStationEquipmentTree.GetChattelanalyseEquipmentTree;
import com.ucs.rcm.ztree.warningEquipmentTree.GetOneStationEquipmentree;
public class Chattelanalyse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//在线监测
		//getChattelanalyses();
		
		//运营指数
		//getOperateindexList();
		
		//设备树
		//getEquipmentTree();
		
		//按照条件插叙设备
		//getEquipmentinfo();
		
		//按照条件查询故障部件
		//getFaultinfo();
		
		//故障树
		//getfaultTree();
		
		
		//options
		//findsystemname();
		
		//findequipment();
		
		
		//findequipmentSubCoponent();
		
		
		//findFaultSubCoponent();
		
		/**实件分析的全车站的设备树*/
		//getReqgetAllEquipmentTree();
		
		/**user2 user 3 user4 详情中 指定的站的设备**/
		//GetOneStationEquipmentree();
		
		//趋势分析数据
		getTendencyList();
	}
	
	

	private static void getTendencyList() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getTendencyList");
		json.setType("ChattelanalyseManager");
		ReqgetTendencyList reqs=new ReqgetTendencyList();
		
		reqs.setComponentId("10.29.02.24.001");
		json.setReq(reqs);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);

		Chattelanalysebo bo = new Chattelanalysebo();
		result = JSONObject.fromObject(bo.getTendencyList(reqs)).toString();
		System.out.println(result);
	}



	private static void GetOneStationEquipmentree() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("showOneStationsubComponent");
		json.setType("ChattelanalyseManager");
		ReqshowOneStationsubComponent reqs=new ReqshowOneStationsubComponent();
		reqs.setStationName("航中路站");
		reqs.setSystemName("信号系统");
		reqs.setSubComponentId("10.29.08.01");
		json.setReq(reqs);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);

		GetOneStationEquipmentree ge = new GetOneStationEquipmentree();
		result = JSONObject.fromObject(ge.getonestationEquipmentTree(reqs)).toString();
		System.out.println(result);
	}



	private static void getReqgetAllEquipmentTree() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getReqgetAllEquipmentTree");
		json.setType("ChattelanalyseManager");
		ReqgetAllEquipmentTree reqs=new ReqgetAllEquipmentTree();
		
		json.setReq(reqs);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);

		GetChattelanalyseEquipmentTree getChattelanalyseEquipmentTree= new GetChattelanalyseEquipmentTree();
		result = JSONObject.fromObject(getChattelanalyseEquipmentTree.getAllEquipmentTree(reqs)).toString();
		System.out.println(result);
	}



	private static void getFaultinfo() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getFaultinfo");
		json.setType("ChattelanalyseManager");
		ReqgetEquipmentinfo reqs=new ReqgetEquipmentinfo();
		
		
		reqs.setSystemName("信号系统");
		reqs.setEquipmentName("轨道区段");
		reqs.setSubcomponent("50HZ轨道");
		//reqs.setId(3);
		json.setReq(reqs);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);

	
		//result = JSONObject.fromObject(Chattelanalysebo.getFaultTypeinfo(reqs)).toString();
		System.out.println(result);
		
	}



	private static void getEquipmentinfo() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getEquipmentinfo");
		json.setType("ChattelanalyseManager");
		ReqgetEquipmentinfo reqs=new ReqgetEquipmentinfo();
		
		
		/*reqs.setSystemName("信号系统");
		reqs.setEquipmentName("轨道区段");
		reqs.setSubcomponent("T01A");*/
		reqs.setId(3);
		json.setReq(reqs);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);

	
		result = JSONObject.fromObject(ChattelanalyseManager.getEquipmentinfo(reqs)).toString();
		System.out.println(result);
	}



	private static void getOperateindexList() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getOperateindexList");
		json.setType("ChattelanalyseManager");
		ReqgetOperateindexList reqs=new ReqgetOperateindexList();
		Page page=new Page();
		page.setPageCount(10);
		page.setPageNo(1);
		reqs.setPage(page);
		OperateindexQueryObj obj=new OperateindexQueryObj();
		obj.setLineNo("10");
		reqs.setQuery(obj);
		json.setReq(reqs);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);

		Chattelanalysebo bo = new Chattelanalysebo();
		result = JSONObject.fromObject(bo.getOperateindexList(reqs)).toString();
		System.out.println(result);
	}



	private static void getChattelanalyses() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getChattelanalyseList");
		json.setType("ChattelanalyseManager");
		ReqgetChattelanalyseList reqs=new ReqgetChattelanalyseList();
		Page page=new Page();
		page.setPageCount(10);
		page.setPageNo(1);
		reqs.setPage(page);
		ChattelanalyseQueryObj obj=new ChattelanalyseQueryObj();
		obj.setLineNo("10");
		reqs.setQuery(obj);
		json.setReq(reqs);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);

		Chattelanalysebo bo = new Chattelanalysebo();
		result = JSONObject.fromObject(bo.getChattelanalyses(reqs)).toString();
		System.out.println(result);
		
	}



	
	
	
	
	
	
	//options
	public static void findsystemname(){
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getSystemName");
		json.setType("ChattelanalyseManager");
		ReqgetSystemName sys=new ReqgetSystemName();
		sys.setReqString("names");
		json.setReq(sys);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);
		Chattelanalysebo bo = new Chattelanalysebo();
		result = JSONObject.fromObject(bo.findsystemName(sys)).toString();
		System.out.println(result);
	}
	private static void findequipmentSubCoponent() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getEquipmentSubComponent");
		json.setType("ChattelanalyseManager");
		ReqgetEquipmentSubComponent sys=new ReqgetEquipmentSubComponent();
		sys.setSystemName("信号系统");
		sys.setEquipmentName("轨旁设备");
		json.setReq(sys);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);
		Chattelanalysebo bo = new Chattelanalysebo();
		result = JSONObject.fromObject(bo.findEquipmentSubComponentName(sys)).toString();
		System.out.println(result);
		
	}
	private static void findequipment() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getEquipmentName");
		json.setType("ChattelanalyseManager");
		ReqgetEquipmentName sys=new ReqgetEquipmentName();
		sys.setSystemName("信号系统");
		json.setReq(sys);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);
		Chattelanalysebo bo = new Chattelanalysebo();
		result = JSONObject.fromObject(bo.findequipmentName(sys)).toString();
		System.out.println(result);
	}
	private static void findFaultSubCoponent() {
		// TODO Auto-generated method stub
		HttpRequest json=new HttpRequest();
		String result;
		json.setMethod("getFaultName");
		json.setType("ChattelanalyseManager");
		ReqgetFaultSubComponent sys=new ReqgetFaultSubComponent();
		sys.setSystemName("信号系统");
		sys.setEquipmentName("轨旁设备");
		json.setReq(sys);
		result = JSONObject.fromObject(json).toString();
		System.out.println(result);
		Chattelanalysebo bo = new Chattelanalysebo();
		result = JSONObject.fromObject(bo.findFaultSubComponentName(sys)).toString();
		System.out.println(result);
	}

}
