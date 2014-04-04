package com.ucs.gk.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

public class TimerTest {

	/**
	 * @param args
	 * 单例定时器
	 */
	private TimerTest(){}
	private static Timer timer3=null;
	private static Timer timer1=new Timer();
	
	public static void main(String[] args) {
		timer1.schedule(new TimerTask() {
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("ooooooooooooo");
				timer1.cancel();
			}
		}, 4000);
		System.out.println("----------------");
	}
	
	
	
	public static void start(){
		Calendar synchronizationPlayCal = Calendar.getInstance();  
		 synchronizationPlayCal.set(Calendar.HOUR_OF_DAY,9);
		 synchronizationPlayCal.set(Calendar.MINUTE, 18);
    	 synchronizationPlayCal.set(Calendar.SECOND,0);
    	 System.out.println(synchronizationPlayCal.getTime());
    	
		timer3.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("我执行了一次"+new Date());
				
			}
		}, synchronizationPlayCal.getTime(), 1000*2);
	}
	public static void start3(){
		Calendar synchronizationPlayCal = Calendar.getInstance();  
		 synchronizationPlayCal.set(Calendar.HOUR_OF_DAY,9);
		 synchronizationPlayCal.set(Calendar.MINUTE, 18);
    	 synchronizationPlayCal.set(Calendar.SECOND,0);
    	 System.out.println(synchronizationPlayCal.getTime());
    	
		timer3.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("hello:"+new Date());
				
			}
		}, synchronizationPlayCal.getTime(), 1000*2);
	}
	
	
	public static void startTimer1(){
		timer1.schedule(new TimerTask() {
			
			int i=0;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				i++;
				System.out.println(i);
				if(i==5){
					timer3.cancel();
				}
			}
		}, 0,3000);
	}
	
	public static Timer gettimer(){
		if (timer3 == null) {
			 synchronized (TimerTest.class) {
				 timer3 = new Timer();
				 start();
				 start3();
				 
			 }
	       }

	       return timer3;
	}
	public static Timer closeTimer(){
	       return timer3;
	}

}
