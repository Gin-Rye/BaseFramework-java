package com.ginrye.baseframework.java.base.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ginrye.baseframework.java.base.exception.SystemException;
import com.ginrye.baseframework.java.util.MessageResourcesUtils;

public abstract class BaseDao<E> {

	@PersistenceContext
	protected EntityManager em;

	protected Class<?> entityClass;
	protected String entityClassName;

	public BaseDao() {
		Class<?> clazz = getClass();
		while (clazz != null) {
			Type superType = clazz.getGenericSuperclass();
			if (superType instanceof ParameterizedType) {
				Type type = ((ParameterizedType) superType).getActualTypeArguments()[0];
				if (type instanceof Class) {
					entityClass = (Class<?>) type;
					entityClassName = entityClass.getName();
				} else {
					throw new SystemException(MessageResourcesUtils.getMessage("com.ginrye.baseframework.java.base.dao.BaseDao.GenericType"));
				}
				break;
			} else {
				clazz = clazz.getSuperclass();
			}
		}
	}
	
	public E findById(Serializable id) {
		E entity = (E) em.find(entityClass, id);
		return entity;
	}

	public void create(E entity) {
		em.persist(entity);
	}

	public void update(E entity) {
		em.refresh(entity);
	}

	public void remove(E entity) {
		em.remove(entity);
	}
	
	public List<E> getAll() {
		String jpql = "select o from " + entityClassName + " o";
		Query query = em.createQuery(jpql);
		return (List<E>) query.getResultList();
	}
}
