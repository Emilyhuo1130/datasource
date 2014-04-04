package com.ucs.rcm.business.bo;

import java.util.Iterator;


import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.reqobj.ReqfindFaultInfoByID;
import com.ucs.rcm.reqobj.RequpdateFaultInfo;
import com.ucs.rcm.responseObj.FaultType;
import com.ucs.rcm.responseObj.ResInfo;

/**策略定制和影响分析放一块*/
public class Strategybo extends BaseHibernateDAO {
	static Logger log = Logger.getLogger(Strategybo.class);
	public  ResInfo updateFaultInfo(RequpdateFaultInfo req) {
		// TODO Auto-generated method stub
		String sql=null;
		if((req.getInfluence()!=null)&&(req.getInfluence().trim().length()>0)){
			//更新影响分析
			sql=updateInfluence(req);
		}else{
			//更新策略定制
			sql=getHqls(req);
		}
		log.warn(sql);
		ResInfo info=new ResInfo();
		Session session = getSession();
		try{
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			 query.executeUpdate();
			info.setInfo("sueecss");
			tx.commit();
			return info;
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		info.setInfo("false");
		return info;
	}

	private  String updateInfluence(RequpdateFaultInfo req) {
		// TODO Auto-generated method stub
		
		String findtypeById="select f.equipmentname from Faultinfo f where f.id="+req.getId();
		String equipmentname=null;
		try{
		Query query = getSession().createQuery(findtypeById);
		@SuppressWarnings("unchecked")
		Iterator<String> it = query.list().iterator();
			while(it.hasNext()){
				equipmentname=it.next();
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		String update="update Faultinfo f set f.influence='"+req.getInfluence()+"'"+"where f.equipmentname= '"+equipmentname+"'";
		return update;
	}

	private static String getsqls(RequpdateFaultInfo req) {
		// TODO Auto-generated method stub
		StringBuffer buf=new StringBuffer();
		buf.append("update faultinfo set ");
		if((req.getFultDescription()!=null)&&(req.getFultDescription().trim().length()>0)){
			buf.append("faulttype = '"+req.getFultDescription()+"' ");
		}
		if((req.getInfluence()!=null)&&(req.getInfluence().trim().length()>0)){
			buf.append(",Influence = '"+req.getInfluence()+"' ");
		}
		if((req.getFaultCause()!=null)&&(req.getFaultCause().trim().length()>0)){
			buf.append(", FaultCause= '"+req.getFaultCause()+"' ");
		}
		if((req.getMaintenancePolicy()!=null)&&(req.getMaintenancePolicy().trim().length()>0)){
			buf.append(", MaintenancePolicy= '"+req.getMaintenancePolicy()+"' ");
		}
		buf.append(" where id="+req.getId());
		
		return buf.toString();
	}
    //封装hql语句
	@SuppressWarnings("unused")
	private static String getHqls(RequpdateFaultInfo req){
		
		// TODO Auto-generated method stub
		StringBuffer buf=new StringBuffer();
		buf.append("update Faultinfo f set ");
		if((req.getFultDescription()!=null)&&(req.getFultDescription().trim().length()>0)){
			buf.append("f.faulttype = '"+req.getFultDescription()+"' ");
		}
		if((req.getInfluence()!=null)&&(req.getInfluence().trim().length()>0)){
			buf.append(",f.influence = '"+req.getInfluence()+"' ");
		}
		if((req.getFaultCause()!=null)&&(req.getFaultCause().trim().length()>0)){
			buf.append(", f.faultCause= '"+req.getFaultCause()+"' ");
		}
		if((req.getMaintenancePolicy()!=null)&&(req.getMaintenancePolicy().trim().length()>0)){
			buf.append(", f.maintenancePolicy= '"+req.getMaintenancePolicy()+"' ");
		}
		buf.append(" where f.id="+req.getId());
		
		return buf.toString();
	};
	
	public FaultType findFaultInfoByFaultType(
			ReqfindFaultInfoByID req) {
		// TODO Auto-generated method stub
		String sql="from Faultinfo  f where f.id=? ";
		FaultType faultType =new FaultType();
		try{
		Query query = getSession().createQuery(sql);
		query.setInteger(0, req.getId());
		Iterator<FaultType> it  = query.list().iterator();
			while(it.hasNext()){
				faultType = it.next();
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return faultType;
	}

}
