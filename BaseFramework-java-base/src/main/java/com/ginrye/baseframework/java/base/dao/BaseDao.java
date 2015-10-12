package com.ginrye.baseframework.java.base.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ginrye.baseframework.java.base.exception.SystemException;
import com.ginrye.baseframework.java.util.MessageResourcesUtils;

public abstract class BaseDao<E> {

	@PersistenceContext
	protected EntityManager em;

	protected Class entityClass;

	public BaseDao() {
		Class<?> clazz = getClass();
		while (clazz != null) {
			Type superType = clazz.getGenericSuperclass();
			if (superType instanceof ParameterizedType) {
				Type type = ((ParameterizedType) superType).getActualTypeArguments()[0];
				if (type instanceof Class) {
					entityClass = (Class) type;
				} else {
					throw new SystemException(MessageResourcesUtils.getMessage("daoGenericType"));
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
}
