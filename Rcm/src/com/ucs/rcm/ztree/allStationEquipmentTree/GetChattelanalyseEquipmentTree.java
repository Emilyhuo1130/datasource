package com.ucs.rcm.ztree.allStationEquipmentTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gson.Gson;
import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.business.bo.BaseHibernateDAO;
import com.ucs.rcm.pojo.Equipmenttreeinfo;
import com.ucs.rcm.reqobj.ReqgetAllEquipmentTree;



public class GetChattelanalyseEquipmentTree extends BaseHibernateDAO{
	static Logger log = Logger.getLogger(GetChattelanalyseEquipmentTree.class);
	Gson gson = new Gson();
	public  ResAllEquipmentTree getAllEquipmentTree(ReqgetAllEquipmentTree req){
	
		ResAllEquipmentTree tree=new ResAllEquipmentTree();
		List<StationName> stationList=new ArrayList<StationName>();
		Session session = getSession();
		session.clear();
		try{
			String findStationNames="select DISTINCT e.stationName from Equipmenttreeinfo e";
			Query query_4 = session.createQuery(findStationNames);
			@SuppressWarnings("unchecked")
			Iterator<String> it = query_4.list().iterator();
			while(it.hasNext()){
				StationName station=new StationName();
				String s = it.next();
				station.setName(s);
				station.setChildren(findSystemNames(s,req));
				if((req.getStationName()!=null)&&(req.getStationName().trim().length()>0)){
					if(s.equals(req.getStationName())){
						station.setOpen(true);
					}
					
				}
				stationList.add(station);
				
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
		}
		tree.setName(stationList);	
		return tree;
	}
	private  List<SystemName> findSystemNames(String station,
			ReqgetAllEquipmentTree req) {
		// TODO Auto-generated method stub
		List<SystemName> systemList=new ArrayList<SystemName>();
		Session session = getSession();
		session.clear();
		try{
			String findsystemNames="select DISTINCT e.systemname from Equipmenttreeinfo e where e.stationName= '"+station+"'";
			Query query_3 = session.createQuery(findsystemNames);
			@SuppressWarnings("unchecked")
			Iterator<String> it = query_3.list().iterator();
			while(it.hasNext()){
				SystemName system=new SystemName();
				String s = it.next();
				system.setName(s);
				system.setChildren(findSubSystemName(station,s,req));
				if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
					if(s.equals(req.getSystemName())){
						system.setOpen(true);
					}
					
				}
				systemList.add(system);
			}
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		
		return systemList;
	}
	
	
	@SuppressWarnings("unchecked")
	private  List<SubSystemName> findSubSystemName(String station,String SystemName,
			ReqgetAllEquipmentTree req) {
		// TODO Auto-generated method stub
		
	
		List<SubSystemName> subList=new ArrayList<SubSystemName>();
		String flag=null;
		
		if((req.getComponent()!=null)&&(req.getComponent().trim().length()>0)){
			String subsystenNameFlag="select DISTINCT e.subsystemname " +
			"from Equipmenttreeinfo e where e.stationName= '"+station+"' and e.systemname= '"
			+SystemName+"' and e.component='"+req.getComponent()+"'";
			
			try{
				Session session = getSession();
				session.clear();
				Query query = session.createQuery(subsystenNameFlag);
				subList = query.list();
				Iterator<SubSystemName> it = subList.iterator();
				while(it.hasNext()){
					flag=it.next().getName();
				}
				
			}catch(Exception e){
				log.error(RcmUtils.getTrace(e));
			}
			
		}
		
		try{
			Session session = getSession();
			session.clear();
			String findsystemNames="select DISTINCT e.subsystemname " +
					"from Equipmenttreeinfo e where e.stationName= '"+station+"' and e.systemname= '"+SystemName+"'";
			Query query_2 = session.createQuery(findsystemNames);
			Iterator<String> it = query_2.list().iterator();
			while(it.hasNext()){
				SubSystemName subSystem=new SubSystemName();
				String s = it.next();
				subSystem.setName(s);
				String[] str={station,s};
				subSystem.setChildren(findcomponents(str,req));
				if(flag!=null){
					if(s.equals(flag)){
						subSystem.setOpen(true);
					}
				}
				
				subList.add(subSystem);
			}
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		
		return subList;
	}
	private  List<Component> findcomponents(String[] str,
			ReqgetAllEquipmentTree req) {
		// TODO Auto-generated method stub
		
		List<Component> comList=new ArrayList<Component>();
		Session session = getSession();
		session.clear();
		try{
			String findcomponent="select DISTINCT e.component " +
					" from Equipmenttreeinfo e where e.stationName='"+str[0]+"'  and " +
					" e.subsystemname= '"+str[1]+"'";
			Query query_5 = session.createQuery(findcomponent);
			@SuppressWarnings("unchecked")
			Iterator<String> it = query_5.list().iterator();
			while(it.hasNext()){
				Component com=new Component();
				String s = it.next();
				com.setName(s);
				com.setChildren(findSubComponent(str,s,req));
				if((req.getComponent()!=null)&&(req.getComponent().trim().length()>0)){
					if(s.equals(req.getComponent())){
						com.setOpen(true);
					}
				}
				comList.add(com);
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		
		return comList;
	}
	
	
	
	
	private  List<SubComponent> findSubComponent(String[] strs,
			String subcomponent, ReqgetAllEquipmentTree req) {
		// TODO Auto-generated method stub
	
		List<SubComponent> subList=new ArrayList<SubComponent>();
		Session session = getSession();
		session.clear();
		try{
			String findsub="select DISTINCT new Equipmenttreeinfo(e.id,e.subcomponent) " +
					" from Equipmenttreeinfo e where e.stationName=? and e.subsystemname=?  and  e.component=?";
			Query query_6 = session.createQuery(findsub);
			query_6.setString(0, strs[0]);
			query_6.setString(1, strs[1]);
			query_6.setString(2, subcomponent);
			@SuppressWarnings("unchecked")
			Iterator<Equipmenttreeinfo> it = query_6.list().iterator();
			while(it.hasNext()){
				SubComponent sub=new SubComponent();
				Equipmenttreeinfo e = it.next();
				sub.setName(e.getSubcomponent());
				sub.setClick("showEquipmentInfo("+e.getId()+");");
				subList.add(sub);
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return subList;
	}
	
}
