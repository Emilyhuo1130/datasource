package com.ucs.rcm.webservice;

import java.util.List;

import bean.ArrayOfMyAnalogProperty;
import bean.ArrayOfMyDigitalProperty;
import bean.ArrayOfMyEventProperty;
import bean.MyAnalogProperty;
import bean.MyDigitalProperty;
import bean.MyEventProperty;
import bean.RtsipServiceClient;
import bean.RtsipServicePortType;

import com.ucs.rcm.business.bo.WarningBo;
import com.ucs.rcm.webservice.partner.EquipmentService;
import com.ucs.rcm.webservice.partner.EquipmentServiceClient;

public class TestRtsip {
	public static void reg(){
		
		RtsipServiceClient rc =  new RtsipServiceClient();
		
	  	RtsipServicePortType  port = rc.getRtsipServiceHttpPort(); 
	  	port.registerWebService("http://10.201.2.5:8080/Rcm/services/UcsService?wsdl", "ucs");
		
	}
	
	public static void testRtsip(){
		

		// TODO Auto-generated method stub
		
		System.out.println(" testRtsip() start");
		
		RtsipServiceClient rc =  new RtsipServiceClient();
		
		System.out.println(" RtsipServiceClient");
	
  	RtsipServicePortType  port = rc.getRtsipServiceHttpPort(); 
  	
  	System.out.println(" getRtsipServiceHttpPort");
  	
		//port.registerWebService("http://10.201.2.5:8080/Rcm/services/UcsService?wsdl", "Ucs");  
		ArrayOfMyAnalogProperty myAnalogs = port.getAnalogProperty("WZL", "10.32.08.01.001");		
		ArrayOfMyDigitalProperty  digtitals =  port.getDigitalProperty("WZL", "10.32.08.01.032");
		ArrayOfMyEventProperty events =  port.getEventProperty("ucs", 1);
		
		System.out.println(" getProperty");
		
		List<MyAnalogProperty> myAnalogList = myAnalogs.getMyAnalogProperty();
		//System.out.println("模擬量："+myAnalogList.size());
		List<MyDigitalProperty> digtitalsList = digtitals.getMyDigitalProperty();
		//System.out.println("数字量："+digtitalsList.size());
		List<MyEventProperty> eventList= events.getMyEventProperty();
		System.out.println("警告个数"+eventList.size());
		
		
	/*	MyAnalogProperty myAnalogProperty= new MyAnalogProperty();
		MyDigitalProperty myDigitalProperty = new MyDigitalProperty();
		MyEventProperty myEventProperty = new MyEventProperty();*/
		
/*		
		for(MyAnalogProperty myAnalogProperty :myAnalogList){
			System.out.println(myAnalogProperty.getValue().toString());
			System.out.println(myAnalogProperty.getDescription().getValue());
			
		}
		
		for(MyDigitalProperty myDigitalPropert:digtitalsList){
			System.out.println(myDigitalPropert.getDescription().getValue());
			System.out.println(myDigitalPropert.getValue().intValue());
			
		}*/
	
		for(MyEventProperty  myEventProperty:eventList){
			myEventProperty.getDevicecode();
			myEventProperty.getGroupcode();
			myEventProperty.getMessage();
		
			System.out.println(myEventProperty.getDevicecode().getValue());
			System.out.println(myEventProperty.getGroupcode().getValue());
			System.out.println(myEventProperty.getMessage().getValue());
			System.out.println(myEventProperty.getStamptime().intValue());
			
			  myAnalogs = port.getAnalogProperty(myEventProperty.getGroupcode().getValue(),myEventProperty.getDevicecode().getValue());		
			   digtitals =  port.getDigitalProperty(myEventProperty.getGroupcode().getValue(),myEventProperty.getDevicecode().getValue());
			   
			    myAnalogList = myAnalogs.getMyAnalogProperty();
				System.out.println("模擬量："+myAnalogList.size());
				  digtitalsList = digtitals.getMyDigitalProperty();
				System.out.println("数字量："+digtitalsList.size());
			
			//WarningBo bo = getWaringAll(myEventProperty.getDevicecode().getValue(),"0");
			
			
		}
	
		
		
		

	
		
	}
	
	
	public static void saveWarnings(List<MyEventProperty> eventList){
		
		for(MyEventProperty event:eventList){
			
			
			WarningBo wb =  new WarningBo();
			wb.setEquipmentId(event.getDevicecode().getValue());
			wb.setWarninginfo(event.getMessage().getValue());
			wb.setWarningDate(event.getStamptime().toString());
			
		
	
		}
		
	
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RtsipServiceClient rc =  new RtsipServiceClient();
	
  	RtsipServicePortType  port = rc.getRtsipServiceHttpPort(); 
		port.registerWebService("http://10.201.2.5:8080/Rcm/services/UcsService?wsdl", "Ucs");  
		ArrayOfMyAnalogProperty myAnalogs = port.getAnalogProperty("WZL", "10.32.08.01.001");		
		ArrayOfMyDigitalProperty  digtitals =  port.getDigitalProperty("WZL", "10.32.08.01.032");
		ArrayOfMyEventProperty events =  port.getEventProperty("ucs", 1);
		
		List<MyAnalogProperty> myAnalogList = myAnalogs.getMyAnalogProperty();
		System.out.println("模擬量："+myAnalogList.size());
		List<MyDigitalProperty> digtitalsList = digtitals.getMyDigitalProperty();
		System.out.println("数字量："+digtitalsList.size());
		List<MyEventProperty> eventList= events.getMyEventProperty();
		System.out.println("警告个数"+eventList.size());
		
		
	/*	MyAnalogProperty myAnalogProperty= new MyAnalogProperty();
		MyDigitalProperty myDigitalProperty = new MyDigitalProperty();
		MyEventProperty myEventProperty = new MyEventProperty();*/
		
		
		for(MyAnalogProperty myAnalogProperty :myAnalogList){
			System.out.println(myAnalogProperty.getValue().toString());
			System.out.println(myAnalogProperty.getDescription().getValue());
			
		}
		
		for(MyDigitalProperty myDigitalPropert:digtitalsList){
			System.out.println(myDigitalPropert.getDescription().getValue());
			System.out.println(myDigitalPropert.getValue().intValue());
			
		}
	
		for(MyEventProperty  myEventProperty:eventList){
			myEventProperty.getDevicecode();
			myEventProperty.getGroupcode();
			myEventProperty.getMessage();
			System.out.println(myEventProperty.getDevicecode().getValue());
			
			//WarningBo bo = getWaringAll(myEventProperty.getDevicecode().getValue(),"0");
			
			
		}
	
		
		
		

	}

	private static WarningBo getWaringAll(String deviceCode,String type) {
		
		WarningBo bo = new WarningBo();
		
		 EquipmentServiceClient ec = new EquipmentServiceClient();
		  EquipmentService es = ec.getEquipmentSoapPort( );
		  String xml = es.getEquipment(deviceCode, type);
		// TODO Auto-generated method stub
	
		return bo;
	}

}
