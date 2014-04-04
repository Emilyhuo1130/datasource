package com.ucs.rcm.business.bo;
/**警告信息*/
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.google.gson.Gson;
import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.db.KSKDBUtil;
import com.ucs.rcm.pdrdataUtils.KSKPointInfo;
import com.ucs.rcm.pdrdataUtils.ReadInfo;
import com.ucs.rcm.pojo.Equipmenttreeinfo;
import com.ucs.rcm.pojo.FaultinfoUser2;
import com.ucs.rcm.pojo.InformPlan;
import com.ucs.rcm.pojo.InformUser4;
import com.ucs.rcm.pojo.LineHealth;
import com.ucs.rcm.pojo.MaintainCoupleBack;
import com.ucs.rcm.pojo.MaintainPlanTable;
import com.ucs.rcm.pojo.Message;
import com.ucs.rcm.pojo.ReportformsRequisition;
import com.ucs.rcm.pojo.Warning;
import com.ucs.rcm.pojo.WarningPlan;
import com.ucs.rcm.reqobj.PlanWarningQueryObj;
import com.ucs.rcm.reqobj.ReqcommitPlanWaringById;
import com.ucs.rcm.reqobj.Reqconfirmorcancel;
import com.ucs.rcm.reqobj.ReqgetDataShow;
import com.ucs.rcm.reqobj.ReqgetEquipmentAllInfo;
import com.ucs.rcm.reqobj.ReqgetEquipmentName;
import com.ucs.rcm.reqobj.ReqgetFaultInfo_user2ById;
import com.ucs.rcm.reqobj.ReqgetIds;
import com.ucs.rcm.reqobj.ReqgetPlanWaringList;
import com.ucs.rcm.reqobj.ReqgetSystemName;
import com.ucs.rcm.reqobj.ReqgetWaringList;
import com.ucs.rcm.reqobj.ReqgetWaringListBystatments;
import com.ucs.rcm.reqobj.ReqgetWarningCounting;
import com.ucs.rcm.reqobj.ReqgetLineHealth;
import com.ucs.rcm.reqobj.ReqgetthisDayLineHealth;
import com.ucs.rcm.reqobj.Reqinsertinform_user4;
import com.ucs.rcm.reqobj.Reqmessage;
import com.ucs.rcm.reqobj.ReqstopFlow;
import com.ucs.rcm.reqobj.RequpdateFaultInfo_user2ById;
import com.ucs.rcm.reqobj.ReqwarningOperate;
import com.ucs.rcm.reqobj.WarningQueryObj;
import com.ucs.rcm.reqobj.ReqinsertFaultInfo_user2;
import com.ucs.rcm.responseObj.DateShow;
import com.ucs.rcm.responseObj.PlanWarning;
import com.ucs.rcm.responseObj.Plan_Inform;
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
import com.ucs.rcm.responseObj.ResWaringList2;
import com.ucs.rcm.responseObj.ResWarningCounting;
import com.ucs.rcm.responseObj.TendencyInfo;
import com.ucs.rcm.utils.StringUtils;

public class WarningBo extends BaseHibernateDAO {
	 Logger log = Logger.getLogger(WarningBo.class);
	
	/**警告页面返回的参数*/
	private String id;//序号
	private String warningId;//警告id  
	private String equipmentId;//设备id
	private String equipmentname;//设备名称
	private String systemName;//所属系统
	private String equipmentDescription;//部件描述
	private String lineNo;//线号
	private String stationName;//站台名称
	private String warningType;//警告类型 
	private String warningTypeLevel;//警告类型级别
	private String warninginfo;//警告内容
	private String warningDate;//警告日期
	private String statments;//0：未处理 1：确认 2：核实 3：审定 4：发送（已处理） 5：以取消
								// 确认，核实，审定统称为 确认中显示在页面   页面的来自  是来自当前状态
	private String fromuser;//数据来源于哪个用户
	

	public String getFromuser() {
		return fromuser;
	}
	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}
	
	
	
	
	Gson gson = new Gson();
	@SuppressWarnings("unchecked")
	public ResWaringList getWarnins(ReqgetWaringList query)   {
		Gson gson  = new Gson();
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());

		ResWaringList res = new ResWaringList();
		List<Warning> warningList = new ArrayList<Warning>();
		//根据入参，确定查询语句
		//String sqlselect="select * from warning where 1=1";
		//select * from warning where warningType = '故障告警' and statments in(0,1,2,3,4) order by warningDate desc limit 10,10;
		
		String hql = "from Warning w where 1=1";
		String hqls = hql+getHqls(query , "w");
		//将"and  and"  替换为"and"
		hqls = StringUtils.replaceByRegExp("and(\\s*)and", hqls, "and");
		log.info("*************hqls=***************"+hqls);
		try {
			 Query query_1 = getSession().createQuery(hqls).setFirstResult((pageNo-1)*pageCount).setMaxResults(pageCount);
			List<Warning> lists = query_1.list();
			 log.info("***********"+gson.toJson(lists)+"**************");
			 //使用迭代器
			 Iterator<Warning> iterator = lists.iterator();
			 while(iterator.hasNext()){
					Warning warning = iterator.next();
					Warning bo = new Warning();
					bo.setEquipmentDescription(warning.getEquipmentDescription());
					bo.setEquipmentId(warning.getEquipmentId());
					bo.setEquipmentname(warning.getEquipmentname());
					bo.setFromuser(warning.getFromuser());
					bo.setId(warning.getId());
					bo.setLineNo(warning.getLineNo());
					bo.setStationName(warning.getStationName());
					bo.setStatments(warning.getStatments());
					bo.setSystemName(warning.getSystemName());
					bo.setWarningDate(warning.getWarningDate());
					bo.setWarningId(warning.getWarningId());
					bo.setWarninginfo(warning.getWarninginfo());
					bo.setWarningType(warning.getWarningType());
					bo.setWarningTypeLevel(warning.getWarningTypeLevel());
					warningList.add(bo);
				 
			 }
		
		} catch (Exception e) {
			log.info("*************error WarningBo132行*****************************");
			log.error(RcmUtils.getTrace(e));
		}
		
		try{
			//获取总记录数
			String returnhql=getHqls(query , "w");
			String returncount="select count(*) as count from Warning w  where 1=1 "+returnhql;
			log.info("**********获取总记录数**************"+returncount);
			//将and  and  替换为and
			returncount = StringUtils.replaceByRegExp("and(\\s*)and", returncount, "and");
			Query query_2;
			try {
				log.info("sql(167有效)=**********"+returncount+"**************");
				query_2= getSession().createQuery(returncount);
				if(query_2.list().size()!=0){
					List<Long> list = query_2.list();
			        int ttc = list.get(0).intValue();
					log.info("******获得总的记录数******"+ttc);
					totalCount = ttc;
				}
				
				
			} catch (Exception e) {
				log.error("********error**********");
				e.printStackTrace();
			}
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
		res.setWarningList(warningList);
		log.info("*************返回的结果:"+res.getWarningList()+"***************");
		// System.out.println("warningList.size():" + warningList.size());
		return res;

	}
	
	
	/**根据页面上的查询条件生成条件查询语句*/
	@SuppressWarnings("unused")
	private  String getSqls(ReqgetWaringList query) {
		WarningQueryObj queryObj = query.getQuery();

		//String sqls = "select * from warning where 1=1 ";
		String sqls = " ";
		if ((queryObj.getWarningType() != null)&&(queryObj.getWarningType().trim().length()>0) ) {
			sqls = sqls + " and ";
			sqls = sqls + " warningType= '" + queryObj.getWarningType()+"' ";
		}
		
		if ((queryObj.getLineNo() !=  null)&&(queryObj.getLineNo().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + " lineNo= '" + queryObj.getLineNo()+"' ";
		}

		if ((queryObj.getStationName() != null)&&(queryObj.getStationName().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + " stationName= '" + queryObj.getStationName()+"' ";
		}

		if ((queryObj.getStatments() != null)&&(queryObj.getStatments().trim().length()>0)) {
			sqls = sqls + " and ";
			//首页查询用的 状态的查询条件
			if(queryObj.getStatments().equals("未处理")){
				sqls = sqls + " statments=0 ";
			}
			if(queryObj.getStatments().equals("确认中")){
				sqls = sqls + " (statments='1' or statments='2' or statments='3')";
			}
			if(queryObj.getStatments().equals("已处理")){
				sqls = sqls + " statments=4 ";
			}
			if(queryObj.getStatments().equals("已取消")){
				sqls = sqls + " statments=5 ";
			}
			
			
		}

		if ((queryObj.getSystemName() != null)&&(queryObj.getSystemName().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + "systemName= '" + queryObj.getSystemName()+"' ";
		}

		if ((queryObj.getEquipmentname()!= null)&&(queryObj.getEquipmentname().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + "equipmentname LIKE '%" + queryObj.getEquipmentname()+"%' ";
		}

		if ((queryObj.getWarningTypeLevel() != null)&&(queryObj.getWarningTypeLevel().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + " warningTypeLevel= '" + queryObj.getWarningTypeLevel()+"' ";
		}

		if ((queryObj.getStartDate() != null)&&(queryObj.getStartDate().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + "WarningDate>=" + "'" + queryObj.getStartDate() + "'";

		}

		if ((queryObj.getEndDate() != null)&&(queryObj.getEndDate().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + "WarningDate<=" + "'" + queryObj.getEndDate() + "'";

		}
		sqls=sqls+" and statments in(0,1,2,3,4) ORDER by statments,warningDate DESC ";
		String teststr = "and";
		Pattern pattern = Pattern.compile("and(\\s*)and");
		Matcher matcher = pattern.matcher(teststr);
		while(matcher.find()){
			log.info("*************and and存在***************");
			teststr = teststr.replaceAll("and(\\s*)and", "and");
			log.info("**看看替换没*******"+teststr+"*******");
		}
		return sqls;
	}
	
	
	
	//为hql封装查询条件
	private  String getHqls(ReqgetWaringList query , String className) {
		WarningQueryObj queryObj = query.getQuery();

		String sqls = " ";
		if ((queryObj.getWarningType() != null)&&(queryObj.getWarningType().trim().length()>0) ) {
			sqls = sqls + " and ";
			sqls = sqls + className+".warningType= '" + queryObj.getWarningType()+"' ";
		}
		
		if ((queryObj.getLineNo() !=  null)&&(queryObj.getLineNo().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + className+".lineNo= '" + queryObj.getLineNo()+"' ";
		}

		if ((queryObj.getStationName() != null)&&(queryObj.getStationName().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + className+".stationName= '" + queryObj.getStationName()+"' ";
		}

		if ((queryObj.getStatments() != null)&&(queryObj.getStatments().trim().length()>0)) {
			sqls = sqls + " and ";
			//首页查询用的 状态的查询条件
			if(queryObj.getStatments().equals("未处理")){
				sqls = sqls + className+".statments=0 ";
			}
			if(queryObj.getStatments().equals("确认中")){
				sqls = sqls + " ("+className+".statments='1' or "+className+".statments='2' or "+className+".statments='3')";
			}
			if(queryObj.getStatments().equals("已处理")){
				sqls = sqls + className+".statments=4 ";
			}
			if(queryObj.getStatments().equals("已取消")){
				sqls = sqls + className+".statments=5 ";
			}
			
			
		}

		if ((queryObj.getSystemName() != null)&&(queryObj.getSystemName().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + className+".systemName= '" + queryObj.getSystemName()+"' ";
		}

		if ((queryObj.getEquipmentname()!= null)&&(queryObj.getEquipmentname().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + className+".equipmentname LIKE '%" + queryObj.getEquipmentname()+"%' ";
		}

		if ((queryObj.getWarningTypeLevel() != null)&&(queryObj.getWarningTypeLevel().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + className+".warningTypeLevel= '" + queryObj.getWarningTypeLevel()+"' ";
		}

		if ((queryObj.getStartDate() != null)&&(queryObj.getStartDate().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + className+".warningDate>=" + "'" + queryObj.getStartDate() + "'";

		}

		if ((queryObj.getEndDate() != null)&&(queryObj.getEndDate().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + className+".warningDate<=" + "'" + queryObj.getEndDate() + "'";

		}
		sqls=sqls+" and "+className+".statments in(0,1,2,3,4) ORDER by "+className+".statments,"+className+".warningDate DESC ";
		String teststr = "and";
		Pattern pattern = Pattern.compile("and(\\s*)and");
		Matcher matcher = pattern.matcher(teststr);
		while(matcher.find()){
			log.info("*************and and存在***************");
			teststr = teststr.replaceAll("and(\\s*)and", "and");
			log.info("**看看替换没*******"+teststr+"*******");
		}
		log.info("*****************************getHqls拼装好的hqls="+sqls+"******************************");
		return sqls;
	}
	
	
	
	

	public String getWarningDate() {
		return warningDate;
	}

	public void setWarningDate(String warningDate) {
		this.warningDate = warningDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWarningId() {
		return warningId;
	}

	public void setWarningId(String warningId) {
		this.warningId = warningId;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
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

	public String getEquipmentDescription() {
		return equipmentDescription;
	}

	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getWarningType() {
		return warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	public String getWarningTypeLevel() {
		return warningTypeLevel;
	}

	public void setWarningTypeLevel(String warningTypeLevel) {
		this.warningTypeLevel = warningTypeLevel;
	}

	public String getWarninginfo() {
		return warninginfo;
	}

	public void setWarninginfo(String warninginfo) {
		this.warninginfo = warninginfo;
	}

	public String getStatments() {
		return statments;
	}

	public void setStatments(String statments) {
		this.statments = statments;
	}
	
	/***计划修预警list**/
	public ResPlanWaringList getPlanWaringList(ReqgetPlanWaringList query) {
		Gson gson = new Gson();
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());
		log.info("**pageNo**pageCount*****"+pageNo+","+pageCount);
		ResPlanWaringList res = new ResPlanWaringList();
		List<PlanWarning> planwarningList = new ArrayList<PlanWarning>();
		//根据入参，确定查询语句
		/*String sql="select * from warning_plan where 1=1  ";
		String sqls = sql+getPlanSqls(query);*/
		String hql = "from WarningPlan w where  1=1 ";
		String hqls = hql + getPlanHqls(query,"w");
		Query query_3 ;
		
		//获得session
				
	try {
		query_3 = getSession().createQuery(hqls).setFirstResult((pageNo-1)*pageCount).setMaxResults(pageCount);
		log.warn("***************"+hqls+"***********************");
		@SuppressWarnings("unchecked")
		List<WarningPlan> lists = query_3.list();
		 log.info("***********"+gson.toJson(lists)+"**************");
		 if(lists.size()!=0){
			 Iterator<WarningPlan> iterator = lists.iterator();
			 PlanWarning bo = new PlanWarning();
				while(iterator.hasNext()){
					WarningPlan pw = iterator.next();
					bo.setEquipmentId(pw.getEquipmentId());
					bo.setEquipmentname(pw.getEquipmentname());
					bo.setId(pw.getId().toString());
					bo.setLineNo(pw.getLineNo());
					bo.setStationName(pw.getStationName());
					bo.setStatments(pw.getStatments());
					bo.setSystemName(pw.getSystemName());
					bo.setWarningDate(pw.getWarningDate().toString());
					bo.setWarninginfo(pw.getWarninginfo());
					bo.setWarningType(pw.getWarningType());
					bo.setWarningTypeLevel(pw.getWarningTypeLevel());
					bo.setSubsystemName(pw.getSubsystemName());
					planwarningList.add(bo);
				}
			 
		 }
			
		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
		try{
			//获取总记录数
			String returnHql=getPlanHqls(query,"w");
			String returncount="select  count(*)  from WarningPlan w where 1=1 "+returnHql;
			log.info("******查看记录数语句***"+returncount);
			Query query_4;
			query_4 = getSession().createQuery(returncount);
		        @SuppressWarnings("unchecked")
				List<Long> list = query_4.list();
		        int ttc = list.get(0).intValue();
					totalCount=ttc;
					
		
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
		log.info("*******获得总页数*****"+totalPage);
		// System.out.println("totalPage:" + res.getTotalPage());
		//设置返回结果的总警告记录
		res.setPlanWarningList(planwarningList);
		log.info("**********************"+res.getPlanWarningList()+"************************");
		// System.out.println("warningList.size():" + warningList.size());
		return res;
		
	}
	
	//为计划修预警封装条件查询
	@SuppressWarnings("unused")
	private  String getPlanSqls(ReqgetPlanWaringList query) {
		PlanWarningQueryObj queryObj = query.getQuery();

		String sqls = " ";
		if ((queryObj.getWarningTypeLevel() != null)&&(queryObj.getWarningTypeLevel().trim().length()>0) ) {
			sqls = sqls + " and ";
			sqls = sqls + " warningType= '" + queryObj.getWarningTypeLevel()+"' ";
		}
		
		if ((queryObj.getLineNo() !=  null)&&(queryObj.getLineNo().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + " lineNo= '" + queryObj.getLineNo()+"' ";
		}

		if ((queryObj.getStationName() != null)&&(queryObj.getStationName().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + " stationName= '" + queryObj.getStationName()+"' ";
		}

		if ((queryObj.getSystemName() != null)&&(queryObj.getSystemName().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + "systemName= '" + queryObj.getSystemName()+"' ";
		}

		if ((queryObj.getEquipmentname()!= null)&&(queryObj.getEquipmentname().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + "equipmentname like '%" + queryObj.getEquipmentname()+"%' ";
		}

		if ((queryObj.getStartDate() != null)&&(queryObj.getStartDate().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + "WarningDate>=" + "'" + queryObj.getStartDate() + "'";

		}

		if ((queryObj.getEndDate() != null)&&(queryObj.getEndDate().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + "WarningDate<=" + "'" + queryObj.getEndDate() + "'";

		}
		
		sqls=sqls+" and statments = '"+queryObj.getStatments()+"'  order by warningDate desc ";
		return sqls;
	}
	
	
	
	//为计划修预警封装条件查询(hql)
	private  String getPlanHqls(ReqgetPlanWaringList query , String className ) {
		PlanWarningQueryObj queryObj = query.getQuery();

		String sqls = " ";
		if ((queryObj.getWarningTypeLevel() != null)&&(queryObj.getWarningTypeLevel().trim().length()>0) ) {
			sqls = sqls + " and ";
			sqls = sqls + className+".warningType= '" + queryObj.getWarningTypeLevel()+"' ";
		}
		
		if ((queryObj.getLineNo() !=  null)&&(queryObj.getLineNo().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + className+".lineNo= '" + queryObj.getLineNo()+"' ";
		}

		if ((queryObj.getStationName() != null)&&(queryObj.getStationName().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + className+".stationName= '" + queryObj.getStationName()+"' ";
		}

		if ((queryObj.getSystemName() != null)&&(queryObj.getSystemName().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + className+".systemName= '" + queryObj.getSystemName()+"' ";
		}

		if ((queryObj.getEquipmentname()!= null)&&(queryObj.getEquipmentname().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + className+".equipmentname like '%" + queryObj.getEquipmentname()+"%' ";
		}

		if ((queryObj.getStartDate() != null)&&(queryObj.getStartDate().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + className+".warningDate>=" + "'" + queryObj.getStartDate() + "'";

		}

		if ((queryObj.getEndDate() != null)&&(queryObj.getEndDate().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + className+".warningDate<=" + "'" + queryObj.getEndDate() + "'";

		}
		
		sqls=sqls+" and "+className+".statments = '"+queryObj.getStatments()+"'  order by "+className+".warningDate desc ";
		return sqls;
	}
	
	/***发送计划修通知单      0==》1 表示已经发送通知单  不会在计划修里面显示****/
	
   //通过id来更新计划修的状态
	public ResInfo commitPlanWaringById(ReqcommitPlanWaringById query) {
		ResInfo info=new ResInfo();
	//获得session
		//必须开启事务
		Transaction tx = getSession().beginTransaction();
		
		try{
			String hql=" update WarningPlan w set w.statments='1' where w.id="+Integer.parseInt(query.getId());
			log.info("***************输出hql="+hql+"********************");
			
			getSession().createQuery(hql).executeUpdate();//执行
			
		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		/***读取规则表 获取规则表计划修的信息***/
		Plan_Inform planinform =new Plan_Inform();
		try{
			String findinfo="from MaintainPlanTable m where m.equipmentId= '"+query.getEquipmentId()+"' ";
		Query query_5 = getSession().createQuery(findinfo);
		@SuppressWarnings("unchecked")
		List<MaintainPlanTable> lists = query_5.list();
		Iterator<MaintainPlanTable> iterator = lists.iterator();
			while(iterator.hasNext()){
				MaintainPlanTable m = iterator.next();
				planinform.setEquipmentId(m.getEquipmentId());
				planinform.setEquipmentname(m.getEquipmentname());
				planinform.setSubsystemName(m.getSubsystemName());
				planinform.setSystemName(m.getSystemName());
				planinform.setLineNo(m.getLineNo());
				planinform.setStationName(m.getStationName());
				planinform.setWarningType(m.getWarningType());
				planinform.setWarningTypeLevel(m.getWarningTypeLevel());
				planinform.setMaintaininfo(m.getMaintaininfo());
				planinform.setNext_maintainDate(m.getNextMaintainDate().toString());
				planinform.setMaintain_period(m.getMaintainPeriod());
				planinform.setNext_maintainDate(m.getNextMaintainDate().toString());
				
			}
			
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
		/***发通知单**/
		try{
			
			InformPlan informPlan = new InformPlan();
			String maintain_id=null;
			maintain_id=(new Date()).getTime()/1000+"";
			//检查日期类型
				informPlan.setMaintainId(maintain_id);
				informPlan.setEquipmentId( planinform.getEquipmentId());
				informPlan.setEquipmentname( planinform.getEquipmentname());
				informPlan.setLineNo(planinform.getLineNo());
				informPlan.setMaintaininfo(planinform.getMaintaininfo());
				informPlan.setMaintainPeriod( planinform.getMaintain_period());
				informPlan.setNextMaintainDate(planinform.getNext_maintainDate());
				informPlan.setStationName(planinform.getStationName());
				informPlan.setStatments("0");
				informPlan.setSubsystemName(planinform.getSubsystemName());
				informPlan.setSystemName( planinform.getSystemName());
				informPlan.setWarningType(planinform.getWarningType());
				informPlan.setWarningTypeLevel(planinform.getWarningTypeLevel());
				getSession().save(informPlan);
				info.setInfo("success");
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
			info.setInfo("false");
		}finally{
			tx.commit();
		}
		
		return info;
	}
	
	
	
	//通过id来获取告警信息
	/**根据id来获取警告信息*/
	public Warning getWarningInfoById(ReqgetIds req){
		String hql = "from Warning w where w.id="+req.getId();
		Gson gson = new Gson();
		
		Warning bo = new Warning();
		try{
			Query query_6 = getSession().createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Warning> lists = query_6.list();
			
			Iterator<Warning> iterator = lists.iterator();
			while(iterator.hasNext()){
				bo = iterator.next();
				
			}
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
		}
		//检查返回告警信息
		//log.info("*******根据id来获取警告信息********"+gson.toJson(bo));
		return bo;
	}
	
	
	
	
	
	
	//更新操作
	/**信息更新，展现处理情况*/
	public ResInfo update(ReqwarningOperate reqwarningOperate) {
		log.info("**************信息更新，展现处理情况****************");
		String operatorIndex = reqwarningOperate.getAction();// confirm/cancel
		int[] ids = reqwarningOperate.getIds();
		Transaction tx = getSession().beginTransaction();
		ResInfo r = new ResInfo();

		try {
			//0：未处理 1：确认 2：核实 3：审定 4：发送（已处理） 5：以取消
			for (int id : ids) {
				//设置处理状态
				if (reqwarningOperate.getAction().equalsIgnoreCase("untreated"))//未处理
					operatorIndex = "0";
				if (reqwarningOperate.getAction().equalsIgnoreCase("confirm"))//确认
					operatorIndex = "1";
				if (reqwarningOperate.getAction().equalsIgnoreCase("check"))//核实
					operatorIndex = "2";
				if (reqwarningOperate.getAction().equalsIgnoreCase("approve"))//审定
					operatorIndex = "3";
				if (reqwarningOperate.getAction().equalsIgnoreCase("processed"))//发送（已处理）
					operatorIndex = "4";
				if (reqwarningOperate.getAction().equalsIgnoreCase("cancel"))//取消
					operatorIndex = "5";
				//根据id来更新处理状态
				String hql ="update Warning w set w.statments =? , w.fromuser=? where id=?";
				log.info("***************将id为"+id+"的状态改为"+operatorIndex+"************************");
				Query query = getSession().createSQLQuery(hql);
				query.setString(0, operatorIndex);
				query.setString(1, reqwarningOperate.getOperator());
				query.setInteger(2, id);
				query.executeUpdate();
				log.info("********updatehql="+hql+"***********");
			
				
			}

			///处理成功返回success。用于前台界面显示处理结果的标识
			r.setInfo("sucess");
			log.info("***************处理结果*************"+r.getInfo());
			return r;

		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
			tx.commit();
		}
		//处理失败返回false。用于前台界面显示处理结果的标识
		r.setInfo("false");
		return r;

	}
	//保存信息
	//保存告警管理 消息内容
	public  ResInfo saveMessage(Reqmessage req) {
		//获得session
		ResInfo r = new ResInfo();
		try{
			Message ms = new Message();
			ms.setInfo(req.getStarMessage());
			ms.setInfodate(req.getStarTime());
			getSession().save(ms);
			
			Message me  = new Message();
			me.setInfo(req.getEndMessage());
			me.setInfodate(req.getEndTime());
			getSession().save(me);
			
			r.setInfo("sucess");
			return r;
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
		}
		r.setInfo("false");
		return r;
	}
	//返回线路健康指数
	//返回线路健康状况
	public ResLineHealth getLineHealth(ReqgetLineHealth req) {
		ResLineHealth res = new ResLineHealth();
		List<Health> listHealth = new ArrayList<Health>();
		//获得session
		
		String lineName;
		try {
			Health health;
			//String sqls = "select distinct lineNo  from warning  order by CAST(lineNo as UNSIGNED  )";
			String hqls = "select distinct w.lineNo from Warning w order by w.lineNo)";
			Query query_7 = getSession().createQuery(hqls);
			//线号
			List<String> lines = new ArrayList<String>();
			@SuppressWarnings("unchecked")
			List<String> lists = query_7.list();
			Iterator<String> iterator = lists.iterator();
			while (iterator.hasNext()) {
				String lineNo = iterator.next();
				lines.add(lineNo);

			}
		
			for (int i = 0; i < lines.size(); i++) {
				int level;
				//状态分四个等级
				int[] levels = new int[4];

				lineName = lines.get(i);
				// System.out.println(lineName);
				//sqls = "select * from warning where lineNo = '" + lineName+"' and statments in(0,1,2,3)";
				hqls = "from Warning w where w.lineNo = '" + lineName+"' and w.statments in(0,1,2,3)";
				Query query_8 = getSession().createQuery(hqls);
				@SuppressWarnings("unchecked")
				List<Warning> ws = query_8.list(); 
				List<WarningBo> warningList = new ArrayList<WarningBo>();
				warningList = wsToBo(ws);
				for (WarningBo bo : warningList) {
					// System.out.println("id:"+bo.getId());

					// System.out.println(new
					// Integer(bo.getWarningTypeLevel()));
					level = new Integer(bo.getWarningTypeLevel());
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

				health = new Health();
				health.setLineNo(lineName);
				health.setStats((int) RcmUtils.getHealthLevel(levels) + "");
				listHealth.add(health);

			}

		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));

		}finally{
		}

		res.setListHealth(listHealth);
		log.info("***************"+res);

		return res;
	}
	
	

	@SuppressWarnings("unused")
	private  List<WarningBo> rsToBo(ResultSet rs) {
		List<WarningBo> warningList = new ArrayList<WarningBo>();
		WarningBo bo;

		try {
			while (rs.next()) {
				bo = new WarningBo();
				bo.setEquipmentId(rs.getString("equipmentId"));
				bo.setEquipmentDescription(rs.getString("Equipmentdescription"));
				bo.setEquipmentname(rs.getString("equipmentname"));
				bo.setId(rs.getString("id"));
				bo.setLineNo(rs.getString("lineNo"));
				bo.setStationName(rs.getString("stationName"));
				bo.setStatments(rs.getString("statments"));
				bo.setSystemName(rs.getString("systemName"));
				bo.setWarningDate(rs.getString("warningDate"));
				bo.setWarninginfo(rs.getString("warninginfo"));
				bo.setWarningId(rs.getString("warningId"));
				bo.setWarningType(rs.getString("warningType"));
				bo.setWarningTypeLevel(rs.getString("warningTypeLevel"));
				bo.setFromuser(rs.getString("fromuser"));
				warningList.add(bo);

			}
		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));

		}

		return warningList;
	}
	private  List<WarningBo> wsToBo(List<Warning> wns) {
		List<WarningBo> warningList = new ArrayList<WarningBo>();
		WarningBo bo;

		try {
			for(Warning ws : wns){
				bo = new WarningBo();
				bo.setEquipmentId(ws.getEquipmentId());
				bo.setEquipmentDescription(ws.getEquipmentDescription());
				bo.setEquipmentname(ws.getEquipmentname());
				bo.setId(ws.getId().toString());
				bo.setLineNo(ws.getLineNo());
				bo.setStationName(ws.getStationName());
				bo.setStatments(ws.getStatments());
				bo.setSystemName(ws.getSystemName());
				bo.setWarningDate(ws.getWarningDate());
				bo.setWarninginfo(ws.getWarningDate());
				bo.setWarningId(ws.getWarningId());
				bo.setWarningType(ws.getWarningType());
				bo.setWarningTypeLevel(ws.getWarningTypeLevel());
				bo.setFromuser(ws.getFromuser());
				warningList.add(bo);
			}
		

		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));

		}

		return warningList;
	}
	
	//告警数量ok
	public ResWarningCounting getWarningCounting(
			ReqgetWarningCounting req) {
		ResWarningCounting res = new ResWarningCounting();
		List<LevelCount> levelCounts = new ArrayList<LevelCount>();
		LevelCount levelCount;
		LevelHistory levelHistory = new LevelHistory();

		// 设置时间间隔 小时为单位      表示过去的每一天，页面 24小时  对应天
		int interval = 24 ;

		levelHistory.setInterval(interval + "");

		List<LineCount> lineCounts = new ArrayList<LineCount>();
		LineCount lineCount;

		List<LevelSeqs> levelSeqs = new ArrayList<LevelSeqs>();
		
		
		Gson gson = new Gson();
		try {
				//获取不同线号的报警数量
			String hql = "select count(*) as count ,  w.lineNo   from Warning w group by w.lineNo  order by  w.lineNo";
		Query query_3_ = getSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Object[]> obs = query_3_.list();
		log.info("解析集合obs="+gson.toJson(obs));
		Iterator<Object[]> it = obs.iterator();
			while (it.hasNext()) {
				Object[] ot = it.next();
				lineCount = new LineCount();
				lineCount.setCount(ot[0].toString());
				lineCount.setLineNo(ot[1].toString());
				lineCounts.add(lineCount);

			}
				//获取不同告警等级的数量
			hql = "select count(*) as count ,  w.warningTypeLevel   from Warning  w group by w.warningTypeLevel  order by  w.warningTypeLevel";
			Query query_4_ = getSession().createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Object[]> obt = query_4_.list();
			Iterator<Object[]> its = obt.iterator();
			while (its.hasNext()) {
				Object[]  ob = its.next();
				levelCount = new LevelCount();
				levelCount.setLevel(ob[1].toString());
				levelCount.setCount(ob[0].toString());
				levelCounts.add(levelCount);

			}
			

			for (int k = 1; k < 5; k++) {
				LevelSeqs ls = new LevelSeqs();
				ls.setLevel(k + "");
				String[] counts = new String[10];

				for (int i = 0; i < 10; i++) {//过去显示过去10天的告警数量
					SimpleDateFormat dateformat1 = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");

					Calendar c = Calendar.getInstance();
					c.add(Calendar.HOUR,
							i * (-1) * new Integer(levelHistory.getInterval()));
					Date date1 = c.getTime();
					// ps.setDate(1, new java.sql.Date(c.getTime().getTime()));
					c = Calendar.getInstance();
					c.add(Calendar.HOUR, (i + 1) * (-1)
							* new Integer(levelHistory.getInterval()));
					// ps.setDate(2, new java.sql.Date( c.getTime().getTime()));
					Date date2 = c.getTime();
					// ps.setString(3, ls.getLevel());

					hql = "select count(*) as count   from Warning w  where w.warningDate < '"
							+ dateformat1.format(date1)
							+ "' and  w.warningDate > '"
							+ dateformat1.format(date2)
							+ "' and w.warningTypeLevel = " + ls.getLevel();
					//System.out.println(sql);
					Query query_5_ = getSession().createQuery(hql);
					if(query_5_.iterate().hasNext()){
						String cnt = ((Long)query_5_.iterate().next()).toString();
						counts[i] = cnt;
					}
				
				}

				ls.setCount(counts);
				levelSeqs.add(ls);
			}

		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
		}

		levelHistory.setLines(levelSeqs);
		res.setLevelCounts(levelCounts);
		res.setLevelHistory(levelHistory);
		res.setLineCounts(lineCounts);
		log.info("*****res=***************"+gson.toJson(res));
		return res;
	}
	
	/**options  用于页面的查询按钮内容*/
	
	//获取系统名称ok
	public  ResSystemName  findsystemName(ReqgetSystemName req){
		//获得session
		Gson gson = new Gson();
		
		
		
		ResSystemName systemnamelist=new ResSystemName ();
		String[] options = null;
		String hql = "select DISTINCT e.systemname from Equipmenttree e";
		String count = "select count(DISTINCT   e.systemname) as namenumber from   Equipmenttree e";
		try{
			Query query_9 = getSession().createQuery(count);
			Query query_9_=getSession().createQuery(hql);
			int num=((Number)query_9.iterate().next()).intValue(); 
			options = new String[num];
			@SuppressWarnings("unchecked")
			List<String> systemNames = query_9_.list();
			Iterator<String> its = systemNames.iterator();
			
			int i=0;
			while(its.hasNext()){
				options[i]=its.next();
				i++;
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
		}
		systemnamelist.setOprions(options);
		log.info("**********options*********"+gson.toJson(options));
		return systemnamelist;
	}
	
	//user2核实ok
	public  ResEquipmentName findequipmentName(ReqgetEquipmentName req) {
		
		
		Gson gson = new Gson();
		ResEquipmentName equipment=new ResEquipmentName();
		String[] options = null;
		String hql="select DISTINCT e.equipment from Equipmenttree e ";
		String count="select count(DISTINCT   equipment) as namenumber from   Equipmenttree e";
		if(req.getSystemName()!=null&&req.getSystemName().trim().length()>0){
			hql=hql+" where e.systemname='"+req.getSystemName()+"'";
			count=count+" where e.systemname='"+req.getSystemName()+"'";
		}
	 
		try{
			Query query_1_=getSession().createQuery(count);
			if(query_1_.iterate().hasNext()){
				int number = ((Long)query_1_.iterate().next()).intValue();
				log.info("********设备数量=:******"+number);
				options=new String[number];
			}
		
			int i=0;
			log.info("******输出hql语句=*****"+hql);
			Query query_2_ = getSession().createQuery(hql);
			@SuppressWarnings("unchecked")
			List<String> ops = query_2_.list();
			log.info("options="+gson.toJson(ops));
			Iterator<String> it = ops.iterator();
			while(it.hasNext()){
				options[i++]=it.next();
				
			}
		
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		equipment.setOptions(options);
		
		log.info("********equipment=****************"+gson.toJson(equipment));
		return equipment;
	}
	/**用于user2生成副表*/
	public ResInfo insertFault_user2(ReqinsertFaultInfo_user2 req) {
	
		ResInfo r = new ResInfo();
		
		int id=0;
		//String select ="select id from Faultinfo_user2 where id="+req.getId();
		String   select = "select f.id from FaultinfoUser2 f where f.id="+req.getId();
		try{
		
			Query query_0_=getSession().createQuery(select);
			@SuppressWarnings("unchecked")
			List<Integer> ids = query_0_.list();
			if(ids.size()!=0){
				id=ids.get(0);
			}
			log.info("****id=****"+id);
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		
		
		try{
			
		if(id==0){
			String find="select new Equipmenttreeinfo(e.maintenanceId,e.subsystemname) from Equipmenttreeinfo e where e.subcomponentid= '"+req.getEquipmentid()+"'";
			Session session = getSession();
			Query query_1_=session.createQuery(find);
			Equipmenttreeinfo ets = (Equipmenttreeinfo)query_1_.iterate().next();
			log.info("**********查看从数据库获取的对象************"+ets);
			log.info("**********查看req*"+gson.toJson(req)+"************************");
			String maintenanceId=ets.getMaintenanceId();//维保编号
			@SuppressWarnings("unused")
			String subsystemname=ets.getSubsystemname();//子系统
			
			//TODO 从电科所获取里程
			/*ReqgetEquipmentAllInfo info =new ReqgetEquipmentAllInfo();
			info.setEquipmentId(req.getEquipmentid());
			Equipment e=getEquipmentAllInfo(info);*/
			//测试
			Equipment e=new Equipment();
			
			String equipmentDescription=getequipmentDescription(req.getId());
			FaultinfoUser2 fu = new FaultinfoUser2();
			fu.setId(req.getId());
			fu.setEquipmentid(req.getEquipmentid());
			fu.setEquipmentname(req.getEquipmentname());
			fu.setSystemname(req.getSystemname());
			fu.setWarningType(req.getWarningType());
			fu.setWarningTypeLevel(req.getWarningTypeLevel());
			fu.setFultDescription(req.getFultDescription());
			fu.setLineNo(req.getLineNo()==null?"10":req.getLineNo());
			fu.setStationName(req.getStationName());
			fu.setWarningDate(req.getWarningDate());
			fu.setFaultCause(req.getFaultCause());
			fu.setInfluence(req.getInfluence());
			fu.setMainTenancePolicy(req.getMaintenancePolicy());
			fu.setEquipmentDescription(equipmentDescription);
			fu.setOpinion(req.getOpinion());
			fu.setMaintenanceId(maintenanceId);
			fu.setWarningId(req.getWarningId());
			fu.setMileage(Float.parseFloat(e.getLcsz()));
			fu.setStatments("2");
			fu.setFromuser(req.getOperator());
			session.save(fu);
			Gson gson = new Gson();
			log.info("***********************"+gson.toJson(fu)+"************************");
			Transaction tx = getSession().beginTransaction();
			String insertHql = "update  Warning w set w.statments=? ,w.fromuser=? where w.id=?";
			Query query = session.createQuery(insertHql);
				query.setString(0, "2");
				query.setString(1, req.getOperator());
				query.setInteger(2, req.getId());
				query.executeUpdate();
				session.flush();
			tx.commit();
		}else{
			Session session = getSession();
			Transaction tx2 = session.beginTransaction();
			String updateinfo="update Faultinfo_user2 set statments='2', fromuser ='"+req.getOperator()+"' where id="+req.getId();
			session.createSQLQuery(updateinfo).executeUpdate();
			String updateWarning="update warning set statments='2', fromuser ='"+req.getOperator()+"' where id="+req.getId();
			session.createSQLQuery(updateWarning).executeUpdate();
			session.flush();
			tx2.commit();
		}
			r.setInfo("sucess");
			return r;
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		r.setInfo("false");
		return r;
	}
	
	
	//获取设备详细信息改为hibernate 
	public  String getequipmentDescription(int id2) {
		
		String name=null;
		String hql = "select w.equipmentDescription  from Warning w where w.id="+id2;
		try{
		Query query = getSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<String> names = query.list();
		Iterator<String> ite = names.iterator();
			while(ite.hasNext()){
				name=ite.next();
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		log.info("*******设备详细信息=*********"+name);
		return name;
	}
	/**user3点击详情的时候返回这条记录**/
	//获得故障信息
public ResFaultInfo_user2 getFaultInfo_user2ById(ReqgetFaultInfo_user2ById req) {
		
		ResFaultInfo_user2 user2 =new ResFaultInfo_user2();
		String hql="from FaultinfoUser2 f where f.id=?";
		Gson gson = new Gson();
		try{
		Query query_2_ = getSession().createQuery(hql).setInteger(0, req.getId());
		log.info("****query_2_=******"+gson.toJson(query_2_)+","+gson.toJson(query_2_.list())+"****************");
		@SuppressWarnings("unchecked")
		List<FaultinfoUser2> fu = query_2_.list();
		if(fu.size()>0){
			Iterator<FaultinfoUser2> ite = fu.iterator();
			while(ite.hasNext()){
				FaultinfoUser2 fur=ite.next();
				//log.info("**********fur=************"+gson.toJson(fur));
				user2.setId(fur.getId());
				user2.setEquipmentid(fur.getEquipmentid());
				user2.setEquipmentname(fur.getEquipmentname());
				user2.setSystemname(fur.getSystemname());
				user2.setWarningType(fur.getWarningType());
				user2.setWarningTypeLevel(fur.getWarningTypeLevel());
				user2.setFultDescription(fur.getFultDescription());
				user2.setLineNo(fur.getLineNo());
				user2.setStationName(fur.getStationName());
				user2.setWarningDate(fur.getWarningDate().toString());
				user2.setFaultCause(fur.getFaultCause());
				user2.setInfluence(fur.getInfluence());
				user2.setMaintenancePolicy(fur.getMainTenancePolicy());
				user2.setOpinion(fur.getOpinion());
				user2.setStatments(fur.getStatments());
				user2.setFromuser(fur.getFromuser());
				user2.setMaintenanceId(fur.getMaintenanceId());
				user2.setSubsystemName(fur.getSystemname());
				
			}
		}else{
			log.error("*************得到的是空集，请检查数据库是否有该条记录*************");
		}
		
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		log.info("************fromuser=************"+user2.getFromuser());
		
		if(user2.getFromuser().equals("3")||"曹雪".equals(user2.getFromuser())){
			String number="0000000000";
			String id=user2.getId()+"";
			int x=number.length()-id.length();
			String suggest_Number=number.substring(0,x+1)+id;
			user2.setSuggest_Number(suggest_Number);
			log.info("*********suggest_Number=*********"+user2.getSuggest_Number());
			
		}
		return user2;
	}


	/**user3 终止和审定按钮  终止时添加审定意见 同时修改warning的状态*/
	public ResInfo updateFaultInfo_user2ById(
			RequpdateFaultInfo_user2ById req) {
		ResInfo r = new ResInfo();
		Transaction tx = getSession().beginTransaction();
		//检查请求信息
		log.info("*********************************"+gson.toJson(req));
		try{//审定
			if(req.getOk().equals("goto")){
				String sql=" update  FaultinfoUser2 f  set f.statments = '3',f.fromuser= '"+req.getOperator()+"' ,f.opinion='"+req.getOpinion()+"' ,f.faultCause=?,f.influence=?,f.mainTenancePolicy=? where f.id =  " + req.getId();
				String updateWaring=" update  warning  set statments = '3',fromuser='"+req.getOperator()+"'  where id =  " + req.getId();
				
				getSession().createQuery(sql).setString(0, req.getFaultCause()).setString(1, req.getInfluence()).setString(2, req.getMainTenancePolicy()).executeUpdate();
				
				getSession().createSQLQuery(updateWaring).executeUpdate();
			}else if(req.getOk().equals("goback")){//终止流程
				String Nook=" update  FaultinfoUser2 f set f.statments = '1',f.fromuser= '"+req.getOperator()+"' ,f.opinion='"+req.getOpinion()+"'  where id =  " + req.getId();
				String updateWaring=" update  warning  set statments = '1',fromuser='"+req.getOperator()+"'  where id =  " + req.getId();
				
				getSession().createQuery(Nook).executeUpdate();
				
				getSession().createSQLQuery(updateWaring).executeUpdate();
				
			}
			r.setInfo("sucess");
			return r;
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			tx.commit();
		}
		r.setInfo("false");
		return r;
	}
	
	
	/**生成通知单，修改warning的状态为4 修改user2的状态为4 
	 *发通知单的时候生成状态：0 表示通知单发送，但是未返回维修反馈
	 *等维保方将维修反馈记录传回来的时候将会将状态置为 1，表示已经得到维修，通知单不显示状态为1的信息*/
	public ResInfo insertinform_user4(Reqinsertinform_user4 req) {
		Gson gson = new Gson();
		log.info("**********req="+gson.toJson(req)+"****************");
		//拿到审定意见
		String opinions = req.getOpinions();
		log.info("*****拿到的审定意见********"+req.getOpinions()+"*************");
		ResInfo r = new ResInfo();
		@SuppressWarnings("unused")
		String maintenanceId=null;/**对外界的维保 资产编号**/
		String find_equipment_ID=null;
		try{
			String hql = "select f.equipmentid from FaultinfoUser2 f where f.id="+req.getId();
			Query query_3_ = getSession().createQuery(hql);
			if(query_3_.iterate().hasNext()){
				find_equipment_ID = query_3_.iterate().next().toString();
				
			}else{
				log.error("************FaultinfoUser2得到的equipmentid为空*************");
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		
		maintenanceId=req.getEquipmentId();
		req.setEquipmentId(find_equipment_ID);
		String updateOpinions = "update FaultinfoUser2 f set f.opinion = ? ,f.faultCause=?,f.influence=?,f.mainTenancePolicy=?,f.fromuser=? where id= "+req.getId();
		String updateWaring=" update  warning  set statments = '4', fromuser='"+req.getOperator()+"' where id =  " + req.getId();
		@SuppressWarnings("unused")
		String deleteuser2=" delete  from Faultinfo_user2   where id =  " + req.getId();
		String findequipmentDescription_hql = "select new Warning(w.equipmentDescription,w.warningType) from Warning w where  w.id="+req.getId();
		String[] ss=findequipmentDescription(findequipmentDescription_hql);
		try{
			InformUser4 iur = new InformUser4();
			iur.setId(req.getId());
			iur.setJobNumber(req.getJobNumber());
			iur.setOperator(req.getOperator());
			iur.setStarTime(req.getStarTime());
			iur.setLineNo(req.getLineNo());
			iur.setStationName(req.getStationName());
			iur.setSystemname(req.getSystemName());
			iur.setSubSystemname(req.getSubSystemName());
			iur.setEquipmentname(req.getEquipmentName());
			iur.setEquipmentId(req.getEquipmentId());
			iur.setWarningTypeLevel(req.getWarningTypeLevel());
			iur.setHealthLevel(req.getHealthLevel());
			iur.setFultDescription(req.getFultDescription());
			iur.setFaultCause(req.getFaultCause());
			iur.setInfluence(req.getInfluence());
			iur.setMaintenancePolicy(req.getMainTenancePolicy());
			iur.setEquipmentDescription(ss[0]);
			iur.setWarningType(ss[1]);
			iur.setStatments("0");
			
			//待定
			//iur.setMileage(Float.parseFloat(req.getMileage()));
			iur.setMileage((float) 10.0);
			Transaction tx1 = getSession().beginTransaction();
			getSession().save(iur);
			//更新审定意见
			if(opinions!=null){
				Query query = getSession().createQuery(updateOpinions);
				query.setString(0, opinions);
				query.setString(1, req.getFaultCause());
				query.setString(2, req.getInfluence());
				query.setString(3, req.getMainTenancePolicy());
				query.setString(4, req.getOperator());
				query.executeUpdate();
			}
			
			
			log.info("************save success*************");
			getSession().createSQLQuery(updateWaring).executeUpdate();
			tx1.commit();
			
			// TODO Auto-generated method stub
			/***报表管理发送维保通知单    requisitionstatments状态设置为   0 表示已经发送通知到   1表示已经维修完成**/
			//send_reportforms_requisition(req,ss);
			
			/***与维保方的接口将通知单传过去    修好以后将warning表的状态设置为6表示故障已经维修完成
			 * 不会再首页显示。，
			 * 再将收到的信息存入历史告警中
			 * 再将数据存到维修反馈记录中，将通知单的状态设置为1.不在通知单显示已经维修好的设备，
			 * 并且将主动维保通知单的requisitionstatments状态设置为1表示已经维修完成
			 * 
			 * ---待开发***/
			
			
			/**模拟生成维修反馈记录     实际由维保方提供数据**/
			//makeservicefeedback(req,ss);
			
			/***通知单发送完毕之后删除收集到的数字量和模拟量*/
			/**
			ps=conn.prepareStatement("DELETE from pdrdata where componentId= '"+req.getEquipmentId()+"' ");
			ps.executeUpdate();
			ps.close();
			return r;
			**/
			r.setInfo("sucess");
			return r;
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		r.setInfo("false");
		return r;
	}
	
	
	
	/***报表管理发送维保通知单**/
	@SuppressWarnings("unused")
	private  void send_reportforms_requisition(Reqinsertinform_user4 req,
			String[] ss) {
		
		
		/**
		 * 0:已发送通知单
		 * 1：对方收到后返回标志  处理中
		 * 2：对方维修完成后返回  维修完毕
		 * */
		
		try{
			ReportformsRequisition rf = new ReportformsRequisition();
			rf.setComponentId(req.getEquipmentId());
			rf.setSystemName(req.getSystemName());
			rf.setSubsystemName(req.getSubSystemName());
			rf.setLineNo(req.getLineNo());
			rf.setStationName(req.getStationName());
			rf.setComponent(req.getEquipmentName());
			rf.setRequisitionNo(req.getJobNumber());
			rf.setRequisitionstatments("0");
			rf.setSendTime(req.getStarTime());
			Transaction tx = getSession().beginTransaction();
			getSession().save(rf);
			tx.commit();
			log.info("**********send_reportforms_requisition save success************");
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			
		}
		
	}
	
	/**模拟生成维修反馈记录ok
	 * @throws InterruptedException **/
	public  void makeservicefeedback(Reqinsertinform_user4 req,
			String[] ss) {
		//延迟10秒，生成维修反馈
		System.out.println("*****************模拟生成维修反馈记录********************");
		Transaction tx = getSession().beginTransaction();
		try{
			MaintainCoupleBack mc = new MaintainCoupleBack();
			mc.setId(req.getId());
			mc.setComponentId(req.getEquipmentId());
			mc.setComponentName(req.getEquipmentName());
			mc.setSystemName(req.getSystemName());
			mc.setLineNo(req.getLineNo());
			mc.setStationName(req.getStationName());
			mc.setRepairsDate(req.getStarTime());
			mc.setMaintainInfo("更换部件");
			mc.setMaintainPerson("张师傅");
			mc.setMaintainResult("完成维修");
			mc.setRemark("维修内容");
			mc.setCheckPerson("师傅");
			mc.setEquipmentDescription("故障告警");
			mc.setChangeEquipment("良好");
			mc.setWarningType(ss[0]);
			getSession().save(mc);
			tx.commit();
		log.info("***************makeservicefeedback success************************");
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			
		}
		
		
	}
	
	//获得设备详情
	private  String[] findequipmentDescription(
			String findequipmentDescription) {
	
		String[] ss=new String[2];
		try{
			Query query = getSession().createQuery(findequipmentDescription);
			@SuppressWarnings("unchecked")
			List<Warning> ws = query.list();
			Iterator< Warning> it = ws.iterator();
			while(it.hasNext()){
				Warning w = it.next();
				ss[0]=w.getEquipmentDescription();
				ss[1]=w.getWarningType();
			}
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return ss;
	}
	
	/**终止流程ok*/
	public ResInfo stopFlow(ReqstopFlow req) {
		Transaction tx = getSession().beginTransaction();
		ResInfo r = new ResInfo();
		String updatewarning=" update  warning  set statments = ?,fromuser= ? where id =?";
		String updateuser2=" update  Faultinfo_user2  set statments = ?,fromuser =?,faultCause='',opinion=''  where id =?";
		
		try{
			
			Query query_1 = getSession().createSQLQuery(updatewarning);
			query_1.setInteger(0, Integer.parseInt(req.getStatments())-1);
			query_1.setString(1, req.getOperator());
			query_1.setInteger(2,  req.getId());
			query_1.executeUpdate();
			
			Query query_2 = getSession().createSQLQuery(updateuser2);
			query_2.setInteger(0, Integer.parseInt(req.getStatments())-1);
			query_2.setString(1, req.getOperator());
			query_2.setInteger(2,  req.getId());
			query_2.executeUpdate();
			tx.commit();
			r.setInfo("sucess");
			return r;
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			
		}
		r.setInfo("false");
		return r;
		
	}
	
	
	//告警页面的按0,1,2,3四个状态  和告警类型四个用户的代办事物的查找
	public  ResInfo saveuser1Message(Reqconfirmorcancel req) {
		Transaction tx = getSession().beginTransaction();
		ResInfo r = new ResInfo();
		try{
			Message m = new Message();
			m.setInfo( req.getMessage());
			m.setInfodate(req.getTime());
			getSession().save(m);
			r.setInfo("sucess");
			log.info("********保存成功*****");
			return r;
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
			tx.commit();
		}
		r.setInfo("false");
		return r;
	}
	
	//XXX
	public ResWaringList findWaringListBystatments(
			ReqgetWaringListBystatments query) {
			int totalCount = 0;
			int totalPage = 0;
			//第几页
			int pageNo = new Integer(query.getPage().getPageNo());
			//每页几条
			int pageCount = new Integer(query.getPage().getPageCount());

			ResWaringList res = new ResWaringList();
			List<Warning> warningList = new ArrayList<Warning>();
			//根据入参，确定查询语句
			String hql=null;
			if(query.getWarningType()==null){
				 hql = "from Warning w where w.statments in("+query.getStatments().trim()+")  order by w.warningDate desc";
			}else{
				hql = "from Warning w where w.statments in("+query.getStatments().trim()+") and w.warningType=?  order by w.warningDate desc";
			}
		
			Session session = getSession();
			session.clear();
			try {
				//链接数据库
				//做查询
				Query query_4_ = session.createQuery(hql);
				if(query.getWarningType()==null){
					query_4_.setFirstResult((pageNo-1)*pageCount).setMaxResults(pageCount);
				}else{
					query_4_.setString(0, query.getWarningType().trim()).setFirstResult((pageNo-1)*pageCount).setMaxResults(pageCount);
				}
				log.info("********查询语句*******"+query_4_);
				//返回数据库查询结果
				@SuppressWarnings("unchecked")
				Iterator<Warning> it = query_4_.iterate();
					while(it.hasNext()){
						Warning w = it.next();
						Warning bo = new Warning();
						bo.setEquipmentId(w.getEquipmentId());
						bo.setEquipmentDescription(w.getEquipmentDescription());
						bo.setEquipmentname(w.getEquipmentname());
						bo.setId(w.getId());
						bo.setLineNo(w.getLineNo());
						bo.setStationName(w.getStationName());
						bo.setStatments(w.getStatments());
						bo.setSystemName(w.getSystemName());
						bo.setWarningDate(w.getWarningDate());
						bo.setWarninginfo(w.getWarninginfo());
						bo.setWarningId(w.getWarningId());
						bo.setWarningType(w.getWarningType());
						bo.setWarningTypeLevel(w.getWarningTypeLevel());
						bo.setFromuser(w.getFromuser());
						warningList.add(bo);
					}
			} catch (Exception e) {
				log.error(RcmUtils.getTrace(e));
			}finally{
				
			}
			try{
				String hqlc = null;
				if(query.getWarningType()==null){
					hqlc = "select count(*) as count from Warning w where w.statments=? ";
				}else{
					 hqlc = "select count(*) as count from Warning w where w.statments=? and w.warningType=?";
				}
				
				Query query_5_ = session.createQuery(hqlc);
				if(query.getWarningType()==null){
					query_5_.setString(0, query.getStatments());
				}else{
					query_5_.setString(0, query.getStatments());
					query_5_.setString(1, query.getWarningType());
				}
				log.info("********hqlc***********"+hqlc);
				long  total = (Long)query_5_.iterate().next();
				totalCount=(int)total;
				log.info("*******总记录条数=*********"+totalCount);
			}catch (Exception e) {
				log.error(RcmUtils.getTrace(e));
			}finally{
				session.close();
				
			} 
			res.setTotalCount(totalCount + "");
			totalPage = totalCount / pageCount;
			if (totalCount % pageCount > 0) {
				totalPage = totalCount / pageCount + 1;
			}
			res.setTotalPage(totalPage + "");
			res.setWarningList(warningList);
			return res;
		}

	
	
	/****user1 鼠标悬停显示 从DKE所获取的数据**/
	public Equipment getEquipmentAllInfo(ReqgetEquipmentAllInfo req) {
		Gson gson = new Gson();
		try{
			//Equipment info=Save_EquipmentInfo_fromDKS.getEquipmentFromDKS(req.getEquipmentId(), "sgncom_signal_controller_inst");
			Equipment info=new Equipment();
			log.info("user1 鼠标悬停显示 从DKE所获取的数据"+gson.toJson(info));
			return info;
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
			Equipment info=new Equipment();
			return info;
		}
	}
	
	
	
	
	/***时间到了生成计划修预警(ok)**/
	public  void createPlanWarning() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(new Date());
		String endTime=time+" 24:00:00";
		String StartTime=time+" 00:00:00";
			List<PlanWarning> warningList = new ArrayList<PlanWarning>();
		try{
			String find_hql="from MaintainPlanTable m where" +
					" m.nextMaintainDate >= '"+StartTime+"' and m.nextMaintainDate <= '"+endTime+"' ";
			System.out.println(find_hql);
			Query query_5_ = getSession().createQuery(find_hql);
			@SuppressWarnings("unchecked")
			List<MaintainPlanTable> ms = query_5_.list();
			Iterator<MaintainPlanTable> it = ms.iterator();
			log.info("*********it.size=****************"+ms.size());
			while(it.hasNext()){
				PlanWarning bo=new PlanWarning();
				MaintainPlanTable m = it.next();
				bo.setEquipmentId(m.getEquipmentId());
				bo.setEquipmentname(m.getEquipmentname());
				bo.setSubsystemName(m.getSubsystemName());
				bo.setSystemName(m.getSystemName());
				bo.setLineNo(m.getLineNo());
				bo.setStatments("0");
				bo.setStationName(m.getStationName());
				bo.setWarningType(m.getWarningType());
				bo.setWarningTypeLevel(m.getWarningTypeLevel());
				bo.setWarninginfo(m.getMaintaininfo()+"维修时间已到");
				bo.setWarningDate(m.getNextMaintainDate().toString());
				warningList.add(bo);
			}
			
			insertintoPlan_Warning(warningList);
			
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}/*finally{
			session.close();
		}*/
	}
	
	/**ok*/
	private  void insertintoPlan_Warning(List<PlanWarning> warningList) {
		Gson gson = new Gson();
		log.info("**********insertintoPlan_Warning=***************"+gson.toJson(warningList));
		Transaction tx = getSession().beginTransaction();
		try{
			if(warningList.size()!=0){
				for(PlanWarning bo:warningList){
					WarningPlan wp = new WarningPlan();
					wp.setEquipmentId(bo.getEquipmentId());
					wp.setEquipmentname(bo.getEquipmentname());
					wp.setSubsystemName(bo.getSubsystemName());
					wp.setSystemName(bo.getSystemName());
					wp.setLineNo(bo.getLineNo());
					wp.setStatments("0");
					wp.setStationName(bo.getStationName());
					wp.setWarningType(bo.getWarningType());
					wp.setWarningTypeLevel(bo.getWarningTypeLevel());
					wp.setWarninginfo(bo.getWarninginfo());
					wp.setWarningDate(bo.getWarningDate());
					getSession().save(wp);
					log.info("********save success********");
				}
				
			}
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
			tx.commit();
		}
	}
	
	
	/***首页中的某条线的一天的健康状态(ok)***/
	public  ResDayLineHealth getthisDayLineHealth(
		ReqgetthisDayLineHealth query) {
		ResDayLineHealth res=new ResDayLineHealth();
		List<TendencyInfo> list=new ArrayList<TendencyInfo>();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar   c   =   Calendar.getInstance();
		c.set(Calendar.HOUR,-24);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MINUTE,0);
		@SuppressWarnings("unused")
		String startTime=sdf.format(c.getTime());//今天的开始时间
		c.set(Calendar.HOUR,+24);
		@SuppressWarnings("unused")
		String endTime=sdf.format(c.getTime());//今天结束的时间
		String findHealth="from LineHealth h where h.lineNo =? ";
		try{
			Query query_6_ = getSession().createQuery(findHealth);
			query_6_.setString(0, query.getLineNo());
			@SuppressWarnings("unchecked")
			List<LineHealth> hs = query_6_.list();
			Iterator<LineHealth> it = hs.iterator();
			while(it.hasNext()){
				LineHealth h = it.next();
				TendencyInfo bo=new TendencyInfo();
				bo.setValue(h.getValue());
				bo.setSavetime((sdf.parse(h.getSaveTime().toString())).getTime()*1000);
				list.add(bo);
			}
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}finally{
			
		}
		res.setInfoList(list);
		Gson gson = new Gson();
		log.info("******lists=*********"+gson.toJson(list));
		return res;
	}
	
	
	/***每一个小时取一次的一条线的健康值(ok)**/
	public  void readHealrhLevel() {
		
		Transaction tx = getSession().beginTransaction();
		String lineName;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String hqls = "select distinct w.lineNo  from Warning w  order by w.lineNo";
			Query query_7_ = getSession().createQuery(hqls);
			//线号
			List<String> lines = new ArrayList<String>();
			@SuppressWarnings("unchecked")
			List<String> lis = query_7_.list();
			Iterator< String> it = lis.iterator();
			while (it.hasNext()) {
				String lins = it.next();
				log.info("*******线路名称：********"+lins);
				lines.add(lins);

			}
			for (int i = 0; i < lines.size(); i++) {
				int level;
				//状态分四个等级
				int[] levels = new int[4];

				lineName = lines.get(i);
				hqls =  "from Warning w where w.lineNo = '" + lineName+"' and w.statments in(0,1,2,3)";
				Query query_8_ = getSession().createQuery(hqls);
				@SuppressWarnings("unchecked")
				List<Warning> ws = query_8_.list();
				List<WarningBo> warningList = new ArrayList<WarningBo>();
				warningList = wsToBo(ws);
				for (WarningBo bo : warningList) {
					level = new Integer(bo.getWarningTypeLevel());
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
				float count=(float) (100-levels[0]*5-levels[1]*3-levels[2]*1-levels[3]*0.5);
				
				
				LineHealth ht = new LineHealth();
				ht.setId(null);
				ht.setLineNo(lineName);
				ht.setSaveTime(sdf.format(new Date()));
				ht.setValue(count);
				getSession().save(ht);
				log.info("**********保存成功***********");
			}

		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));

		}finally{
			tx.commit();
		}
	}
	
	/****当前故障告警、预警告警、计划修告警当前未被处理的有几条，需要有数字醒目表示。(ok)**/
	public  ResNeeddoOfCount getcountofNeedtoDO() {
		ResNeeddoOfCount info=new ResNeeddoOfCount();
		String hql1="select count(*) as count  from Warning w where w.warningType ='故障告警' and w.statments='0' ";
		String hql2="select count(*) as count  from Warning w where w.warningType ='故障预警' and w.statments='0' ";
		String hql3="select count(*) as count  from Warning w where w.statments='0' ";
		try{
			Query query_9_ = getSession().createQuery(hql1);
			info.setWarningTODOCount1(((Long)query_9_.iterate().next()).intValue());
			
			Query query_9_1 = getSession().createQuery(hql2);
			info.setWarningTODOCount2(((Long)query_9_1.iterate().next()).intValue());
			
			Query query_9_2 = getSession().createQuery(hql3);
			info.setPlan_warningTODOCount(((Long)query_9_2.iterate().next()).intValue());
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));

		}finally{
		}
		Gson gson = new Gson();
		log.info("*******info=******"+gson.toJson(info));
		return info;
	}
	
	/****推演时显示追忆数据()**/
	public  ResDataShowList getDataShow(ReqgetDataShow req) {
		ResDataShowList info=new ResDataShowList();
		List<DateShow> list=new ArrayList<DateShow>();
		String saveTime_String=null;
		long saveTime=0;
		long starttime=0;
		long endtime=0;
		List<KSKPointInfo> pointInfoList=new ArrayList<KSKPointInfo>();
			try{
				String gettime_h="select warningDate from Warning w where w.equipmentId= '"+req.getEquipmentId()+"' and w.statments ='"+1+"' ";
				Query query_9_3 = getSession().createQuery(gettime_h);
				@SuppressWarnings("unchecked")
				List<String> times = query_9_3.list();
				Iterator<String> it = times.iterator();
				while(it.hasNext()){
					String time = it.next();
					log.info("******time=********"+time);
					saveTime_String=time;
				}
				SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date =  new Date();
				date = formatDate.parse(saveTime_String);
				saveTime = date.getTime()/1000;//卡斯柯的报警时间和数据存数时间差了33秒
				starttime=saveTime-36;
				endtime=saveTime-30;
			}catch(Exception e){
				log.error(RcmUtils.getTrace(e));
			}
			
		try{
			//卡斯柯数据库
			//select * from tpdr_groupdata  where starttime = 1385174466 order by pointcode;
			java.sql.Connection connksk = KSKDBUtil.getConn();
			PreparedStatement ps;
			ResultSet rs;
			String sql="select * from tpdr_groupdata  where starttime <= "+endtime+" and starttime >="+starttime+" order by pointcode";
			ps=connksk.prepareStatement(sql);
			rs=ps.executeQuery();
			pointInfoList=ReadInfo.getKSKpointInfoList(rs);
			ps.close();
			rs.close();
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		KSKPointInfo dbzl_vol=pointInfoList.get(0);//定位直流电压
		KSKPointInfo dbjl_vol=pointInfoList.get(1);//定位交流电压
		
		KSKPointInfo fbzl_vol=pointInfoList.get(2);//反位直流电压
		KSKPointInfo fbjl_vol=pointInfoList.get(3);//反位交流电压
		KSKPointInfo dbbs_stat=pointInfoList.get(4);//定位表示状态
		KSKPointInfo fbbs_stat=pointInfoList.get(5);//反位表示状态
		
		if((dbbs_stat.getValues()[0]==1)&&(fbbs_stat.getValues()[0]==0)){//定位
			DateShow show1=new DateShow();
			show1.setName("定位直流电压");
			show1.setSource(dbzl_vol.getValues());
			DateShow show2=new DateShow();
			show2.setName("定位交流电压");
			show2.setSource(dbjl_vol.getValues());
			list.add(show1);list.add(show2);
			info.setListInfo(list);
		}else  if((dbbs_stat.getValues()[0]==0)&&(fbbs_stat.getValues()[0]==1)){//反位
			DateShow show1=new DateShow();
			show1.setName("反位直流电压");
			show1.setSource(fbzl_vol.getValues());
			DateShow show2=new DateShow();
			show2.setName("反位交流电压");
			show2.setSource(fbjl_vol.getValues());
			list.add(show1);list.add(show2);
			info.setListInfo(list);
		}
		
		return info;
	}
	

	
/********************************************************************************/	
	
	@SuppressWarnings("unchecked")
	public  ResWaringList2 getWarnins2(ReqgetWaringList query) {
		int pageSize=query.getPage().getPageCount();
		int page=query.getPage().getPageNo();
		try {
			String queryString = "from Warning where 1=1 ";
			queryString=queryString+getreturnreq(query.getQuery());//添加查询条件
			//开启事务管理。查询可以不开事务，但是删除修改必须开启事务，不然无法删除或者修改
			Transaction ts =getSession().beginTransaction();
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult((page-1)*pageSize);
			queryObject.setMaxResults(pageSize);
			ResWaringList2 res=new ResWaringList2();
			List<Warning> warninglist=queryObject.list();
			res.setWarningList(warninglist);
			
			String getcount = "select count(*)  from Warning where 1=1 "+getreturnreq(query.getQuery());
			Query querycount= getSession().createQuery(getcount);
			 Object obj=querycount.list().get(0);
			 int totalRows=Integer.parseInt(obj.toString());
			 res.setTotalCount(totalRows);
			 if (totalRows % pageSize == 0) {
				 res.setTotalPage(totalRows / pageSize);
	        } else {
	        	 res.setTotalPage(totalRows / pageSize + 1);
	        }
			ts.commit();//提交事务
			
			//log.info("返回的res="+gson.toJson(res));
			return res;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}finally{
			//closeSession();
			
		}
	}
	private String getreturnreq(WarningQueryObj queryObj) {
		
		String sqls = " ";
		if ((queryObj.getWarningType() != null)&&(queryObj.getWarningType().trim().length()>0) ) {
			sqls = sqls + " and ";
			sqls = sqls + " warningType= '" + queryObj.getWarningType()+"' ";
		}
		
		if ((queryObj.getLineNo() !=  null)&&(queryObj.getLineNo().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + " lineNo= '" + queryObj.getLineNo()+"' ";
		}

		if ((queryObj.getStationName() != null)&&(queryObj.getStationName().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + " stationName= '" + queryObj.getStationName()+"' ";
		}

		if ((queryObj.getStatments() != null)&&(queryObj.getStatments().trim().length()>0)) {
			sqls = sqls + " and ";
			//首页查询用的 状态的查询条件
			if(queryObj.getStatments().equals("未处理")){
				sqls = sqls + " statments=0 ";
			}
			if(queryObj.getStatments().equals("确认中")){
				sqls = sqls + " (statments='1' or statments='2' or statments='3')";
			}
			if(queryObj.getStatments().equals("已处理")){
				sqls = sqls + " statments=4 ";
			}
			if(queryObj.getStatments().equals("已取消")){
				sqls = sqls + " statments=5 ";
			}
			
			
		}

		if ((queryObj.getSystemName() != null)&&(queryObj.getSystemName().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + "systemName= '" + queryObj.getSystemName()+"' ";
		}

		if ((queryObj.getEquipmentname()!= null)&&(queryObj.getEquipmentname().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + "equipmentname LIKE '%" + queryObj.getEquipmentname()+"%' ";
		}

		if ((queryObj.getWarningTypeLevel() != null)&&(queryObj.getWarningTypeLevel().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + " warningTypeLevel= '" + queryObj.getWarningTypeLevel()+"' ";
		}

		if ((queryObj.getStartDate() != null)&&(queryObj.getStartDate().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + "WarningDate>=" + "'" + queryObj.getStartDate() + "'";

		}

		if ((queryObj.getEndDate() != null)&&(queryObj.getEndDate().trim().length()>0)) {
			sqls = sqls + " and ";
			sqls = sqls + "WarningDate<=" + "'" + queryObj.getEndDate() + "'";

		}
		sqls=sqls+" and statments in(0,1,2,3,4) ORDER by statments,warningDate DESC ";
		
		return sqls;
	}
	
	/****删除*/
/*	
	public void deleteone(){
		Warning w=new Warning();
		w.setId(28);
		Transaction ts =session.beginTransaction();
		session.delete(w);
		ts.commit();
	}
	
	
	@Test
	public void add(){
		Warning w=new Warning();
		w.setWarningDate("2013-10-22 13:18:22");
		Transaction ts =session.beginTransaction();
		session.save(w);
		ts.commit();
	}
	
	public void findbyid(){
		Warning w=(Warning)session.get(Warning.class, 1);
		System.out.println(w.getEquipmentname());
	}
	
	public void update(){
		Warning w=new Warning();
		w.setWarningDate("2013-10-22 13:18:22");
		Transaction ts =session.beginTransaction();
		session.update(w);
		ts.commit();
	}*/
	

	
	
	
	
}
