/**测试hibernate方法*/
package com.ucs.rcm.test;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import org.apache.log4j.Logger;

import com.ucs.rcm.business.bo.BaseHibernateDAO;
import com.ucs.rcm.business.bo.Page;
import com.ucs.rcm.business.bo.WarningBo;
import com.ucs.rcm.pojo.Equipmenttreeinfo;
import com.ucs.rcm.pojo.FaultinfoUser2;
import com.ucs.rcm.pojo.Warning;
import com.ucs.rcm.reqobj.PlanWarningQueryObj;
import com.ucs.rcm.reqobj.ReqcommitPlanWaringById;
import com.ucs.rcm.reqobj.Reqconfirmorcancel;
import com.ucs.rcm.reqobj.ReqgetDataShow;
import com.ucs.rcm.reqobj.ReqgetEquipmentName;
import com.ucs.rcm.reqobj.ReqgetFaultInfo_user2ById;
import com.ucs.rcm.reqobj.ReqgetIds;
import com.ucs.rcm.reqobj.ReqgetLineHealth;
import com.ucs.rcm.reqobj.ReqgetPlanWaringList;
import com.ucs.rcm.reqobj.ReqgetSystemName;
import com.ucs.rcm.reqobj.ReqgetWaringList;
import com.ucs.rcm.reqobj.ReqgetWaringListBystatments;
import com.ucs.rcm.reqobj.ReqgetWarningCounting;
import com.ucs.rcm.reqobj.ReqgetthisDayLineHealth;
import com.ucs.rcm.reqobj.ReqinsertFaultInfo_user2;
import com.ucs.rcm.reqobj.Reqinsertinform_user4;
import com.ucs.rcm.reqobj.ReqstopFlow;
import com.ucs.rcm.reqobj.RequpdateFaultInfo_user2ById;
import com.ucs.rcm.reqobj.ReqwarningOperate;
import com.ucs.rcm.reqobj.WarningQueryObj;
import com.ucs.rcm.responseObj.ResPlanWaringList;

public class HibernateHqlTest extends BaseHibernateDAO{
 //Logger log = Logger.getLogger(HibernateHqlTest.class);
	
//@Test
		//测试首页告警
	WarningBo wb = new WarningBo();
	Session session = getSession();
public void  find1() {
	ReqgetWaringList query = new ReqgetWaringList();
	Page page = new Page();
	page.setPageCount(5);
	page.setPageNo(1);
	WarningQueryObj wq = new WarningQueryObj();
	wq.setEndDate("2014-9-9");
	wq.setEquipmentname("");
	wq.setLineNo("");
	wq.setStartDate("2010-1-1");
	wq.setStationName("");
	wq.setStatments("0");
	wq.setSystemName("");
	wq.setWarningType("");
	wq.setWarningTypeLevel("");
	query.setPage(page);
	query.setQuery(wq);
	wb.getWarnins(query);
	/*String teststr = "123and     and456";
	String newstr = StringUtils.replaceByRegExp("and(\\s*)and", teststr, "and");*/
	
	}

//@Test
	//测试计划修预警
public void find2(){
	ReqgetPlanWaringList query = new ReqgetPlanWaringList();
	Page page = new Page();
	page.setPageCount(50);
	page.setPageNo(1);
	PlanWarningQueryObj pwqo = new PlanWarningQueryObj();
	pwqo.setEndDate("2014-09-09");
	pwqo.setEquipmentname("");
	pwqo.setLineNo("");
	pwqo.setStartDate("2013-01-01");
	pwqo.setStationName("");
	pwqo.setStatments("1");
	pwqo.setSystemName("");
	pwqo.setWarningTypeLevel("");
	
	query.setPage(page);
	query.setQuery(pwqo);
	wb.getPlanWaringList(query);
	
	
}


//通过id来更新计划修的状态
	//@Test
	public void testPlanMaintainUpdate(){
		ReqcommitPlanWaringById query = new ReqcommitPlanWaringById();
		query.setId("1");
		query.setEquipmentId("10.29.08.01.001");
		wb.commitPlanWaringById(query);
		
		
	}
	
//通过id来获得告警信息
	//@Test
	public void testFindWarninginfoByid(){
		ReqgetIds req = new ReqgetIds();
		req.setId(1);
		wb.getWarningInfoById(req);
		
		
	}
//更新操作(待测)
	//@Test
	public void testUpdate(){
		ReqwarningOperate reqwarningOperate = new ReqwarningOperate();
	
		
		
	}
//返回线路健康指数
	//@Test
	public void testHealthLines(){
		ReqgetLineHealth req = new ReqgetLineHealth();
		req.setReqString("");
		wb.getLineHealth(req);
		
		
	}
	//@Test
	public void testInsertFault_user2(){
		ReqinsertFaultInfo_user2 req = new ReqinsertFaultInfo_user2();
		req.setId(2);
		req.setEquipmentid("10.29.08.01.001");
		req.setEquipmentname("隧道风机TVF_I1");
		req.setSystemname("综合监控系统");
		req.setWarningType("故障告警");
		req.setWarningTypeLevel("1");
		req.setFultDescription("隧道风机TVF_I1故障");
		req.setLineNo("10");
		req.setStationName("龙柏新村");
		req.setWarningDate("2013-12-11 09:14:40");
		req.setFaultCause("供电电源失电");
		req.setInfluence("风机故障对于轨道交通运营造成的影响相对较小，不影响列车的正常运行，为避免造成大的影响，故障出现后应立即维修。");
		req.setMaintenancePolicy("检查设备供电电源是否失电");
		req.setOpinion("333||333||3333");
		req.setWarningId("09834543");
		
		wb.insertFault_user2(req);
		
		/*WarningBo wb=new WarningBo();
		Session session = wb.getSession();
		//String   select = "select f.id from FaultinfoUser2 f where f.id=8";
		String find="select new Equipmenttreeinfo(e.maintenanceId,e.subsystemname) from Equipmenttreeinfo e where e.subcomponentid='10.30.03.04.008'";
		Query query = session.createQuery(find);
		Equipmenttreeinfo e =(Equipmenttreeinfo) query.iterate().next();
		//String   select = "select f.id from FaultinfoUser2 f where f.id=8";
	//	int id = (Integer)session.createQuery(select).iterate().next();
		System.out.println(e.getSubsystemname()+","+e.getMaintenanceId());*/
		
		
		
		
		
	}
	
	//获取设备详细信息改为hibernate
	//@Test
	public void testgetequipmentDescription(){
		wb.getequipmentDescription(24);
		
		
	}
	
	//获得故障信息
	//@Test
	public void testgetFaultInfo_user2ById(){
		ReqgetFaultInfo_user2ById req = new ReqgetFaultInfo_user2ById();
		req.setId(3);
		wb.getFaultInfo_user2ById(req);
		/*String hql="from FaultinfoUser2 f where f.id='3'";
		WarningBo wb = new WarningBo();
		Session session = wb.getSession();
		Query query = session.createQuery(hql);
		List<FaultinfoUser2> fu = query.list();
		for(FaultinfoUser2 fu_e : fu){
			System.out.println(fu_e.getEquipmentDescription());
			
			
		}*/
		
		
		
	}
	
	//updateFaultInfo_user2ById
	//@Test
	public void testUpdateFaultInfo_user2ById(){
		RequpdateFaultInfo_user2ById req = new RequpdateFaultInfo_user2ById();
		req.setId(1);
		req.setOk("goto");
		req.setOpinion("");
		wb.updateFaultInfo_user2ById(req);
	}
	//insertinform_user4
	//@Test
	public void testInsertinform_user4(){
		Reqinsertinform_user4 req = new Reqinsertinform_user4();
		req.setId(3);
		req.setJobNumber("ODD7369633");
		req.setOperator("小王");
		req.setStarTime("2013-12-25 09:50:46");
		req.setLineNo("10");
		req.setStationName("龙柏新村");
		req.setSystemName("信号系统");
		req.setSubSystemName("轨旁设备");
		req.setEquipmentName("给排水系统");
		req.setEquipmentId("10.29.03.04.008");
		req.setWarningTypeLevel("1");
		req.setHealthLevel("1");
		req.setFultDescription("区间水泵故障");
		req.setFaultCause("sssdsd");
		req.setInfluence("水泵故障对于轨道交通运营造成的影响相对较小，不影响列车的正常运行，为避免造成大的影响，故障出现后应立即维修。");
		req.setMainTenancePolicy("1.检查潜水泵轴承因安装和使用寿命等原因");
		req.setMileage("23455.00");
		wb.insertinform_user4(req);
		/*String findequipmentDescription_hql = "select new Warning(w.equipmentDescription,w.warningType) from Warning w where  w.id=3";
		WarningBo wb = new WarningBo();
		Session session = wb.getSession();
		Query query = session.createQuery(findequipmentDescription_hql);
		List<Warning> ws = query.list();
		for(Warning w : ws){
			System.out.println(w.getEquipmentDescription());
			
		}*/
	}
	
	//send_reportforms_requisition
	//@Test
	public void testsend_reportforms_requisition(){
		Reqinsertinform_user4 req = new Reqinsertinform_user4();
		req.setSystemName("信号系统");
		req.setSubSystemName("轨旁设备");
		req.setLineNo("10");
		req.setStationName("龙柏新村");
		req.setEquipmentName("给排水系统");
		req.setJobNumber("ODD7369633");
		req.setStarTime("2013-12-25 09:50:46");
		String[] ss={"0","1"};
		
		//WarningBo.send_reportforms_requisition(req, ss);
		
		
	}
	
	//makeservicefeedback
	//@Test
	public void testmakeservicefeedback(){
		Reqinsertinform_user4 req = new Reqinsertinform_user4();
		req.setEquipmentId("10.31.03.04.008");
		req.setEquipmentName("给排水系统");
		req.setSystemName("信号系统");
		req.setLineNo("10");
		req.setStationName("龙柏新村");
		req.setStarTime("2013-12-25 09:50:46");
		String[] ss = {"0"};
		wb.makeservicefeedback(req, ss);
		
	}
	/**终止流程*/
	//@Test
	public void teststopFlow(){
		ReqstopFlow req = new ReqstopFlow();
		req.setId(3);
		req.setStatments("0");
		req.setUsername("user3");
		wb.stopFlow(req);
		
	}
	//告警页面的按0,1,2,3四个状态  和告警类型四个用户的代办事物的查找ok
	//@Test
	public void testfindWaringListBystatments(){
		ReqgetWaringListBystatments query = new ReqgetWaringListBystatments();
		Page page = new Page();
		page.setPageCount(4);
		page.setPageNo(1);
		query.setPage(page);
		query.setStatments("0");
		query.setWarningType("故障告警");
		wb.findWaringListBystatments(query);
		
		/*WarningBo wb = new WarningBo();
		Session session = wb.getSession();
		String hql = "from Warning w where w.statments in(0) and w.warningType='故障告警'  order by w.warningDate desc";
		Query query = session.createQuery(hql);
		List<Warning> ws = query.list();
		for(Warning w : ws){
			System.out.println(w.getEquipmentname());
			
		}*/
	}
	//saveuser1Message
	//@Test
	public void testsaveuser1Message(){
		Reqconfirmorcancel req = new Reqconfirmorcancel();
		req.setMessage("测试用");
		req.setOk("goto");
		req.setTime("2014-03-31 1:23:00");
		req.setWarningId("0");
		wb.saveuser1Message(req);
		
	}
	///***时间到了生成计划修预警()**/
	//@Test
	public void testcreatePlanWarning(){
		wb.createPlanWarning();
		
	}
	
	/***首页中的某条线的一天的健康状态***/
	//@Test
	public void testResDayLineHealth(){
		ReqgetthisDayLineHealth query = new ReqgetthisDayLineHealth();
		query.setLineNo("10");
		wb.getthisDayLineHealth(query);
		
	}
	/***每一个小时取一次的一条线的健康值()**/
	//@Test
	public void testreadHealrhLevel(){
		wb.readHealrhLevel();
		
		
		
	}
	/****当前故障告警、预警告警、计划修告警当前未被处理的有几条，需要有数字醒目表示。(未测)**/
	
	//@Test
	public void testgetcountofNeedtoDO(){
		wb.getcountofNeedtoDO();
		
	}
	/****推演时显示追忆数据()**/
	//@Test
	public void testgetDataShow(){
		ReqgetDataShow req = new ReqgetDataShow();
		req.setEquipmentId("10.29.03.04.008 ");
		wb.getDataShow(req);
		
		
	}
	//user2核实
	//@Test
	public void testfindequipmentName(){
		ReqgetEquipmentName req = new ReqgetEquipmentName();
		req.setSystemName("综合监控系统");
		wb.findequipmentName(req);
		
	}
	
	//获取系统名称
	//@Test
	public void testfindsystemName(){
		ReqgetSystemName req = new ReqgetSystemName();
		wb.findsystemName(req);
		
	}
	//告警数量
	@Test
	public void tesgtgetWarningCounting(){
		ReqgetWarningCounting req = new ReqgetWarningCounting();
		req.setReqString("");
		wb.getWarningCounting(req);
		
		
	}
	
	
	
	
	
	
}
