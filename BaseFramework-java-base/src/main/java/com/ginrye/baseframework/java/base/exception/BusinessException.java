package com.ginrye.baseframework.java.base.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 5061956407965856014L;

	public BusinessException() {
		super();
	}
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(Throwable e) {
		super(e);
	}
	
	public BusinessException(String message, Throwable e) {
		super(message, e);
	}
}
