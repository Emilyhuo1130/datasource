package com.ucs.cctv.Implement_Dao;

import java.sql.SQLException;
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

import com.ucs.cctv.Interface_Dao.RecorderManage;
import com.ucs.cctv.Pojo.RecorderInfo;
import com.ucs.cctv.Response.RecorderResponse;
@Repository("RecorderManageImpl")
public class RecorderManageImpl extends HibernateDaoSupport implements RecorderManage{
	static Logger log = Logger.getLogger(CameraManageImpl.class);
	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	
	
	public RecorderResponse findAllRecorders(String recorderName,
			String recorderType, final int pageSize, final int page) {
		RecorderResponse response = new RecorderResponse();
		StringBuffer buf=new StringBuffer();
		buf.append(" from RecorderInfo where 1=1 ");
		if((recorderName!=null)&&(recorderName.length()>0)){
			buf.append(" and recorderName like '%"+recorderName+"%' ");
		}
		if((recorderType!=null)&&(recorderType.length()>0)){
			buf.append(" and recorderModel like '%"+recorderType+"%' ");
		}
		final String hql=buf.toString();
		List list = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				//int count=(page-1)*pageSize+1;
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
				List<RecorderInfo> list=query.list();
				return list;
			}});
			response.setRecorderInfo(list);
			response.setPages(recorderPages(pageSize, recorderName,recorderType));
			return response;
	}

	public Boolean updateRecorderInfo(RecorderInfo recorder) {
		getHibernateTemplate().update(recorder);
		return true;
	}

	public Boolean addRecorder(RecorderInfo recorder) {
		getHibernateTemplate().save(recorder);
		return true;
	}

	public int recorderPages(int pageSize, String recorderName,
			String recorderType) {
		String hql="select count(*) from RecorderInfo where 1=1 ";
		if((recorderName!=null)&&(recorderName.length()>0)){
			hql=hql+" and recorderName like '%"+recorderName+"%' ";
		}
		if((recorderType!=null)&&(recorderType.length()>0)){
			hql=hql+" and recorderType like '%"+recorderType+"%' ";
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

	public RecorderInfo findRecorderbyId(int id) {
		RecorderInfo info=getHibernateTemplate().get(RecorderInfo.class, id);
		return info;
	}
	//验证ip
	public Boolean verifyIP(String ip) {
		String hql = "from RecorderInfo where recorderIp=?";
		List<RecorderInfo> list = getHibernateTemplate().find(hql,ip);
		if(list.size()==0){
			return false;
		}else{
			return true;
		}
	}


	public boolean deleteRecorder(final int id) {
			final String hql = "delete from RecorderInfo where recorderId=:id";
			@SuppressWarnings({ "unchecked" , "rawtypes" })
			boolean deleteRecorderInfo = getHibernateTemplate().execute(new HibernateCallback() {

				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
						try {
							log.info("********************"+hql);
							session.createQuery(hql).setInteger("id", id).executeUpdate();
						} catch (Exception e) {
							log.info("删除摄像机失败");
							e.printStackTrace();
						}
					return true;
				}
			});
			return deleteRecorderInfo;
		}

	}

