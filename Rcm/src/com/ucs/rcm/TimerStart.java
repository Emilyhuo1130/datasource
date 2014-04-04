package com.ucs.rcm;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import com.ucs.rcm.business.bo.WarningBo;
public class TimerStart implements ServletContextListener{
	static Logger log = Logger.getLogger(TimerStart.class);
	/**
	 * @param args
	 */
	WarningBo wb = new WarningBo();
	
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	
	public void contextInitialized(ServletContextEvent config) {
		log.warn(config.getServletContext().getRealPath("/"));
		System.setProperty("user.dir", config.getServletContext().getRealPath("/"));
		log.warn("*************************定时器开始启动*****************************");
	//	System.getProperty("user.dir"+"/web-inf/");
		/**定时读取告警信息统计运营指数给运营指数做更新**/
		Timer timer=new Timer();
		timer.schedule(new TimerTask(){
			
			public void run(){
				log.warn(new Date()+"*************更新运营指标*************");
				//Chattelanalysebo.updateOperateIndex();
			}
		}, 900000,900000);
		
		
		/***定时 实件分析-健康指数-设施指数 定时更新*/
		Timer timer2=new Timer();
		timer2.schedule(new TimerTask(){

			@Override
			public void run() {
				log.warn(new Date()+"***************更新设施指数*************************");
				//Chattelanalysebo.selecctEquipmentinfo();
			}
			
		},600000,600000);
		
		
		
		
		/***设置定时访问设备信息。看看是否存在计划修的数据**/
		Calendar upcal = Calendar.getInstance();  
		upcal.set(Calendar.HOUR_OF_DAY,02);  
		upcal.set(Calendar.MINUTE,  00);  
		upcal.set(Calendar.SECOND,  00);  
        Date startUPdate = upcal.getTime(); 
        selectInfoFromEquipment();//访问计划修信息
        if(startUPdate.before(new Date())){  
        	upcal.add(Calendar.DATE,+1);  
        }  
    	Timer timer3=new Timer();
        timer3.schedule(new TimerTask() {
			@Override
			public void run() {
				 selectInfoFromEquipment();//下次访问计划修信息
			}
		}, upcal.getTime(), 1000*60*60*24);
		
        /***每一个小时取一次的一条线的健康值**/
        Timer timer4=new Timer();
        timer4.schedule(new TimerTask() {
			@Override
			public void run() {
				log.info("定时计算线路的健康指数");
				//WarningBo.readHealrhLevel();
			}
		}, 3000, 1000*60*60);
	}
	

	/**访问计划修的信息，如果在今天存在要计划修的部件。加到告警管理里面，设置类型为 计划预警**/
	private void selectInfoFromEquipment() {
		System.out.println("访问计划修");
		wb.createPlanWarning();
	}

}
