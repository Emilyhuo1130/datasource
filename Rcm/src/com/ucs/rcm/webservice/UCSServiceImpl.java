
package com.ucs.rcm.webservice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import bean.MyEventProperty;

import com.mysql.jdbc.Connection;
import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.afterfromServices.MainPage_Refresh;
import com.ucs.rcm.business.bo.Equipment;
import com.ucs.rcm.business.bo.WarningBo;
import com.ucs.rcm.db.DBUtil;
import com.ucs.rcm.readxelInfoUtils.EquipmentInfo;
import com.ucs.rcm.responseObj.HistoryWarning;


public class UCSServiceImpl implements UCSService {
	static Logger log = Logger.getLogger(UCSServiceImpl.class);
	
	public void NotifyEvent(String count) {
		
		System.out.println("start soap");
		System.out.println("get data");
		//Save_EquipmentInfo_fromDKS.getEquipmentFromDKS("10.29.08.01.001","0");
		List<MyEventProperty> eventList = WebServiceUtil.getEventList();
		
		saveWarnings(eventList);
		
		}
	
	

	public static void main(String[] args ){
	}
	

	private static void saveWarnings(List<MyEventProperty> eventList) {

		//获取警告信息后，组装设备信息，存入数据库
		List<WarningBo> warningList =  new ArrayList<WarningBo>();

		List<HistoryWarning> historyList = new ArrayList<HistoryWarning>();
		//现场代码
		/***跟卡斯柯确定下来，告警和预警的区分通过告警内容后面加;告警或者预警。
   			例如预警信号：第一区间轨道不平顺;预警
   			告警信号：列车信号非正常关闭报警;告警***/
		for (MyEventProperty event : eventList) {
			if((event.getDevicecode().getValue()!=null)&&
					(event.getGroupcode().getValue()!=null)&&
					(event.getDevicecode().getValue()!=null)){
					WarningBo wb = new WarningBo();
					HistoryWarning hw=new HistoryWarning();
					wb.setEquipmentId(event.getDevicecode().getValue());//资产编号
					String warningInfo=event.getMessage().getValue();//列车信号非正常关闭报警:告警
					String[] infos=warningInfo.split(":");
					wb.setWarninginfo(infos[0]);//告警信息
					wb.setWarningDate(event.getStamptime().toString());//告警日期
					Connection conn = DBUtil.getConn();
					PreparedStatement ps;
					ResultSet rs;
					
					/**初步    先从本地库搜索设备信息，有的从本地取
					 * 没有设备信息 就从电科所webservices获取 
					 * ***/
					
					/***从电科所获取设备信息***/
					/*Equipment equipmentInfo=
						Save_EquipmentInfo_fromDKS.getEquipmentFromDKS(event.getDevicecode().getValue(),"0");*/
					
					/***看本地库有没有对应设备的信息*/
					boolean flag=true;
					/*try{
						String find="select * from equipmenttreeinfo where subcomponentid='"+event.getDevicecode().getValue()+"'";
						ps=conn.prepareStatement(find);
						rs=ps.executeQuery();
						while(rs.next()){
							flag=true;
						}
					}catch(Exception e){
						log.error("搜索本地设备信息失败"+RcmUtils.getTrace(e));
					}*/
				
					if(flag){//本地信息存在，从本地读取
						wb=readEquipmentInfo(wb,infos,event);
						
						//hw=addHistoryWarning( hw, infos, event);
					}else{//本地库不存在信息。从DKS获取信息，存本地库，然后读取本地信息
						/*String str=event.getDevicecode().getValue();
						String str2=str.replace(".", ",");
						String[] ss=str2.split(",");
						String findstationName="select stationName from station where groupno="+ss[1]+" and lineNo= '"+ss[0]+"' ";
						
						try{
							ps=conn.prepareStatement(findstationName);
							rs=ps.executeQuery();
							while(rs.next()){
								equipmentInfo.setLineNo(ss[1]);
								equipmentInfo.setStationName(rs.getString("stationName"));
							}
						}catch(Exception e){
							log.error("搜索车站失败"+RcmUtils.getTrace(e));
						}
						从DKS获取的信息存本地设备信息本地库
						saveEquipmentfromDKStoDB(equipmentInfo);
						存好后读取设备信息生成告警记录
						wb=readEquipmentInfo(wb,infos,event);
						hw=addHistoryWarning( hw, infos, event);*/
				}
					if(wb.getSystemName()!=null){
						warningList.add(wb);
					}
			}
			
			
			
			
			// historyList.add(hw);
		}
		
		
		/***告警信息存库*/
		saveToDb(warningList);
		
		/**报表历史记录*/
		//saveHistoryWarning(historyList);
		
	}
	
	
	/**从DKS获取的信息存本地设备信息本地库**/
	private static void saveEquipmentfromDKStoDB(Equipment equipmentInfo) {
		// TODO Auto-generated method stub
		String l2="111031022";
		int a=40000;
		Connection conn = DBUtil.getConn();
		PreparedStatement ps;
		try{
			ps = conn.prepareStatement("INSERT INTO  EquipmentTreeinfo " +
					"(lineno,stationName,systemname,subsystemname,component,subcomponent," +
					"subcomponentid,maintenanceId,Level" +
					") " +
			"VALUES (?,?,?,?,?,?,?,?,?)");
				/*ps.setString(1, equipmentInfo.getLineNo());
				ps.setString(2, equipmentInfo.getStationName());
				ps.setString(3, equipmentInfo.getSystemName());
				ps.setString(4, equipmentInfo.getSubsystemName());
				ps.setString(5, equipmentInfo.getEquipmentName());
				ps.setString(6, equipmentInfo.getComponentName());
				ps.setString(7, equipmentInfo.getEquipmentId());
				ps.setString(8, l2+a+"");
				ps.setString(9, "2");
				ps.executeUpdate();*/
				ps.close();
			
			
		}catch (Exception e) {
			log.error("从DKS获取的信息存本地设备信息本地库失败"+RcmUtils.getTrace(e));
		}
		
	}



	/****收到卡斯柯信息后读取本地设备信息*****/
	private static WarningBo readEquipmentInfo(WarningBo wb,String[] infos,MyEventProperty event){
		Connection conn = DBUtil.getConn();
		PreparedStatement ps;
		ResultSet rs;
		try{//本地库存在信息  本地取
			String find="select * from equipmenttreeinfo where subcomponentid='"+event.getDevicecode().getValue()+"'";
			ps=conn.prepareStatement(find);
			rs=ps.executeQuery();
			
			while(rs.next()){
				wb.setWarningId(event.getStamptime().toString());
				wb.setEquipmentname(rs.getString("component"));
				wb.setSystemName(rs.getString("systemName"));
				wb.setEquipmentDescription(rs.getString("subcomponent"));
				wb.setLineNo(rs.getString("lineNo"));
				wb.setStationName(rs.getString("stationName"));
				if(infos[1].equals("报警")){
					infos[1]="告警";
				}
				wb.setWarningType("故障"+infos[1]);//区分告警还是预警
				wb.setWarningTypeLevel(rs.getString("Level"));
				wb.setStatments("0");
				wb.setFromuser("0");
				
			}
			ps.close();
			rs.close();
		}
		catch(Exception e){
			log.error("收到卡斯柯信息后读取本地设备信息失败:可能没有找到对应的设备编号"+RcmUtils.getTrace(e));
		}
		return wb;
		
	}
	
	/***增加历史记录***/
	private static HistoryWarning addHistoryWarning(HistoryWarning hw,String[] infos,MyEventProperty event){
		Connection conn = DBUtil.getConn();
		PreparedStatement ps;
		ResultSet rs;
		try{//本地库存在信息  本地取
			String find="select * from equipmenttreeinfo where subcomponentid='"+event.getDevicecode().getValue()+"'";
			ps=conn.prepareStatement(find);
			rs=ps.executeQuery();
			while(rs.next()){
				
				//**报表记录*//*
				hw.setSystemName(rs.getString("systemName"));
				hw.setSubSystemName(rs.getString("subSystemName"));
				hw.setLineNo(rs.getString("lineNo"));
				hw.setStationName(rs.getString("stationName"));
				hw.setComponent(rs.getString("component"));
				hw.setWarninginfo(event.getMessage().getValue());
				hw.setWarningTypeLevel(rs.getString("Level"));
				hw.setWarningstatments("1");
				hw.setWarningTime(event.getStamptime().toString());
				hw.setWarningType("故障"+infos[1]);
			}
			ps.close();
			rs.close();
		}
		catch(Exception e){
			log.error("收到卡斯柯信息后读取本地设备信息失败"+RcmUtils.getTrace(e));
		}
		return hw;
		
	}
	
	/**报表管理----告警，预警历史统计表**/
	private	static void saveHistoryWarning(List<HistoryWarning> historyList) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConn();
		PreparedStatement ps;
		try{
			String sql="INSERT INTO  historywarning " +
				"(systemName,subsystemName,lineNo,stationName,component,warningType,warninginfo," +
				"warningTypeLevel,warningstatments,warningDate) " +
		"VALUES (?,?,?,?,?,?,?,?,?,?)";
			for(HistoryWarning bo:historyList){
				ps=conn.prepareStatement(sql);
				ps.setString(1, bo.getSystemName());
				ps.setString(2, bo.getSubSystemName());
				ps.setString(3, bo.getLineNo());
				ps.setString(4, bo.getStationName());
				ps.setString(5, bo.getComponent());
				ps.setString(6, bo.getWarningType());
				ps.setString(7, bo.getWarninginfo());
				String level=bo.getWarningTypeLevel();
				System.out.println("---------------"+level+"------------------");
				if(level==null){
					level="3";
				}
				ps.setString(8, level);
				ps.setString(9, bo.getWarningstatments());
				ps.setTimestamp(10, new Timestamp (new Long(bo.getWarningTime())*1000));
				ps.executeUpdate();
				ps.close();
			}
		}catch(Exception e){
			log.error("报表管理----告警，预警历史统计表操作失败"+RcmUtils.getTrace(e));
			
		}
	}


	private static void saveToDb(List<WarningBo> warningList) {
		log.info("---------------------------保存告警记录----------------------");
		try{
			for(WarningBo bo: warningList){
				
			Connection conn = DBUtil.getConn();
			PreparedStatement ps;
			ps = conn.prepareStatement("INSERT INTO  warning " +
					"(warningId,EquipmentId,Equipmentname,SystemName,EquipmentDescription,LineNo,StationName," +
					"WarningType,warningTypeLevel,Warninginfo,WarningDate,Statments,fromuser) " +
			"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, bo.getWarningId());
			ps.setString(2, bo.getEquipmentId());
			ps.setString(3, bo.getEquipmentDescription());
			ps.setString(4, bo.getSystemName());
			ps.setString(5, bo.getEquipmentDescription());
			ps.setString(6, bo.getLineNo());
			ps.setString(7, bo.getStationName());
			ps.setString(8, bo.getWarningType());
			ps.setString(9, bo.getWarningTypeLevel());
			ps.setString(10, bo.getWarninginfo());
			ps.setTimestamp(11, new Timestamp (new Long(bo.getWarningDate())*1000));
			ps.setString(12, bo.getStatments());
			ps.setString(13, bo.getFromuser());
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		}
		catch(Exception e){
			log.error("将告警信息存入本地库操作失败"+RcmUtils.getTrace(e));
			
		}
		
		/**入库后告诉前端，数据已入库，可以跟新页面**/
		MainPage_Refresh.infoistoDb();
		
	}

	
	
	
	
	private List<Equipment> xmlParser(String xmlString) {

		List<Equipment> equipmentList = new ArrayList<Equipment>();
		try {

			// xmlString = new String (xmlString.getBytes(),"UTF-8");

			xmlString = xmlString.replaceAll("[^\\x20-\\x7e]", "");
			System.out.println(xmlString);
			// System.out.println(xmlString);
			Document document = DocumentHelper.parseText(xmlString);
			Element root = document.getRootElement();
			for (Iterator i = root.elementIterator(); i.hasNext();) {
				Element el = (Element) i.next();
				if (el.getQName().getName().equalsIgnoreCase("EquipmentOutput")) {
					Equipment eq = new Equipment();
					for (Iterator ii = el.elementIterator(); ii.hasNext();) {
						Element els = (Element) ii.next();

						System.out.println(els.getQName().getName() + ":"
								+ els.getText());
						/*if (els.getQName().getName()
								.equalsIgnoreCase("EquipmentId"))
							eq.setEquipmentId(els.getText());
						if (els.getQName().getName()
								.equalsIgnoreCase("TheSystem"))
							eq.setSystemName(els.getText());
						if (els.getQName().getName()
								.equalsIgnoreCase("EquipmentName"))
							eq.setEquipmentName(els.getText());
						if (els.getQName().getName()
								.equalsIgnoreCase("Details"))
							eq.setDescription(els.getText());*/

					}
					equipmentList.add(eq);
				}

			}

			System.out.println(equipmentList.size() + "size");
			//System.out.println(equipmentList.get(0).getEquipmentId());

		} catch (Exception e) {
			log.error(RcmUtils.getTrace(e));

		}

		return equipmentList;

	}

}
