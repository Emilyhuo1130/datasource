package com.ucs.rcm.ztree.strategyDesign;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.business.bo.BaseHibernateDAO;
import com.ucs.rcm.pojo.Equipmenttree;
import com.ucs.rcm.pojo.Faultinfo;
import com.ucs.rcm.reqobj.ReqgetFaultTree;
/**故障树*/
public class ZTreegetFault extends BaseHibernateDAO {
	static Logger log = Logger.getLogger(ZTreegetFault.class);
	public ResFaultZTree getFaultTree(ReqgetFaultTree req){
		ResFaultZTree faultTree=new ResFaultZTree();
		List<FaultSystemName> systenNameList=new ArrayList<FaultSystemName>();
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
				FaultSystemName systemname=new FaultSystemName();
				String s = its.next();
				systemname.setChildren(findEquipments(s,req));
				systemname.setName(s);
				//if((req.getSystemName()!=null)&&(req.getSystemName().trim().length()>0)){
					if(s.equals(req.getSystemName())){
						systemname.setOpen(true);
				//	}
					
				}
				
				systenNameList.add(systemname);
			}
			
			faultTree.setName(systenNameList);
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}finally{
			session.close();
		}
		return faultTree;
		
	}
	
//session shuld'nt close
	private  List<FaultEquipments> findEquipments(String systemname,ReqgetFaultTree req){
	
		List<FaultEquipments> equipmentsList=new ArrayList<FaultEquipments>();
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
			Query query_1 = session.createQuery(findEquipments);
			Iterator<String> its = query_1.list().iterator();
			while(its.hasNext()){
				FaultEquipments equipments=new FaultEquipments();
				//返回Components 设备树
				String ep = its.next();
				equipments.setChildren(findComponents(systemname,ep,req));
				equipments.setName(ep);
				if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
					if(eq!=null){
						if(eq.equals(ep)){
							equipments.setOpen(true);
						}
						
					}
					
					
				}
				equipmentsList.add(equipments);
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return equipmentsList;
	}
	//session shuld'nt close
	public  List<FaultComponent> findComponents(String systemName,String equipment,ReqgetFaultTree req){
		
		List<FaultComponent> ComponentList=new ArrayList<FaultComponent>();
		Session session = getSession();
		session.clear();
		try{
			String findComponents="select DISTINCT new Equipmenttree(e.id,e.component) from Equipmenttree e where e.systemname=? and e.equipment=?";
			Query query = session.createQuery(findComponents);
			query.setString(0, systemName);
			query.setString(1, equipment);
			Iterator<Equipmenttree> it = query.list().iterator();
				while(it.hasNext()){
					FaultComponent component=new FaultComponent();
					//设备详情
					Equipmenttree ep = it.next();
					component.setChildren(findsubcomponent(ep.getId().toString(),req));
					component.setName(ep.getComponent());
					if((req.getEquipmentName()!=null)&&(req.getEquipmentName().trim().length()>0)){
						if(ep.getComponent().equals(req.getEquipmentName())){
							component.setOpen(true);
						}
						
					}
					
					ComponentList.add(component);
				}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return ComponentList;
	}

//session shuld'nt close
	private  List<FaultSub> findsubcomponent(String componentid,
			ReqgetFaultTree req) {
	
		List<FaultSub> subList=new ArrayList<FaultSub>();
		Session session = getSession();
		session.clear();
		try{
			String findsubcomponent="select DISTINCT new Faultinfo(f.componentid, f.subcomponent) from Faultinfo f where f.componentid= '"+componentid+"'";
			Query query = session.createQuery(findsubcomponent);
			@SuppressWarnings("unchecked")
			Iterator<Faultinfo> it = query.list().iterator();
			while(it.hasNext()){
				FaultSub sub=new FaultSub();
				Faultinfo f = it.next();
				sub.setName(f.getSubcomponent());
				sub.setChildren(findfaultType(f.getSubcomponent(),req));
				sub.setOpen(true);
				subList.add(sub);
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return subList;
	}

//session shuld'nt shutdown
	private  List<FaultType> findfaultType(String subcomponent,ReqgetFaultTree req) {
		// TODO Auto-generated method stub
	
		List<FaultType> typeList=new ArrayList<FaultType>();
		Session session = getSession();
		session.clear();
		try{
				String findPointsByFaulttype="select DISTINCT f.faulttype from Faultinfo f where f.subcomponent=?";
				Query query = session.createQuery(findPointsByFaulttype);
				query.setString(0, subcomponent);
				Iterator<String> it = query.list().iterator();
				while(it.hasNext()){
					FaultType type=new FaultType();
					String fp = it.next();
					type.setName(fp);
					type.setOpen(true);
					type.setChildren(findFaultCase(fp,subcomponent,req));
					if((req.getFaulttype()!=null)&&(req.getFaulttype().trim().length()>0)){
							if(req.getFaulttype().equals(fp)){
								type.setOpen(true);
							}
					}
					typeList.add(type);
				}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return typeList;
	}

//session shuld'nt shutdown
	private  List<FaultCase> findFaultCase(String faulttype,String subcomponent,ReqgetFaultTree req) {
		// TODO Auto-generated method stub
	
		List<FaultCase> caseList= new ArrayList<FaultCase>();
		Session session = getSession();
		session.clear();
		try{
			String findcase="select DISTINCT new Faultinfo(f.faultCause ,f.id) from Faultinfo f where f.faulttype='"+faulttype+"' and f.subcomponent='"+subcomponent+"'";
			Query query = session.createQuery(findcase);
			@SuppressWarnings("unchecked")
			Iterator<Faultinfo> it = query.list().iterator();
			while(it.hasNext()){
				FaultCase fcase=new FaultCase();
				Faultinfo f = it.next();
				fcase.setName(f.getFaultCause());
				fcase.setClick("showFaultInfo("+f.getId()+");");
				caseList.add(fcase);
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return caseList;
	}


	
}
