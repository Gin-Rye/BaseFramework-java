package com.ginrye.baseframework.java.base.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

@Component("daoHelper")
public class DaoHelper {
	
	@PersistenceContext
	protected EntityManager em;

	public void clearEm() {
		em.clear();
	}
	
	public void flushEm() {
		em.flush();
	}
}
