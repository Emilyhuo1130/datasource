package com.ucs.rcm.webservice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.db.DBUtil;
import com.ucs.rcm.pdrdataUtils.PointInfo;

import bean.ArrayOfMyAnalogProperty;
import bean.ArrayOfMyDigitalProperty;
import bean.ArrayOfMyEventProperty;
import bean.MyAnalogProperty;
import bean.MyDigitalProperty;
import bean.MyEventProperty;
import bean.RtsipServiceClient;
import bean.RtsipServicePortType;



public class WebServiceUtil {
	static Logger log = Logger.getLogger(WebServiceUtil.class);
	public static List<MyEventProperty> getEventList() {

		// TODO Auto-generated method stub
		ArrayOfMyEventProperty events = new ArrayOfMyEventProperty();
		 List<MyEventProperty> eventList;
		 
		System.out.println(" testRtsip() start");

		RtsipServiceClient rc = new RtsipServiceClient();

		System.out.println(" RtsipServiceClient");

		RtsipServicePortType port = rc.getRtsipServiceHttpPort();

		System.out.println(" getRtsipServiceHttpPort");

		events = port.getEventProperty("ucs", 1);
		eventList = events.getMyEventProperty();
		
		log.info("警告个数" + eventList.size());

		log.info(" getProperty");
		
		/**显示从卡斯柯获取的信号**/
			showInfofromKSK(eventList);
	
			/***开始数字量模拟量存库**/
			tostartsaveAandD(eventList);
		return eventList;

	}
	
	
	/***开始数字量模拟量存库
	 * @param eventList **/
	private static void tostartsaveAandD(List<MyEventProperty> eventList) {
		ArrayOfMyAnalogProperty myAnalogs = new ArrayOfMyAnalogProperty();//模拟量
		ArrayOfMyDigitalProperty digtitals = new ArrayOfMyDigitalProperty();//数字量
		 List<MyAnalogProperty> myAnalogList;
		 List<MyDigitalProperty> digtitalsList;
		 /**点代码信息**/
		 List<PointInfo> pointinfoList_D=new ArrayList<PointInfo>();//数字量存储
		 List<PointInfo> pointinfoList_A=new ArrayList<PointInfo>();//模拟量存储
		RtsipServiceClient rc = new RtsipServiceClient();
		RtsipServicePortType port = rc.getRtsipServiceHttpPort();
		for (MyEventProperty myEventProperty : eventList) {
			log.info("开始获取:"+myEventProperty.getGroupcode().getValue()+"    "+myEventProperty.getDevicecode().getValue()+"数字量和模拟量");
			
			if((myEventProperty.getGroupcode().getValue()!=null)&&(myEventProperty.getDevicecode().getValue()!=null)){
				
				log.info("根据警告信息获取模拟量数字量");
				myAnalogs = port.getAnalogProperty(myEventProperty.getGroupcode().getValue(), 
						myEventProperty.getDevicecode().getValue());
				digtitals = port.getDigitalProperty(myEventProperty.getGroupcode().getValue(),
						myEventProperty.getDevicecode().getValue());
				
				
				myAnalogList = myAnalogs.getMyAnalogProperty();
				log.info("模擬量     数量：" + myAnalogList.size());
				digtitalsList = digtitals.getMyDigitalProperty();
				log.info("数字量     数量：" + digtitalsList.size());
			
				 
				 /**模拟量***/
				for(MyAnalogProperty myAnalogProperty :myAnalogList){
					
					  log.info("模拟量值："+myAnalogProperty.getValue().toString());
					  log.info("设备："+myAnalogProperty.getDescription().getValue());
					  log.info("点代码："+myAnalogProperty.getPointCode().getValue());
				}
				log.info("-----------------------------------------------------------");
			/**数字量***/
				for(MyDigitalProperty myDigitalPropert:digtitalsList){
					log.info("数字量值："+myDigitalPropert.getValue().intValue());
					log.info("设备："+myDigitalPropert.getDescription().getValue());
					log.info("点代码："+myDigitalPropert.getPointCode().getValue());
					
					 PointInfo pointinform=new PointInfo();
					 pointinform.setWarninginfo(myEventProperty.getMessage().getValue());
					 pointinform.setComponentId(myEventProperty.getDevicecode().getValue());
					 pointinform.setPointCode(myDigitalPropert.getPointCode().getValue());
					 pointinform.setDateType("2");
					 pointinform.setValue1(myDigitalPropert.getValue().intValue()+"");
					 pointinfoList_D.add(pointinform);
					 
				}
				
			

			}
		}
			
		/***存数数字量**/
		if(pointinfoList_D.size()>0){
			log.info("存数数字量");
			savepdrData_D(pointinfoList_D);
		}
		/**存储模拟量***/
		if(pointinfoList_A.size()>0){
			log.info("存储模拟量");
			savepdrData_A(pointinfoList_A);
		}
	}



	/***显示从卡斯柯获取的信息**/
	private static void showInfofromKSK(List<MyEventProperty> eventList) {
		// TODO Auto-generated method stub
		for (MyEventProperty myEventProperty : eventList) {
			log.info("设备id: "+myEventProperty.getDevicecode().getValue());
			log.info("组代码: "+myEventProperty.getGroupcode().getValue());
			log.info("告警信息: "+myEventProperty.getMessage().getValue());
			log.info("告警时间: "+myEventProperty.getStamptime().intValue());
			log.info("点代码: "+myEventProperty.getPointcode().getValue());
			log.info("--------------------------------------------------------------------");
		}
	}


	/**存储模拟量***/
	private static void savepdrData_A(List<PointInfo> pointinfoList_A) {
		
	}


	/***存储数字量**/
	private static void savepdrData_D(List<PointInfo> pointinfoList_D) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		try{
			String sql="insert into pdrdata (warningType,componentId,pointCode,dateType,value1) " +
					"values(?,?,?,?,?) ";
			for(PointInfo info:pointinfoList_D){
				ps=conn.prepareStatement(sql);
				ps.setString(1, info.getWarninginfo());
				ps.setString(2, info.getComponentId());
				ps.setString(3, info.getPointCode());
				ps.setString(4, info.getDateType());
				ps.setString(5, info.getValue1());
				ps.executeUpdate();
			}
			ps.close();
			
		}
		catch(Exception e){
			log.error("收到卡斯柯数据 数字量存储失败"+RcmUtils.getTrace(e));
		}
	}
	
	public static void main(String[] args) {
		//测试数字量存库
		 List<PointInfo> pointinfoList_D=new ArrayList<PointInfo>();//数字量存储
		 PointInfo pointinform=new PointInfo();
		 pointinform.setWarninginfo("一级警报");
		 pointinform.setComponentId("10.29.02.08.001");
		 pointinform.setPointCode("hdksio_01");
		 pointinform.setDateType("2");
		 pointinform.setValue1("1");
		 pointinfoList_D.add(pointinform);
		 savepdrData_D(pointinfoList_D);
	}


	public static void reg(){
		try{
			RtsipServiceClient rc =  new RtsipServiceClient();
			RtsipServicePortType  port = rc.getRtsipServiceHttpPort(); 
			port.registerWebService("http://10.201.2.1:8080/Rcm/services/UcsService?wsdl", "ucs");
			System.out.println("http://ip:8080/Rcm/services/UcsService?wsdl");
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		
	}
	
	

}
