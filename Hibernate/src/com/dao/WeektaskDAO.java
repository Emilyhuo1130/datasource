package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pojo.Weektask;

/**
 * A data access object (DAO) providing persistence and search support for
 * Weektask entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.pojo.Weektask
 * @author MyEclipse Persistence Tools
 */

public class WeektaskDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(WeektaskDAO.class);

	public void save(Weektask transientInstance) {
		log.debug("saving Weektask instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Weektask persistentInstance) {
		log.debug("deleting Weektask instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Weektask findById(java.lang.Integer id) {
		log.debug("getting Weektask instance with id: " + id);
		try {
			Weektask instance = (Weektask) getSession().get(
					"com.pojo.Weektask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Weektask instance) {
		log.debug("finding Weektask instance by example");
		try {
			List results = getSession().createCriteria("com.pojo.Weektask")
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
		log.debug("finding Weektask instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Weektask as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Weektask instances");
		try {
			String queryString = "from Weektask";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Weektask merge(Weektask detachedInstance) {
		log.debug("merging Weektask instance");
		try {
			Weektask result = (Weektask) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Weektask instance) {
		log.debug("attaching dirty Weektask instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Weektask instance) {
		log.debug("attaching clean Weektask instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}