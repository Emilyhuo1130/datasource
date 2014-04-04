
package com.ucs.rcm.readxelInfoUtils;

import java.sql.PreparedStatement;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.ucs.rcm.business.bo.WarningBo;
import com.ucs.rcm.db.DBUtil;

public class SaveInfoToDb {
	
	static Connection conn = DBUtil.getConn();
	static PreparedStatement  ps;
	public static void saveWarning(List<WarningBo> warningList){
		
		try{
			ps = conn.prepareStatement("INSERT INTO  warning " +
					"(warningId,EquipmentId,Equipmentname,SystemName,EquipmentDescription,LineNo,StationName," +
					"WarningType,WarningTypeLevel,Warninginfo,WarningDate,Statments,fromuser) " +
			"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			for(WarningBo bo:warningList){
				ps.setString(1, bo.getWarningId());
				ps.setString(2, bo.getEquipmentId());
				ps.setString(3, bo.getEquipmentname());
				ps.setString(4, bo.getSystemName());
				ps.setString(5, bo.getEquipmentDescription());
				ps.setString(6, bo.getLineNo());
				ps.setString(7, bo.getStationName());
				ps.setString(8, bo.getWarningType());
				ps.setString(9, bo.getWarningTypeLevel());
				ps.setString(10, bo.getWarninginfo());
				ps.setString(11, bo.getWarningDate());
				ps.setString(12, bo.getStatments());
				ps.setString(13, bo.getFromuser());
				ps.executeUpdate();
				
			}
			ps.close();
			System.out.println("OK");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void saveequipmentInfo(List<EquipmentInfo> list) {
		String l2="111031022";
		int a=10000;
		try{
			ps = conn.prepareStatement("INSERT INTO  EquipmentTreeinfo " +
					"(lineno,stationName,systemname,subsystemname,component,subcomponent," +
					"subcomponentid,maintenanceId,Level" +
					") " +
			"VALUES ('10',?,?,?,?,?,?,?,?)");
			for(EquipmentInfo bo:list){
				a++;
				ps.setString(1, bo.getStationName());
				ps.setString(2, bo.getSystemName());
				ps.setString(3, bo.getSubsystemName());
				ps.setString(4, bo.getComponent());
				ps.setString(5, bo.getSubComponent());
				ps.setString(6, bo.getSubcomponentId());
				ps.setString(7, l2+a+"");
				ps.setString(8, bo.getLevel());
				
				ps.executeUpdate();
				
			}
			ps.close();
			System.out.println("OK");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		
	}
	public static void savestations(List<ReadStation> list) {
		// TODO Auto-generated method stub
		try{
			ps = conn.prepareStatement("INSERT INTO  station " +
					"(lineno,stationid,stationName " +
					") " +
			"VALUES (?,?,?)");
			for(ReadStation bo:list){
				ps.setString(1, bo.getLineNo());
				ps.setString(2, bo.getStationId());
				ps.setString(3, bo.getStationName());
				ps.executeUpdate();
				
			}
			ps.close();
			System.out.println("OK");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public static void savespdrdata(List<Readpdrdata> list) {
		// TODO Auto-generated method stub
		try{
			ps = conn.prepareStatement("INSERT INTO  pdrdata " +
					"(componentId,pointCode,dateType," +
					"value1,value2,value3,value4,value5,value6,value7,value8,value9,value10, " +
					"value11,value12,value13,value14,value15,value16,value17,value18,value19,value20," +
					"value21,value22,value23,value24,value25,value26,value27,value28,value29,value30," +
					"value31,value32,value33,value34,value35 " +
					") " +
			"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			for(Readpdrdata bo:list){
				ps.setString(1, bo.getComponentId());
				ps.setString(2, bo.getPointCode());
				ps.setString(3, bo.getDateType());
				ps.setString(4, bo.getValue1());
				ps.setString(5, bo.getValue2());
				ps.setString(6, bo.getValue3());
				ps.setString(7, bo.getValue4());
				ps.setString(8, bo.getValue5());
				ps.setString(9, bo.getValue6());
				ps.setString(10, bo.getValue7());
				ps.setString(11, bo.getValue8());
				ps.setString(12, bo.getValue9());
				ps.setString(13, bo.getValue10());
				ps.setString(14, bo.getValue11());
				ps.setString(15, bo.getValue12());
				ps.setString(16, bo.getValue13());
				ps.setString(17, bo.getValue14());
				ps.setString(18, bo.getValue15());
				ps.setString(19, bo.getValue16());
				ps.setString(20, bo.getValue17());
				ps.setString(21, bo.getValue18());
				ps.setString(22, bo.getValue19());
				ps.setString(23, bo.getValue20());
				ps.setString(24, bo.getValue21());
				ps.setString(25, bo.getValue22());
				ps.setString(26, bo.getValue23());
				ps.setString(27, bo.getValue24());
				ps.setString(28, bo.getValue25());
				ps.setString(29, bo.getValue26());
				ps.setString(30, bo.getValue27());
				ps.setString(31, bo.getValue28());
				ps.setString(32, bo.getValue29());
				ps.setString(33, bo.getValue30());
				ps.setString(34, bo.getValue31());
				ps.setString(35, bo.getValue32());
				ps.setString(36, bo.getValue33());
				ps.setString(37, bo.getValue34());
				ps.setString(38, bo.getValue35());
				ps.executeUpdate();
				
			}
			ps.close();
			System.out.println("OK");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void savepointtableinfo(List<ReadPointTable> list) {
		// TODO Auto-generated method stub
		try{
			ps=conn.prepareStatement("insert into pointtable_d values(null,?,?,?,?,?)");
			for(ReadPointTable bo:list){
				ps.setString(1, bo.getPointCode());
				ps.setString(2, bo.getPointDesc());
				ps.setString(3, bo.getGroupCode());
				ps.setString(4, bo.getDeviceCode());
				ps.setString(5, bo.getAppSysID());
				ps.executeUpdate();
				
			}
			ps.close();
			System.out.println("OK");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

