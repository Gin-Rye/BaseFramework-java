package com.ginrye.baseframework.java.base.exception;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 6221309000269255619L;

	public SystemException() {
		super();
	}
	
	public SystemException(String message) {
		super(message);
	}
	
	public SystemException(Throwable e) {
		super(e);
	}
	
	public SystemException(String message, Throwable e) {
		super(message, e);
	}
}
