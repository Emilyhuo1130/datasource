package com.ucs.rcm.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.ucs.rcm.db.DBUtil;

public class UtilsQuShi {
private String pointcode;
private long savetime;
private int flag=1;
private int status=1;
private float value;
private long opertime;
private String componentId;

	

	public String getPointcode() {
	return pointcode;
}

public void setPointcode(String pointcode) {
	this.pointcode = pointcode;
}

public long getSavetime() {
	return savetime;
}

public void setSavetime(long savetime) {
	this.savetime = savetime;
}

public int getFlag() {
	return flag;
}

public void setFlag(int flag) {
	this.flag = flag;
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}

public float getValue() {
	return value;
}

public void setValue(float value) {
	this.value = value;
}

public long getOpertime() {
	return opertime;
}

public void setOpertime(long opertime) {
	this.opertime = opertime;
}

public String getComponentId() {
	return componentId;
}

public void setComponentId(String componentId) {
	this.componentId = componentId;
}

	/**
	 * @param args
	 */
	/**趋势分析伪造数据
	 * @throws ParseException **/
	
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Calendar   c   =   Calendar.getInstance();
		c.set(Calendar.HOUR,-12);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MINUTE,0);
		List<UtilsQuShi> list=new ArrayList<UtilsQuShi>();
		for(int i=0;i<3*24;i++){
			UtilsQuShi bo=new UtilsQuShi();
			
			long l=sdf.parse(sdf.format(c.getTime())).getTime()/1000;
			bo.setPointcode("lb_em_ai239");
			bo.setSavetime(l);
			bo.setFlag(1);
			bo.setStatus(1);
			bo.setValue((float) ((float)(Math.random()*(95))+40));//31.5-38.5
			bo.setOpertime(l);
			bo.setComponentId("10.31.03.36.001");
			
			list.add(bo);
			
			c.add(Calendar.MINUTE, +20);
			
		}
		
		savetoDb(list);
	}

	private static void savetoDb(List<UtilsQuShi> list) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConn();
		PreparedStatement ps=null;
		
		String insert="insert into hisdata201308 values(null,?,?,?,?,?,?,?)";
		try{
			for(UtilsQuShi bo:list){
				ps=conn.prepareStatement(insert);
				ps.setString(1, bo.getPointcode());
				ps.setLong(2, bo.getSavetime());
				ps.setInt(3, bo.getFlag());
				ps.setInt(4, bo.getStatus());
				ps.setFloat(5, bo.getValue());
				ps.setLong(6, bo.getOpertime());
				ps.setString(7, bo.getComponentId());
				ps.executeUpdate();
				
			}
			System.out.println("OK");
			ps.close();
			
		} catch (Exception e) {
            e.printStackTrace();
        }
	}

}
