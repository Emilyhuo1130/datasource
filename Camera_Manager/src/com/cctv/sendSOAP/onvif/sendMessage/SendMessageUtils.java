package com.cctv.sendSOAP.onvif.sendMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class SendMessageUtils {
	public  static SOAPMessage sendMessage(String soapReuqest,String url){
		SOAPMessage response=null;
		try {
    		MessageFactory mf = MessageFactory.newInstance();
    		SOAPConnection con = SOAPConnectionFactory.newInstance().createConnection();
    		MimeHeaders mineHeaders=new MimeHeaders();
    		/*mineHeaders.addHeader("Host", "webservice.webxml.com.cn");
    		mineHeaders.addHeader("Content-Type", "text/xml;charset=utf-8");
    		mineHeaders.addHeader("SOAPAction","http://WebXml.com.cn/getRegionCountry");*/
    		SOAPMessage reqMessage = mf.createMessage(mineHeaders, new ByteArrayInputStream(soapReuqest.getBytes()));
    		//SOAPPart soappart = reqMessage.getSOAPPart();

    		System.out.println("\n Soap Request:\n");
    		reqMessage.writeTo(System.out);
    		System.out.println();

    		URL endpoint = new URL(url);
    		response = con.call(reqMessage, endpoint);

    		// Print response
    		System.out.println("\n Soap Response:\n");
    		response.writeTo(System.out);
    		System.out.println("\n");
    		} catch (SOAPException e) {
    		e.printStackTrace();
    		} catch (IOException e) {
    		e.printStackTrace();
    		}
		return response;
	}
	
	public static String getSendTime(){
		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		String time= sdf.format(new Date());
		return time.substring(0,10)+"T"+time.substring(10)+"Z";*/
		SimpleDateFormat foo = new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");  
	//	System.out.println("foo:"+foo.format(new Date()));  
		  
		Calendar gc = GregorianCalendar.getInstance();  
		//System.out.println("gc.getTime():"+gc.getTime());  
		//System.out.println("gc.getTimeInMillis():"+new Date(gc.getTimeInMillis()));  
		  
		//当前系统默认时区的时间：  
		Calendar calendar=new GregorianCalendar();  
	//	System.out.print("时区："+calendar.getTimeZone().getID()+"  ");  
	//	System.out.println("时间："+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));  
		//美国洛杉矶时区  
		TimeZone tz=TimeZone.getTimeZone("America/Los_Angeles");  
		//时区转换  
		calendar.setTimeZone(tz);  
	//	System.out.print("时区："+calendar.getTimeZone().getID()+"  ");  
	//	System.out.println("时间："+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));  
		Date time=new Date();  
		  
		//1、取得本地时间：  
		java.util.Calendar cal = java.util.Calendar.getInstance();  
		  
		//2、取得时间偏移量：  
		int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);  
		  
		//3、取得夏令时差：  
		int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);  
		  
		//4、从本地时间里扣除这些差量，即可以取得UTC时间：  
		cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));  
		  
		//之后调用cal.get(int x)或cal.getTimeInMillis()方法所取得的时间即是UTC标准时间。  
		/*System.out.println("UTC:"+new Date(cal.getTimeInMillis()));  
		System.out.println(foo.format(new Date(cal.getTimeInMillis())));*/
		String UTCtime= foo.format(new Date(cal.getTimeInMillis()));
		return UTCtime.substring(0,10)+"T"+UTCtime.substring(10)+"Z";
		
	}
	
	
	
	public static void main(String[] args){
		System.out.println(getSendTime());
	}
}
