package com.ucs.rcm.ztree.impactAnalyst;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.business.bo.BaseHibernateDAO;
import com.ucs.rcm.db.DBUtil;
import com.ucs.rcm.pojo.Equipmenttree;
import com.ucs.rcm.reqobj.Reqgetimpactanalysttree;
import com.ucs.rcm.ztree.warningFaultTree.GetStationFaultTree;

public class GetimpactAnalystTree extends BaseHibernateDAO{
	static Logger log = Logger.getLogger(GetimpactAnalystTree.class);
	public Resshuck getimpactanalysttree(Reqgetimpactanalysttree req){
		Resshuck shuck=new Resshuck();
		List<Level1> l1List=new ArrayList<Level1>();
		
		Session session = getSession();
		session.clear();
		try{
			String findsys="select DISTINCT e.systemname from Equipmenttree e" ;
			Query query = session.createQuery(findsys);
			@SuppressWarnings("unchecked")
			List<String> stnames = query.list();
			Iterator<String> it = stnames.iterator();
			while(it.hasNext()){
				Level1 v1=new Level1();
				String sysname = it.next();
				v1.setName(sysname);
				v1.setChildren(findLevel2s(sysname,req));
				if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
					if(sysname.equals(req.getSystemName())){
						v1.setOpen(true);
					}
				}
				l1List.add(v1);
			}
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
		}
		shuck.setName(l1List);
		return shuck; 
		
	}
//this session shuld not close
	private  List<Level2> findLevel2s(String sys,
			Reqgetimpactanalysttree req) {
		// TODO Auto-generated method stub
		List<Level2> list=new ArrayList<Level2>();
		String equipemnt=null;
		Session session = getSession();
		session.clear();
		if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
			try{
				String findequipment="select DISTINCT e.equipment from Equipmenttree e where e.component='"+req.getEquipmentName()+"'";
				Query query = session.createQuery(findequipment);
				@SuppressWarnings("unchecked")
				Iterator<String> it = query.list().iterator();
				while(it.hasNext()){
					equipemnt=it.next();
				}
			}catch(Exception e){
				log.error(RcmUtils.getTrace(e));
			}
			
		}
		
		try{
			String findlevel2s="select DISTINCT e.equipment from Equipmenttree e where e.systemname='"+sys+"'";
			Query query_1 = session.createQuery(findlevel2s);
			@SuppressWarnings("unchecked")
			Iterator<String>  its = query_1.list().iterator();
			
			while(its.hasNext()){
				Level2 v2=new Level2();
				String eq = its.next();
				v2.setName(eq);
				v2.setChildren(findLevel3s(eq,req));
				if(equipemnt!=null){
					if(eq.equals(equipemnt)){
						v2.setOpen(true);
					}
				}
				list.add(v2);
			}
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return list;
	}

	private  List<Level3> findLevel3s(String equipment,
			Reqgetimpactanalysttree req) {
		// TODO Auto-generated method stub
		List<Level3> list=new ArrayList<Level3>();
		Session session = getSession();
		session.clear();
		try{
			String findlevel3s="select new Equipmenttree(e.id,e.component) from Equipmenttree e where e.equipment='"+equipment+"'";
			Query query = session.createQuery(findlevel3s);
			Gson gson = new Gson();
			log.warn(gson.toJson(query.list()));
			@SuppressWarnings("unchecked")
			Iterator<Equipmenttree>  it = query.list().iterator();
			while(it.hasNext()){
				Level3 v=new Level3();
				Equipmenttree ep = it.next();
				v.setName(ep.getComponent());
				v.setChildren(findLevel4s(ep.getId(),req,ep.getComponent()));
				if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
					if(ep.getComponent().equals(req.getEquipmentName())){
						v.setOpen(true);
					}
				}
				list.add(v);
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return list;
	}
//this session shuld not close
	private  List<Level4> findLevel4s(int componentid,
			Reqgetimpactanalysttree req,String component) {
		// TODO Auto-generated method stub
		List<Level4> list=new ArrayList<Level4>();
		Session session = getSession();
		session.clear();
		try{
			int id=0;
			String findlevel4s="select DISTINCT f.faulttype  from Faultinfo f where f.componentid='"+componentid+"' and f.equipmentname= '"+component+"' ";
			
			Query query = session.createQuery(findlevel4s);
			@SuppressWarnings("unchecked")
			Iterator<String> it = query.list().iterator();
			while(it.hasNext()){
				Level4 v=new Level4();
				String fp = it.next();
				v.setName(fp);
				String findid="select DISTINCT f.id from Faultinfo f where f.faulttype='"+fp+"' and f.equipmentname= '"+component+"' ";
				Query query_1 = session.createQuery(findid);
				@SuppressWarnings("unchecked")
				Iterator<Integer>  its = query_1.list().iterator();
				while(its.hasNext()){
					id=its.next();
				}
				v.setClick("showFaultInfo("+id+");");
				list.add(v);
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return list;
	}
}
