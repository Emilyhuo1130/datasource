package com.ucs.tdc.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.ucs.tdc.interFace.UtilsInterface;
import com.ucs.tdc.pojo.ProjectInfo;
@Repository("UtilsImpl")
public class UtilsImpl  extends HibernateDaoSupport implements UtilsInterface{
	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }

	public List<String> select_project() {
		// TODO Auto-generated method stub
		List<String> list;
		String hql="select distinct projectName from ProjectInfo";
		list=getHibernateTemplate().find(hql);
		return list;
	}

	public List<Integer> select_weeks() {
		// TODO Auto-generated method stub
		List<Integer> list=new ArrayList<Integer>();
		for(int i=1;i<54;i++){
			list.add(i);
		}
		return list;
	}

	public String show_thisweek() {
		  Date date = new Date();
		  Calendar calendar = Calendar.getInstance();
		  calendar.setFirstDayOfWeek(Calendar.MONDAY);
		  calendar.setTime(date);
		 int week=calendar.get(Calendar.WEEK_OF_YEAR);
		  return week+"";
		  
	}
	
	
	
	
	
	
}
