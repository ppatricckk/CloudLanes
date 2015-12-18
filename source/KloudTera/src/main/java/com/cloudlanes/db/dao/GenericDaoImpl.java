package com.cloudlanes.db.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDaoImpl<E, K extends Serializable> implements
		GenericDao<E, K> {
	@Autowired
	private SessionFactory sessionFactory;

	protected Class<? extends E> daoType;

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean add(E entity) {
		try {
			currentSession().save(entity);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		}
	}

	public void update(E entity) {
		currentSession().saveOrUpdate(entity);
	}

	public void remove(E entity) {
		currentSession().delete(entity);
	}

	public E find(K key) {
		return (E) currentSession().get(daoType, key);
	}

	public List<E> getAll() {
		return currentSession().createCriteria(daoType).list();
	}

	public List<E> getByColumnValue(Class<E> entityType, String columnName,
			String columnValue) {
		Criteria criteria = currentSession().createCriteria(entityType);  
		criteria.add(Restrictions.eq(columnName, columnValue));
		List<E> results = criteria.list();
		return results;
	}

	public E getFirstByMatchedColumnValue(Class<E> entityType,
			String columnName, String columnValue) {
		List<E> entityList = getByColumnValue(entityType, columnName, columnValue);
		if(entityList != null && entityList.size() > 0) {
			return entityList.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public E getByPrimaryKeyId(K id) {
		return (E) currentSession().get(daoType, id);
	}

}
