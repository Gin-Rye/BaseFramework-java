package com.ginrye.baseframework.java.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class ComponentContext {
	
	@Autowired
	private static ApplicationContext atx;

	public static <T> T getInstance(Class<T> clazz) {
		T instance = atx.getBean(clazz);
		return instance;
	}
}
