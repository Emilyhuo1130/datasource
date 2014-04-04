
package com.ucs.rcm.ztree.warningFaultTree;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import net.sf.json.JSONObject;
import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.business.bo.BaseHibernateDAO;
import com.ucs.rcm.db.DBUtil;
import com.ucs.rcm.pojo.Equipmenttree;
import com.ucs.rcm.pojo.Equipmenttreeinfo;
import com.ucs.rcm.pojo.Faultinfo;
import com.ucs.rcm.reqobj.ReqgetstationFaultTree;
import com.ucs.rcm.ztree.warningEquipmentTree.Font;

public class GetStationFaultTree extends BaseHibernateDAO{
	static Logger log = Logger.getLogger(GetStationFaultTree.class);
	private static  String faultCause=null;
	Gson gson = new Gson();
	public static String getFaultCause() {
		return faultCause;
	}

	public static void setFaultCause(String faultCause) {
		GetStationFaultTree.faultCause = faultCause;
	}

	
	public ResFaultList getstationFaultTree(ReqgetstationFaultTree req){
		
		/***从设备树找到设备的资产名称  转辙机==》道岔***/
		
		try{
			Session session = getSession();
			session.clear();
			String findequipment="from Equipmenttreeinfo e where e.subcomponentid=?";
			Equipmenttreeinfo e = (Equipmenttreeinfo)session.createQuery(findequipment).setString(0, req.getEquipmentId()).uniqueResult();
			//log.info("********Equipmenttreeinfo e="+gson.toJson(e)+"********************");
			req.setEquipmentName(e.getComponent());
			session.close();
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		
		//log.warn("******************请求参数=*******************"+gson.toJson(req));
		if(req.getUser().trim().equals("user2")){
		}else{
			req.setFlag("Yes");
		}
		
		try{
			Session session = getSession();
			session.clear();
			String findfault="select DISTINCT f.faultCause from FaultinfoUser2 f where f.equipmentid= '"+req.getEquipmentId()+"'";
			Query query = session.createQuery(findfault);
			@SuppressWarnings("unchecked")
			Iterator<String> it = query.list().iterator();
			while(it.hasNext()){
				
				GetStationFaultTree.setFaultCause(it.next());
			}
			session.close();
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		//10.29.14.101.001  ==>  14.101
		String str1=req.getEquipmentId();
		String str2=str1.replace(".", ",");
		String[] ss=str2.split(",");
		String str3=ss[2]+"."+ss[3];
		req.setEquipmentId(str3);
				
		ResFaultList faultopen=new ResFaultList();
		List<SystemName> sysList=new ArrayList<SystemName>();
		
		String[] str=new String[2];
		try{
			Session session = getSession();
			session.clear();
			String findsysand="select distinct  new Equipmenttree(e.systemname , e.equipment) from Equipmenttree e where e.component=?";
			Query query_1 = session.createQuery(findsysand).setString(0, req.getEquipmentName());
			@SuppressWarnings("unchecked")
			Iterator<Equipmenttree> its = query_1.list().iterator();
			while(its.hasNext()){
				Equipmenttree e= its.next();
				str[0]=e.getSystemname();
				str[1]=e.getEquipment();
			}
			session.close();
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		
		
		try{
			Session session = getSession();
			session.clear();
			String findsys="select DISTINCT e.systemname from  Equipmenttree e ";
			Query query_2 = session.createQuery(findsys);
			@SuppressWarnings("unchecked")
			Iterator<String> it = query_2.list().iterator();
			while(it.hasNext()){
				String s = it.next();
				SystemName sys=new SystemName();
				sys.setName(s);
				sys.setChildren(findsubsysName(s,str,req));
				if(req.getFlag().trim().equals("Yes")){
					if(s.trim().equals(str[0])){
						sys.setOpen(true);
					}
				}
				sysList.add(sys);
			}
			session.close();
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		
		faultopen.setName(sysList);
		return faultopen;
	}

	//这个session不要关闭
	private  List<SubSystemName> findsubsysName(String sysName,
			String[] str, ReqgetstationFaultTree req) {
		List<SubSystemName> subList=new ArrayList<SubSystemName>();
		
		Session session = getSession();
		session.clear();
		
		try{
			String findsubSys="select DISTINCT e.equipment from Equipmenttree e where e.systemname= '"+sysName+"' ";
			Query query = session.createQuery(findsubSys);
			@SuppressWarnings("unchecked")
			List<String> equipments = query.list();
			Iterator<String> it = equipments.iterator();
			while(it.hasNext()){
				SubSystemName subsys=new SubSystemName();
				String ep = it.next();
				subsys.setName(ep);
				subsys.setChildren(findcomponents(ep,req));
				if(req.getFlag().trim().equals("Yes")){
					if(ep.trim().equals(str[1])){
						subsys.setOpen(true);
					}
				}
				subList.add(subsys);
			}
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		
		return subList;
	}

	private  List<Component> findcomponents(String subsys,
			ReqgetstationFaultTree req) {
		List<Component> comList=new ArrayList<Component>();
		Session session = getSession();
		session.clear();
		try{
			String findcoms="select DISTINCT e.component from Equipmenttree e where e.equipment= '"+subsys+"' ";
			Query query = session.createQuery(findcoms);
			@SuppressWarnings("unchecked")
			List<String> equipments = query.list();
			Iterator<String> it = equipments.iterator();
			while(it.hasNext()){
				Component com=new Component();
				String co = it.next();
				com.setName(co);
				com.setChildren(findsubcomponents(co,req));
				if(req.getFlag().trim().equals("Yes")){
					if(co.equals(req.getEquipmentName())){
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

	private  List<SubComponent> findsubcomponents(String component,ReqgetstationFaultTree req) {
		List<SubComponent> subList=new ArrayList<SubComponent>();
		Session session  = getSession();
		session.clear();
		try{
			//String find="select distinct new Faultinfo(f.id , f.equipmentid , f.subcomponent) from Faultinfo f where f.equipmentname= '"+component+"' ";
			String find="select DISTINCT f.subcomponent,f.equipmentid from Faultinfo  f  where f.equipmentname= '"+component+"' ";
			Query query = session.createQuery(find);
			@SuppressWarnings("unchecked")
			List<Object[]> fo = query.list();
			log.info("treenode1="+gson.toJson(fo));
			Iterator<Object[]> it = fo.iterator();
			while(it.hasNext()){
				SubComponent sub=new SubComponent();
				Object[] ob = it.next();
				sub.setName(ob[0].toString());
				if(req.getFlag().trim().equals("Yes")){
					if(ob[1].toString().trim().equals(req.getEquipmentId())){
						sub.setOpen(true);
						Font ft=new Font();
						ft.setColor("red");
						sub.setFont(JSONObject.fromObject(ft));
					}
				}
				
				sub.setChildren(findfaultTypes(ob[0].toString(),req));
				subList.add(sub);
			}
			
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return subList;
	}

	//sessoin could'nt shutdown
	private  List<FaultType> findfaultTypes(String subcomponent,ReqgetstationFaultTree req) {
		List<FaultType> faultList= new ArrayList<FaultType>();
		Session session  = getSession();
		session.clear();
		try{
			String findfaule="select distinct new Faultinfo(f.faultCause,f.id)  from Faultinfo f where f.subcomponent= '"+subcomponent+"' ";
			Query query = session.createQuery(findfaule);
			@SuppressWarnings("unchecked")
			List<Faultinfo> fo = query.list();
			log.info("treeminnode="+gson.toJson(fo));
			Iterator<Faultinfo> it = fo.iterator();
			while(it.hasNext()){
				FaultType fault=new FaultType();
				Faultinfo f = it.next();
				fault.setName(f.getFaultCause());
				//fault.setClick("showFaultInfo("+rs.getInt("id")+");");
				fault.setValue(f.getId());
				/***user2的故障原因锁定*/
				if(req.getFlag().trim().equals("Yes")){
					if((req.getFaultType()!=null)&&(req.getFaultType().trim().length()>0)){
						if(f.getFaultCause().trim().equals(req.getFaultType().trim())){
							Font ft=new Font();
							ft.setColor("blue");
							fault.setFont(ft);
						}
					}
				}
				
				
				/**user3,4故障原因锁定**/
				if((req.getFlag().trim().equals("Yes"))){
					if((GetStationFaultTree.getFaultCause()!=null)){
						if(f.getFaultCause().trim().equals(GetStationFaultTree.getFaultCause().trim())){
							Font ft=new Font();
							ft.setColor("blue");
							fault.setFont(ft);
							
						}
					}
				}
				faultList.add(fault);
			}
			
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return faultList;
	}
}
