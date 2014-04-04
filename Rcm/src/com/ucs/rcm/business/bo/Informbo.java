package com.ucs.rcm.business.bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.db.DBUtil;
import com.ucs.rcm.pojo.InformPlan;
import com.ucs.rcm.pojo.MaintainCoupleBack;
import com.ucs.rcm.pojo.MaintainPlanTable;
import com.ucs.rcm.reqobj.InformQueryObj;
import com.ucs.rcm.reqobj.MaintainQueryObj;
import com.ucs.rcm.reqobj.PlanInformQueryObj;
import com.ucs.rcm.reqobj.Plan_TableQueryObj;
import com.ucs.rcm.reqobj.ReqgetInformList;
import com.ucs.rcm.reqobj.ReqgetMaintain_couple_back;
import com.ucs.rcm.reqobj.ReqgetPlanInformList;
import com.ucs.rcm.reqobj.Reqgetmaintain_plan_table;
import com.ucs.rcm.responseObj.Informinfo;
import com.ucs.rcm.responseObj.Plan_table;
import com.ucs.rcm.responseObj.ResInformList;
import com.ucs.rcm.responseObj.ResPlanInformList;
import com.ucs.rcm.responseObj.Res_plan_tableList;

import com.ucs.rcm.responseObj.Res_maintain_couple_back;

public class Informbo extends BaseHibernateDAO{
	static Logger log = Logger.getLogger(Informbo.class);
	Gson gson = new Gson();
	public  static ResInformList getInformList(ReqgetInformList query) {
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());

		ResInformList res = new ResInformList();
		List<Informinfo> informList = new ArrayList<Informinfo>();
		//根据入参，确定查询语句
		//String sqls = "select * from inform_user4 where 1=1 "+getSqls(query);
		String sql="select * from inform_user4 where 1=1 "+getSqls(query);
		sql=sql+" limit "+pageCount*(pageNo-1)+","+pageCount;
		log.warn(sql);
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		

		try {
			//链接数据库
			//做查询
			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){

					Informinfo bo = new Informinfo();
					bo.setId(rs.getInt("id"));
					bo.setJobNumber(rs.getString("jobNumber"));
					bo.setOperator(rs.getString("operator"));
					bo.setStarTime(rs.getString("starTime"));
					bo.setLineNo(rs.getString("lineNo"));
					bo.setStationName(rs.getString("stationName"));
					bo.setSystemName(rs.getString("systemName"));
					bo.setSubSystemName(rs.getString("subSystemName"));
					bo.setEquipmentName(rs.getString("equipmentName"));
					bo.setEquipmentId(rs.getString("equipmentId"));
					bo.setWarningTypeLevel(rs.getString("warningTypeLevel"));
					bo.setHealthLevel(rs.getString("healthLevel"));
					bo.setFultDescription(rs.getString("fultDescription"));
					bo.setFaultCause(rs.getString("faultCause"));
					bo.setInfluence(rs.getString("influence"));
					bo.setMainTenancePolicy(rs.getString("mainTenancePolicy"));
					bo.setEquipmentDescription(rs.getString("equipmentDescription"));
					bo.setWarningType(rs.getString("warningType"));
					
					informList.add(bo);
					
				}
			
			//关闭流
			rs.close();
			ps.close();
		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));

		}
		
		
		//获取总记录数
		try{
			ps = conn.prepareStatement("select count(*) as count from inform_user4 where 1=1 "+getSqls(query));
			rs = ps.executeQuery();
			rs.next();
			totalCount = rs.getInt("count");
			rs.close();
			ps.close();
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));

		}
		//设置返回结果的总记录数
		res.setTotalCount(totalCount + "");
		// System.out.println("totalCount:" + totalCount);
		// System.out.println(res.getTotalCount());
		//计算总页数
		totalPage = totalCount / pageCount;
		if (totalCount % pageCount > 0) {
			totalPage = totalCount / pageCount + 1;
		}
		//设置返回结果的总页数
		res.setTotalPage(totalPage + "");
		// System.out.println("totalPage:" + res.getTotalPage());
		//设置返回结果的总警告记录
		res.setInformList(informList);
		// System.out.println("warningList.size():" + warningList.size());
		return res;
	}

	private static String getSqls(ReqgetInformList query) {
		InformQueryObj req=query.getQuery();
		StringBuffer buf=new StringBuffer();
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and SystemName= '"+req.getSystemName()+"' ");
		}
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and StationName= '"+req.getStationName()+"' ");
		}
		if((req.getLineNO()!=null)&&(req.getLineNO().trim().length()>0)){
			buf.append(" and LineNO= '"+req.getLineNO()+"' ");
		}
		if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
			buf.append(" and EquipmentName like '%"+req.getEquipmentName()+"%' ");
		}
		if((req.getWarningTypeLevel()!=null)&&(req.getWarningTypeLevel().trim().length()>0)){
			buf.append(" and WarningTypeLevel= '"+req.getWarningTypeLevel()+"' ");
		}
		/**添加了按类型查询*/
		if((req.getWarningType()!=null)&&(req.getWarningType().trim().length()>0)){
			buf.append(" and warningType= '"+req.getWarningType()+"' ");
		}
		if((req.getStarTime()!=null)&&(req.getStarTime().trim().length()>0)){
			buf.append(" and starTime>= '"+req.getStarTime()+"' ");
		}
		if((req.getEndTime()!=null)&&(req.getEndTime().trim().length()>0)){
			buf.append(" and starTime<= '"+req.getEndTime()+"' ");
		}
		buf.append(" and statments = '0' ");
		return buf.toString();
	}

	
	/****维修反馈记录**/
	public  Res_maintain_couple_back getMaintain_couple_back(
			ReqgetMaintain_couple_back query) {
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());

		Res_maintain_couple_back res = new Res_maintain_couple_back();
		List<MaintainCoupleBack> informList = new ArrayList<MaintainCoupleBack>();
		//根据入参，确定查询语句
		//String sqls = "select * from maintain_couple_back where 1=1 "+getSqls(query);
		
		String sql="from MaintainCoupleBack m where 1=1"+getHqls(query,"m");
		//sql=sql+" limit "+pageCount*(pageNo-1)+","+pageCount;
		log.warn(sql);
		
		
	
		try {
			Session session = getSession();
			session.clear();
			//链接数据库
			//做查询
			Query query_0 = session.createQuery(sql).setFirstResult(pageCount*(pageNo-1)).setMaxResults(pageCount);
			//返回数据库查询结果
			@SuppressWarnings("unchecked")
			Iterator<MaintainCoupleBack>  it = query_0.list().iterator();
				while(it.hasNext()){
					MaintainCoupleBack bo = new MaintainCoupleBack();
					bo=it.next();
					informList.add(bo);
				
			}
				session.close();
		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));

		}
		
		
		//获取总记录数
		try{
			Session session = getSession();
			session.clear();
			Query query_1 = session.createQuery("select count(*) as count from MaintainCoupleBack m where 1=1 "+getHqls(query,"m"));
			
			totalCount = ((Long)query_1.list().get(0)).intValue();
			session.close();
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));

		}
		//设置返回结果的总记录数
		res.setTotalCount(totalCount + "");
		// System.out.println("totalCount:" + totalCount);
		// System.out.println(res.getTotalCount());
		//计算总页数
		totalPage = totalCount / pageCount;
		if (totalCount % pageCount > 0) {
			totalPage = totalCount / pageCount + 1;
		}
		//设置返回结果的总页数
		res.setTotalPage(totalPage + "");
		// System.out.println("totalPage:" + res.getTotalPage());
		//设置返回结果的总警告记录
		res.setBackInfoList(informList);
		// System.out.println("warningList.size():" + warningList.size());
		return res;
	}
//通过ID来查找维修反馈记录详情
//@Test
	public MaintainCoupleBack getMaintenDescById(int id){
		String hql = "from MaintainCoupleBack m where m.id=?";
		MaintainCoupleBack maintainCoupleBack = null;
		Session session = getSession();
		session.clear();
		try {
			maintainCoupleBack = (MaintainCoupleBack) session.createQuery(hql).setInteger(0, id).uniqueResult();
			log.info(gson.toJson(maintainCoupleBack));
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return maintainCoupleBack;
	}
	
	
	private static String getSqls(ReqgetMaintain_couple_back query) {
		MaintainQueryObj req=query.getQuery();
		StringBuffer buf=new StringBuffer();
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and SystemName= '"+req.getSystemName()+"' ");
		}
		if((req.getComponent()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and Equipmentname like '%"+req.getComponent()+"%' ");
		}
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and StationName= '"+req.getStationName()+"' ");
		}
		if((req.getLineNO()!=null)&&(req.getLineNO().trim().length()>0)){
			buf.append(" and LineNO= '"+req.getLineNO()+"' ");
		}
		if((req.getComponent()!=null)&&(req.getComponent().trim().length()>0)){
			buf.append(" and componentName like '%"+req.getComponent()+"%' ");
		}
		/**添加了按类型查询*/
		if((req.getWarningType()!=null)&&(req.getWarningType().trim().length()>0)){
			buf.append(" and warningType= '"+req.getWarningType()+"' ");
		}
		
		if((req.getStarTime()!=null)&&(req.getStarTime().trim().length()>0)){
			buf.append(" and repairsDate>= '"+req.getStarTime()+"' ");
		}
		if((req.getEndTime()!=null)&&(req.getEndTime().trim().length()>0)){
			buf.append(" and repairsDate<= '"+req.getEndTime()+"' ");
		}
		buf.append(" order by repairsDate desc ");
		return buf.toString();
	}
	
	private static String getHqls(ReqgetMaintain_couple_back query , String className) {
		//检查query
		Gson g = new Gson();
		MaintainQueryObj req=query.getQuery();
		StringBuffer buf=new StringBuffer();
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and "+className+".systemName= '"+req.getSystemName()+"' ");
		}
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and "+className+".stationName= '"+req.getStationName()+"' ");
		}
		if((req.getLineNO()!=null)&&(req.getLineNO().trim().length()>0)){
			buf.append(" and "+className+".lineNo= '"+req.getLineNO()+"' ");
		}
		if((req.getComponent()!=null)&&(req.getComponent().trim().length()>0)){
			buf.append(" and "+className+".componentName like '%"+req.getComponent()+"%' ");
		}
		/**添加了按类型查询*/
		if((req.getWarningType()!=null)&&(req.getWarningType().trim().length()>0)){
			buf.append(" and "+className+".warningType= '"+req.getWarningType()+"' ");
		}
		
		if((req.getStarTime()!=null)&&(req.getStarTime().trim().length()>0)){
			buf.append(" and "+className+".repairsDate>= '"+req.getStarTime()+"' ");
		}
		if((req.getEndTime()!=null)&&(req.getEndTime().trim().length()>0)){
			buf.append(" and "+className+".repairsDate<= '"+req.getEndTime()+"' ");
		}
		buf.append(" order by "+className+".repairsDate desc ");
		return buf.toString();
	}
	/***计划修通知单***/
	public  ResPlanInformList getPlanInformList(ReqgetPlanInformList query) {
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());

		ResPlanInformList res = new ResPlanInformList();
		List<InformPlan> informList = new ArrayList<InformPlan>();
		//根据入参，确定查询语句
		//String sqls = "select * from inform_user4 where 1=1 "+getSqls(query);
		String sql="from InformPlan i where 1=1 "+getplanHqls(query,"i");
		//sql=sql+" limit "+pageCount*(pageNo-1)+","+pageCount;
		log.warn(sql);
		

		try {
			Session session = getSession();
			session.clear();
			//链接数据库
			//做查询
			Query query_0 = session.createQuery(sql).setFirstResult(pageCount*(pageNo-1)).setMaxResults(pageCount);
			Iterator<InformPlan> it = query_0.list().iterator();
			while(it.hasNext()){
				InformPlan bo = new InformPlan();
				bo = it.next();
					informList.add(bo);
				}
			session.close();
			
		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));

		}
		
		
		//获取总记录数
		try{
			Session session = getSession();
			session.clear();
			Query query_1 = session.createQuery("select count(*) as count from InformPlan i where 1=1 "+getplanHqls(query,"i"));
			
			totalCount = ((Long)query_1.list().get(0)).intValue();
			session.close();
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));

		}
		//设置返回结果的总记录数
		res.setTotalCount(totalCount + "");
		// System.out.println("totalCount:" + totalCount);
		// System.out.println(res.getTotalCount());
		//计算总页数
		totalPage = totalCount / pageCount;
		if (totalCount % pageCount > 0) {
			totalPage = totalCount / pageCount + 1;
		}
		//设置返回结果的总页数
		res.setTotalPage(totalPage + "");
		// System.out.println("totalPage:" + res.getTotalPage());
		//设置返回结果的总警告记录
		res.setPlaninformList(informList);
		// System.out.println("warningList.size():" + warningList.size());
		return res;
	}

	private static String getplanSqls(ReqgetPlanInformList query) {
		PlanInformQueryObj req=query.getQuery();
		StringBuffer buf=new StringBuffer();
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and SystemName= '"+req.getSystemName()+"' ");
		}
		if((req.getSubSystemName()!=null)&&(req.getSubSystemName().trim().length()>0)){
			buf.append(" and subsystemName= '"+req.getSubSystemName()+"' ");
		}
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and StationName= '"+req.getStationName()+"' ");
		}
		if((req.getLineNO()!=null)&&(req.getLineNO().trim().length()>0)){
			buf.append(" and LineNO= '"+req.getLineNO()+"' ");
		}
		if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
			buf.append(" and EquipmentName like '%"+req.getEquipmentName()+"%' ");
		}
		if((req.getWarningTypeLevel()!=null)&&(req.getWarningTypeLevel().trim().length()>0)){
			buf.append(" and WarningTypeLevel= '"+req.getWarningTypeLevel()+"' ");
		}
		if((req.getStarTime()!=null)&&(req.getStarTime().trim().length()>0)){
			buf.append(" and next_maintainDate>= '"+req.getStarTime()+"' ");
		}
		if((req.getEndTime()!=null)&&(req.getEndTime().trim().length()>0)){
			buf.append(" and next_maintainDate<= '"+req.getEndTime()+"' ");
		}
		buf.append(" and statments = '0' order by next_maintainDate desc ");
		return buf.toString();
	}
	private static String getplanHqls(ReqgetPlanInformList query,String className) {
		PlanInformQueryObj req=query.getQuery();
		StringBuffer buf=new StringBuffer();
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and "+className+".systemName= '"+req.getSystemName()+"' ");
		}
		if((req.getSubSystemName()!=null)&&(req.getSubSystemName().trim().length()>0)){
			buf.append(" and "+className+".subsystemName= '"+req.getSubSystemName()+"' ");
		}
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and "+className+".stationName= '"+req.getStationName()+"' ");
		}
		if((req.getLineNO()!=null)&&(req.getLineNO().trim().length()>0)){
			buf.append(" and "+className+".lineNO= '"+req.getLineNO()+"' ");
		}
		if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
			buf.append(" and "+className+".equipmentName like '%"+req.getEquipmentName()+"%' ");
		}
		if((req.getWarningTypeLevel()!=null)&&(req.getWarningTypeLevel().trim().length()>0)){
			buf.append(" and "+className+".warningTypeLevel= '"+req.getWarningTypeLevel()+"' ");
		}
		if((req.getStarTime()!=null)&&(req.getStarTime().trim().length()>0)){
			buf.append(" and "+className+".nextMaintainDate>= '"+req.getStarTime()+"' ");
		}
		if((req.getEndTime()!=null)&&(req.getEndTime().trim().length()>0)){
			buf.append(" and "+className+".nextMaintainDate<= '"+req.getEndTime()+"' ");
		}
		buf.append(" and "+className+".statments = '0' order by "+className+".nextMaintainDate desc ");
		return buf.toString();
	}
	/***计划修维修规程表***/
	public  Res_plan_tableList getmaintain_plan_table(
			Reqgetmaintain_plan_table query) {
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());

		Res_plan_tableList res = new Res_plan_tableList();
		List<Plan_table> informList = new ArrayList<Plan_table>();
		//根据入参，确定查询语句
		//String sqls = "select * from inform_user4 where 1=1 "+getSqls(query);
		String sql="from MaintainPlanTable m where 1=1 "+getplan_tableHqls(query,"m");
		//sql=sql+" limit "+pageCount*(pageNo-1)+","+pageCount;
		log.warn(sql);

		try {
			Session session = getSession();
			session.clear();
			//链接数据库
			//做查询
			Query query_0 = session.createQuery(sql).setFirstResult(pageCount*(pageNo-1)).setMaxResults(pageCount);
			@SuppressWarnings("unchecked")
			Iterator<MaintainPlanTable> it = query_0.list().iterator();
			while(it.hasNext()){

				Plan_table bo = new Plan_table();
				MaintainPlanTable tb = it.next();
					bo.setEquipmentId(tb.getEquipmentId());
					bo.setEquipmentname(tb.getEquipmentname());
					bo.setSystemName(tb.getSystemName());
					bo.setLineNo(tb.getLineNo());
					bo.setStationName(tb.getStationName());
					bo.setWarningTypeLevel(tb.getWarningTypeLevel());
					bo.setMaintaininfo(tb.getMaintaininfo());
					bo.setLastTime_maintainDate(tb.getLastTimeMaintainDate().toString());
					bo.setProductDate(tb.getProductDate());
					bo.setMaintain_period(tb.getMaintainPeriod());
					informList.add(bo);
					
				}
			session.close();
		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));

		}
		
		
		//获取总记录数
		try{
			Session session = getSession();
			session.clear();
			Query query_1 = session.createQuery("select count(*) as count from MaintainPlanTable m where 1=1 "+getplan_tableHqls(query,"m"));
			
			totalCount =((Long)query_1.list().get(0)).intValue();
			session.close();
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));

		}
		//设置返回结果的总记录数
		res.setTotalCount(totalCount + "");
		// System.out.println("totalCount:" + totalCount);
		// System.out.println(res.getTotalCount());
		//计算总页数
		totalPage = totalCount / pageCount;
		if (totalCount % pageCount > 0) {
			totalPage = totalCount / pageCount + 1;
		}
		//设置返回结果的总页数
		res.setTotalPage(totalPage + "");
		// System.out.println("totalPage:" + res.getTotalPage());
		//设置返回结果的总警告记录
		res.setPlan_tableList(informList);
		// System.out.println("warningList.size():" + warningList.size());
		return res;
		
	}

	private static String getplan_tableSqls(Reqgetmaintain_plan_table query) {
		Plan_TableQueryObj req=query.getQuery();
		StringBuffer buf=new StringBuffer();
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and SystemName= '"+req.getSystemName()+"' ");
		}
	
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and StationName= '"+req.getStationName()+"' ");
		}
		if((req.getLineNO()!=null)&&(req.getLineNO().trim().length()>0)){
			buf.append(" and LineNO= '"+req.getLineNO()+"' ");
		}
		if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
			buf.append(" and EquipmentName like '%"+req.getEquipmentName()+"%' ");
		}
		if((req.getWarningTypeLevel()!=null)&&(req.getWarningTypeLevel().trim().length()>0)){
			buf.append(" and WarningTypeLevel= '"+req.getWarningTypeLevel()+"' ");
		}
		if((req.getStarTime()!=null)&&(req.getStarTime().trim().length()>0)){
			buf.append(" and lastTime_maintainDate>= '"+req.getStarTime()+"' ");
		}
		if((req.getEndTime()!=null)&&(req.getEndTime().trim().length()>0)){
			buf.append(" and lastTime_maintainDate<= '"+req.getEndTime()+"' ");
		}
		return buf.toString();
	}

	private static String getplan_tableHqls(Reqgetmaintain_plan_table query,String className) {
		Plan_TableQueryObj req=query.getQuery();
		StringBuffer buf=new StringBuffer();
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and "+className+".systemName= '"+req.getSystemName()+"' ");
		}
	
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and "+className+".stationName= '"+req.getStationName()+"' ");
		}
		if((req.getLineNO()!=null)&&(req.getLineNO().trim().length()>0)){
			buf.append(" and "+className+".lineNO= '"+req.getLineNO()+"' ");
		}
		if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
			buf.append(" and "+className+".equipmentName like '%"+req.getEquipmentName()+"%' ");
		}
		if((req.getWarningTypeLevel()!=null)&&(req.getWarningTypeLevel().trim().length()>0)){
			buf.append(" and "+className+".warningTypeLevel= '"+req.getWarningTypeLevel()+"' ");
		}
		if((req.getStarTime()!=null)&&(req.getStarTime().trim().length()>0)){
			buf.append(" and "+className+".lastTimeMaintainDate>= '"+req.getStarTime()+"' ");
		}
		if((req.getEndTime()!=null)&&(req.getEndTime().trim().length()>0)){
			buf.append(" and "+className+".lastTimeMaintainDate<= '"+req.getEndTime()+"' ");
		}
		return buf.toString();
	}


}
