package com.ucs.tdc.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.ucs.tdc.interFace.TestInterFace;
import com.ucs.tdc.pojo.AdminInfo;
import com.ucs.tdc.pojo.ProjectInfo;
@Repository("TestImpl")
public class TestImpl extends HibernateDaoSupport implements TestInterFace{

	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	
	public List<AdminInfo> getAlladminInfo() {
		 List<AdminInfo> list =getHibernateTemplate().find("from AdminInfo");
		return list;
	}

	public boolean adduser(AdminInfo info) {
		// TODO Auto-generated method stub
		System.out.println(info.getUserName()+info.getUserPw());
		getHibernateTemplate().save(info);
		return true;
	}

	public boolean deleteinfoByID(AdminInfo info) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(info);
		return true;
	}

	public List<AdminInfo> getAlladminInfoById(int id) {
		// TODO Auto-generated method stub
		String hql="from AdminInfo where id=?";
		 Object[] params={id};
		 List<AdminInfo> list=getHibernateTemplate().find(hql,params);
		return list;
	}

	public boolean updaeinfo(AdminInfo info) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(info);
		return true;
	}
	@Test
	public void adduser() {
		// TODO Auto-generated method stub
		AdminInfo info=new AdminInfo();
		info.setId(22);
		info.setUserName("hang");
		info.setUserPw("13456");
		System.out.println(info.getUserName()+info.getUserPw());
		getHibernateTemplate().save(info);
		
	}

	public List<ProjectInfo> do_find_Test_Projects(String projectName, String starTime, String endTime) {
		// TODO Auto-generated method stub
		final String hql=("from ProjectInfo where 1=1" +
				" and projectName=? and createProjectDate>= '"+starTime+"'  and createProjectDate<= '"+endTime+ "'");
	
		 Object[] params={projectName};
		 List<ProjectInfo> list=getHibernateTemplate().find(hql,params);
		return list;
	}
	

}
