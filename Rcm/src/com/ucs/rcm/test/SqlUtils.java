package com.ucs.rcm.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.ucs.rcm.db.DBUtil;

public class SqlUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		try{
			String find="select count(*) as count  from warning ";
			ps=conn.prepareStatement(find);
			rs=ps.executeQuery();
			rs.next();
			count=rs.getInt("count");
			ps.close();
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar   c   =   Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		System.out.println(sdf.format(c.getTime()));
		String sql="update warning set warningDate=? where id=?";
		try{
			for(int i=1;i<=count;i++){
				ps=conn.prepareStatement(sql);
				ps.setString(1, sdf.format(c.getTime()));
				ps.setInt(2, i);
				ps.executeUpdate();
				if(i % 2== 0){
					c.add(Calendar.DATE, -1);
				}
				System.out.println(sdf.format(c.getTime()));
			}
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}

}
