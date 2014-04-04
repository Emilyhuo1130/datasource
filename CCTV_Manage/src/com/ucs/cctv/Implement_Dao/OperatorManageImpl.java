package com.ucs.cctv.Implement_Dao;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.ucs.cctv.Interface_Dao.OperatorManage;
import com.ucs.cctv.Pojo.CameraInfo;
import com.ucs.cctv.Pojo.MonitorGroup;
import com.ucs.cctv.Pojo.OperatorInfo;
import com.ucs.cctv.Response.OperatorResponse;
@Repository("OperatorManageImpl")
public class OperatorManageImpl extends HibernateDaoSupport implements OperatorManage{
	static Logger log = Logger.getLogger(OperatorManageImpl.class);
	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	Gson gson = new Gson();
	public OperatorResponse findAllOperators(String operatorName,
			String operatorAccount, String level, final int pageSize, final int page) {
		OperatorResponse response = new OperatorResponse();
		// TODO Auto-generated method stub
		StringBuffer buf=new StringBuffer();
		buf.append(" from OperatorInfo where 1=1 ");
		if((operatorName!=null)&&(operatorName.length()>0)){
			buf.append(" and operatorName like '%"+operatorName+"%' ");
		}
		if((operatorAccount!=null)&&(operatorAccount.length()>0)){
			buf.append(" and operatorAccount like '%"+operatorAccount+"%' ");
		}
		if((level!=null)&&(level.length()>0)){
			buf.append(" and operatorLevel like '%"+level+"%' ");
		}
		final String hql=buf.toString();
		List list = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				//int count=(page-1)*pageSize+1;
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
				List<OperatorInfo> list=query.list();
				return list;
			}});
		response.setOperatorInfo(list);
		response.setPages(operatorPages(pageSize,operatorName,operatorAccount,level));
		return response;
	}

	public Boolean updateOperatorInfo(OperatorInfo operator) {
		log.info(operator.getOperatorName());
		getHibernateTemplate().merge(operator);
		return true;
	}

	public Boolean addOperator(OperatorInfo operator) {
		getHibernateTemplate().save(operator);
		return true;
	}

	public Boolean deleteOperator(int id) {
		if(findOperatorbyId(id).getOperatorId()!=0){
			getHibernateTemplate().delete(findOperatorbyId(id));
		}
		return true;
	}

	public int operatorPages(int pageSize, String operatorName,
			String operatorAccount, String level) {
		String hql="select count(*) from OperatorInfo where 1=1 ";
		if((operatorName!=null)&&(operatorName.length()>0)){
			hql=hql+" and operatorName like '%"+operatorName+"%' ";
		}
		if((level!=null)&&(level.length()>0)){
			hql=hql+" and operatorLevel like '%"+level+"%' ";
		}
		if((operatorAccount!=null)&&(operatorAccount.length()>0)){
			hql=hql+" and operatorAccount like '%"+operatorAccount+"%' ";
		}
		List<Object> list=this.getHibernateTemplate().find(hql);
		if(list.size()==0){
			return 0;
		}else{
		     Object obj=(Object)list.get(0);
		     int totalRows=Integer.parseInt(obj.toString());
		     if (totalRows % pageSize == 0) {
                return totalRows/pageSize;
                } else {
                return totalRows/pageSize + 1;
                }
		     }
		}
	
	public OperatorInfo findOperatorbyId(int id){
		log.info("****operatormanageImpl********");
		OperatorInfo info=getHibernateTemplate().get(OperatorInfo.class, id);
		log.info("****info******"+info);
		log.info("****info.getMonitorGroups******"+info.getMonitorGroups()==null?"为空":info.getMonitorGroups());
		Set<String> groupNames = new HashSet<String>();
		Iterator<MonitorGroup> iterator = info.getMonitorGroups().iterator();
		while (iterator.hasNext()) {
			MonitorGroup	 groupName = (MonitorGroup) iterator.next();
			groupNames.add(groupName.getGroupName());
			log.info("*****************groupName="+groupName);
			
		}
		info.setMonitorGroups(null);//多对多注意出现嵌套死循环，在可能出现死循环的属性中设为空值返回。
		info.setMonitorGroupNames(groupNames);
		
		return info;
	}

	public List<CameraInfo> findCamerabyOperator(String operatorName) {
		String hql = "select distinct c from CameraInfo c join c.monitorGroups g join g.operators o where o.operatorName=?";
		List<CameraInfo> list=this.getHibernateTemplate().find(hql,operatorName);
		return list;
	};

	
	//根据名称查找监控组
	public MonitorGroup findMonitorbyName(String name) {
		String hql = "from MonitorGroup where groupName=?";
		@SuppressWarnings("unchecked")
		List<MonitorGroup> monitorGroups= getHibernateTemplate().find(hql,name);
		log.info("******monitorGroups!=null*******"+monitorGroups!=null);
		
		if(monitorGroups.size()>0){
			return monitorGroups.get(0);
		}else{
			return null;
		}
	};
	
}
