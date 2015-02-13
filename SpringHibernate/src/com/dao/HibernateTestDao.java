package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.e.AdminInfo;
import com.e.UserInfo;
import com.google.gson.Gson;
import com.interFace.HibernateTestInterface;
@Repository("HibernateTestDao")
public class HibernateTestDao extends HibernateDaoSupport implements HibernateTestInterface{
	@Resource
	public void setMySessionFactory(SessionFactory sf){
		super.setSessionFactory(sf);
	}
	public Object test() {
		UserInfo info=this.getHibernateTemplate().get(UserInfo.class, 1);
		
		List<UserInfo> list=this.getHibernateTemplate().find("from UserInfo");
		Gson gson=new Gson();
		System.out.println(gson.toJson(list));
		
		return info;
	}
	
	public boolean update(UserInfo info) {
//		this.getHibernateTemplate().update(info);
		AdminInfo adminInfo = this.getHibernateTemplate().get(AdminInfo.class, 1L);
		Gson gson=new Gson();
		System.out.println(gson.toJson(adminInfo));
		this.getHibernateTemplate().find("from AdminInfo");
		return true;
	}
	

}
