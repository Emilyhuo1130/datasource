package com.ucs.cctv.Implement_Dao;

import java.sql.SQLException;
import java.util.ArrayList;
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
import com.ucs.cctv.Interface_Dao.SectionManage;
import com.ucs.cctv.Pojo.SectionInfo;
import com.ucs.cctv.Response.SectionResponse;

@Repository("SectionManageImpl")
public class SectionManageImpl extends HibernateDaoSupport implements SectionManage {
	static Logger log = Logger.getLogger(SectionManageImpl.class);
	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	
	//添加区位
	public Boolean addSection(SectionInfo section) {
		getHibernateTemplate().save(section);
		return true;
	}
//删除区位
	public Boolean deleteSection(int id) {
		if(findSectionById(id).getSectionId()!=0){
			getHibernateTemplate().delete(findSectionById(id));
		}
		return true;
	}

	//更新区位
	public Boolean updateSection(SectionInfo section) {
		getHibernateTemplate().update(section);
		return true;
	}

	//查询区位
	public SectionResponse findAllSections(String sectionName , final int pageSize, final int  page) {
		final String hql = "from SectionInfo where 1=1 and sectionName like '%"+sectionName+"%'";
		SectionResponse response = new SectionResponse();
		List list = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
				@SuppressWarnings("unchecked")
				List<SectionInfo> list=query.list();
				return list;
			}});
		response.setSectionInfo(list);
		response.setPages(sectionPages(pageSize , sectionName));
		return response;
	}

	//根据id查询区位
	public SectionInfo findSectionById(int id) {
		SectionInfo info=getHibernateTemplate().get(SectionInfo.class, id);
		return info;
	}
	
	
	//查询记录页数
	public int sectionPages(int pageSize,String sectionName){
		String hql="select count(*) from SectionInfo where 1=1 ";
		if((sectionName!=null)&&(sectionName.length()>0)){
			hql=hql+" and sectionName like '%"+sectionName+"%' ";
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
	//初始化查询所有区位名称

	public List<String> findAllSectionName() {
		final String hql = "from SectionInfo where 1=1 ";
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<String> sectionNames = getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				@SuppressWarnings("unchecked")
				List<SectionInfo> list=query.list();
				 List<String> sectionInfoNames = new ArrayList<String>();
			
				for(SectionInfo sectionInfo : list){
					sectionInfoNames.add(sectionInfo.getSectionName());
				}
				return sectionInfoNames;
			}});
		return sectionNames;
	}




}
