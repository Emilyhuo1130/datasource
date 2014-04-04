
package com.ucs.rcm.pdrdataUtils;

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
import com.ucs.rcm.db.KSKDBUtil;

public class GetpointInfo {
	static Logger log = Logger.getLogger(GetpointInfo.class);
/**道岔**/

	public static String get_08_01_Info(String equipmentId) {
		// TODO Auto-generated method stub
		String type=null;
		String saveTime_String=null;
		long saveTime=0;
		long starttime=0;
		long endtime=0;
		List<KSKPointInfo> pointInfoList=new ArrayList<KSKPointInfo>();
			try{
				Connection conn=DBUtil.getConn();
				PreparedStatement ps;
				ResultSet rs;
				String gettime="select warningDate from warning where equipmentId= '"+equipmentId+"' and statments ='"+1+"' ";
				ps=conn.prepareStatement(gettime);
				rs=ps.executeQuery();
				while(rs.next()){
					saveTime_String=rs.getString("warningDate");
				}
				SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date =  new Date();
				date = formatDate.parse(saveTime_String);
				saveTime = date.getTime()/1000;//卡斯柯的报警时间和数据存数时间差了33秒
				starttime=saveTime-36;
				endtime=saveTime-30;
				ps.close();
				rs.close();
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
			if(pointInfoList.size()>0){
				type=GetOneFaultType.get_08_01_faultType(pointInfoList);
			}else{
				type="定位表示回路故障";
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
			type="定位表示回路故障";
			return type;
		}
		
		return type;
	}
	
	
/**隧道风机系统*/
	public static String get_03_36_Info(String equipmentId) {
		// TODO Auto-generated method stub
		String type=null;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<PointInfo> pointInfoList=new ArrayList<PointInfo>();
		List<PointInfo> powinfos=new ArrayList<PointInfo>();
		
		try{
			String sql="select * from pdrdata where componentId= '"+equipmentId+"' order by pointCode";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			pointInfoList=ReadInfo.getpointInfoList_D(rs);//获取数字量
			ps.close();
			rs.close();
			
			//10.31.02.53.001
			String st="10.xx.02.53.001";
			String powEquipment_id=st.replace("xx", equipmentId.substring(3,5));
			String pow="select * from pdrdata where componentId= '"+powEquipment_id+"' order by pointCode";
			ps=conn.prepareStatement(pow);
			rs=ps.executeQuery();
			powinfos=ReadInfo.getpointInfoList_D(rs);//获取数字量
			ps.close();
			rs.close();
			if(pointInfoList.size()>0){
				type=GetOneFaultType.get_03_36_faultType(pointInfoList,powinfos);
			}else{
				log.warn("----------------------表中不存在可以用来推演的数据--------------------------");
				type="供电电源失电";
			}
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
			type="供电电源失电";
			return type;
		}
		
		
		return type;
	}

	
	
	/***给排水系统***/
	public static String get_03_04_Info(String equipmentId) {
		// TODO Auto-generated method stub
		String type=null;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<PointInfo> pointInfoList=new ArrayList<PointInfo>();
		List<PointInfo> powinfos=new ArrayList<PointInfo>();
		
		try{
			String sql="select * from pdrdata where componentId= '"+equipmentId+"' order by pointCode";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			pointInfoList=ReadInfo.getpointInfoList_D(rs);//获取数字量
			ps.close();
			rs.close();
			
			//10.30.02.53.002
			String st="10.xx.02.53.002";
			String powEquipment_id=st.replace("xx", equipmentId.substring(3,5));
			String pow="select * from pdrdata where componentId= '"+powEquipment_id+"' order by pointCode";
			ps=conn.prepareStatement(pow);
			rs=ps.executeQuery();
			powinfos=ReadInfo.getpointInfoList_D(rs);//获取数字量
			ps.close();
			rs.close();
			if(pointInfoList.size()>0){
				type=GetOneFaultType.get_03_04_faultType(pointInfoList,powinfos);
			}else{
				log.warn("----------------------表中不存在可以用来推演的数据--------------------------");
				type="供电电源失电";
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
			type="供电电源失电";
			return type;
		}
		
		
		return type;
	}

	/***信号机*/
	public static String get_08_02_Info(String equipmentId) {
		// TODO Auto-generated method stub
		String type=null;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<PointInfo> pointInfoList=new ArrayList<PointInfo>();
		String warningType=null;
		try{
			String sql="select * from pdrdata where componentId= '"+equipmentId+"' order by pointCode";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			pointInfoList=ReadInfo.getpointInfoList(rs);
			ps.close();
			rs.close();
			
			
			String findwrningType="select distinct warningType from pdrdata where componentId= '"+equipmentId+"' ";
			ps=conn.prepareStatement(findwrningType);
			rs=ps.executeQuery();
			rs.next();
			warningType=rs.getString("warningType");
			ps.close();
			rs.close();
			if(pointInfoList.size()>0){
				type=GetOneFaultType.get_08_02_faultType(pointInfoList);
			}else{
				log.warn("----------------------表中不存在可以用来推演的数据--------------------------");
				type="列车信号非正常关闭报警";
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
			type="列车信号非正常关闭报警";
			return type;
		}
		
		
		return type;
	}

	/***触网**/
	public static String get_14_101_Info(String equipmentId) {
		// TODO Auto-generated method stub
		String type=null;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<PointInfo> pointInfoList=new ArrayList<PointInfo>();
		try{
			String sql="select * from pdrdata where componentId= '"+equipmentId+"' and pointcode like '%_contact%_di_%' order by pointCode";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			pointInfoList=ReadInfo.getpointInfoList_D(rs);//获取数字量
			ps.close();
			rs.close();
			if(pointInfoList.size()>0){
				type=GetOneFaultType.get_14_101_faultType(pointInfoList);
			}else{
				log.warn("----------------------表中不存在可以用来推演的数据--------------------------");
				type="拉出值超限";
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
			type="拉出值超限";
			return type;
		}
		
		
		return type;
	}

	/***轨道***/
	public static String get_13_100_Info(String equipmentId) {
		// TODO Auto-generated method stub
		String type=null;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<PointInfo> pointInfoList=new ArrayList<PointInfo>();
		try{
			String sql="select * from pdrdata where componentId= '"+equipmentId+"' and pointcode like '%_track%_di_%' order by pointCode";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			pointInfoList=ReadInfo.getpointInfoList_D(rs);//获取数字量
			ps.close();
			rs.close();
			if(pointInfoList.size()>0){
				type=GetOneFaultType.get_13_100_faultType(pointInfoList);
			}else{
				log.warn("----------------------表中不存在可以用来推演的数据--------------------------");
				type="轨向不平顺";
			}
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
			type="轨向不平顺";
			return type;
		}
		
		
		return type;
	}

	/***桥梁**/
	public static String get_16_300_Info(String equipmentId) {
		// TODO Auto-generated method stub
		String type=null;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<PointInfo> pointInfoList=new ArrayList<PointInfo>();
		try{
			String sql="select * from pdrdata where componentId= '"+equipmentId+"' and pointcode like '%_bridge_di_%' order by pointCode";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			pointInfoList=ReadInfo.getpointInfoList_D(rs);//获取数字量
			ps.close();
			rs.close();
			if(pointInfoList.size()>0){
				type=GetOneFaultType.get_16_300_faultType(pointInfoList);
			}else{
				log.warn("----------------------表中不存在可以用来推演的数据--------------------------");
				type="红色预警";
			}
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
			type="红色预警";
			return type;
		}
		return type;
	}

	/**隧道**/
	public static String get_15_20_Info(String equipmentId) {
	// TODO Auto-generated method stub
		String type=null;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<PointInfo> pointInfoList=new ArrayList<PointInfo>();
		try{
			String sql="select * from pdrdata where componentId= '"+equipmentId+"' order by pointCode";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			pointInfoList=ReadInfo.getpointInfoList_D(rs);//获取数字量
			ps.close();
			rs.close();
			if(pointInfoList.size()>0){
				type=GetOneFaultType.get_15_20_faultType(pointInfoList);
			}else{
				log.warn("----------------------表中不存在可以用来推演的数据--------------------------");
				type="一级预警";
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
			type="一级预警";
			return type;
		}
		
		
		return type;
	}

	/**高/低  压开 馈线 关柜*/
	public static String get_02_24_Info(String equipmentId) {
		// TODO Auto-generated method stub
		String type=null;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<PointInfo> pointInfoList=new ArrayList<PointInfo>();
		try{
			String sql="select * from pdrdata where componentId= '"+equipmentId+"' and pointcode like '%_pwr_di_%'  order by pointCode";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			pointInfoList=ReadInfo.getpointInfoList_D(rs);//获取数字量
			ps.close();
			rs.close();
			if(pointInfoList.size()>0){
				type=GetOneFaultType.get_02_24_faultType(pointInfoList);
			}else{
				log.warn("----------------------表中不存在可以用来推演的数据--------------------------");
				type="开关过电流";
			}
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
			type="开关过电流";
			return type;
		}
		
		
		return type;
	}


	

	
}
