package com.ucs.rcm.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Date date =  new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       
        try {
        	date = formatDate.parse("2013-11-23 10:41:06");
        	long ut = date.getTime();
        	System.out.println(ut/1000);
        	
        	long l=1385184836;
        	Date date2=new Date(l*1000);
        	System.out.println(date2);
       
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        
   
		
		
		
		

	}

}
