
package com.ucs.rcm.test;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.ucs.rcm.db.DBUtil;



public class UtilsTest {
	
	public static void main(String[] args) {
		String sql="insert into warning values(null,'13','14','15','16','p','H','g','1','xxxxx','dddd',?,'0','0')";
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		try{
			System.out.println("------------");
			ps=conn.prepareStatement(sql);
			String s="1379349600";
			ps.setTimestamp(1, new Timestamp (new Long(s)*1000));
			ps.executeUpdate();
			ps.close();
			System.out.println("OK");
		}catch(Exception e){
			
		}
		
	}
	
	@Test
	public void timeTest(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		System.out.println(sdf.format(date)+"-00-00 00:00:00");
		
	}
	
	@Test
	public void stringtest(){
		String str="10.29.14.101.001";
		String str2=str.replace(".", ",");
		System.out.println(str2);
		String[] ss=str2.split(",");
		System.out.println(ss.length);
		System.out.println(Arrays.toString(ss));
		
	}

	
}
