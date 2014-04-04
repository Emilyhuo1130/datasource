package com.ucs.rcm.business.bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import com.google.gson.Gson;
import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.db.KSKDBUtil;
import com.ucs.rcm.pojo.Equipmenttreeinfo;
import com.ucs.rcm.pojo.Facilityindex;
import com.ucs.rcm.pojo.Faultinfo;
import com.ucs.rcm.pojo.Hisdata201308;
import com.ucs.rcm.pojo.ReportformsEquipmentIndex;
import com.ucs.rcm.pojo.Warning;
import com.ucs.rcm.reqobj.ChattelanalyseQueryObj;
import com.ucs.rcm.reqobj.OperateindexQueryObj;
import com.ucs.rcm.reqobj.ReqgetChattelanalyseList;
import com.ucs.rcm.reqobj.ReqgetEquipmentName;
import com.ucs.rcm.reqobj.ReqgetEquipmentSubComponent;
import com.ucs.rcm.reqobj.ReqgetTendencyList;
import com.ucs.rcm.reqobj.Reqgetmapview;
import com.ucs.rcm.reqobj.ReqgetEquipmentinfo;
import com.ucs.rcm.reqobj.ReqgetFaultSubComponent;
import com.ucs.rcm.reqobj.ReqgetFaultinfo;
import com.ucs.rcm.reqobj.ReqgetOperateindexList;
import com.ucs.rcm.reqobj.ReqgetSystemName;
import com.ucs.rcm.reqobj.ReqsearchEquipmentInfo;
import com.ucs.rcm.responseObj.FaultType;
import com.ucs.rcm.responseObj.AllTendency;
import com.ucs.rcm.pojo.Operateindex;
import com.ucs.rcm.responseObj.ResOperateindex;
import com.ucs.rcm.responseObj.ResOperateindexList;
import com.ucs.rcm.responseObj.Reportforms_Equipment_indexListInfo;
import com.ucs.rcm.responseObj.ResChattelanalyseList;
import com.ucs.rcm.responseObj.ResEquipmentName;
import com.ucs.rcm.responseObj.ResEquipmentSubComponentName;
import com.ucs.rcm.responseObj.ResFaultSubComponentName;
import com.ucs.rcm.responseObj.ResMapView;
import com.ucs.rcm.responseObj.ResSystemName;
import com.ucs.rcm.responseObj.ResTendencyList;
import com.ucs.rcm.responseObj.Subcomponent;
import com.ucs.rcm.responseObj.SubcomponentInfo;
import com.ucs.rcm.responseObj.TendencyInfo;
import com.ucs.rcm.webservice.partner.EquipmentService;
import com.ucs.rcm.webservice.partner.EquipmentServiceClient;

/**实件分析控制层*/
public class Chattelanalysebo extends BaseHibernateDAO {

	/**
	 * 
	 */

	static Logger log = Logger.getLogger(Chattelanalysebo.class);
	
	private String id;//序号
	private String componentId;//资产编号
	private String equipmentname;//资产名称
	private String systemName;//所属系统
	private String subSystemName;
	private String lineNo;//线号
	private String stationName;//车站
	private String state;//状态
	private String woringCount;//报警数量
	private String time;//时间
	private String healthIndex;//健康指数



	public String getSubSystemName() {
		return subSystemName;
	}

	public void setSubSystemName(String subSystemName) {
		this.subSystemName = subSystemName;
	}
	/**健康指数-设施指数*/
	public   ResChattelanalyseList getChattelanalyses(ReqgetChattelanalyseList query){
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());
		ResChattelanalyseList res = new ResChattelanalyseList();
		List<Facilityindex> chattelanalyseList = new ArrayList<Facilityindex>();
		//根据入参，确定查询语句
		//String str="select * from facilityindex where 1=1  ";
		String sql="from Facilityindex f where 1=1 ";
		sql =sql+ getHqls(query , "f");//+" limit "+pageCount*(pageNo-1)+","+pageCount;
		
		log.info("getChattelanalyses:-----"+sql);
		

		try {
			//链接数据库
			//做查询
			//返回数据库查询结果
			Session session = getSession();
			session.clear();
				Query query_1 = session.createQuery(sql).setFirstResult(pageCount*(pageNo-1)).setMaxResults(pageCount);
				@SuppressWarnings("unchecked")
				Iterator<Facilityindex> it = query_1.list().iterator();
				while(it.hasNext()) {
					Facilityindex f = it.next();
					/*Chattelanalysebo bo = new Chattelanalysebo();
					bo.setComponentId(f.getComponentId());
					bo.setEquipmentname(f.getEquipmentname());
					bo.setId(f.getId().toString());
					bo.setSystemName(f.getSystemName());
					bo.setLineNo(f.getLineNo());
					bo.setStationName(f.getStationName());
					bo.setState(f.getState());
					bo.setWoringCount(f.getWoringCount());
					bo.setTime(f.getUpdatetime());
					bo.setHealthIndex(f.getHealthIndex());*/
					chattelanalyseList.add(f);
					
				}
			
			//关闭流
			//获取总记录数
			session.close();

		} catch (Exception e) {
			log.error("/**健康指数-设施指数*/读取失败"+RcmUtils.getTrace(e));
			e.printStackTrace();
		}
		
		
		try{
			Session session = getSession();
			session.clear();
			String returncount="select count(*) as count from Facilityindex f where 1=1"+getHqls(query , "f");
			totalCount = ((Long)session.createQuery(returncount).uniqueResult()).intValue(); 
			session.close();
		}catch (Exception e) {
			log.error("读取总记录数失败"+RcmUtils.getTrace(e));
			e.printStackTrace();

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
		res.setChattelanalyseList(chattelanalyseList);
		// System.out.println("warningList.size():" + warningList.size());
		try {
			Gson gson = new Gson();
			log.warn("返回的数据="+gson.toJson(res));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;
		
		
	}
	
	@SuppressWarnings("unused")
	private static String getSqls(ReqgetChattelanalyseList query) {
		ChattelanalyseQueryObj queryobj=query.getQuery();
		StringBuffer buf=new StringBuffer();
		buf.append("");
		if((queryobj.getEquipmentname()!=null)&&queryobj.getEquipmentname().trim().length()>0){
			buf.append(" and equipmentname like '%"+queryobj.getEquipmentname()+"%' ");
		}
		if((queryobj.getSystemName()!=null)&&queryobj.getSystemName().trim().length()>0){
			buf.append(" and systemName= '"+queryobj.getSystemName()+"' ");
		}
		if((queryobj.getLineNo()!=null)&&queryobj.getLineNo().trim().length()>0){
			buf.append(" and lineNo= '"+queryobj.getLineNo()+"' ");
		}
		if((queryobj.getStationName()!=null)&&(queryobj.getStationName().trim().length()>0)){
			buf.append(" and stationName= '"+queryobj.getStationName()+"' ");
		}
		if((queryobj.getStatments()!=null)&&(queryobj.getStatments().trim().length()>0)){
			buf.append(" and state= '"+queryobj.getStatments()+"' ");
		}
		
		if((queryobj.getHealthIndex()!=null)&&queryobj.getHealthIndex().trim().length()>0){
			buf.append(" and healthIndex= '"+queryobj.getHealthIndex()+"' ");
		}
		if((queryobj.getStartDate()!=null)&&queryobj.getStartDate().trim().length()>0){
			buf.append(" and updatetime>="+ "'" + queryobj.getStartDate() + "'");
		}
		if((queryobj.getEndDate()!=null)&&queryobj.getEndDate().trim().length()>0){
			buf.append(" and updatetime<="+ "'" + queryobj.getEndDate() + "'");
		}
		buf.append(" order by updatetime desc");
		return buf.toString();
	}
	private static String getHqls(ReqgetChattelanalyseList query,String className) {
		ChattelanalyseQueryObj queryobj=query.getQuery();
		StringBuffer buf=new StringBuffer();
		buf.append("");
		if((queryobj.getEquipmentname()!=null)&&queryobj.getEquipmentname().trim().length()>0){
			buf.append(" and "+className+".equipmentname like '%"+queryobj.getEquipmentname()+"%' ");
		}
		if((queryobj.getSystemName()!=null)&&queryobj.getSystemName().trim().length()>0){
			buf.append(" and "+className+".systemName= '"+queryobj.getSystemName()+"' ");
		}
		if((queryobj.getLineNo()!=null)&&queryobj.getLineNo().trim().length()>0){
			buf.append(" and "+className+".lineNo= '"+queryobj.getLineNo()+"' ");
		}
		if((queryobj.getStationName()!=null)&&(queryobj.getStationName().trim().length()>0)){
			buf.append(" and "+className+".stationName= '"+queryobj.getStationName()+"' ");
		}
		if((queryobj.getStatments()!=null)&&(queryobj.getStatments().trim().length()>0)){
			buf.append(" and "+className+".state= '"+queryobj.getStatments()+"' ");
		}
		
		if((queryobj.getHealthIndex()!=null)&&queryobj.getHealthIndex().trim().length()>0){
			buf.append(" and "+className+".healthIndex= '"+queryobj.getHealthIndex()+"' ");
		}
		if((queryobj.getStartDate()!=null)&&queryobj.getStartDate().trim().length()>0){
			buf.append(" and "+className+".updatetime>="+ "'" + queryobj.getStartDate() + "'");
		}
		if((queryobj.getEndDate()!=null)&&queryobj.getEndDate().trim().length()>0){
			buf.append(" and "+className+".updatetime<="+ "'" + queryobj.getEndDate() + "'");
		}
		buf.append(" order by "+className+".updatetime desc");
		return buf.toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getComponentId() {
		return componentId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getEquipmentname() {
		return equipmentname;
	}
	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	public String getWoringCount() {
		return woringCount;
	}
	public void setWoringCount(String woringCount) {
		this.woringCount = woringCount;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getHealthIndex() {
		return healthIndex;
	}
	public void setHealthIndex(String healthIndex) {
		this.healthIndex = healthIndex;
	}
	
	
	
	/****定时查询设备树，获取信息等待更新设施指数********/
	//@Test
	public   void selecctEquipmentinfo(){
		int equipmentcount=0;
		int facilityindex=0;
		
		try{
			Session session = getSession();
			session.clear();
			String hql1 = "select count(*) as count from Facilityindex f ";
			facilityindex = ((Long)session.createQuery(hql1).uniqueResult()).intValue(); 
			String hql2 = "select count(*) as count from Equipmenttreeinfo e ";
			equipmentcount=((Long)session.createQuery(hql2).uniqueResult()).intValue(); 
			session.close();
		}catch(Exception e){
			log.error("/**定时查询设备树，获取信息等待更新设施指数"+RcmUtils.getTrace(e));
		}
		if(equipmentcount==facilityindex){
			/**数量一样则做更新操作**/
			getfacilityindexinfo();
		}else if(equipmentcount!=facilityindex){
			/**数量不一样就把原来的全部删除，重新插入设备插入操作,然后调用更新**/
			try{
				Session session = getSession();
				session.clear();
				String hql3 = "DELETE from Facilityindex f";
				org.hibernate.Transaction tx = session.beginTransaction();
				session.createQuery(hql3).executeUpdate();
				tx.commit();
			}catch(Exception e){
				log.error("/**DELETE from facilityindex 删除原有的设施指数操作失败"+RcmUtils.getTrace(e));
			}
			getfacilityindexAll();
		}
	}
	
	
	
	/****获取全部信息*/
	private  void getfacilityindexAll() {
		
		List<Chattelanalysebo> boList=new ArrayList<Chattelanalysebo>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updatetime=sdf.format(new Date());
		try{
			Session session = getSession();
			session.clear();
			//String hql = "select e.subcomponentid,e.component,e.systemname,e.subsystemname,e.lineNo,e.stationName from Equipmenttreeinfo e order by e.lineNo";
			String hql = "from Equipmenttreeinfo e order by e.lineNo";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			Iterator<Equipmenttreeinfo> it = query.list().iterator();
			while(it.hasNext()){
				Equipmenttreeinfo e = it.next();
				Chattelanalysebo bo=new Chattelanalysebo();
				bo.setComponentId(e.getSubcomponentid());
				bo.setEquipmentname(e.getComponent());
				bo.setSystemName(e.getSystemname());
				bo.setSubSystemName(e.getSubsystemname());
				bo.setLineNo(e.getLineNo());
				bo.setStationName(e.getStationName());
				bo.setState("良好");
				bo.setWoringCount("0");
				bo.setTime(updatetime);
				bo.setHealthIndex("4");
				boList.add(bo);
			}
			session.close();
		}catch(Exception e){
			log.error("getfacilityindexAll/****获取全部信息*/"+RcmUtils.getTrace(e));
		}
		insertintofacilityindex(boList);
	}

	private  void insertintofacilityindex(List<Chattelanalysebo> boList) {
		Session session = getSession();
		session.clear();
		Transaction tx = session.beginTransaction();
		try{
			for(Chattelanalysebo bo:boList){
				Facilityindex f = new Facilityindex();
				f.setComponentId(bo.getComponentId());
				f.setEquipmentname(bo.getEquipmentname());
				f.setSystemName(bo.getSystemName());
				f.setSubsystemname(bo.getSubSystemName());
				f.setLineNo(bo.getLineNo());
				f.setStationName(bo.getStationName());
				f.setState(bo.getState());
				f.setWoringCount(bo.getWoringCount());
				f.setUpdatetime(bo.getTime());
				f.setHealthIndex(bo.getHealthIndex());
				
				session.save(f);
			}
			tx.commit();
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		/**插入原始数据后，从warning表中插入更新数据*/
		getfacilityindexinfo();
	}
	
	private  void getfacilityindexinfo() {
		
		List<String> warningidList=new ArrayList<String>();
		List<Chattelanalysebo> boList=new ArrayList<Chattelanalysebo>();
		Session session = getSession();
		session.clear();
		try{
			String hql = "select w.equipmentId from Warning w where w.statments in (0,1,2,3)";
			Query query = session.createQuery(hql);
			Iterator<String> it = query.list().iterator();
			while(it.hasNext()){
				warningidList.add(it.next());
			}
			for(String componentId:warningidList){
				String hql2 = "select new Warning(w.id,w.warningType,w.warningTypeLevel) from Warning w where w.statments in (0,1,2,3) and w.equipmentId='"+componentId+"'";
				
				int count=0;
				Chattelanalysebo bo=new Chattelanalysebo();
				String warningType=null;
				int[] levels = new int[4];
				Query query2= getSession().createQuery(hql2);
				@SuppressWarnings("unchecked")
				Iterator<Warning> it2 = query2.list().iterator();
				
				while(it2.hasNext()){
					count++;
					Warning w  = it2.next();
					if(warningType==null){
						warningType=w.getWarningType();
					}else if((w.getWarningType()!=warningType)&&(w.getWarningType()=="故障告警")){
						warningType=w.getWarningType();
					}
					int level=Integer.parseInt(w.getWarningTypeLevel());
					if (level == 1)//严重
						levels[0]++;
					if (level == 2)//危险
						levels[1]++;
					if (level == 3)//良好
						levels[2]++;
					if (level == 4)//健康
						levels[3]++;
				}
				float health=RcmUtils.getHealthLevel(levels);
				bo.setComponentId(componentId);
				bo.setState(warningType);
				bo.setWoringCount(count+"");
				bo.setHealthIndex(((int)health+""));
				boList.add(bo);
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		/***更新设施指标**/
		updatefacilityindex(boList);
	}
	/***更新设施指标**/
	private  void updatefacilityindex(List<Chattelanalysebo> boList) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updateTime=sdf.format(new Date());
		try{
			Session session = getSession();
			session.clear();
			Transaction tx = session.beginTransaction();
			String hql = "UPDATE Facilityindex f set f.state='良好',f.woringCount='0',f.healthIndex='4',f.updatetime=? where f.woringCount!='0';";
			Query query = session.createQuery(hql);
			query.setInteger(0, Integer.parseInt(updateTime));
			tx.commit();
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		
		try{
			Session session = getSession();
			session.clear();
			Transaction tx = session.beginTransaction();
			for(Chattelanalysebo bo:boList){
				String hql3 = "update Facilityindex f set f.state=?,f.woringCount=?,f.healthIndex=?,f.updatetime=? where f.componentId=?";
				Query query3  = session.createQuery(hql3);
				query3.setString(0, bo.getState());
				query3.setString(1, bo.getWoringCount());
				query3.setString(2, bo.getHealthIndex());
				query3.setString(3, updateTime);
				query3.setString(4, bo.getComponentId());
				query3.executeUpdate();
				
			}
			tx.commit();
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		/*****更新后获取更新后的信息，然后插入报表中**/
		goto_insertinto_reportforms_equipment_index();
		
	}
	/*****更新后获取更新后的信息，然后插入报表中-----读取信息**/
	private  void goto_insertinto_reportforms_equipment_index() {
		
		List<Reportforms_Equipment_indexListInfo> infoList=
				new ArrayList<Reportforms_Equipment_indexListInfo>();
				Session session = getSession();
				session.clear();
		try{
			String find="from Facilityindex f ";
			Query query = session.createQuery(find);
			@SuppressWarnings("unchecked")
			Iterator<Facilityindex> it = query.list().iterator();
			while(it.hasNext()){
				Facilityindex f = it.next();
				Reportforms_Equipment_indexListInfo info=new Reportforms_Equipment_indexListInfo();
				info.setSystemName(f.getSystemName());
				info.setSubsystemName(f.getSubsystemname());
				info.setLineNo(f.getLineNo());
				info.setStationName(f.getStationName());
				info.setComponent(f.getEquipmentname());
				info.setLocationId("--");
				info.setComponentDescription("--");
				info.setHealthLevel(f.getHealthIndex());
				infoList.add(info);
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
		}
		/*****更新后获取更新后的信息，然后插入报表中-----保存报表信息**/

		insertinto_reportforms_equipment_index(infoList);
	}
	
	/*****更新后获取更新后的信息，然后插入报表中-----保存报表信息**/
	private  void insertinto_reportforms_equipment_index(
			List<Reportforms_Equipment_indexListInfo> infoList) {
	
		String insert="insert into reportforms_equipment_index values(null,?,?,?,?,?,?,?,?)";
		Session session = getSession();
		session.clear();
		try{
			Transaction tx = session.beginTransaction();
			for(Reportforms_Equipment_indexListInfo info:infoList){
				ReportformsEquipmentIndex rfe = new ReportformsEquipmentIndex();
				rfe.setId(null);
				rfe.setComponent(info.getComponent());
				rfe.setComponentDescription(info.getComponentDescription());
				rfe.setHealthLevel(info.getHealthLevel());
				rfe.setLineNo(info.getLineNo());
				rfe.setLocationId(info.getLocationId());
				rfe.setStationName(insert);
				rfe.setSubsystemName(info.getSubsystemName());
				rfe.setSystemName(insert);
				session.save(rfe);
				
			}
			tx.commit();
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
	}


	/**根据条件查询设备详情   options查询时id=0  当点击查询时，id给值，其他置空 */
	public  Subcomponent getEquipmentinfo(ReqgetEquipmentinfo req){
		
		String sql=getEquipmentinfoSql(req);
		System.out.println(sql);
		Subcomponent subcomponent=new Subcomponent();
		Session session = getSession();
		session.clear();
		try{
			Query query = session.createQuery(sql);
			@SuppressWarnings("unchecked")
			Iterator<Subcomponent> it = query.list().iterator();
			while(it.hasNext()){
				Subcomponent e = it.next();
				subcomponent.setId(e.getId());
				subcomponent.setComponentid(e.getComponentid());
				subcomponent.setEquipmentName(e.getEquipmentName());
				subcomponent.setEquipmentId(e.getEquipmentId());
				subcomponent.setSystemName(e.getSystemName());
				subcomponent.setEquipmentDescription(e.getEquipmentDescription());
				subcomponent.setLevel(e.getLevel());
				subcomponent.setSubcomponentId(e.getSubcomponentId());
				subcomponent.setUpEquipment(e.getUpEquipment());
				subcomponent.setSubComponent(e.getSubComponent());
				
			}
		
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			
			session.close();
		}
		return subcomponent;
	}
	public static String getEquipmentinfoSql(ReqgetEquipmentinfo req){
		StringBuffer buf=new StringBuffer();
		buf.append("from Equipmenttreeinfo e where 1=1 ");
		if(req.getId()!=0){
			buf.append(" and e.id="+req.getId());
		}
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and e.systemname= '"+req.getSystemName()+"' ");
		}
		if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
			buf.append(" and e.equipmentname= '"+req.getEquipmentName()+"' ");
		}
		if((req.getSubcomponent()!=null)&&(req.getSubcomponent().trim().length()>0)){
			buf.append(" and e.subcomponent= '"+req.getSubcomponent()+"' ");
		}
		return buf.toString();
	}
	
	/**根据条件查询故障树详情  options查询时id=0  当点击查询时，id给值，其他置空*/
	
	public FaultType getFaultTypeinfo(ReqgetFaultinfo req){
		String sql=getFaultTypeinfoHql(req,"f");
		log.warn(sql);
		FaultType faultType =new FaultType();
		Session session = getSession();
		session.clear();
		try{
			Query query = session.createQuery(sql);
			@SuppressWarnings("unchecked")
			Iterator<Faultinfo> it = query.list().iterator();
			while(it.hasNext()){
				Faultinfo f = it.next();
				faultType.setId(f.getId());
				faultType.setComponentid(f.getComponentid());
				faultType.setEquipmentName(f.getEquipmentname());
				faultType.setEquipmentId(f.getEquipmentid());
				faultType.setSystemName(f.getSystemname());
				faultType.setEquipmentDescription(f.getEquipmentDescription());
				faultType.setType(f.getFaulttype());
				faultType.setFultDescription(f.getFultDescription());
				faultType.setFaultCause(f.getFaultCause());
				faultType.setInfluence(f.getInfluence());
				faultType.setMaintenancePolicy(f.getMaintenancePolicy());
				faultType.setSubcomponent(f.getSubcomponent());
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
		}
		return faultType;
	}
	public  String getFaultTypeinfoSql(ReqgetFaultinfo req){
		StringBuffer buf=new StringBuffer();
		buf.append("from Faultinfo f where 1=1 ");
		if(req.getId()!=0){
			buf.append(" and f.id="+req.getId());
		}
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and f.systemname= '"+req.getSystemName()+"' ");
		}
		if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
			buf.append(" and f.equipmentname= '"+req.getEquipmentName()+"' ");
		}
		if((req.getSubcomponent()!=null)&&(req.getSubcomponent().trim().length()>0)){
			buf.append(" and f.subcomponent= '"+req.getSubcomponent()+"' ");
		}
		if((req.getFaultType()!=null)&&(req.getFaultType().trim().length()>0)){
			buf.append(" and f.faultType= '"+req.getFaultType()+"' ");
		}
		
		return buf.toString();
	}
	public static String getFaultTypeinfoHql(ReqgetFaultinfo req , String className){
		StringBuffer buf=new StringBuffer();
		buf.append("from Faultinfo f where 1=1 ");
		if(req.getId()!=0){
			buf.append(" and "+className+".id="+req.getId());
		}
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and "+className+".systemname= '"+req.getSystemName()+"' ");
		}
		if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
			buf.append(" and "+className+".equipmentname= '"+req.getEquipmentName()+"' ");
		}
		if((req.getSubcomponent()!=null)&&(req.getSubcomponent().trim().length()>0)){
			buf.append(" and "+className+".subcomponent= '"+req.getSubcomponent()+"' ");
		}
		if((req.getFaultType()!=null)&&(req.getFaultType().trim().length()>0)){
			buf.append(" and"+className+".faultType= '"+req.getFaultType()+"' ");
		}
		
		return buf.toString();
	}
	
	/**options 设备树和故障树的查询选项，当变化系统名称时，后面的设备名称也随之变化，前台也随之变化，部件也是一样*/
	public  ResSystemName  findsystemName(ReqgetSystemName req){
	
		ResSystemName systemnamelist=new ResSystemName ();
		String[] options = null;
		String sql="select DISTINCT e.systemname from Equipmenttree e";
		String count="select count(DISTINCT   e.systemname) as namenumber from  Equipmenttree e";
		Session session = getSession();
		session.clear();
		try{
			Query query = session.createQuery(count);
			@SuppressWarnings("unchecked")
			Iterator<Integer> it = query.list().iterator();
			while(it.hasNext()){
				int number=it.next();
				options=new String[number];
			}
			Query query2 = getSession().createQuery(sql);
			@SuppressWarnings("unchecked")
			Iterator<String> it2 = query2.list().iterator();
			int i=0;
			while(it2.hasNext()){
				options[i]=it2.next();
				i++;
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
		}
		systemnamelist.setOprions(options);
		return systemnamelist;
	}

	public  ResEquipmentName findequipmentName(ReqgetEquipmentName req) {
		
		ResEquipmentName equipment=new ResEquipmentName();
		String[] options = null;
		String sql="select DISTINCT e.equipment from Equipmenttree e ";
		String count="select count(DISTINCT   e.equipment) as namenumber from  Equipmenttree e ";
		if(req.getSystemName()!=null&&req.getSystemName().trim().length()>0){
			sql=sql+" where e.systemname='"+req.getSystemName()+"'";
			count=count+" where e.systemname='"+req.getSystemName()+"'";
		}
		Session session = getSession();
		session.clear();
		try{
			Query query = session.createQuery(count);
			@SuppressWarnings("unchecked")
			Iterator<Integer> it = query.list().iterator();
			while(it.hasNext()){
				int number=it.next();
				options=new String[number];
			}
			Query query2 = getSession().createQuery(sql);
			@SuppressWarnings("unchecked")
			Iterator<String> it2 = query2.list().iterator();
			int i=0;
			while(it2.hasNext()){
				options[i]=it2.next();
				i++;
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
		}
		equipment.setOptions(options);
		return equipment;
	}

	public  ResEquipmentSubComponentName findEquipmentSubComponentName(
			ReqgetEquipmentSubComponent req) {
		
		String[] options = null;
		ResEquipmentSubComponentName SubComponent =new ResEquipmentSubComponentName();
		String sql="select DISTINCT e.subcomponent from Equipmentinfo e where 1=1 ";
		String count="select count(DISTINCT   e.subcomponent) as namenumber from Equipmentinfo e where 1=1 ";
		if(req.getSystemName()!=null&&req.getSystemName().trim().length()>0){
			sql=sql+" and e.systemname='"+req.getSystemName()+"'";
			count=count+" and e.systemname='"+req.getSystemName()+"'";
		}
		if(req.getEquipmentName()!=null&&req.getEquipmentName().trim().length()>0){
			sql=sql+" and e.equipmentname='"+req.getEquipmentName()+"'";
			count=count+" and e.equipmentname='"+req.getEquipmentName()+"'";
		}
		
		Session session = getSession();
		session.clear();
		try{
			Query query = session.createQuery(count);
			@SuppressWarnings("unchecked")
			Iterator<Integer> it = query.list().iterator();
			while(it.hasNext()){
				int number=it.next();
				options=new String[number];
			}
			Query query2 = session.createQuery(sql);
			@SuppressWarnings("unchecked")
			Iterator<String> it2 = query2.list().iterator();
			int i=0;
			while(it2.hasNext()){
				options[i]=it2.next();
				i++;
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
		}
		SubComponent.setOptions(options);
		return SubComponent;
	}

	public  ResFaultSubComponentName findFaultSubComponentName(
			ReqgetFaultSubComponent req) {
		
		String[] options = null;
		ResFaultSubComponentName subComponent=new ResFaultSubComponentName();
		String sql="select DISTINCT f.subcomponent from Faultinfo f where 1=1";
		String count="select count(DISTINCT  f.subcomponent) as namenumber from  Faultinfo f where 1=1 ";
		if(req.getSystemName()!=null&&req.getSystemName().trim().length()>0){
			sql=sql+" and f.systemname='"+req.getSystemName()+"'";
			count=count+" and f.systemname='"+req.getSystemName()+"'";
		}
		if(req.getEquipmentName()!=null&&req.getEquipmentName().trim().length()>0){
			sql=sql+" and f.equipmentname='"+req.getEquipmentName()+"'";
			count=count+" and f.equipmentname='"+req.getEquipmentName()+"'";
		}
		
		Session session = getSession();
		session.clear();
		try{
			Query query = session.createQuery(count);
			@SuppressWarnings("unchecked")
			Iterator<Integer> it = query.list().iterator();
			while(it.hasNext()){
				int number=it.next();
				options=new String[number];
			}
			Query query2 = session.createQuery(sql);
			@SuppressWarnings("unchecked")
			Iterator<Integer> it2 = query2.list().iterator();
			int i=0;
			while(it2.hasNext()){
				options[i]=it2.next().toString();
				i++;
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		subComponent.setOptions(options);
		return subComponent;
	}

	/**运营指数s*/
	public   ResOperateindexList getOperateindexList(ReqgetOperateindexList query) {
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());

		ResOperateindexList res = new ResOperateindexList();
		List<ResOperateindex> operateindexList = new ArrayList<ResOperateindex>();
		//根据入参，确定查询语句
		//String sqlselect="select * from Operateindex where 1=1";
		String sql="from Operateindex where 1=1 ";
		sql = sql+getOperateindexSqls(query);
		//sql=sql+" limit "+pageCount*(pageNo-1)+","+pageCount;
		log.warn(sql);
		try {
			//链接数据库
			//做查询
			Session session = getSession();
			session.clear();
			Query query2 = session.createQuery(sql).setFirstResult(pageCount*(pageNo-1)).setMaxResults(pageCount);
			//返回数据库查询结果
			@SuppressWarnings("unchecked")
			Iterator<Operateindex> it = query2.list().iterator();
				while(it.hasNext()){
					ResOperateindex ro = new ResOperateindex();
					Operateindex bo = it.next();
					ro.setId(bo.getId());
					ro.setHealthindex(bo.getHealthindex());
					ro.setLevelforeCount(bo.getLevelforeCount());
					ro.setLevelthreeCount(bo.getLevelthreeCount());
					ro.setLeveltwoCount(bo.getLeveltwoCount());
					ro.setLeveloneCount(bo.getLeveloneCount());
					ro.setLineNo(bo.getLineNo());
					ro.setTime(bo.getUpdatetime());
					operateindexList.add(ro);
					
				}
			
			//关闭流
				session.close();
		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
		try{
			//获取总记录数
			Session session = getSession();
			session.clear();
			String returnsql=getOperateindexSqls(query);
			String returncount="select count(*) as count from Operateindex o where 1=1 "+returnsql;
			totalCount = ((Long)session.createQuery(returncount).uniqueResult()).intValue();
		}catch (Exception e) {
			log.error("********************"+RcmUtils.getTrace(e));
		}
		//设置返回结果的总记录数
		res.setTotalCount(totalCount + "");
		//计算总页数
		totalPage = totalCount / pageCount;
		if (totalCount % pageCount > 0) {
			totalPage = totalCount / pageCount + 1;
		}
		//设置返回结果的总页数
		res.setTotalPage(totalPage + "");
		//设置返回结果的总警告记录
		res.setOperateindexList(operateindexList);
		return res;
	}
	
	/***定时器定时调用的更新运营指数的方法*****/
	
	public  void updateOperateIndex(){
		
		List<ResOperateindex> operateindexlist= new ArrayList<ResOperateindex>();
		List<String> lines = new ArrayList<String>();
		
		Session session = getSession();
		session.clear();
		try{
			String sqls = "select distinct w.lineNo  from Warning w  order by CAST(w.lineNo as UNSIGNED  )";
			Query query =  session.createQuery(sqls);
			@SuppressWarnings("unchecked")
			Iterator<String> it = query.list().iterator();
			//线号
			while (it.hasNext()) {
				lines.add(it.next());
			}
			
			for (int i = 0; i < lines.size(); i++) {
				ResOperateindex operate=new ResOperateindex();
				int level;
				//状态分四个等级
				int[] levels = new int[4];

				
				// System.out.println(lineName);
				sqls = "select w.warningTypeLevel from Warning w where w.lineNo = '" + lines.get(i)+"' and w.statments in(0,1,2,3)";
				Query query2 = session.createQuery(sqls);
				@SuppressWarnings("unchecked")
				List<String> levelList = query2.list();
				for (String bo : levelList) {
					
					level = new Integer(bo);
						//告警的数量
					if (level == 1)//严重
						levels[0]++;
					if (level == 2)//危险
						levels[1]++;
					if (level == 3)//良好
						levels[2]++;
					if (level == 4)//健康
						levels[3]++;

				}

				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time=sdf.format(new Date());
				float health=RcmUtils.getHealthLevel(levels);
				operate.setLineNo(lines.get(i));
				operate.setHealthindex(((int)health+""));
				operate.setLeveloneCount(levels[0]+"");
				operate.setLeveltwoCount(levels[1]+"");
				operate.setLevelthreeCount(levels[2]+"");
				operate.setLevelforeCount(levels[3]+"");
				operate.setTime(time);
				operateindexlist.add(operate);

			}
			
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
		}
		/***更新实件分析-健康指数-运营指标数据库**/
		insertoperateindex(operateindexlist);
		/***存储报表管理的运营指标**/
		insertreportformsoperateindex(operateindexlist);
	}
	

	
	/***存储报表管理的运营指标**/
	private  void insertreportformsoperateindex(
			List<ResOperateindex> operateindexlist) {
		Session session = getSession();
		session.clear();
		try{
			String insert="insert into reportforms_operateindex (lineNo,healthindex,leveloneCount,leveltwoCount,levelthreeCount," +
					"levelforeCount,updatetime) values(?,?,?,?,?,?,?) ";
			Transaction tx = session.beginTransaction();
			for(ResOperateindex operate:operateindexlist){
				ResOperateindex o = new ResOperateindex();
				o.setLineNo(operate.getLineNo());
				o.setHealthindex(operate.getHealthindex());
				o.setTime(operate.getTime());
				o.setLeveloneCount(operate.getLeveloneCount());
				o.setLeveltwoCount(operate.getLeveltwoCount());
				o.setLevelthreeCount(operate.getLevelthreeCount());
				o.setLevelforeCount(operate.getLevelforeCount());
				getSession().save(o);
			}
			tx.commit();
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
	}
	/***更新实件分析-健康指数-运营指标数据库**/
	private  void insertoperateindex(List<ResOperateindex> operateindexlist) {
		Session session = getSession();
		session.clear();
		try{
			Transaction tx = session.beginTransaction();
			
			String delete="delete from Operateindex o";
			session.createQuery(delete);
			 
			 
			
			for(ResOperateindex operate:operateindexlist){
				ResOperateindex o = new ResOperateindex();
				o.setHealthindex(operate.getHealthindex());
				o.setLevelforeCount(operate.getLevelforeCount());
				o.setLeveloneCount(operate.getLeveloneCount());
				o.setLevelthreeCount(operate.getLevelthreeCount());
				o.setLeveltwoCount(operate.getLeveltwoCount());
				o.setLineNo(operate.getLineNo());
				o.setTime(operate.getTime());
				session.save(o);
			}
			tx.commit();
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
	}

	@SuppressWarnings("unused")
	private static List<String> rsToBo(ResultSet rs) {
		List<String> list=new ArrayList<String>();
		try{
			while(rs.next()){
				String str=rs.getString("WarningTypeLevel");
				list.add(str);
			}
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		return list;
	}

	private static String getOperateindexSqls(ReqgetOperateindexList query) {
		OperateindexQueryObj queryObj=query.getQuery();
		StringBuffer sqls=new StringBuffer();
		if ((queryObj.getLineNo() !=  null)&&(queryObj.getLineNo().trim().length()>0)) {
			sqls.append(" and lineNo= '"+queryObj.getLineNo()+"' ");
		}
		
		if ((queryObj.getStartDate() != null)&&(queryObj.getStartDate().trim().length()>0)) {
			sqls.append(" and time>=" + "'" + queryObj.getStartDate() + "'");

		}

		if ((queryObj.getEndDate() != null)&&(queryObj.getEndDate().trim().length()>0)) {
			sqls.append(" and time<=" + "'" + queryObj.getEndDate() + "'");
		}
		return sqls.toString();
	}
	private static String getOperateindexHqls(ReqgetOperateindexList query , String className) {
		OperateindexQueryObj queryObj=query.getQuery();
		StringBuffer sqls=new StringBuffer();
		if ((queryObj.getLineNo() !=  null)&&(queryObj.getLineNo().trim().length()>0)) {
			sqls.append(" and "+className+".lineNo= '"+queryObj.getLineNo()+"' ");
		}
		
		if ((queryObj.getStartDate() != null)&&(queryObj.getStartDate().trim().length()>0)) {
			sqls.append(" and "+className+".time>=" + "'" + queryObj.getStartDate() + "'");

		}

		if ((queryObj.getEndDate() != null)&&(queryObj.getEndDate().trim().length()>0)) {
			sqls.append(" and "+className+".time<=" + "'" + queryObj.getEndDate() + "'");
		}
		return sqls.toString();
	}

		/**用于查询全部车站的部件*/
	public SubcomponentInfo searchEquipmentInfo(
		ReqsearchEquipmentInfo req) {
	
		String sql=searchEquipmentInfohql(req,"e");
		log.warn(sql);
		SubcomponentInfo subcomponent=new SubcomponentInfo();
		try{
		Query query = getSession().createQuery(sql);
		@SuppressWarnings("unchecked")
		Iterator<Equipmenttreeinfo> it = query.list().iterator();
			while(it.hasNext()){
				Equipmenttreeinfo e = it.next();
				subcomponent.setId(e.getId());
				subcomponent.setComponent(e.getSubcomponent());
				subcomponent.setLevel(e.getLevel());
				subcomponent.setStationName(e.getStationName());
				subcomponent.setSystemName(e.getSystemname());
				subcomponent.setSubComponentid(e.getSubcomponentid());
				subcomponent.setSubSystemName(e.getSystemname());
				subcomponent.setSubComponent(e.getSubcomponent());
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return subcomponent;
	}
	public static String searchEquipmentInfosql(ReqsearchEquipmentInfo req){
		StringBuffer buf=new StringBuffer();
		buf.append("select * from EquipmentTreeinfo where 1=1 ");
		if(req.getId()!=0){
			buf.append(" and id="+req.getId());
		}
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and stationName= '"+req.getStationName()+"' ");
		}
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and systemName= '"+req.getSystemName()+"' ");
		}
		if((req.getComponent()!=null)&&(req.getComponent().trim().length()>0)){
			buf.append(" and component= '"+req.getComponent()+"' ");
		}
		if((req.getSubComponent()!=null)&&(req.getSubComponent().trim().length()>0)){
			buf.append(" and subcomponent= '"+req.getSubComponent()+"' ");
		}
		return buf.toString();
	}
	
	public static String searchEquipmentInfohql(ReqsearchEquipmentInfo req , String className){
		StringBuffer buf=new StringBuffer();
		buf.append("from Equipmenttreeinfo e where 1=1 ");
		if(req.getId()!=0){
			buf.append(" and id="+req.getId());
		}
		if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
			buf.append(" and "+className+".stationName= '"+req.getStationName()+"' ");
		}
		if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
			buf.append(" and "+className+".systemName= '"+req.getSystemName()+"' ");
		}
		if((req.getComponent()!=null)&&(req.getComponent().trim().length()>0)){
			buf.append(" and "+className+".component= '"+req.getComponent()+"' ");
		}
		if((req.getSubComponent()!=null)&&(req.getSubComponent().trim().length()>0)){
			buf.append(" and "+className+".subcomponent= '"+req.getSubComponent()+"' ");
		}
		return buf.toString();
	}
	/******趋势分析的数据*******/
	public  ResTendencyList getTendencyList_old(ReqgetTendencyList query) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar   c   =   Calendar.getInstance();
		c.set(Calendar.HOUR,-12);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MINUTE,0);
		long zhetime = 0;/**今天零点的时间*/
		try{
			zhetime=sdf.parse(sdf.format(c.getTime())).getTime()/1000;
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		
		
		List<String> pointcodes=new ArrayList<String>();
		
		/**测试用**/
		/*String findpointcode="select distinct pointcode from hisdata201308 " +
		"where 1=1 and componentId= '"+query.getComponentId()+"' " ;*/
		
		String findpointcode="select distinct h.pointcode from Hisdata201308 h " +
				"where 1=1 and h.componentId= '"+query.getComponentId()+"' " ;
		
		
		try{
			Session session = getSession();
			session.clear();
			Query query_1 = session.createQuery(findpointcode);
			@SuppressWarnings("unchecked")
			Iterator<String> it = query_1.list().iterator();
			while(it.hasNext()){
				pointcodes.add(it.next());
			}
			session.close();
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}

		ResTendencyList res = new ResTendencyList();
		List<AllTendency> allList=new ArrayList<AllTendency>();
		
		
		/*String sql="select * from hisdata201308 where  pointcode=? ";
		String sqlfind_name="select pointDesc from pointtable_a where pointCode=?";*/
		
		String sql="from Hisdata201308 h where  h.pointcode=? ";
		String sqlfind_name="select p.pointDesc from PointtableA p where p.pointCode=?";
		
		for(int i=0;i<pointcodes.size();i++){
			AllTendency all=new AllTendency();
			try{
				Session session = getSession();
				session.clear();
				String pdc = (String)session.createQuery(sqlfind_name).setString(0, pointcodes.get(i)).uniqueResult();
				all.setName(pdc);
				session.close();
			}catch (Exception e) {
				log.error(RcmUtils.getTrace(e));
			}
			
			List<TendencyInfo> infoList=new ArrayList<TendencyInfo>();
			try{
				Session session = getSession();
				session.clear();
				Query query_3 = session.createQuery(sql).setString(0, pointcodes.get(i));
				@SuppressWarnings("unchecked")
				Iterator<Hisdata201308> it = query_3.list().iterator();
				while(it.hasNext()){
					TendencyInfo info=new TendencyInfo();
					Hisdata201308 h = it.next();
					long l=Integer.parseInt(h.getSavetime().toString());
					info.setSavetime((l*1000));
					info.setValue((float)(h.getValue()*10/10.0));
					infoList.add(info);
				}
				session.close();
			}catch (Exception e) {
				log.error(RcmUtils.getTrace(e));
			}
			
			all.setInfoList(infoList);
			allList.add(all);
			
			
		}
		res.setInfoList(allList);
		//log.info("******模拟数据=*************"+gson.toJson(res));
		return res;
	}
	
	
	
/**在途视图**/
	public static ResMapView getOperateindexList(Reqgetmapview query) {
		ResMapView map=new ResMapView();
		String mapURL=null;
		  EquipmentServiceClient ec = new EquipmentServiceClient();
		  EquipmentService es = ec.getEquipmentSoapPort( );
		  String xml = es.getEquipment(query.getComponentId(), query.getType());
		  try{
			  Document document = DocumentHelper.parseText(xml);
			  Element root=document.getRootElement();
			  Element viewe=root.element("PlaceViewOutput");
			  Element mape=viewe.element("PlaceView");
			  mapURL=mape.getText();
		  }catch(Exception e){
			  log.error(RcmUtils.getTrace(e));
		  }
			
		  map.setMapURL(mapURL);
		return map;
	}

	
	
	
/***新增的方法   从卡斯柯获取历史数据*/	
	public  ResTendencyList getTendencyList(ReqgetTendencyList query) {
	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar   c   =   Calendar.getInstance();
		c.set(Calendar.HOUR,-24);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MINUTE,0);
		long zhetime = 0;/**今天零点的时间*/
		long endtime=0;
		try{
			zhetime=sdf.parse(sdf.format(c.getTime())).getTime()/1000;
			c.set(Calendar.HOUR,+24);
			endtime=sdf.parse(sdf.format(c.getTime())).getTime()/1000;
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		
		java.sql.Connection conn = KSKDBUtil.getConn();
		PreparedStatement ps=null;
		
		ResultSet rs = null;
		List<String> pointcodes =new ArrayList<String>();
		
		
		String findpointcode="select distinct F4007_POINTCODE from vw1205_hispara " +
		"where 1=1 and F1004_DEVCODE= '"+query.getComponentId()+"' " ;
		
		try{
			ps=conn.prepareStatement(findpointcode);
			rs=ps.executeQuery();
			while(rs.next()){
				pointcodes.add(rs.getString("F4007_POINTCODE").trim());
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}

		ResTendencyList res = new ResTendencyList();
		List<AllTendency> allList=new ArrayList<AllTendency>();
		
		
		String groupcode=null;
		String No=null;
		int RECORDNO=0;
		for(int i=0;i<pointcodes.size();i++){
			System.out.println("-----------------------");
			AllTendency all=new AllTendency();
			try{
				String sqlfind_name="select F4007_SAVENO,F1001_GROUPCODE,F4007_pointDesc " +
						""+"from vw1205_hispara where F4007_POINTCODE = '"+pointcodes.get(i)+"' ";
				ps=conn.prepareStatement(sqlfind_name);
				rs=ps.executeQuery();
				rs.next();
				all.setName(rs.getString("F4007_pointDesc").trim());
				System.out.println(rs.getString("F4007_pointDesc").trim());
				
				String saveNo=rs.getString("F4007_SAVENO").trim();
				
				groupcode=rs.getString("F1001_GROUPCODE").trim();
				No=((Integer.parseInt(saveNo)-1)%200)+"";
				RECORDNO =Integer.parseInt(saveNo)/200;
				
				ps.close();
				rs.close();
			}catch (Exception e) {
				log.error(RcmUtils.getTrace(e));
			}
			
				
				
		
			List<TendencyInfo> infoList=new ArrayList<TendencyInfo>();
			String sql="select savetime,val"+No+" from tana_data where groupcode= " +
					"'"+groupcode+"' and RECORDNO="+RECORDNO+" and " +
							" savetime <= "+endtime+" and savetime >= "+zhetime+"";
			System.out.println(sql);
			try{
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					TendencyInfo info=new TendencyInfo();
					long l=Integer.parseInt(rs.getString("savetime"));
					info.setSavetime((l*1000));
					info.setValue(rs.getFloat("val"+No));
					infoList.add(info);
				}
				ps.close();
				rs.close();
			}catch (Exception e) {
				log.error(RcmUtils.getTrace(e));
			}
			
			all.setInfoList(infoList);
			allList.add(all);
			
		}
		res.setInfoList(allList);
		Gson gson = new Gson();
		System.out.println(gson.toJson(res));
		return res;
	}




	
}
