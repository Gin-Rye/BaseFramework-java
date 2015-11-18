package com.ginrye.baseframework.java.util;

import org.apache.log4j.Logger;

public class LogUtils {
	
	public static Logger getLogger(Object obj) {
		return getLogger(obj.getClass());
	}
	
	public static Logger getLogger(Class<?> clazz) {
		Logger logger = Logger.getLogger(clazz);
		return logger;
	}
}
