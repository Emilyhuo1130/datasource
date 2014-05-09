package com.my.strutsTest.dao.impl;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.my.strutsTest.dao.UsersDao;
import com.my.strutsTest.entity.UsersBean;

public class UserDaoImpl implements UsersDao{

	//HibernateTeamplate 模板类
	private HibernateTemplate template;
	
	public UsersBean login(UsersBean usersBean) {
		System.out.println("select from ");
		usersBean.setName("TestName");
		usersBean.setPassword("123456");
		return usersBean;
	}

	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
}
