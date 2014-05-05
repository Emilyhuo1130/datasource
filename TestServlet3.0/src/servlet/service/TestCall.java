package servlet.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


import servlet.db.DBUtil;

public class TestCall {

	/**
	 * @param args
	 * 用于测试存储过程
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		select_info();
	}
	//无参数的查询
	public static void test1(){
		Connection conn = DBUtil.getConn();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			CallableStatement prepareCall = conn.prepareCall("call hello()");
			rs = prepareCall.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("id")+"   :"+rs.getString("name"));
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//带入参的存储过程
	public static void  pom_name(){
		Connection conn = DBUtil.getConn();
		ResultSet rs;
		try{
			CallableStatement prepareCall = conn.prepareCall("call pom_name('zhang',41)");
			rs=prepareCall.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("name")+"     :"+rs.getInt("age"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void select_info(){
		Connection conn = DBUtil.getConn();
		ResultSet rs;
		try{
			CallableStatement prepareCall = conn.prepareCall("call select_info('test',42)");
			rs=prepareCall.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("name")+"     :"+rs.getInt("age"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
