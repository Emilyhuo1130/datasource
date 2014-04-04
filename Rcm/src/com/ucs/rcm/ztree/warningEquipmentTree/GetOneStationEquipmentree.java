
package com.ucs.rcm.ztree.warningEquipmentTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import net.sf.json.JSONObject;
import com.google.gson.Gson;
import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.business.bo.BaseHibernateDAO;
import com.ucs.rcm.pojo.Equipmenttreeinfo;
import com.ucs.rcm.reqobj.ReqshowOneStationsubComponent;

public class GetOneStationEquipmentree extends BaseHibernateDAO{
	static Logger log = Logger.getLogger(GetOneStationEquipmentree.class);
	Gson gson = new Gson();
	
	
	
	public   ResOneStationEquipmentTree getonestationEquipmentTree(ReqshowOneStationsubComponent req){
		if(req.getUser().trim().equals("user2")){
		}else{
			req.setFlag("Yes");
		}
		ResOneStationEquipmentTree tree=new ResOneStationEquipmentTree();
		List<SystemName> systems=new ArrayList<SystemName>();
		
		String findSys=null;
		
		try{
			Session session = getSession();
			session.clear();
			String findsysname="select e.systemname from Equipmenttreeinfo e where e.subcomponentid= '"+req.getSubComponentId()+"'";
			Query query = session.createQuery(findsysname);
			@SuppressWarnings("unchecked")
			Iterator<String> it = query.list().iterator();
			while(it.hasNext()){
				findSys=it.next();
			}
			session.close();
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		
		try{
			Session session = getSession();
			session.clear();
			String findsystems="select DISTINCT e.systemname from Equipmenttreeinfo e where e.stationName= '"+req.getStationName()+"'";
			Query query_1 = session.createQuery(findsystems);
			@SuppressWarnings("unchecked")
			Iterator<String> its = query_1.list().iterator();
			while(its.hasNext()){
				SystemName system=new SystemName();
				String s = its.next();
				system.setName(s);
				//这里session不能关闭
				system.setChildren(findSubSystemNames(req,s));
				if(req.getFlag().trim().equals("Yes")){
					if(s.trim().equals(findSys)){
						system.setOpen(true);
					}
				}
				systems.add(system);
			}
			session.close();
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		tree.setName(systems);
		return tree;
	}

	private  List<SubSystemName> findSubSystemNames(
			ReqshowOneStationsubComponent req,String system) {
		// TODO Auto-generated method stub
		List<SubSystemName> subsList=new ArrayList<SubSystemName>();
		String[] falg=new String[4];
		Session session = getSession();
		session.clear();
		try{
			String findsubsystemname="select DISTINCT new Equipmenttreeinfo(e.component,e.subsystemname) from Equipmenttreeinfo e where e.subcomponentid=?";
			Query query = session.createQuery(findsubsystemname);
			query.setString(0, req.getSubComponentId());
			@SuppressWarnings("unchecked")
			Iterator<Equipmenttreeinfo> it = query.list().iterator();
			
			while(it.hasNext()){
				Equipmenttreeinfo e = it.next();
				falg[0]=e.getSubsystemname();
				falg[1]=e.getComponent();
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		falg[2]=req.getStationName();
		falg[3]=req.getSubComponentId();
		try{
			String findsubsys="select DISTINCT e.subsystemname from Equipmenttreeinfo e " +
					" where e.stationName=? and e.systemname=?";
			Query query_1 = session.createQuery(findsubsys);
			query_1.setString(0, req.getStationName());
			query_1.setString(1, system);
			@SuppressWarnings("unchecked")
			Iterator<String> it = query_1.list().iterator();
		
			while(it.hasNext()){
				SubSystemName sub=new SubSystemName();
				String ep = it.next();
				sub.setName(ep);
				sub.setChildren(findcomponents(req,falg,ep));
				if(req.getFlag().trim().equals("Yes")){
					if(falg!=null){
						if(falg[0].trim().equals(ep.trim())){
							sub.setOpen(true);
						}
					}
				}
				subsList.add(sub);
			}
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return subsList;
	}
//session could'nt close
	private  List<Component> findcomponents(ReqshowOneStationsubComponent req,String[] falg,String subsystenName) {
		// TODO Auto-generated method stub
		List<Component> comList=new ArrayList<Component>();
		Session session = getSession();
		session.clear();
		try{
			String findcoms="select DISTINCT new Equipmenttreeinfo(e.component,e.subsystemname) from Equipmenttreeinfo e where e.stationName=? and e.subsystemname=? ";
			Query query = session.createQuery(findcoms);
			query.setString(0, falg[2]);
			query.setString(1, subsystenName);
			System.out.println(findcoms);
			System.out.println(falg[2]+"     "+subsystenName);
			@SuppressWarnings("unchecked")
			Iterator<Equipmenttreeinfo> it = query.list().iterator();
			
			while(it.hasNext()){
				Component com=new Component();
				Equipmenttreeinfo e = it.next();
				com.setName(e.getComponent());
				com.setChildren(findsubcomponents(req,falg,e.getComponent(),e.getSubsystemname()));
				if(req.getFlag().trim().equals("Yes")){
					if(e.getComponent().trim().equals(falg[1])){
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
//这个session不要关
	private  List<SubComponent> findsubcomponents(ReqshowOneStationsubComponent req,String[] falg,String component,String subSys) {
		// TODO Auto-generated method stub
		List<SubComponent> subList=new ArrayList<SubComponent>();
		Session session = getSession();
		session.clear();
		try{
			String findsub="select DISTINCT subcomponent,subcomponentid  from EquipmentTreeinfo where stationName=? and component=? and subsystemname=?";
			Query query = session.createSQLQuery(findsub);
			query.setString(0, falg[2]);
			query.setString(1, component);
			query.setString(2, subSys);
			@SuppressWarnings("unchecked")
			Iterator<Object[]> it = query.list().iterator();
			while(it.hasNext()){
				SubComponent sub=new SubComponent();
				Object[] e = it.next();
				sub.setName(e[0].toString());
				if(req.getFlag().trim().equals("Yes")){
					if(falg[3].trim().equals(e[1].toString().trim())){
						Font f=new Font();
						f.setColor("red");
						sub.setFont(JSONObject.fromObject(f));
					}
				}
				subList.add(sub);
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return subList;
	}
}

