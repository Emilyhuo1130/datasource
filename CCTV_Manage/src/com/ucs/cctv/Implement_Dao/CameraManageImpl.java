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
import com.google.gson.Gson;
import com.ucs.cctv.Interface_Dao.CameraManage;
import com.ucs.cctv.Pojo.CameraInfo;
import com.ucs.cctv.Response.CameraResponse;

@Repository("CameraManageImpl")
public class CameraManageImpl extends HibernateDaoSupport implements CameraManage{
	static Logger log = Logger.getLogger(CameraManageImpl.class);
	Gson gson = new Gson();
	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }

	
	//查询所有的摄像机
	@SuppressWarnings("unchecked")
	public CameraResponse findAllCameras(String cameraName,
			String cameraType, String section,  final int pageSize, final int page) {
		CameraResponse  response = new CameraResponse();
		StringBuffer buf=new StringBuffer();
		buf.append(" from CameraInfo where 1=1 ");
		if((cameraName!=null)&&(cameraName.length()>0)){
			buf.append(" and cameraName like '%"+cameraName+"%' ");
		}
		if((cameraType!=null)&&(cameraType.length()>0)){
			buf.append(" and cameraModel like '%"+cameraType+"%' ");
		}
		if((section!=null)&&(section.length()>0)){
			buf.append(" and section like '%"+section+"%' ");
		}
		final String hql=buf.toString();
		@SuppressWarnings("rawtypes")
		List list = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				//int count=(page-1)*pageSize+1;
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
				List<CameraInfo> list=query.list();
				return list;
			}});
		response.setCameraInfo(list);
		response.setPages(cameraPages(pageSize,cameraName, cameraType,section));
		return response;
	}

	//更新一台摄像机
	public Boolean updateCameraInfo(CameraInfo camera) {
		 getHibernateTemplate().update(camera);
		return true;
	}
	//添加一台摄像机
	public Boolean addCamera(CameraInfo camera) {
		getHibernateTemplate().save(camera);
		return true;
	}

	
	public int cameraPages(int pageSize, String cameraName, String cameraType,
			String section) {
		String hql="select count(*) from CameraInfo where 1=1 ";
		if((cameraName!=null)&&(cameraName.length()>0)){
			hql=hql+" and cameraName like '%"+cameraName+"%' ";
		}
		if((cameraType!=null)&&(cameraType.length()>0)){
			hql=hql+" and cameraType like '%"+cameraType+"%' ";
		}
		if((section!=null)&&(section.length()>0)){
			hql=hql+" and section like '%"+section+"%' ";
		}
		@SuppressWarnings("unchecked")
		List<Object> list=getHibernateTemplate().find(hql);
		if(list==null){
			return 0;
		}
		else{
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
//通过id查询摄像机
	public CameraInfo findCamerabyId(int id) {
		log.info("*********************调用通过id查询摄像机的方法***********************************");
		CameraInfo info=getHibernateTemplate().get(CameraInfo.class, id);
		info.setMonitorGroups(null);
		return info;
	}
//通过名称查询摄像机
	public CameraInfo findCamerabyName(String cameraName) {
		String hql = "from CameraInfo where cameraName=?";
		@SuppressWarnings("unchecked")
		List<CameraInfo> list = getHibernateTemplate().find(hql,cameraName);
		if(list.size()!=0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
//验证ip是否存在
	public Boolean verifyIP(String ip) {
		log.info("需要验证的ip"+ip);
		String hql = "from CameraInfo where cameraIp=?";
		@SuppressWarnings("unchecked")
		List<CameraInfo> list = getHibernateTemplate().find(hql,ip);
		//log.info("*****查到的ip****"+gson.toJson(list));
		log.info("*****查到的ip的长度****"+gson.toJson(list.size()));
		
		if(list.size()==0){
			return false;
		}else{
			return true;
		}
	}

	public boolean deleteCamera(final int id) {
		final String hql = "delete from CameraInfo where cameraId=:id ";
		@SuppressWarnings({ "unchecked" , "rawtypes" })
		boolean deleteCameraInfo = getHibernateTemplate().execute(new HibernateCallback() {

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
		return deleteCameraInfo;
	}

}
