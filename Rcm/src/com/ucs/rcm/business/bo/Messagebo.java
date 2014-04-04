package com.ucs.rcm.business.bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.db.DBUtil;
import com.ucs.rcm.reqobj.Reqconfirmorcancel;
import com.ucs.rcm.reqobj.ReqgetMessageList;
import com.ucs.rcm.reqobj.Reqmessage;
import com.ucs.rcm.responseObj.Messageinfo;
import com.ucs.rcm.responseObj.ResInfo;
import com.ucs.rcm.responseObj.ResMessageList;

public class Messagebo {
	static Logger log = Logger.getLogger(Messagebo.class);
	public static  ResMessageList getmessageList(ReqgetMessageList req) {
		// TODO Auto-generated method stub
		ResMessageList messageList=new ResMessageList();
		List<Messageinfo> infolist=new ArrayList<Messageinfo>();
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql=getsql(req);
		if(sql!=null){
			try{
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					Messageinfo info=new Messageinfo();
					info.setWarningId(rs.getString("warningid"));
					info.setMessage(rs.getString("info"));
					info.setInfodate(rs.getString("infodate"));
					info.setFromuser(rs.getString("fromuser"));
					infolist.add(info);
				}
				
			}catch (Exception e) {
				log.error(RcmUtils.getTrace(e));
				
			}
			
		}
		messageList.setMessageList(infolist);
		return messageList;
	}

	private static String getsql(ReqgetMessageList req) {
		// TODO Auto-generated method stub
		StringBuffer buf=new StringBuffer();
		buf.append("select * from message where 1=1 ");
		if(req.getUserName().equals("user2")){
			buf.append(" and warningId= '"+req.getWarningId()+"' and fromuser= '"+req.getFromuser()+"' ");
			buf.append(" order by infodate desc");
			return buf.toString();
		}else if(req.getUserName().equals("user1")){
			buf.append(" and fromuser= '"+req.getFromuser()+"'  and statments='0' ");
			buf.append(" order by infodate desc");
			return buf.toString();
		}else{
			return null;
		}
	}

	public ResInfo saveuser1Message(Reqconfirmorcancel req) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		
		String saveuser1Message=null;
		if(req.getOk().equals("goback")){
			saveuser1Message="insert into message (warningid,info,infodate,fromuser,statments) values(?,?,?,'1','5')";
		}else if(req.getOk().equals("goto")){
			saveuser1Message="insert into message (warningid,info,infodate,fromuser,statments) values(?,?,?,'1','1')";
		}
		
		ResInfo r = new ResInfo();
		if(saveuser1Message!=null){
			
			try{
				ps=conn.prepareStatement(saveuser1Message);
				ps.setString(1, req.getWarningId());
				ps.setString(2, req.getMessage());
				ps.setString(3, req.getTime());
				ps.executeUpdate();
				
				ps.close();
				
				r.setInfo("sucess");
				return r;
			}catch (Exception e) {
				log.error(RcmUtils.getTrace(e));
			}
		}
		r.setInfo("false");
		return r;
	
	}

	public static ResInfo saveMessage(Reqmessage req) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConn();
		PreparedStatement ps = null;
		String saveStar=null;
		String saveEnd=null;
		if(req.getFromuser().equals("2")){
			if(req.getOk().equals("goto")){
				saveStar="insert into message (warningid,info,infodate,fromuser,statments) values(?,?,?,'2','2')";
				saveEnd="insert into message (warningid,info,infodate,fromuser,statments) values(?,?,?,'2','2')";
				
			}else if(req.getOk().equals("goback")){
				saveStar="insert into message (warningid,info,infodate,fromuser,statments) values(?,?,?,'2','0')";
				saveEnd="insert into message (warningid,info,infodate,fromuser,statments) values(?,?,?,'2','0')";
			}
			
		}else if(req.getFromuser().equals("3")){
			if(req.getOk().equals("goto")){
				saveStar="insert into message (warningid,info,infodate,fromuser,statments) values(?,?,?,'3','3')";
				saveEnd="insert into message (warningid,info,infodate,fromuser,statments) values(?,?,?,'3','3')";
				
			}else if(req.getOk().equals("goback")){
				saveStar="insert into message (warningid,info,infodate,fromuser,statments) values(?,?,?,'3','1')";
				saveEnd="insert into message (warningid,info,infodate,fromuser,statments) values(?,?,?,'3','1')";
			}
		}
		ResInfo r = new ResInfo();
		if((saveStar!=null)&&(saveEnd!=null)){
			
			try{
				ps=conn.prepareStatement(saveStar);
				ps.setString(1, req.getWarningId());
				ps.setString(2, req.getStarMessage());
				ps.setString(3, req.getStarTime());
				ps.executeUpdate();
				
				ps.close();
				ps=conn.prepareStatement(saveEnd);
				ps.setString(1, req.getWarningId());
				ps.setString(2, req.getEndMessage());
				ps.setString(3, req.getEndTime());
				ps.executeUpdate();
				ps.close();
				r.setInfo("sucess");
				return r;
			}catch (Exception e) {
				log.error(RcmUtils.getTrace(e));
			}
		}
		r.setInfo("false");
		return r;
		
	}

}
