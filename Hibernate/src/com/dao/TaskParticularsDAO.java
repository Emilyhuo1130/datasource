package com.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pojo.TaskParticulars;

/**
 * A data access object (DAO) providing persistence and search support for
 * TaskParticulars entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.pojo.TaskParticulars
 * @author MyEclipse Persistence Tools
 */

public class TaskParticularsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TaskParticularsDAO.class);

	public void save(TaskParticulars transientInstance) {
		log.debug("saving TaskParticulars instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TaskParticulars persistentInstance) {
		log.debug("deleting TaskParticulars instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaskParticulars findById(java.lang.Integer id) {
		log.debug("getting TaskParticulars instance with id: " + id);
		try {
			TaskParticulars instance = (TaskParticulars) getSession().get(
					"com.pojo.TaskParticulars", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TaskParticulars instance) {
		log.debug("finding TaskParticulars instance by example");
		try {
			List results = getSession()
					.createCriteria("com.pojo.TaskParticulars")
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
		log.debug("finding TaskParticulars instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TaskParticulars as model where model."
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
		log.debug("finding all TaskParticulars instances");
		try {
			String queryString = "from TaskParticulars";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TaskParticulars merge(TaskParticulars detachedInstance) {
		log.debug("merging TaskParticulars instance");
		try {
			TaskParticulars result = (TaskParticulars) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskParticulars instance) {
		log.debug("attaching dirty TaskParticulars instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskParticulars instance) {
		log.debug("attaching clean TaskParticulars instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}