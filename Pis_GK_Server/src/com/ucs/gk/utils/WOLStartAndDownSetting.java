package com.ucs.gk.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;



public class WOLStartAndDownSetting {

	/**
	 * @param args
	 */
	static Logger log = Logger.getLogger(WOLStartAndDownSetting.class);
	private static String config_properties=System.getProperty("user.dir")+"/WEB-INF";
	private static String startuptimes[]=null;
	private  static Timer timer=null;
	private static Timer fileTimer=new Timer();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static void readSetting(){
		Properties p = new Properties();
		try{
			FileInputStream fis=new FileInputStream(config_properties+"/effects.properties");
			BufferedReader buffer = new BufferedReader(   
				       new InputStreamReader(fis,"utf-8"));  
			p.load(buffer);
			startuptimes=p.getProperty("Servr.startUpTime").split(":");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**设置网络唤醒***/
	public static  void settingWakeOn(){
		readSetting();
		/**制定唤醒的时间**/
		Calendar upcal = Calendar.getInstance();  
		upcal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startuptimes[0]));  
		upcal.set(Calendar.MINUTE,  Integer.parseInt(startuptimes[1]));  
		upcal.set(Calendar.SECOND,  Integer.parseInt(startuptimes[2]));  
        Date startUPdate = upcal.getTime(); 
        
        log.warn("启动服务器，开始唤醒远程主机");
        WakeOnLan.getIPAndMACstoWakeUp();//启动及唤醒远程主机
        if(startUPdate.before(new Date())){  
        	upcal.add(Calendar.DATE,+1);  
        	log.warn("/**制定下一次唤醒的时间**/"+upcal.getTime());
        }  
        
        /**网络唤醒定时任务**/
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				log.warn(new Date()+"唤醒远程主机");
				WakeOnLan.getIPAndMACstoWakeUp();
			}
		}, upcal.getTime(), 1000*60*60*24);
	}
	
	
	
	/***定时检查文件是否发生变化**/
	public static void modifyFileDate(){
		File file2=new File(config_properties+"/effects.properties");
		long filetime2=file2.lastModified();
		log.warn("服务器配置最后修改时间:"+new Date(filetime2));
		fileTimer.schedule(new TimerTask() {
			File file=null;
			
			public void run() {
				file=new File(config_properties+"/effects.properties");
				long filetime=file.lastModified();
				long var=new Date().getTime();
				if((var-filetime)<=3000){
					/**关闭定时器**/
					timer.cancel();
					/**重开定时器***/
					timer=null;
					openTimer();
					log.warn("重新配置网络唤醒和网络关闭成功"+new Date());
					
					/**重新将特效类型同步到数据库***/
					//SaveToDB db=new SaveToDB();
					//db.saveEffectTOdb();
				}
			}
		}, 0,3000);
	}
	
	/**开启定时器任务*/
	public static Timer openTimer(){
		if (timer == null) {
			timer = new Timer();
			settingWakeOn();
	       }
	       return timer;
	}
	
	
	/**关闭定时器任务*/
	public static Timer closeTimer(){
	       return timer;
	}

}
