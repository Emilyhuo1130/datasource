package com.dao;

import org.hibernate.Session;

import com.sessionfactory.HibernateSessionFactory;



/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	public void closeSession() {
		 HibernateSessionFactory.closeSession();
	}
	
}