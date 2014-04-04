package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pojo.AdminInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * AdminInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.pojo.AdminInfo
 * @author MyEclipse Persistence Tools
 */

public class AdminInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AdminInfoDAO.class);
	
	

	public void save(AdminInfo transientInstance) {
		log.debug("saving AdminInfo instance");
		Transaction ts =getSession().beginTransaction();
		try {
			getSession().save(transientInstance);
			ts.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AdminInfo persistentInstance) {
		log.debug("deleting AdminInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AdminInfo findById(java.lang.Integer id) {
		log.debug("getting AdminInfo instance with id: " + id);
		try {
			AdminInfo instance = (AdminInfo) getSession().get(
					"com.pojo.AdminInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AdminInfo instance) {
		log.debug("finding AdminInfo instance by example");
		try {
			List results = getSession().createCriteria("com.pojo.AdminInfo")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding AdminInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AdminInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll(int page,int pageSize) {//分页
		log.debug("finding all AdminInfo instances");
		try {
			String queryString = "from AdminInfo";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult((page-1)*pageSize);
			queryObject.setMaxResults(pageSize);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}finally{
			//closeSession();
			
		}
	}

	public AdminInfo merge(AdminInfo detachedInstance) {
		log.debug("merging AdminInfo instance");
		try {
			AdminInfo result = (AdminInfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AdminInfo instance) {
		log.debug("attaching dirty AdminInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AdminInfo instance) {
		log.debug("attaching clean AdminInfo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}