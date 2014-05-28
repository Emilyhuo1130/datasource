package com.ucs.tdc.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.ucs.tdc.interFace.Project;
import com.ucs.tdc.pojo.ProjectInfo;
import com.ucs.tdc.pojo.Project_Statistics;
import com.ucs.tdc.pojo.Project_Statistics_details;
import com.ucs.tdc.pojo.Project_Statistics_details_List;
import com.ucs.tdc.pojo.TaskParticulars;
import com.ucs.tdc.services.ProjectController;
@Repository("ProjectImpl")
public class ProjectImpl extends HibernateDaoSupport implements Project {
	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	static Logger log = Logger.getLogger(ProjectImpl.class);
	/***返回共几页**/
	public int do_projectpages(int pageSize,String projectName,String starTime,String endTime) {
		// TODO Auto-generated method stub
		StringBuffer buf=new StringBuffer();
		buf.append("select count(*) from ProjectInfo where 1=1 ");
		if((projectName!=null)&&(projectName.length()>0)){
			buf.append(" and projectName = '"+projectName+"' ");
		}
		if((starTime!=null)&&(starTime.length()>0)){
			buf.append("  and createProjectDate >= '"+starTime+"' ");
		}
		if((endTime!=null)&&(endTime.length()>0)){
			buf.append("  and createProjectDate <= '"+endTime+"' ");
		}
		String hql=buf.toString();
		List<Object> list=this.getHibernateTemplate().find(hql);
		 Object obj=(Object)list.get(0);
		 int totalRows=Integer.parseInt(obj.toString());
		 if (totalRows % pageSize == 0) {
            return totalRows / pageSize;
        } else {
            return totalRows / pageSize + 1;
        }
	}
	
	/**管理员     项目管理  添加项目**/
	public Boolean do_addProject(ProjectInfo info) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(info);
		return true;
	}
	/**管理员  显示项目***/
	public List<ProjectInfo> do_findProjects(final int pageSize, final int page,
			String projectName,String starTime,String endTime) {
		// TODO Auto-generated method stub
		StringBuffer buf=new StringBuffer();
		buf.append("from ProjectInfo where 1=1 ");
		if((projectName!=null)&&(projectName.length()>0)){
			buf.append(" and projectName = '"+projectName+"' ");
		}
		if((starTime!=null)&&(starTime.length()>0)){
			buf.append("  and createProjectDate >= '"+starTime+"' ");
		}
		if((endTime!=null)&&(endTime.length()>0)){
			buf.append("  and createProjectDate <= '"+endTime+"' ");
		}
		final String hql=buf.toString();
		
		List<ProjectInfo> list=(List<ProjectInfo>) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query=session.createQuery(hql);
				int count=(page-1)*pageSize+1;
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
				List<ProjectInfo> list=query.list();
				return list;
			}
		});
		for(ProjectInfo info:list){
			info.setCreateProjectDate(null);
		}
		return list;
	}
	
	
	
	/***返 回    项目统计的   页数**/
	public int do_Project_Statisticspages(int pageSize, String projectName,String starTime,String endTime) {
		// TODO Auto-generated method stub
		StringBuffer buf=new StringBuffer("select distinct projectName from ProjectInfo where 1=1 ");
		if((projectName!=null)&&(projectName.length()>0)){
			buf.append(" and projectName = '"+projectName+"' ");
		}
		if((starTime!=null)&&(starTime.length()>0)){
			buf.append(" and createProjectDate >= '"+starTime+"' ");
		}
		if((endTime!=null)&&(endTime.length()>0)){
			buf.append(" and createProjectDate <= '"+endTime+"' ");
		}
		String hql=buf.toString();
		List<Object> list=this.getHibernateTemplate().find(hql);
		 Object obj=(Object)list.size();
		 int totalRows=Integer.parseInt(obj.toString());
		 if (totalRows % pageSize == 0) {
            return totalRows / pageSize;
        } else {
            return totalRows / pageSize + 1;
        }
	}
	
	/***查询  项目 统计    列表***/
	public List<Project_Statistics> do_Project_Statistics(final int pageSize,final int page,
			String projectName, String starTime,String endTime) {
		// TODO Auto-generated method stub
		List<Project_Statistics> satisticsList=new ArrayList<Project_Statistics>();
		StringBuffer buf=null;
		if((projectName!=null)&&(projectName.length()>0)){
			buf=new StringBuffer();
			buf.append( "from TaskParticulars where projectName = '"+projectName+"' ");
			if((starTime!=null)&&(starTime.length()>0)){
				buf.append(" and commitDare >= '"+starTime+"' ");
			}
			if((endTime!=null)&&(endTime.length()>0)){
				buf.append(" and commitDare <= '"+endTime+"' ");
			}
			
			final String hql=buf.toString();
			List<TaskParticulars> infoList=this.getHibernateTemplate().find(hql);
			float AllDays = 0 ;
			Project_Statistics statis=new Project_Statistics();
			for(TaskParticulars info:infoList){
				AllDays=AllDays+info.getFinishHouse();
			}
			statis.setProjectName(projectName);
			statis.setStarTime(starTime);
			statis.setEndTime(endTime);
			statis.setUseDays(AllDays/8);
			satisticsList.add(statis);
		}else{
			String hql_findProject="select distinct projectName from TaskParticulars";
			List<String> pro_list=this.getHibernateTemplate().find(hql_findProject);
			for(String name:pro_list){
				buf=new StringBuffer();
				buf.append( "from TaskParticulars where projectName = '"+name+"' ");
				
				if((starTime!=null)&&(starTime.length()>0)){
					buf.append(" and commitDare >= '"+starTime+"' ");
				}
				if((endTime!=null)&&(endTime.length()>0)){
					buf.append(" and commitDare <= '"+endTime+"' ");
				}
				final String hql=buf.toString();
				List<TaskParticulars> project_list=getHibernateTemplate().find(hql);
				Project_Statistics statis=new Project_Statistics();
				statis.setProjectName(name);
				statis.setStarTime(starTime);
				statis.setEndTime(endTime);
				float AllDays = 0 ;
				for(TaskParticulars info:project_list){
					AllDays=AllDays+info.getFinishHouse();
				}
				statis.setUseDays(AllDays/8);
				satisticsList.add(statis);
			}
			if(page*pageSize>pro_list.size()){
				satisticsList=satisticsList.subList((page-1)*pageSize, pro_list.size());
			}else{
				satisticsList=satisticsList.subList((page-1)*pageSize, page*pageSize);
			}
		}
		return satisticsList;
	}
	
	
	
	/****人力明细表**/

	public Project_Statistics_details_List do_Project_Statistics_details(
			int pageSize, int page, String projectName,
			 String starTime, String endTime,String userName) {
		// TODO Auto-generated method stub
		List<String> name_list=new ArrayList<String>();
		if((userName==null)||(userName.length()==0)){
			String hql_find_user="select distinct userName from TaskParticulars where projectName= '"+projectName+"' and " +
			"commitDare >= '"+starTime+"' and commitDare <= '"+endTime+"' ";
			name_list=this.getHibernateTemplate().find(hql_find_user);
		}else{
			name_list.add(userName);
		}
		
		Project_Statistics_details_List details_List=new Project_Statistics_details_List();
		List<Project_Statistics_details> ofdetails_List=new ArrayList<Project_Statistics_details>();
		float total=0;
		for(String user_name:name_list){
			String hql="from TaskParticulars where projectName='"+projectName+"'" +
					" and userName='"+user_name+"'" +
							" and  commitDare >= '"+starTime+"'and commitDare <= '"+endTime+"' ";
			
			List<TaskParticulars> infoList=this.getHibernateTemplate().find(hql);
			Project_Statistics_details detail=new Project_Statistics_details();
			detail.setProjectName(projectName);
			detail.setUserName(user_name);
			float count=0;
			for(TaskParticulars info:infoList){
				count=count+info.getFinishHouse();
			}
			total=total+count;
			detail.setDays(count/8);
			ofdetails_List.add(detail);
		}
		int totalPage=0;
		int totalrows=ofdetails_List.size();
		if(page*pageSize>name_list.size()){
			ofdetails_List=ofdetails_List.subList((page-1)*pageSize, name_list.size());
		}else{
			ofdetails_List=ofdetails_List.subList((page-1)*pageSize, page*pageSize);
		}
		
		if(totalrows % pageSize==0){
			totalPage=totalrows/pageSize;
		}else{
			totalPage=totalrows/pageSize+1;
		}
		details_List.setTotalpages(totalPage);
		details_List.setTotal(total/8);
		details_List.setDetailsList(ofdetails_List);
		return details_List;
	}
	
	/***项目统计  某个人在某个项目上的工时详情***/
	public List<TaskParticulars> do_Project_one_details(final int pageSize, final int page,
			String projectName, String userName, String starTime, String endTime) {
		// TODO Auto-generated method stub
		final String hql="from TaskParticulars where projectName= '"+projectName+"' and" +
				" userName = '"+userName+"' and commitDare >= '"+starTime+"' and " +
						"commitDare <= '"+endTime+"' order by commitDare DESC ";
			
			List<TaskParticulars> list=(List) this.getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) {
					Query query=session.createQuery(hql);
					int count=(page-1)*pageSize+1;
					query.setFirstResult((page-1)*pageSize);
					query.setMaxResults(pageSize);
					List<TaskParticulars> list=query.list();
					return list;
				}
			});
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			for(TaskParticulars info:list){
				Date date=info.getCommitDare();
				info.setFarmatDate(sdf.format(date));
				info.setCommitDare(null);
			}
		return list;
	}
	
	/***根据id查找项目信息***/
	public ProjectInfo do_findProjectsByID(int id) {
		return this.getHibernateTemplate().get(ProjectInfo.class, id);
	}
	/***管理员  更新项目信息**/
	public Boolean do_updateProjectsByID(ProjectInfo info) {
		getHibernateTemplate().update(info);
		return true;
	}

	
	

}
