package com.ucs.rcm.test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.ucs.rcm.db.DBUtil;

public class Line_health_Test {
	public static void main(String[] args) throws SQLException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar   c   =   Calendar.getInstance();
		c.set(Calendar.HOUR,-24);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MINUTE,0);
		String insert="insert into line_health values(null,'10',?,70)";
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		for(int i=0;i<24;i++){
			System.out.println(sdf.format(c.getTime()));
			ps=conn.prepareStatement(insert);
			ps.setString(1, sdf.format(c.getTime()));
			ps.executeUpdate();
			c.add(Calendar.HOUR,+1);
		}
	}
}
