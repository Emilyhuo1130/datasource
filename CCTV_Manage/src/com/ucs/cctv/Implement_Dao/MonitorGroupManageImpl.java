package com.ucs.cctv.Implement_Dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

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
import com.ucs.cctv.Interface_Dao.MonitorGroupManage;
import com.ucs.cctv.Pojo.CameraInfo;
import com.ucs.cctv.Pojo.MonitorGroup;
import com.ucs.cctv.Response.GroupResponse;
@Repository("MonitorGroupManageImpl")
public class MonitorGroupManageImpl extends HibernateDaoSupport implements MonitorGroupManage {
	static Logger log = Logger.getLogger(MonitorGroupManageImpl.class);
	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	Gson gson = new Gson();
	
	
	//查询所有的监控组
	public GroupResponse findAllMonitorGroup(String monitorName,
			final int pageSize, final int page) {
		GroupResponse response = new GroupResponse();
		StringBuffer buf=new StringBuffer();
		buf.append(" from MonitorGroup where 1=1 ");
		if((monitorName!=null)&&(monitorName.length()>0)){
			buf.append(" and groupName like '%"+monitorName+"%' ");
		}
		final String hql=buf.toString();
		List list = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				//int count=(page-1)*pageSize+1;
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
				List<MonitorGroup> list=query.list();
				return list;
			}});
		response.setMonitorGroup(list);
		response.setPages(MonitorGroupPages(pageSize, monitorName));
		return response;
	}

	//添加监控组
	public Boolean addMonitorGroup(MonitorGroup monitorgroup) {
		Iterator<CameraInfo> iterator = monitorgroup.getCameraMerbers().iterator();
		while(iterator.hasNext()){
			CameraInfo cameraInfo = iterator.next();
			log.info("迭代出摄像机信息"+gson.toJson(cameraInfo.getCameraName()+","+cameraInfo.getCameraIp()+","+cameraInfo.getCameraId()));
			
		}
		getHibernateTemplate().merge(monitorgroup);
		return true;
	}

	public Boolean updateMonitorGroup(MonitorGroup monitorgroup) {
		getHibernateTemplate().update(monitorgroup);
		return true;
	}

	public boolean deleteMonitorGroup(int id) {
		if(findOperatorbyId(id).getGroupId()!=0){
			getHibernateTemplate().delete(findOperatorbyId(id));
		}
		return true;
	}
	public MonitorGroup findOperatorbyId(int id){
		MonitorGroup info=getHibernateTemplate().get(MonitorGroup.class, id);
		return info;
	}

	public int MonitorGroupPages(int pageSize, String monitorName) {
		String hql="select count(*) from MonitorGroup where 1=1 ";
		if((monitorName!=null)&&(monitorName.length()>0)){
			hql=hql+" and groupName like '%"+monitorName+"%' ";
		}
		List<Object> list=getHibernateTemplate().find(hql);
		if(list.size()==0){
			return 0;
		}else{
		     Object obj=(Object)list.get(0);
		     int totalRows=Integer.parseInt(obj.toString());
		     if (totalRows % pageSize == 0) {
                return totalRows / pageSize;
             } else {
                return totalRows / pageSize + 1;
                }
		     }
		}

	public MonitorGroup findMonitorbyId(int id) {
		log.info("******getHibernateTemplate1=*****"+getHibernateTemplate());
		MonitorGroup info=getHibernateTemplate().get(MonitorGroup.class, id);
		return info;
	};

}
