package com.ucs.rcm.ztree.strategyDesign;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gson.Gson;
import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.business.bo.BaseHibernateDAO;
import com.ucs.rcm.pojo.Equipmenttree;
import com.ucs.rcm.readxelInfoUtils.EquipmentInfo;
import com.ucs.rcm.reqobj.ReqgetEquipmentTree;


/**实件分析控制层*/
public class ZTreegetEquipment extends BaseHibernateDAO {
	static Logger log = Logger.getLogger(ZTreegetEquipment.class);
	/**设备树-ZTree*/
	public  ResZTree getEquipmentTree(ReqgetEquipmentTree req){
		
		ResZTree EquipmentTree=new ResZTree();
		List<SystemName> systenNameList=new ArrayList<SystemName>();
		Session session = getSession();
		session.clear();
		try{
			
				String findsystenNameByequipmentName="select DISTINCT e.systemname from Equipmenttree e where e.component= '"+req.getEquipmentName()+"'";
				Query query = session.createQuery(findsystenNameByequipmentName);
				Iterator<String> it = query.list().iterator();
					while(it.hasNext()){
						req.setSystemName(it.next());
					}
			String findsystenName="select DISTINCT e.systemname from Equipmenttree e";
			Query query_1 = session.createQuery(findsystenName);
			Iterator<String> its = query_1.list().iterator();
			
			while(its.hasNext()){
				SystemName systemname=new SystemName();
				//返回设备树
				String s = its.next();
				systemname.setChildren(findEquipments(s,req));
				systemname.setName(s);
				
				//if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
					if(s.equals(req.getSystemName())){
						systemname.setOpen(true);
					}
					
				//}
				systenNameList.add(systemname);
			}
			
			EquipmentTree.setName(systenNameList);
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
		}
		return EquipmentTree;
		
	}
	

	private  List<Equipments> findEquipments(String systemname,ReqgetEquipmentTree req){
		List<Equipments> equipmentsList=new ArrayList<Equipments>();
		String eq=null;
		Session session = getSession();
		session.clear();
		try{
			
			if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
				String findEquipmentBySubcom="select DISTINCT e.equipment from Equipmenttree e where e.component='"+req.getEquipmentName()+"'";
				Query query = session.createQuery(findEquipmentBySubcom);
				Iterator<String> it = query.list().iterator();
				while(it.hasNext()){
					eq=it.next();
				}
				
			}
			
			
			String findEquipments="select DISTINCT e.equipment from Equipmenttree e where e.systemname='"+systemname+"'";
			Query  query_1 = session.createQuery(findEquipments);
			Iterator<String> its = query_1.list().iterator();
			
			while(its.hasNext()){
				Equipments equipments=new Equipments();
				//返回Components 设备树
				String ep = its.next();
				equipments.setChildren(findComponents(systemname,ep,req));
				equipments.setName(ep);
				if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
					if(eq.equals(ep)){
						equipments.setOpen(true);
					}
					
				}
				
				equipmentsList.add(equipments);
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
		}
		return equipmentsList;
	}
	
	public  List<Component> findComponents(String systemName,String equipment,ReqgetEquipmentTree req){
		List<Component> ComponentList=new ArrayList<Component>();
		Session session = getSession();
		session.clear();
		try{
			String findComponents="select DISTINCT new Equipmenttree(e.id,e.component) from Equipmenttree e  where e.systemname=? and e.equipment=?";
			Query query = session.createQuery(findComponents);
			query.setString(0, systemName);
			query.setString(1, equipment);
			Iterator<Equipmenttree> it = query.list().iterator();
				while(it.hasNext()){
					Component component=new Component();
					//设备详情
					Equipmenttree e = it.next();
					component.setChildren(findSubcomonents(e.getComponent()));
					component.setName(e.getComponent());
					if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
						
						if(e.getComponent().equals(req.getEquipmentName())){
							component.setOpen(true);
						}
						
					}
					
					
					
					ComponentList.add(component);
				}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
		}
		return ComponentList;
	}
	

	//设备树的 实体
	public  List<Sub> findSubcomonents(String component){
		
		List<Sub> subList=new ArrayList<Sub>();
		Session session = getSession();
		session.clear();
		try{
			String findSubcomponent="from Equipmentinfo e where e.equipmentname=?";
			Query query = session.createQuery(findSubcomponent);
			query.setString(0, component);
			@SuppressWarnings("unchecked")
			Iterator<EquipmentInfo> it = query.list().iterator();
			while(it.hasNext()){
				Sub s=new Sub();
				EquipmentInfo e = it.next();
				s.setName(e.getSubComponent());
				s.setClick("showEquipmentInfo("+e.getId()+");");
				subList.add(s);
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
		}
		return subList;
	}

}
