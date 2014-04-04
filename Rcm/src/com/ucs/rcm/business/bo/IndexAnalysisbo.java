package com.ucs.rcm.business.bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.db.DBUtil;
import com.ucs.rcm.reqobj.ReqgetMaintainStatistics;
import com.ucs.rcm.reqobj.ReqgetOneSysmaintai;
import com.ucs.rcm.reqobj.ReqgetOperateStatistics;
import com.ucs.rcm.reqobj.ReqgetoneLineAllOperateStatistics;
import com.ucs.rcm.reqobj.ReqgetoneLineOperateStatistics;
import com.ucs.rcm.responseObj.MaintainStatistics;
import com.ucs.rcm.responseObj.OneLineOperateStatistics;
import com.ucs.rcm.responseObj.OneLineSysmaintai;
import com.ucs.rcm.responseObj.OperateStatistics;

import com.ucs.rcm.responseObj.ResOneLineOperateStatisticsList;
import com.ucs.rcm.responseObj.ResOneSysmaintaiList;

import com.ucs.rcm.responseObj.ResMaintainStatisticsList;
import com.ucs.rcm.responseObj.ResOperateStatisticsList;
/***指标分析*/
public class IndexAnalysisbo {
	static Logger log = Logger.getLogger(IndexAnalysisbo.class);
	public static ResOperateStatisticsList getOperateStatistics(
		ReqgetOperateStatistics query) {
		// TODO Auto-generated method stub
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());

		ResOperateStatisticsList res = new ResOperateStatisticsList();
		List<OperateStatistics> statisticsList = new ArrayList<OperateStatistics>();
		String sql="select * from operatestatistics where 1=1 ";
		sql=sql+" limit "+pageCount*(pageNo-1)+","+pageCount;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs = null;
		log.warn(sql);
		try{
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				OperateStatistics bo = new OperateStatistics();
				bo.setId(rs.getInt("id"));
				bo.setLineNo(rs.getString("lineNo"));
				bo.setMonth(rs.getString("month"));
				bo.setNoPlanoffstreamDays(rs.getString("noPlanoffstreamDays"));
				bo.setOffstreamDays(rs.getString("offstreamDays"));
				bo.setSafetyDays(rs.getString("safetyDays"));
				bo.setUpdateDay(rs.getString("updateDay"));
				statisticsList.add(bo);
			}
			rs.close();
			ps.close();
			
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
		try{
			//获取总记录数
			
			String returncount=null;
			returncount="select count(*) as count from operatestatistics where 1=1 ";
			ps = conn.prepareStatement(returncount);
			
			rs = ps.executeQuery();
			while(rs.next()){
				totalCount = rs.getInt("count");
			}
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
		res.setOperateStatisticsList(statisticsList);
		return res;
	}

	
	/**维修统计指标*/
	public static ResMaintainStatisticsList getMaintainStatistics(
		ReqgetMaintainStatistics query) {
		// TODO Auto-generated method stub
		
		int totalCount = 0;
		int totalPage = 0;
		//第几页
		int pageNo = new Integer(query.getPage().getPageNo());
		//每页几条
		int pageCount = new Integer(query.getPage().getPageCount());

		ResMaintainStatisticsList res = new ResMaintainStatisticsList();
		List<MaintainStatistics> statisticsList = new ArrayList<MaintainStatistics>();
		//根据入参，确定查询语句
		//String sqls="select * from MaintainStatistics where 1=1  order by updateday desc";
		String sql="select * from MaintainStatistics where 1=1 ";
		sql=sql+" limit "+pageCount*(pageNo-1)+","+pageCount;
		log.warn(sql);
		Connection conn = DBUtil.getConn();
		PreparedStatement ps;
		ResultSet rs;
		try {
			//链接数据库
			//做查询
			ps = conn.prepareStatement(sql);
			//返回数据库查询结果
			rs = ps.executeQuery();
				while(rs.next()){

					MaintainStatistics bo = new MaintainStatistics();
					bo.setId(rs.getInt("id"));
					bo.setSystemName(rs.getString("systemName"));
					bo.setEquipmentName(rs.getString("equipmentName"));
					bo.setNoFaultDays(rs.getString("noFaultDays"));
					bo.setStatisticsDays(rs.getString("statisticsDays"));
					bo.setStatisticsInterval(rs.getString("statisticsInterval"));
					bo.setRealityMistiming(rs.getString("realityMistiming"));
					bo.setMonth(rs.getString("month"));
					bo.setUpdateDay(rs.getString("updateDay"));
					statisticsList.add(bo);
					
				}
			
			//关闭流
			rs.close();
			ps.close();
		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		try{
			//获取总记录数
			
			String returncount="select count(*) as count from MaintainStatistics where 1=1 ";
			ps = conn.prepareStatement(returncount);
			
			rs = ps.executeQuery();
			while(rs.next()){
				totalCount = rs.getInt("count");
			}
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
		res.setMaintainStatisticsList(statisticsList);
		// System.out.println("warningList.size():" + warningList.size());
		return res;
	}


	public static ResOneLineOperateStatisticsList getoneLineOperateStatistics(
		ReqgetoneLineOperateStatistics query) {
		// TODO Auto-generated method stub
		ResOneLineOperateStatisticsList res=new ResOneLineOperateStatisticsList();
		List<OneLineOperateStatistics> infolist=new ArrayList<OneLineOperateStatistics>();
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		String upTime=sdf.format(date)+"-00-00 00:00:00";
		int count=0;
		try{
			ps=conn.prepareStatement("select count(*) count from operatestatistics where updateDay > '"+upTime+"'");
			rs=ps.executeQuery();
			rs.next();
			count=rs.getInt("count");
			ps.close();
			rs.close();
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
		
		try{
			String sql="select * from operatestatistics  " +
					"where lineno='"+query.getLineNo()+"' and updateDay > '"+upTime+"'  order by updateday ";
			log.warn(sql);
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			OneLineOperateStatistics SafetyDaysinfo=new OneLineOperateStatistics();
			OneLineOperateStatistics offstreamDays=new OneLineOperateStatistics();
			OneLineOperateStatistics noPlanoffstreamDays=new OneLineOperateStatistics();
			SafetyDaysinfo.setName("安全生产天数");
			offstreamDays.setName("强迫停运天数");
			noPlanoffstreamDays.setName("非计划停运天数");
			
			int[] sdays=new int[count];
			int[] odays=new int[count];
			int[] ndays=new int[count];
			int i=0;
			while(rs.next()){
				sdays[i]=rs.getInt("SafetyDays");
				odays[i]=rs.getInt("offstreamDays");
				ndays[i]=rs.getInt("noPlanoffstreamDays");
				i++;
			}
			
			SafetyDaysinfo.setDays(sdays);
			offstreamDays.setDays(odays);
			noPlanoffstreamDays.setDays(ndays);
			infolist.add(SafetyDaysinfo);
			infolist.add(offstreamDays);
			infolist.add(noPlanoffstreamDays);
			rs.close();
			ps.close();
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		res.setInfoList(infolist);
		return res;
	}
	
	
	/**某一条线的   所统计的数据库的运营统计指标**/
	public static ResOneLineOperateStatisticsList getoneLineAllOperateStatistics(
			ReqgetoneLineAllOperateStatistics query) {
		// TODO Auto-generated method stub
		ResOneLineOperateStatisticsList res=new ResOneLineOperateStatisticsList();
		List<OneLineOperateStatistics> infolist=new ArrayList<OneLineOperateStatistics>();
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		int count=0;
		try{
			ps=conn.prepareStatement("select count(*) count from operatestatistics");
			rs=ps.executeQuery();
			rs.next();
			count=rs.getInt("count");
			ps.close();
			rs.close();
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		//更新时间
		long[] uTime=new long[count];
		
		try{
			String sql="select * from operatestatistics  where lineno='"+query.getLineNo()+"' order by updateday ";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			OneLineOperateStatistics SafetyDaysinfo=new OneLineOperateStatistics();
			OneLineOperateStatistics offstreamDays=new OneLineOperateStatistics();
			OneLineOperateStatistics noPlanoffstreamDays=new OneLineOperateStatistics();
			
			SafetyDaysinfo.setName("安全生产天数");
			offstreamDays.setName("强迫停运天数");
			noPlanoffstreamDays.setName("非计划停运天数");
			
			
			int[] sdays=new int[count];
			int[] odays=new int[count];
			int[] ndays=new int[count];
			
			
			Date date =  new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			int i=0;
			while(rs.next()){
				sdays[i]=rs.getInt("SafetyDays");
				odays[i]=rs.getInt("offstreamDays");
				ndays[i]=rs.getInt("noPlanoffstreamDays");
				date = sdf.parse(rs.getString("updateDay"));
	        	long ut = date.getTime();
				uTime[i]=ut;
				i++;
			}
			
			SafetyDaysinfo.setDays(sdays);
			offstreamDays.setDays(odays);
			noPlanoffstreamDays.setDays(ndays);
			
			
			infolist.add(SafetyDaysinfo);
			infolist.add(offstreamDays);
			infolist.add(noPlanoffstreamDays);
			
			rs.close();
			ps.close();
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		res.setUpdateTime(uTime);
		res.setInfoList(infolist);
		return res;
	}
	
	

	/**维修统计指标 --------图*/
	public static ResOneSysmaintaiList getOneSysmaintai(
			ReqgetOneSysmaintai query) {
		// TODO Auto-generated method stub
		ResOneSysmaintaiList res=new ResOneSysmaintaiList();
		List<OneLineSysmaintai> infolist=new ArrayList<OneLineSysmaintai>();
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		long[] update=null;
		int count=0;
		try{
			String sqlcount=null;
			sqlcount="select count(*) as count from maintainstatistics ";
			if((query.getSystemName()!=null)&&(query.getSystemName().trim().length()>0)){
				sqlcount=sqlcount+" where systemname='"+query.getSystemName()+"'";
			}
			ps=conn.prepareStatement(sqlcount);
			rs=ps.executeQuery();
			rs.next();
			count=rs.getInt("count");
			ps.close();
			rs.close();
			
			//where systemname='"+query.getSystemName()+"'
			String sql=null;
			sql="select * from maintainstatistics   ";
			
			if((query.getSystemName()!=null)&&(query.getSystemName().trim().length()>0)){
				sql=sql+" where systemname='"+query.getSystemName()+"'";
			}
			
			sql=sql+" order by updateday ";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			OneLineSysmaintai noFaultDays=new OneLineSysmaintai();
			OneLineSysmaintai statisticsDays=new OneLineSysmaintai();
			OneLineSysmaintai statisticsInterval=new OneLineSysmaintai();
			OneLineSysmaintai realityMistiming=new OneLineSysmaintai();
			
			noFaultDays.setName("平均无故障时间");
			statisticsDays.setName("平均维修时间");
			statisticsInterval.setName("平均维修间隔时间");
			realityMistiming.setName("计划维修工时数与实际差");
			
			
			long[] nodays=new long[count];
			long[] stdays=new long[count];
			long[] indays=new long[count];
			long[] redays=new long[count];
			
			update=new long[count];
			
			int i=0;
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			while(rs.next()){
				nodays[i]=rs.getInt("noFaultDays");
				stdays[i]=rs.getInt("statisticsDays");
				indays[i]=rs.getInt("statisticsInterval");
				redays[i]=rs.getInt("realityMistiming");
				Date da2=sdf.parse(rs.getString("updateDay"));
				long ltime=da2.getTime();
				update[i]=ltime;
				
				i++;
			}
			
			noFaultDays.setDays(nodays);
			statisticsDays.setDays(stdays);
			statisticsInterval.setDays(indays);
			realityMistiming.setDays(redays);
			
			infolist.add(noFaultDays);
			infolist.add(statisticsDays);
			infolist.add(statisticsInterval);
			infolist.add(realityMistiming);
			
			rs.close();
			ps.close();
		}catch (Exception e) {
			log.error(RcmUtils.getTrace(e));
		}
		
		res.setInfoList(infolist);
		res.setUpdateTime(update);
		return res;
	}

	

}
