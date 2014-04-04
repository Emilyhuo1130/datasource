package com.ucs.cctv.Implement_Dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.google.gson.Gson;
import com.ucs.cctv.Interface_Dao.LogInfoManage;
import com.ucs.cctv.Pojo.OperateLog;
import com.ucs.cctv.Response.LogResponse;

@Repository("LogInfoManageImpl")
public class LogInfoManageImpl extends HibernateDaoSupport implements LogInfoManage{
	static Logger log = Logger.getLogger(LogInfoManageImpl.class);
	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	Gson gson = new Gson();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LogResponse findAllLogInfo(String operatorName,String operatorAccount, String startTime, String endTime,
			String operateType, final int pageSize, final int page) {
		LogResponse response = new LogResponse();
		StringBuffer buf=new StringBuffer();
		buf.append("from OperateLog where 1=1").append(" ");
		if((operatorName!=null)&&(operatorName.length()>0)){
			buf.append("and operatorName like '%"+operatorName+"%' ").append(" ");
		}
		if((operatorAccount!=null)&&(operatorAccount.length()>0)){
			buf.append(" and operatorAccount like '%"+operatorAccount+"%' ").append(" ");
		}
		if((operateType!=null)&&(operateType.length()>0)){
			buf.append(" and operateType like '%"+operateType+"%' ").append(" ");
		}
		if((startTime!=null)&&(startTime.length()>0)){
		    buf.append(" and operateTime >= '"+startTime+"' ").append(" ");
		}
		if((endTime!=null)&&(endTime.length()>0)){
			buf.append(" and operateTime <= '"+endTime+"' ").append(" ");
		}
		final String hql=buf.toString();
		List<OperateLog> list=null;
		try {
			log.info("getHibernateTemplate="+getHibernateTemplate());
			list = getHibernateTemplate().executeFind(new HibernateCallback(){
				public Object doInHibernate(Session session)throws HibernateException, SQLException {
					log.info("---session=----"+session);
					log.info("**********hql="+hql);
					Query query=session.createQuery(hql);
					log.info("query="+gson.toJson(query));
					query.setFirstResult((page-1)*pageSize);
					query.setMaxResults(pageSize);
					List<OperateLog> list=query.list();
					log.info("查询日志记录返回："+list);
					return list;
				}});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		response.setLogInfo(list);
		int totalPages = logPages(pageSize,operatorName,operatorAccount,startTime, endTime,operateType);
		response.setPages(totalPages);
			return response;
	}
	
	public int logPages(int pageSize, String operatorName,String operatorAccount, String startTime, 
			String endTime,String operateType){
		String hql="select count(*) from OperateLog where 1=1 ";
		if((operatorName!=null)&&(operatorName.length()>0)){
			hql=hql+" and operatorName like '%"+operatorName+"%' ";
		}
		if((operatorAccount!=null)&&(operatorAccount.length()>0)){
			hql=hql+" and operatorAccount like '%"+operatorAccount+"%' ";
		}
		if((operateType!=null)&&(operateType.length()>0)){
			hql=hql+" and operateType like '%"+operateType+"%' ";
		}
		@SuppressWarnings("unchecked")
		List<Object> list=getHibernateTemplate().find(hql);
		if(list==null){
			return 0;
		}else{
			Object obj=(Object)list.get(0);
		    int totalRows=Integer.parseInt(obj.toString());
		    if (totalRows % pageSize == 0) {
		    	return totalRows / pageSize;
		    	}
		    else {
		    	return totalRows / pageSize + 1;
		    	}
		    }
		}
	
	public boolean addLogInfo(OperateLog log) {
		log.setOperateTime(new Date());
		getHibernateTemplate().save(log);
		return true;
	}
	
	
	//根据账号名称查找用户名
	public String getOperatorNameByAccount(final String operatorAccount){
		final String sql = "select operator_Name from Operator_log where operator_Account=?";
		log.info("**********************getOperatorNameByAccount************************");
		log.info("*********getHibernateTemplate=*************"+getHibernateTemplate());
		@SuppressWarnings("unchecked")
		List<String> operatorNames = getHibernateTemplate().executeFind(new HibernateCallback<String>() {
			public String doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria query = session.createCriteria(sql);
				query.add(Restrictions.eq("operatorAccount", operatorAccount));
				List<String> userAccounts=null;
				try {
					userAccounts = query.list();
					log.info("***************query.list=************"+gson.toJson(userAccounts));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return userAccounts.get(0);
			}
			
		});
		log.info("******operatorNames.get(0)****"+operatorNames.get(0));
		return operatorNames.get(0);
	}
	
	
//将每天一次用户的操作记录到数据库中
	public  boolean insertInfoToDataBase(OperateLog operateLog) {
		log.info("*************开始记录日志********");
		try {
			getHibernateTemplate().save(operateLog);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
