package com.ucs.tdc.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.ucs.tdc.interFace.Task;
import com.ucs.tdc.pojo.HistoryTaskList;
import com.ucs.tdc.pojo.TaskParticulars;
import com.ucs.tdc.pojo.TaskParticularsInfoResponse;
import com.ucs.tdc.pojo.UserInfo;
import com.ucs.tdc.pojo.WeekTaskInfoResponse;
import com.ucs.tdc.pojo.Weektask;


@Repository("TaskImpl")
public class TaskImpl extends HibernateDaoSupport implements Task{
	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	
	/**添加 任务***/
	public Boolean do_addTasks(List<TaskParticulars> list) {
		// TODO Auto-generated method stub
		Date date=new Date();
		long l=date.getTime();
		for(TaskParticulars info:list){
			info.setCommitDare(new Timestamp(l));
			getHibernateTemplate().save(info);
		}
		return true;
	}
	/***普通用户 修改密码前的原密码验证**/
	public Boolean do_verifyPW(String userName, String password) {
		String hql="from UserInfo where userAccount= '"+userName+"' ";
		List<UserInfo> list=getHibernateTemplate().find(hql);
		UserInfo info=list.get(0);
		if(info.getUserPw().equals(password)){
			return true;
		}else{
			return false;
		}
	}

	/***普通用户 修改密码**/
	public Boolean do_modifyPW(String userName, String password) {
		// TODO Auto-generated method stub
		if((password!=null)&&(password.length()>0)){
			String hql="from UserInfo where userAccount=?";
			Object[] params={userName};
			List<UserInfo> list=getHibernateTemplate().find(hql,params);
			UserInfo info=list.get(0);
			info.setUserPw(password);
			getHibernateTemplate().update(info);
			return true;
		}else{
			return false;
		}
	}
	
	/***管理员 任务管理查询**/
	
	public List<TaskParticulars> do_findTasks(String projectName, int fromWeek, int toWeek,
			String year ,final int pageSize, final int page,final String userName) {
		// TODO Auto-generated method stub
		StringBuffer buf=new StringBuffer();
		buf.append(" from TaskParticulars where 1=1 ");
		/**年份必填**/
		buf.append(" and commitDare >= '"+year+"' ");
		
		if((projectName!=null)&&(projectName.length()>0)){
			buf.append(" and projectName= '"+projectName+"' ");
		}
		if((userName!=null)&&(userName.length()>0)){
			buf.append(" and userName= '"+userName+"' ");
		}
		if((fromWeek!=0)){
			buf.append(" and weekNumber >="+fromWeek);
		}
		if((toWeek!=0)){
			buf.append(" and weekNumber <="+toWeek);
		}
		
		buf.append(" order by weekNumber");
		final String hql=buf.toString();
		List<TaskParticulars> list;
		 list=(List) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query=session.createQuery(hql);
				int count=(page-1)*pageSize+1;
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
				List<TaskParticulars> list=query.list();
				return list;
			}
		});
		for(TaskParticulars info2:list){
			info2.setCommitDare(null);
		}
		return list;
		
	}

	/***管理员 任务管理   显示 共几页**/
	public int do_findTaskspage(int pageSize, String projectName, int fromWeek,
			int toWeek,String userName) {
		// TODO Auto-generated method stub
		String hql="select count(*) from TaskParticulars where 1=1 " ;
		if((userName!=null)&&(userName.length()>0)){
			hql=hql+"and userName = '"+userName+"' ";
		}
		if((projectName!=null)&&(projectName.length()>0)){
			hql=hql+" and projectName = '"+projectName+"' ";
		}
		if(fromWeek!=0){
			hql=hql+" and weekNumber >= "+fromWeek+" ";
		}
		if(toWeek!=0){
			hql=hql+" and weekNumber <= "+toWeek+" ";
		}
		List<Object> list=this.getHibernateTemplate().find(hql);
		 Object obj=(Object)list.get(0);
		 int totalRows=Integer.parseInt(obj.toString());
		 if (totalRows % pageSize == 0) {
            return totalRows / pageSize;
        } else {
            return totalRows / pageSize + 1;
        }
	}

	public List<String> do_select_AllUser() {
		// TODO Auto-generated method stub
		String hql="select distinct userName from TaskParticulars ";
		List<String> list=getHibernateTemplate().find(hql);
		return list;
	}
	/***返回周计划总页数**/
	public Integer do_geWeekTaskPageCount(int weekNo, String year,
			String userAccount, int pageSize) {
		// TODO Auto-generated method stub
		String backyear=(Integer.parseInt(year)-1)+"";
		String hql="select count(*) from Weektask where " +
				"weekNo="+weekNo+" and userAccount = '"+userAccount+"' and updateTime >'"+backyear+"' and updateTime <= '"+year+"'";
		System.out.println(hql);
		List<Integer> list=getHibernateTemplate().find(hql);
		 Object obj=(Object)list.get(0);
		 int totalRows=Integer.parseInt(obj.toString());
		if (totalRows % pageSize == 0) {
            return totalRows / pageSize;
        } else {
            return totalRows / pageSize + 1;
        }
	}

	/***查看本周和下周计划任务**/
	public List<Weektask> showThisWeekTask(int weekNo, String year,String userAccount,
			int page, int pageSize) {
		// TODO Auto-generated method stub
		String backyear=(Integer.parseInt(year)-1)+"";
		String hql="from Weektask where " +
				"weekNo = "+weekNo+" " +
						"and userAccount = '"+userAccount+"' and updateTime >'"+backyear+"' and updateTime <= '"+year+"'";
		List<Weektask> list=getHibernateTemplate().find(hql);
		if(list.size()>0){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			for(Weektask info:list){
				info.setFromatDate(sdf.format(info.getUpdateTime()));
				info.setUpdateTime(null);
			}
			int last=0;
			if(list.size()>page*pageSize){
				last=page*pageSize;
			}else{
				last=list.size();
			}
			list=list.subList((page-1)*pageSize, last);
			
		return list;
		
		}else{
			List<Weektask> list2=new ArrayList<Weektask>();
			return list2;
		}
		
	}
	/***通过id查找周计划任务**/
	public Weektask do_findNextWeekTaskById(int id) {
		// TODO Auto-generated method stub
		Weektask info=getHibernateTemplate().get(Weektask.class, id);
		return info;
	}
	/***更新周计划任务**/
	public Boolean do_updateNextWeekTask(Weektask task) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(task);
		return true;
	}
	/**添加下周计划任务**/
	public Boolean do_addNextWeekTask(Weektask task) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(task);
		return true;
	}
	/***删除周计划任务***/
	public Boolean do_deleteWeekTask(Weektask info) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(info);
		return true;
	}
	
	/****显示每个人的周任务 按人查 like***/
	public WeekTaskInfoResponse do_select_AllUserHistoryTasks(int startweekNo,int endweekNo,
			String userName, final int page, final int pageSize) {
		// TODO Auto-generated method stub
		WeekTaskInfoResponse responseinfo=new WeekTaskInfoResponse();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		String year=sdf.format(new Date());
		String nextYear=(Integer.parseInt(year)+1)+"";
		
		StringBuffer buf=new StringBuffer();
		StringBuffer bufcount=new StringBuffer();
		buf.append(" from Weektask where 1=1  ");
		bufcount.append("select count(*) from Weektask where 1=1 ");
		if((userName!=null)&&(userName.length()>0)){
			buf.append(" and userAccount like '%"+userName+"%' " );
			bufcount.append(" and userAccount like '%"+userName+"%' ");
		}
		buf.append(" and weekNo >="+startweekNo+" and weekNo <= "+endweekNo+"and updateTime>= '"+year+"' and updateTime<= '"+nextYear+"' ORDER by  updateTime DESC");
		bufcount.append(" and weekNo >="+startweekNo+" and weekNo <= "+endweekNo+" and updateTime>= '"+year+"' and updateTime<= '"+nextYear+"' ");
		final String  hql=buf.toString();
		String hqlcount=bufcount.toString();
		int totalPage=0;
		List<Integer> listcount=getHibernateTemplate().find(hqlcount);
		 Object obj=(Object)listcount.get(0);
		 int totalRows=Integer.parseInt(obj.toString());
		if (totalRows % pageSize == 0) {
			totalPage= totalRows / pageSize;
       } else {
    	   totalPage= totalRows / pageSize + 1;
       }
		
		
		List<Weektask> list;
		 list=(List) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query=session.createQuery(hql);
				int count=(page-1)*pageSize+1;
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
				List<Weektask> list=query.list();
				return list;
			}
		});
		 for(Weektask info:list){
			 info.setUpdateTime(null);
		 }
		 responseinfo.setWeektaskList(list);
		 responseinfo.setTotalPage(totalPage);
		return responseinfo;
	}
	/**每人的历史日报 名字 时间查**/
	public TaskParticularsInfoResponse do_select_AllUserHistoryWeekTasks(
			String userName, final int page, final int pageSize, String starTime,
			String endTime) {
		// TODO Auto-generated method stub
		TaskParticularsInfoResponse responseinfo=new TaskParticularsInfoResponse();
		StringBuffer buf=new StringBuffer();
		StringBuffer bufcount=new StringBuffer();
		buf.append(" from TaskParticulars where 1=1 ");
		bufcount.append("select count(*) from TaskParticulars where 1=1 ");
		if((userName!=null)&&(userName.length()>0)){
			buf.append(" and userName= '"+userName+"' ");
			bufcount.append(" and userName= '"+userName+"' ");
		}
		if((starTime!=null)&&(starTime.length()>0)){
			buf.append(" and commitDare >= '"+starTime+"' ");
			bufcount.append(" and commitDare >= '"+starTime+"' ");
		}
		if((endTime!=null)&&(endTime.length()>0)){
			buf.append(" and commitDare <= '"+endTime+"' ");
			bufcount.append(" and commitDare <= '"+endTime+"' ");
		}
		
		
		buf.append(" ORDER by  commit_Dare DESC ");
		String hqlcount=bufcount.toString();
		final String hql=buf.toString();
		int totalPage=0;
		List<Integer> listcount=getHibernateTemplate().find(hqlcount);
		 Object obj=(Object)listcount.get(0);
		 int totalRows=Integer.parseInt(obj.toString());
		if (totalRows % pageSize == 0) {
			totalPage= totalRows / pageSize;
       } else {
    	   totalPage= totalRows / pageSize + 1;
       }
		List<TaskParticulars> list;
		 list=(List) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query=session.createQuery(hql);
				int count=(page-1)*pageSize+1;
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
				List<TaskParticulars> list=query.list();
				return list;
			}
		});
		 for(TaskParticulars info:list){
			 info.setCommitDare(null);
		 }
		 responseinfo.setTaskParticularsList(list);
		 responseinfo.setTotalPage(totalPage);
		return responseinfo;
	}
	
	/***普通用户查看自己历史任务**/
	public HistoryTaskList do_findHitoryTask(String startTime, String endTime,
			String userName,final int page,final int pageSize) {
		// TODO Auto-generated method stub
		HistoryTaskList info=new HistoryTaskList();
		List<TaskParticulars> taskList=new ArrayList<TaskParticulars>();
		StringBuffer buf=new StringBuffer();
		StringBuffer countbuf=new StringBuffer();
		buf.append("from TaskParticulars where 1=1 ");
		countbuf.append("select count(*) from TaskParticulars where 1=1");
		if((userName!=null)&&(userName.length()>0)){
			buf.append(" and userName= '"+userName+"' ");
			countbuf.append(" and userName= '"+userName+"' ");
		}
		if((startTime!=null)&&(startTime.length()>0)){
			buf.append(" and commitDare >= '"+startTime+"' ");
			countbuf.append(" and commitDare >= '"+startTime+"' ");
		}
		if((endTime!=null)&&(endTime.length()>0)){
			buf.append(" and commitDare <= '"+endTime+"' ");
			countbuf.append(" and commitDare <= '"+endTime+"' ");
		}
		buf.append(" ORDER by  commitDare DESC");
		final String hql=buf.toString();
		String countPage=countbuf.toString();
		taskList =(List) this.getHibernateTemplate().execute(new HibernateCallback() {
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
		for(TaskParticulars task:taskList ){
			Date date =task.getCommitDare();
			String dateFormat=sdf.format(date);
			task.setCommitDare(null);
			task.setFarmatDate(dateFormat);
		}
		info.setTaskListInfo(taskList);
		
		List<Object> list=this.getHibernateTemplate().find(countPage);
		if(list.size()>0){
			Object obj=(Object)list.get(0);
			int totalRows=Integer.parseInt(obj.toString());
			if (totalRows % pageSize == 0) {
				info.setPage(totalRows / pageSize);
			} else {
				info.setPage(totalRows / pageSize+1);
			}
		}
		return info;
	}
	
	/***普通用户要修改自己信息，得到自己的信息*/
	public UserInfo do_getMyUserInfo(String userAccount) {
		// TODO Auto-generated method stub
		String hql="from UserInfo where userAccount= '"+userAccount+"'";
		List<UserInfo>infoList=getHibernateTemplate().find(hql);
		UserInfo info=infoList.get(0);
		return info;
	}
	/***普通用户 修改个人信息**/
	public Boolean do_modifyMyInfo(UserInfo oldinfo) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(oldinfo);
		return true;
	}
	/**根据id来获取某个历史任务***/
	public TaskParticulars do_findHitoryTaskById(int id) {
		// TODO Auto-generated method stub
		TaskParticulars info=getHibernateTemplate().get(TaskParticulars.class, id);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		info.setFarmatDate(sdf.format(info.getCommitDare()));
		info.setCommitDare(null);
		return info;
	}
	/**根据id来获取某个历史任务***/
	public TaskParticulars do_findHitoryTaskByIdNoModify(int id) {
		// TODO Auto-generated method stub
		TaskParticulars info=getHibernateTemplate().get(TaskParticulars.class, id);
		return info;
	}
	
	/***根据id来修改某个历史任务**/

	public Boolean do_updateHitoryTask(TaskParticulars infonew) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(infonew);
		return true;
	}
	/***根据id来删除某个历史任务**/
	public Boolean do_deleteHistoryMission(TaskParticulars infoold) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(infoold);
		return true;
	}



	
	



	

	

	
	
	
	
	
	
	
}