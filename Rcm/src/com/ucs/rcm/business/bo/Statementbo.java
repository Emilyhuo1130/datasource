package com.ucs.rcm.business.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.pojo.Historywarning;
import com.ucs.rcm.pojo.ReportformsEquipmentIndex;
import com.ucs.rcm.pojo.ReportformsEquipmentRecord;
import com.ucs.rcm.pojo.ReportformsKpi;
import com.ucs.rcm.pojo.ReportformsOperateindex;
import com.ucs.rcm.pojo.ReportformsRequisition;
import com.ucs.rcm.pojo.WarningPlan;
import com.ucs.rcm.reqobj.Equipment_recordQueryObj;
import com.ucs.rcm.reqobj.FormsOperateindexQueryObj;
import com.ucs.rcm.reqobj.Reportforms_Equipment_indexQueryObj;
import com.ucs.rcm.reqobj.Reportforms_requisitionQueryObj;
import com.ucs.rcm.reqobj.ReqgetEquipment_record;
import com.ucs.rcm.reqobj.ReqgetFormsOperateindexList;
import com.ucs.rcm.reqobj.ReqgetReportforms_Equipment_index;
import com.ucs.rcm.reqobj.ReqgetReportforms_KPI;
import com.ucs.rcm.reqobj.ReqgetReportforms_KPIQueryObj;
import com.ucs.rcm.reqobj.ReqgetReportforms_requisition;
import com.ucs.rcm.reqobj.ReqgetcountofPlanhistoryWarning;
import com.ucs.rcm.reqobj.ReqgetcountofhistoryWarning;
import com.ucs.rcm.reqobj.ReqgethistoryWarningList;
import com.ucs.rcm.responseObj.CountPlanWarningstatistics;
import com.ucs.rcm.responseObj.HistoryWarning;
import com.ucs.rcm.responseObj.HistoryWarningstatistics;
import com.ucs.rcm.responseObj.ResOperateindex;
import com.ucs.rcm.responseObj.ResOperateindexList;
import com.ucs.rcm.responseObj.ResReportforms_Equipment_indexList;
import com.ucs.rcm.responseObj.Reportforms_Equipment_indexListInfo;
import com.ucs.rcm.responseObj.Reportforms_requisitionInfo;
import com.ucs.rcm.responseObj.ResEquipment_recordList;
import com.ucs.rcm.responseObj.ResEquipment_recordinfo;
import com.ucs.rcm.responseObj.ResReportforms_KPI;
import com.ucs.rcm.responseObj.ResReportforms_KPI_Info;
import com.ucs.rcm.responseObj.ResReportforms_requisition;
import com.ucs.rcm.responseObj.RescountPlanWaringList;
import com.ucs.rcm.responseObj.RescountofWarning;
import com.ucs.rcm.responseObj.ReshistoryWarningList;
import com.ucs.rcm.ztree.allStationEquipmentTree.Component;

public class Statementbo extends BaseHibernateDAO{
	static Logger log = Logger.getLogger(Statementbo.class);
	
	
	public  ReshistoryWarningList gethistoryWarningList(ReqgethistoryWarningList query) {
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());
		
		ReshistoryWarningList res = new ReshistoryWarningList();
		List<HistoryWarning> historyList = new ArrayList<HistoryWarning>();
		//根据入参，确定查询语句
		String sqlselect="select * from historywarning where 1=1 ";
		String hqlselect="from Historywarning where 1=1 ";
		String sqls = sqlselect+getHqls(query , "w")+" order by w.warningDate desc";
		sqls=sqls+" limit "+pageCount*(pageNo-1)+","+pageCount;
		log.warn(sqls);
		try {
			Session session = getSession();
			session.clear();
			//链接数据库
			//做查询
		Query query_0 = session.createQuery(hqlselect).setFirstResult((pageNo-1)*pageCount).setMaxResults(pageCount);
			//返回数据库查询结果
			@SuppressWarnings("unchecked")
			List<Historywarning> hws = query_0.list();
			Iterator<Historywarning> it = hws.iterator();
				while(it.hasNext()){
					Historywarning hw = it.next();
					HistoryWarning bo = new HistoryWarning();
					bo.setId(hw.getId());
					bo.setSystemName(hw.getStationName());
					bo.setSubSystemName(hw.getSubsystemName());
					bo.setLineNo(hw.getLineNo());
					bo.setStationName(hw.getStationName());
					bo.setComponent(hw.getComponent());
					bo.setWarninginfo(hw.getWarninginfo());
					bo.setWarningTypeLevel(hw.getWarningTypeLevel());
					bo.setWarningstatments(hw.getWarningstatments());
					bo.setWarningTime(hw.getWarningDate().toString());
					bo.setWarningType(hw.getWarningType());
					historyList.add(bo);
					
				}
			
			//关闭流
			
			session.close();
		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
		try{
			Session session = getSession();
			session.clear();
			//获取总记录数
			String returnsql=getHqls(query , "h");
			String returncount=null;
			if(query.getQuery().getWarningType().equals("故障告警")){
				returncount="select count(*) as count from Historywarning h where 1=1 and h.warningType='故障告警' "+returnsql;
				
			}else if(query.getQuery().getWarningType().equals("故障预警")){
				returncount="select count(*) as count from Historywarning h where 1=1 and h.warningType='故障预警' "+returnsql;
			}
			Query query_2 = session.createQuery(returncount);
			if(query_2.iterate().hasNext()){
				totalCount = ((Long)query_2.iterate().next()).intValue();
				
			}
			session.close();
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
			
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
		res.setHistoryList(historyList);
		// System.out.println("warningList.size():" + warningList.size());
		return res;
	}

	private static String getSqls(ReqgethistoryWarningList query) {
		StringBuffer buf=new StringBuffer();
		if((query.getQuery().getSystemName()!=null)&&(query.getQuery().getSystemName().trim().length()>0)){
			buf.append(" and systemName= '"+query.getQuery().getSystemName()+"' ");
		}
		if((query.getQuery().getWarningType()!=null)&&(query.getQuery().getWarningType().trim().length()>0)){
			buf.append(" and WarningType= '"+query.getQuery().getWarningType()+"' ");
		}
		if((query.getQuery().getSubSystemName()!=null)&&(query.getQuery().getSubSystemName().trim().length()>0)){
			buf.append(" and subsystemName= '"+query.getQuery().getSubSystemName()+"' ");
		}
		if((query.getQuery().getLineNo()!=null)&&(query.getQuery().getLineNo().trim().length()>0)){
			buf.append(" and lineno= '"+query.getQuery().getLineNo()+"' ");
		}
		if((query.getQuery().getStationName()!=null)&&(query.getQuery().getStationName().trim().length()>0)){
			buf.append(" and stationName= '"+query.getQuery().getStationName()+"' ");
		}
		if((query.getQuery().getComponent()!=null)&&(query.getQuery().getComponent().trim().length()>0)){
			buf.append(" and Component like '%"+query.getQuery().getComponent()+"%' ");
		}
		if((query.getQuery().getWarningTypeLevel()!=null)&&(query.getQuery().getWarningTypeLevel().trim().length()>0)){
			buf.append(" and WarningTypeLevel= '"+query.getQuery().getWarningTypeLevel()+"' ");
		}
		if((query.getQuery().getStarTime()!=null)&&(query.getQuery().getStarTime().trim().length()>0)){
			buf.append(" and warningDate>= '"+query.getQuery().getStarTime()+"' ");
		}
		if((query.getQuery().getEndTime()!=null)&&(query.getQuery().getEndTime().trim().length()>0)){
			buf.append(" and warningDate<= '"+query.getQuery().getEndTime()+"' ");
		}
		return buf.toString();
	}
	//封装 hql条件
	private static String getHqls(ReqgethistoryWarningList query , String classNameShort) {
		StringBuffer buf=new StringBuffer();
		if((query.getQuery().getSystemName()!=null)&&(query.getQuery().getSystemName().trim().length()>0)){
			buf.append(" and "+classNameShort+".systemName= '"+query.getQuery().getSystemName()+"' ");
		}
		if((query.getQuery().getWarningType()!=null)&&(query.getQuery().getWarningType().trim().length()>0)){
			buf.append(" and "+classNameShort+".warningType= '"+query.getQuery().getWarningType()+"' ");
		}
		if((query.getQuery().getSubSystemName()!=null)&&(query.getQuery().getSubSystemName().trim().length()>0)){
			buf.append(" and "+classNameShort+".subsystemName= '"+query.getQuery().getSubSystemName()+"' ");
		}
		if((query.getQuery().getLineNo()!=null)&&(query.getQuery().getLineNo().trim().length()>0)){
			buf.append(" and "+classNameShort+".lineno= '"+query.getQuery().getLineNo()+"' ");
		}
		if((query.getQuery().getStationName()!=null)&&(query.getQuery().getStationName().trim().length()>0)){
			buf.append(" and stationName= '"+query.getQuery().getStationName()+"' ");
		}
		if((query.getQuery().getComponent()!=null)&&(query.getQuery().getComponent().trim().length()>0)){
			buf.append(" and "+classNameShort+".component like '%"+query.getQuery().getComponent()+"%' ");
		}
		if((query.getQuery().getWarningTypeLevel()!=null)&&(query.getQuery().getWarningTypeLevel().trim().length()>0)){
			buf.append(" and "+classNameShort+".warningTypeLevel= '"+query.getQuery().getWarningTypeLevel()+"' ");
		}
		if((query.getQuery().getStarTime()!=null)&&(query.getQuery().getStarTime().trim().length()>0)){
			buf.append(" and "+classNameShort+".warningDate>= '"+query.getQuery().getStarTime()+"' ");
		}
		if((query.getQuery().getEndTime()!=null)&&(query.getQuery().getEndTime().trim().length()>0)){
			buf.append(" and "+classNameShort+".warningDate<= '"+query.getQuery().getEndTime()+"' ");
		}
		return buf.toString();
	}
	/**健康指数分析表--运营指数*/
	public   ResOperateindexList getFormsOperateindexList(
			ReqgetFormsOperateindexList query) {
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());

		ResOperateindexList res = new ResOperateindexList();
		List<ResOperateindex> operateindexList = new ArrayList<ResOperateindex>();
		String sql="select * from reportforms_operateindex where 1=1 ";
		String hql = "from ReportformsOperateindex r where 1=1 "; 
		sql=sql+getFormsOperateindexHQL(query , "r")+" order by r.updatetime desc ";
		
		Session session = getSession();
		session.clear();
		Query query_3 = session.createQuery(hql).setFirstResult((pageNo-1)*pageCount).setMaxResults(pageCount);
		try{
			
			@SuppressWarnings("unchecked")
			List<ReportformsOperateindex> rfs = query_3.list();
			Iterator<ReportformsOperateindex> it = rfs.iterator();
			while(it.hasNext()){
				ReportformsOperateindex rf = it.next();
				ResOperateindex bo = new ResOperateindex();
				bo.setId(rf.getId());
				bo.setLineNo(rf.getLineNo());
				bo.setHealthindex(rf.getHealthindex());
				bo.setLeveloneCount(rf.getLeveloneCount());
				bo.setLeveltwoCount(rf.getLeveltwoCount());
				bo.setLevelthreeCount(rf.getLevelthreeCount());
				bo.setLevelforeCount(rf.getLevelforeCount());
				bo.setTime(rf.getUpdatetime().toString());
				operateindexList.add(bo);
			}
			
			
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
			
		}
		
		try{
			
			//获取总记录数
			String returnsql=getFormsOperateindexHQL(query , "r");
			String returncount=null;
			returncount="select count(*) as count from ReportformsOperateindex r where 1=1 "+returnsql;
			Query query_5 = session.createQuery(returncount);
			if(query_5.iterate().hasNext()){
				totalCount =((Long) query_5.iterate().next()).intValue();
			}
			
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
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
		res.setOperateindexList(operateindexList);
		return res;
	}

	@SuppressWarnings("unused")
	private static String getFormsOperateindexSQL(
			ReqgetFormsOperateindexList query) {
		StringBuffer buf=new StringBuffer();
		FormsOperateindexQueryObj  queryObj=query.getQuery();
		if((queryObj.getLineNo()!=null)&&(queryObj.getLineNo().trim().length()>0)){
			buf.append(" and lineNo= '"+queryObj.getLineNo()+"' ");
		}
		if((queryObj.getHealthindex()!=null)&&(queryObj.getHealthindex().trim().length()>0)){
			buf.append(" and Healthindex= '"+queryObj.getHealthindex()+"' ");
		}
		if((queryObj.getStartDate()!=null)&&(queryObj.getStartDate().trim().length()>0)){
			buf.append(" and updatetime >= '"+queryObj.getStartDate()+"' ");
		}
		if((queryObj.getEndDate()!=null)&&(queryObj.getEndDate().trim().length()>0)){
			buf.append(" and updatetime <= '"+queryObj.getEndDate()+"' ");
		}
		return buf.toString();
	}
	
	private static String getFormsOperateindexHQL(
			ReqgetFormsOperateindexList query , String className) {
		StringBuffer buf=new StringBuffer();
		FormsOperateindexQueryObj  queryObj=query.getQuery();
		if((queryObj.getLineNo()!=null)&&(queryObj.getLineNo().trim().length()>0)){
			buf.append(" and "+className+".lineNo= '"+queryObj.getLineNo()+"' ");
		}
		if((queryObj.getHealthindex()!=null)&&(queryObj.getHealthindex().trim().length()>0)){
			buf.append(" and "+className+".healthindex= '"+queryObj.getHealthindex()+"' ");
		}
		if((queryObj.getStartDate()!=null)&&(queryObj.getStartDate().trim().length()>0)){
			buf.append(" and "+className+".updatetime >= '"+queryObj.getStartDate()+"' ");
		}
		if((queryObj.getEndDate()!=null)&&(queryObj.getEndDate().trim().length()>0)){
			buf.append(" and "+className+".updatetime <= '"+queryObj.getEndDate()+"' ");
		}
		return buf.toString();
	}

	
	/****主动维保通知单*********/
	public  ResReportforms_requisition getReportforms_requisition(
			ReqgetReportforms_requisition query) {
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());

		ResReportforms_requisition res = new ResReportforms_requisition();
		List<Reportforms_requisitionInfo> infoList = new ArrayList<Reportforms_requisitionInfo>();
		String sql="from ReportformsRequisition r where 1=1 ";
		sql=sql+getReportforms_requisitionSQL(query);
		Session session = getSession();
		session.clear();
		try{
			Query query_2_ = session.createQuery(sql).setFirstResult((pageNo-1)*pageCount).setMaxResults(pageCount);
			List<ReportformsRequisition> rfs = query_2_.list();
			Iterator<ReportformsRequisition> it = rfs.iterator();
			while(it.hasNext()){
				Reportforms_requisitionInfo bo = new Reportforms_requisitionInfo();
				ReportformsRequisition r = it.next();
				bo.setId(r.getId());
				bo.setComponentId(r.getComponentId());
				bo.setSystemName(r.getSystemName());
				bo.setSubsystemName(r.getSubsystemName());
				bo.setLineNo(r.getLineNo());
				bo.setStationName(r.getStationName());
				bo.setComponent(r.getComponent());
				bo.setRequisitionNo(r.getRequisitionNo());
				bo.setRequisitionstatments(r.getRequisitionstatments());
				bo.setSendTime(r.getSendTime().toString());
				bo.setMaintainTime(r.getMaintainTime().toString());
				bo.setMaintainDepartment(r.getMaintainDepartment());
				bo.setMaintainPerson(r.getMaintainPerson());
				infoList.add(bo);
			}
		
			
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
		try{
			//获取总记录数
			String returnsql=getReportforms_requisitionHQL(query,"r");
			String returncount=null;
			returncount="select count(*) as count from ReportformsRequisition r  where 1=1 "+returnsql;
			Query query_2_ = session.createQuery(returncount);
			if(query_2_.iterate().hasNext()){
				totalCount = ((Long)query_2_.list().iterator().next()).intValue();
			}
			
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
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
		res.setRequisitionList(infoList);
		return res;
	}

private static String getReportforms_requisitionSQL(
		ReqgetReportforms_requisition query) {
	Reportforms_requisitionQueryObj req=query.getQuery();
	StringBuffer buf=new StringBuffer();
	if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
		buf.append(" and SystemName= '"+req.getSystemName()+"' ");
	}
	if((req.getSubsystemName()!=null)&&(req.getSubsystemName().trim().length()>0)){
		buf.append(" and SubsystemName= '"+req.getSubsystemName()+"' ");
	}
	if((req.getLineNo()!=null)&&(req.getLineNo().trim().length()>0)){
		buf.append(" and LineNO= '"+req.getLineNo()+"' ");
	}
	if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
		buf.append(" and StationName= '"+req.getStationName()+"' ");
	}
	if((req.getComponent()!=null)&&(req.getComponent().trim().length()>0)){
		buf.append(" and Component like '%"+req.getComponent()+"%' ");
	}
	if((req.getStarTime()!=null)&&(req.getStarTime().trim().length()>0)){
		buf.append(" and sendTime>= '"+req.getStarTime()+"' ");
	}
	if((req.getEndTime()!=null)&&(req.getEndTime().trim().length()>0)){
		buf.append(" and sendTime<= '"+req.getEndTime()+"' ");
	}
	return buf.toString();
}
@SuppressWarnings("unused")
private static String getReportforms_requisitionHQL(
		ReqgetReportforms_requisition query , String className) {
	Reportforms_requisitionQueryObj req=query.getQuery();
	StringBuffer buf=new StringBuffer();
	if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
		buf.append(" and "+className+".systemName= '"+req.getSystemName()+"' ");
	}
	if((req.getSubsystemName()!=null)&&(req.getSubsystemName().trim().length()>0)){
		buf.append(" and "+className+".subsystemName= '"+req.getSubsystemName()+"' ");
	}
	if((req.getLineNo()!=null)&&(req.getLineNo().trim().length()>0)){
		buf.append(" and "+className+".lineNO= '"+req.getLineNo()+"' ");
	}
	if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
		buf.append(" and "+className+".stationName= '"+req.getStationName()+"' ");
	}
	if((req.getComponent()!=null)&&(req.getComponent().trim().length()>0)){
		buf.append(" and "+className+".component like '%"+req.getComponent()+"%' ");
	}
	if((req.getStarTime()!=null)&&(req.getStarTime().trim().length()>0)){
		buf.append(" and "+className+".sendTime>= '"+req.getStarTime()+"' ");
	}
	if((req.getEndTime()!=null)&&(req.getEndTime().trim().length()>0)){
		buf.append(" and "+className+".sendTime<= '"+req.getEndTime()+"' ");
	}
	return buf.toString();
}

	public  ResEquipment_recordList getEquipment_record(
			ReqgetEquipment_record query) {
		
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());
	
		ResEquipment_recordList res = new ResEquipment_recordList();
		List<ResEquipment_recordinfo> infoList = new ArrayList<ResEquipment_recordinfo>();
		String sql="from ReportformsEquipmentRecord r where 1=1 ";
		sql=sql+getEquipment_recordListHQL(query , "r");
		log.warn("设备履历表：       "+sql);
		Session session = getSession();
		session.clear();
		try{
			Query query_1_ = session.createQuery(sql).setFirstResult((pageNo-1)*pageCount).setMaxResults(pageCount);
			@SuppressWarnings("unchecked")
			List<ReportformsEquipmentRecord> rep = query_1_.list();
			Iterator<ReportformsEquipmentRecord> it = rep.iterator();
			while(it.hasNext()){
				ResEquipment_recordinfo bo = new ResEquipment_recordinfo();
				ReportformsEquipmentRecord rp = it.next();
				bo.setId(rp.getId());
				bo.setSystemName(rp.getSystemName());
				bo.setSubsystemName(rp.getSubsystemName());
				bo.setLineNo(rp.getLineNo());
				bo.setStationName(rp.getStationName());
				bo.setComponent(rp.getComponent());
				bo.setComponentId(rp.getComponentId());
				bo.setLocationId(rp.getLocationId());
				bo.setModelstandard(rp.getModelstandard());
				bo.setOutTime(rp.getOutTime().toString());
				bo.setBuyTime(rp.getBuyTime().toString());
				bo.setProduction_house(rp.getProductionHouse());
				bo.setProduction_coding(rp.getProductionCoding());
				infoList.add(bo);
			}
		
			
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
		try{
			//获取总记录数
			String returnsql=getEquipment_recordListHQL(query , "r");
			String returncount=null;
			returncount="select count(*) as count from ReportformsEquipmentRecord r where 1=1 "+returnsql;
			Query query_2_ = session.createQuery(returncount);
			if(query_2_.iterate().hasNext()){
				totalCount = ((Long)query_2_.list().iterator().next()).intValue();
			}
			
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
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
		res.setResEquipment_recordList(infoList);
		return res;
	}

	private static String getEquipment_recordListSQL(
			ReqgetEquipment_record query) {
		Equipment_recordQueryObj req=query.getQuery();
		StringBuffer buf=new StringBuffer();
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and SystemName= '"+req.getSystemName()+"' ");
		}
		if((req.getSubsystemName()!=null)&&(req.getSubsystemName().trim().length()>0)){
			buf.append(" and SubsystemName= '"+req.getSubsystemName()+"' ");
		}
		if((req.getLineNo()!=null)&&(req.getLineNo().trim().length()>0)){
			buf.append(" and LineNO= '"+req.getLineNo()+"' ");
		}
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and StationName= '"+req.getStationName()+"' ");
		}
		if((req.getComponent()!=null)&&(req.getComponent().trim().length()>0)){
			buf.append(" and Component like '%"+req.getComponent()+"%' ");
		}
		if((req.getProduction_house()!=null)&&(req.getProduction_house().trim().length()>0)){
			buf.append(" and Production_house= '"+req.getProduction_house()+"' ");
		}
		if((req.getStarTime()!=null)&&(req.getStarTime().trim().length()>0)){
			buf.append(" and outTime>= '"+req.getStarTime()+"' ");
		}
		if((req.getEndTime()!=null)&&(req.getEndTime().trim().length()>0)){
			buf.append(" and outTime<= '"+req.getEndTime()+"' ");
		}
		return buf.toString();
	}
	@SuppressWarnings("unused")
	private static String getEquipment_recordListHQL(
			ReqgetEquipment_record query , String className) {
		Equipment_recordQueryObj req=query.getQuery();
		StringBuffer buf=new StringBuffer();
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and "+className+".systemName= '"+req.getSystemName()+"' ");
		}
		if((req.getSubsystemName()!=null)&&(req.getSubsystemName().trim().length()>0)){
			buf.append(" and "+className+".subsystemName= '"+req.getSubsystemName()+"' ");
		}
		if((req.getLineNo()!=null)&&(req.getLineNo().trim().length()>0)){
			buf.append(" and "+className+".lineNO= '"+req.getLineNo()+"' ");
		}
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and "+className+".stationName= '"+req.getStationName()+"' ");
		}
		if((req.getComponent()!=null)&&(req.getComponent().trim().length()>0)){
			buf.append(" and "+className+".component like '%"+req.getComponent()+"%' ");
		}
		if((req.getProduction_house()!=null)&&(req.getProduction_house().trim().length()>0)){
			buf.append(" and "+className+".production_house= '"+req.getProduction_house()+"' ");
		}
		if((req.getStarTime()!=null)&&(req.getStarTime().trim().length()>0)){
			buf.append(" and "+className+".outTime>= '"+req.getStarTime()+"' ");
		}
		if((req.getEndTime()!=null)&&(req.getEndTime().trim().length()>0)){
			buf.append(" and "+className+".outTime<= '"+req.getEndTime()+"' ");
		}
		return buf.toString();
	}
	/**健康指数分析表--设施指数*/
	public   ResReportforms_Equipment_indexList getReportforms_Equipment_index(
			ReqgetReportforms_Equipment_index query) {
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());
	
		ResReportforms_Equipment_indexList res = new ResReportforms_Equipment_indexList();
		List<Reportforms_Equipment_indexListInfo> infoList = new ArrayList<Reportforms_Equipment_indexListInfo>();
		String sql="from ReportformsEquipmentIndex r where 1=1 ";
		sql=sql+getReportforms_Equipment_indexHQL(query , "r");
		Session session = getSession();
		session.clear();
		try{
			Query query_7_ = session.createQuery(sql).setFirstResult((pageNo-1)*pageCount).setMaxResults(pageCount);
			@SuppressWarnings("unchecked")
			List<ReportformsEquipmentIndex> re = query_7_.list();
			Iterator<ReportformsEquipmentIndex> it = re.iterator();
			while(it.hasNext()){
				Reportforms_Equipment_indexListInfo bo = new Reportforms_Equipment_indexListInfo();
				ReportformsEquipmentIndex r = it.next();
				bo.setId(r.getId());
				bo.setSystemName(r.getSystemName());
				bo.setSubsystemName(r.getSubsystemName());
				bo.setLineNo(r.getLineNo());
				bo.setStationName(r.getStationName());
				bo.setComponent(r.getComponent());
				bo.setLocationId(r.getLocationId());
				bo.setComponentDescription(r.getComponentDescription());
				bo.setHealthLevel(r.getHealthLevel());
				infoList.add(bo);
			}
			
			
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
		try{
			//获取总记录数
			String returnsql=getReportforms_Equipment_indexHQL(query , "r");
			String returncount=null;
			returncount="select count(*) as count from ReportformsEquipmentIndex r where 1=1 "+returnsql;
			Query query_8_ = session.createQuery(returncount);
			if(query_8_.iterate().hasNext()){
				totalCount = ((Long)query_8_.list().iterator().next()).intValue();
			}
		
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
		res.setInfoList(infoList);
		return res;
	}

	@SuppressWarnings("unused")
	private static String getReportforms_Equipment_indexSQL(
			ReqgetReportforms_Equipment_index query) {
		Reportforms_Equipment_indexQueryObj req=query.getQuery();
		StringBuffer buf=new StringBuffer();
		
		if((req.getLineNo()!=null)&&(req.getLineNo().trim().length()>0)){
			buf.append(" and LineNO= '"+req.getLineNo()+"' ");
		}
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and StationName= '"+req.getStationName()+"' ");
		}
		if((req.getComponent()!=null)&&(req.getComponent().trim().length()>0)){
			buf.append(" and Component like '%"+req.getComponent()+"%' ");
		}
		if((req.getHealthLevel()!=null)&&(req.getHealthLevel().trim().length()>0)){
			buf.append(" and HealthLevel= '"+req.getHealthLevel()+"' ");
		}
		return buf.toString();
	}
	private static String getReportforms_Equipment_indexHQL(
			ReqgetReportforms_Equipment_index query ,String className) {
		Reportforms_Equipment_indexQueryObj req=query.getQuery();
		StringBuffer buf=new StringBuffer();
		
		if((req.getLineNo()!=null)&&(req.getLineNo().trim().length()>0)){
			buf.append(" and "+className+".lineNO= '"+req.getLineNo()+"' ");
		}
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and "+className+".stationName= '"+req.getStationName()+"' ");
		}
		if((req.getComponent()!=null)&&(req.getComponent().trim().length()>0)){
			buf.append(" and "+className+".component like '%"+req.getComponent()+"%' ");
		}
		if((req.getHealthLevel()!=null)&&(req.getHealthLevel().trim().length()>0)){
			buf.append(" and "+className+".healthLevel= '"+req.getHealthLevel()+"' ");
		}
		return buf.toString();
	}
	/**KPI分析表*/
	public  ResReportforms_KPI getReportforms_KPI(
			ReqgetReportforms_KPI query) {
		
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());
	
		ResReportforms_KPI res = new ResReportforms_KPI();
		List<ResReportforms_KPI_Info> infoList = new ArrayList<ResReportforms_KPI_Info>();
		String sql="from ReportformsKpi   where 1=1  ";
		sql=sql+getReportforms_KPIHQL(query,"r");
		Session session = getSession();
		session.clear();
		try{
			Query query_5_ = session.createQuery(sql).setFirstResult((pageNo-1)*pageCount).setMaxResults(pageCount);
			@SuppressWarnings("unchecked")
			List<ReportformsKpi> rfk = query_5_.list();
			Iterator<ReportformsKpi> it = rfk.iterator(); 
			while(it.hasNext()){
				ResReportforms_KPI_Info bo = new ResReportforms_KPI_Info();
				ReportformsKpi r = it.next();
				bo.setId(r.getId());
				bo.setSystemName(r.getSystemName());
				bo.setSubsystemName(r.getSubsystemName());
				bo.setLineNo(r.getLineNo());
				bo.setStationName(r.getStationName());
				bo.setComponent(r.getComponent());
				bo.setSafety_production_days(r.getSafetyProductionDays());
				bo.setForce_stop_days(r.getForceStopDays());
				bo.setNoplan_stop_days(r.getNoplanStopDays());
				bo.setNo_fault_days(r.getNoFaultDays());
				bo.setKey_equipment_repair_time(r.getKeyEquipmentRepairTime());
				bo.setKey_technology_repair_time(r.getKeyTechnologyRepairTime());
				bo.setPhysical_variance(r.getPhysicalVariance());
				infoList.add(bo);
			}
			
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
		try{
			//获取总记录数
			String returnsql=getReportforms_KPIHQL(query,"r");
			String returncount=null;
			returncount="select count(*) as count from ReportformsKpi r where 1=1 "+returnsql;
			Query query_6_ = session.createQuery(returncount);
			if(query_6_.iterate().hasNext()){
				totalCount =((Long) query_6_.iterate().next()).intValue();
			}
		
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
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
		res.setInfoList(infoList);
		return res;
	}

	@SuppressWarnings("unused")
	private static String getReportforms_KPISQL(ReqgetReportforms_KPI query) {
		StringBuffer buf=new StringBuffer();
		ReqgetReportforms_KPIQueryObj req=query.getQuery();
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and SystemName= '"+req.getSystemName()+"' ");
		}
		if((req.getSubSystemName()!=null)&&(req.getSubSystemName().trim().length()>0)){
			buf.append(" and SubsystemName= '"+req.getSubSystemName()+"' ");
		}
		if((req.getLineNo()!=null)&&(req.getLineNo().trim().length()>0)){
			buf.append(" and LineNO= '"+req.getLineNo()+"' ");
		}
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and StationName= '"+req.getStationName()+"' ");
		}
		if((req.getComponent()!=null)&&(req.getComponent().trim().length()>0)){
			buf.append(" and Component like '%"+req.getComponent()+"%' ");
		}
		
		if((req.getStarTime()!=null)&&(req.getStarTime().trim().length()>0)){
			buf.append(" and updateTime>= '"+req.getStarTime()+"' ");
		}
		if((req.getEndTime()!=null)&&(req.getEndTime().trim().length()>0)){
			buf.append(" and updateTime<= '"+req.getEndTime()+"' ");
		}
		return buf.toString();
	}
	private static String getReportforms_KPIHQL(ReqgetReportforms_KPI query , String className) {
		StringBuffer buf=new StringBuffer();
		ReqgetReportforms_KPIQueryObj req=query.getQuery();
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and "+className+".systemName= '"+req.getSystemName()+"' ");
		}
		if((req.getSubSystemName()!=null)&&(req.getSubSystemName().trim().length()>0)){
			buf.append(" and "+className+".subsystemName= '"+req.getSubSystemName()+"' ");
		}
		if((req.getLineNo()!=null)&&(req.getLineNo().trim().length()>0)){
			buf.append(" and "+className+".lineNO= '"+req.getLineNo()+"' ");
		}
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and "+className+".stationName= '"+req.getStationName()+"' ");
		}
		if((req.getComponent()!=null)&&(req.getComponent().trim().length()>0)){
			buf.append(" and "+className+".component like '%"+req.getComponent()+"%' ");
		}
		
		if((req.getStarTime()!=null)&&(req.getStarTime().trim().length()>0)){
			buf.append(" and "+className+".updateTime>= '"+req.getStarTime()+"' ");
		}
		if((req.getEndTime()!=null)&&(req.getEndTime().trim().length()>0)){
			buf.append(" and "+className+".updateTime<= '"+req.getEndTime()+"' ");
		}
		return buf.toString();
	}
	
	
	/**历史告警、预警对设备告警次数的统计***/
	public  RescountofWarning getcountofhistoryWarning(
			ReqgetcountofhistoryWarning query) {
		
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());

		RescountofWarning res = new RescountofWarning();
		List<HistoryWarningstatistics> historyList = new ArrayList<HistoryWarningstatistics>();
		//根据入参，确定查询语句
		String sqlselect="select distinct h.component from Historywarning h where 1=1";
		
		String sqls = sqlselect+getcomponentHqls(query , "h");
		//sqls=sqls+" limit "+pageCount*(pageNo-1)+","+pageCount;
		log.warn(sqls);
		List<String> components=new ArrayList<String>();
		Session session = getSession();
		session.clear();
		try {
			//链接数据库
			//做查询
			Query query_1_ = session.createQuery(sqls).setFirstResult((pageNo-1)*pageCount).setMaxResults(pageCount);
			//返回数据库查询结果
			@SuppressWarnings("unchecked")
			List<String> hrs = query_1_.list();
			Iterator<String> it = hrs.iterator();
		
				while(it.hasNext()){
					
					String c =it.next();
					components.add(c);
				}
			
		
			
		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		try{
			String findinfo="from Historywarning h where h.component=?"; 
			String findcount="select count(*) as count from Historywarning h where h.component=? and h.warningType=? "+getcomponentSqls(query); 
			for(String c:components){
				Query query_2_ = session.createQuery(findinfo);
				query_2_.setString(0, c);
				@SuppressWarnings("unchecked")
				Iterator<Historywarning> it = query_2_.list().iterator();
				HistoryWarningstatistics info=new HistoryWarningstatistics();
				Historywarning h = it.next();
				info.setComponent(h.getComponent());
				info.setLineNo(h.getLineNo());
				info.setStationName(h.getStationName());
				info.setSubSystemName(h.getSubsystemName());
				info.setSystemName(h.getSystemName());
				
				
				Query query_3_ = session.createQuery(findcount);
				query_3_.setString(0, c);
				query_3_.setString(1, query.getQuery().getWarningType());
				@SuppressWarnings("unchecked")
				Iterator<Long> itr = query_3_.list().iterator(); 
				int total= (itr.next()).intValue();
				info.setCount(total);
				historyList.add(info);
			}
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
		try{
			//获取总记录数
			String returnsql=getcomponentHqls(query , "h");
			String returncount=null;
			if(query.getQuery().getWarningType().equals("故障告警")){
				returncount="select count(distinct h.component) as count from Historywarning h where 1=1 and h.warningType='故障告警' "+returnsql;
				
			}else if(query.getQuery().getWarningType().equals("故障预警")){
				returncount="select count(distinct h.component) as count from Historywarning h where 1=1 and h.warningType='故障预警' "+returnsql;
			}
			Query query_4_ = session.createQuery(returncount);
			if(query_4_.iterate().hasNext()){
				totalCount =((Long) query_4_.iterate().next()).intValue();
			}
		
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
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
		res.setHistoryList(historyList);
		// System.out.println("warningList.size():" + warningList.size());
		return res;
	}

	private static String getcomponentSqls(ReqgetcountofhistoryWarning query) {
		StringBuffer buf=new StringBuffer();
		if((query.getQuery().getSystemName()!=null)&&(query.getQuery().getSystemName().trim().length()>0)){
			buf.append(" and systemName= '"+query.getQuery().getSystemName()+"' ");
		}
		if((query.getQuery().getWarningType()!=null)&&(query.getQuery().getWarningType().trim().length()>0)){
			buf.append(" and WarningType= '"+query.getQuery().getWarningType()+"' ");
		}
		if((query.getQuery().getSubSystemName()!=null)&&(query.getQuery().getSubSystemName().trim().length()>0)){
			buf.append(" and subsystemName= '"+query.getQuery().getSubSystemName()+"' ");
		}
		if((query.getQuery().getLineNo()!=null)&&(query.getQuery().getLineNo().trim().length()>0)){
			buf.append(" and lineno= '"+query.getQuery().getLineNo()+"' ");
		}
		if((query.getQuery().getStationName()!=null)&&(query.getQuery().getStationName().trim().length()>0)){
			buf.append(" and stationName= '"+query.getQuery().getStationName()+"' ");
		}
		
		if((query.getQuery().getStarTime()!=null)&&(query.getQuery().getStarTime().trim().length()>0)){
			buf.append(" and warningDate>= '"+query.getQuery().getStarTime()+"' ");
		}
		if((query.getQuery().getEndTime()!=null)&&(query.getQuery().getEndTime().trim().length()>0)){
			buf.append(" and warningDate<= '"+query.getQuery().getEndTime()+"' ");
		}
		return buf.toString();
	}
	
	private static String getcomponentHqls(ReqgetcountofhistoryWarning query , String className) {
		StringBuffer buf=new StringBuffer();
		if((query.getQuery().getSystemName()!=null)&&(query.getQuery().getSystemName().trim().length()>0)){
			buf.append(" and "+className+".systemName= '"+query.getQuery().getSystemName()+"' ");
		}
		if((query.getQuery().getWarningType()!=null)&&(query.getQuery().getWarningType().trim().length()>0)){
			buf.append(" and "+className+".warningType= '"+query.getQuery().getWarningType()+"' ");
		}
		if((query.getQuery().getSubSystemName()!=null)&&(query.getQuery().getSubSystemName().trim().length()>0)){
			buf.append(" and "+className+".subsystemName= '"+query.getQuery().getSubSystemName()+"' ");
		}
		if((query.getQuery().getLineNo()!=null)&&(query.getQuery().getLineNo().trim().length()>0)){
			buf.append(" and "+className+".lineno= '"+query.getQuery().getLineNo()+"' ");
		}
		if((query.getQuery().getStationName()!=null)&&(query.getQuery().getStationName().trim().length()>0)){
			buf.append(" and "+className+".stationName= '"+query.getQuery().getStationName()+"' ");
		}
		
		if((query.getQuery().getStarTime()!=null)&&(query.getQuery().getStarTime().trim().length()>0)){
			buf.append(" and "+className+".warningDate>= '"+query.getQuery().getStarTime()+"' ");
		}
		if((query.getQuery().getEndTime()!=null)&&(query.getQuery().getEndTime().trim().length()>0)){
			buf.append(" and "+className+".warningDate<= '"+query.getQuery().getEndTime()+"' ");
		}
		return buf.toString();
	}
	/**计划修历史统计***/
	public  RescountPlanWaringList getcountofPlanhistoryWarning(
			ReqgetcountofPlanhistoryWarning query) {
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());

		RescountPlanWaringList res = new RescountPlanWaringList();
		List<CountPlanWarningstatistics> historyList = new ArrayList<CountPlanWarningstatistics>();
		//根据入参，确定查询语句
		String sqlselect=null;
			sqlselect="select distinct w.equipmentname e from WarningPlan w where 1=1 and e.statments='1' ";

		String sqls = sqlselect+getequipmentnameHqls(query , "e");
		log.warn(sqls);
		List<String> components=new ArrayList<String>();
		Session session = getSession();
		session.clear();
		try {
			//链接数据库
			//做查询
			Query query_6 = session.createQuery(sqls);
			//返回数据库查询结果
			List<String> enames = query_6.list();
			Iterator<String> it = enames.iterator();
		
				while(it.hasNext()){
					String e = it.next();
					components.add(e);
				}
			
			//关闭流
		
			
		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		try{
			String findinfo="from WarningPlan w where w.equipmentname=?"; 
			String findcount="select count(*) as count from WarningPlan w where w.equipmentname=? " +getequipmentnameHqls(query ,"w");
			for(String c:components){
				Query query_7 = session.createQuery(findinfo).setString(0, c);
				@SuppressWarnings("unchecked")
				Iterator<WarningPlan> it = query_7.list().iterator();
				WarningPlan wp = it.next();
				CountPlanWarningstatistics info=new CountPlanWarningstatistics();
				info.setEquipmentname(c);
				info.setLineNo(wp.getLineNo());
				info.setStationName(wp.getStationName());
				info.setSubsystemName(wp.getSubsystemName());
				info.setSystemName(wp.getSystemName());
				
				
				Query query_8 = getSession().createQuery(findcount).setString(0, c);
				@SuppressWarnings("unchecked")
				Iterator<Integer> itc = query_8.list().iterator();
				int total = ((Integer)itc.next()).intValue();
				info.setCount(total);
				historyList.add(info);
			}
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
		try{
			//获取总记录数
			String returnsql=getequipmentnameHqls(query , "w");
			String returncount=null;
			
				returncount="select count(distinct w.equipmentname) as count from WarningPlan w where 1=1 and w.statments='1' "+returnsql;
				
			
				Query query_9 = session.createQuery(returncount);
				
			if(query_9.iterate().hasNext()){
				totalCount =(Integer) query_9.iterate().next();
			}
			
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
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
		res.setHistoryList(historyList);
		// System.out.println("warningList.size():" + warningList.size());
		return res;
	}

	@SuppressWarnings("unused")
	private static String getequipmentnameSqls(
			ReqgetcountofPlanhistoryWarning query) {
		StringBuffer buf=new StringBuffer();
		if((query.getQuery().getSystemName()!=null)&&(query.getQuery().getSystemName().trim().length()>0)){
			buf.append(" and systemName= '"+query.getQuery().getSystemName()+"' ");
		}
		if((query.getQuery().getWarningTypeLevel()!=null)&&(query.getQuery().getWarningTypeLevel().trim().length()>0)){
			buf.append(" and warningTypeLevel= '"+query.getQuery().getWarningTypeLevel()+"' ");
		}
		if((query.getQuery().getLineNo()!=null)&&(query.getQuery().getLineNo().trim().length()>0)){
			buf.append(" and lineno= '"+query.getQuery().getLineNo()+"' ");
		}
		if((query.getQuery().getStationName()!=null)&&(query.getQuery().getStationName().trim().length()>0)){
			buf.append(" and stationName= '"+query.getQuery().getStationName()+"' ");
		}
		
		if((query.getQuery().getStartDate()!=null)&&(query.getQuery().getStartDate().trim().length()>0)){
			buf.append(" and warningDate>= '"+query.getQuery().getStartDate()+"' ");
		}
		if((query.getQuery().getEndDate()!=null)&&(query.getQuery().getEndDate().trim().length()>0)){
			buf.append(" and warningDate<= '"+query.getQuery().getEndDate()+"' ");
		}
		return buf.toString();
	}

	private static String getequipmentnameHqls(
			ReqgetcountofPlanhistoryWarning query , String className) {
		StringBuffer buf=new StringBuffer();
		if((query.getQuery().getSystemName()!=null)&&(query.getQuery().getSystemName().trim().length()>0)){
			buf.append(" and "+className+".systemName= '"+query.getQuery().getSystemName()+"' ");
		}
		if((query.getQuery().getWarningTypeLevel()!=null)&&(query.getQuery().getWarningTypeLevel().trim().length()>0)){
			buf.append(" and "+className+".warningTypeLevel= '"+query.getQuery().getWarningTypeLevel()+"' ");
		}
		if((query.getQuery().getLineNo()!=null)&&(query.getQuery().getLineNo().trim().length()>0)){
			buf.append(" and "+className+".lineno= '"+query.getQuery().getLineNo()+"' ");
		}
		if((query.getQuery().getStationName()!=null)&&(query.getQuery().getStationName().trim().length()>0)){
			buf.append(" and "+className+".stationName= '"+query.getQuery().getStationName()+"' ");
		}
		
		if((query.getQuery().getStartDate()!=null)&&(query.getQuery().getStartDate().trim().length()>0)){
			buf.append(" and "+className+".warningDate>= '"+query.getQuery().getStartDate()+"' ");
		}
		if((query.getQuery().getEndDate()!=null)&&(query.getQuery().getEndDate().trim().length()>0)){
			buf.append(" and "+className+".warningDate<= '"+query.getQuery().getEndDate()+"' ");
		}
		return buf.toString();
	}

}
