package com.ucs.rcm.business.bo;

import org.hibernate.Session;

import com.ucs.rcm.HibernateSessionFactory;





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