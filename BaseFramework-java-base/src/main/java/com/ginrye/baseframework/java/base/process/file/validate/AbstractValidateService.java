package com.ginrye.baseframework.java.base.process.file.validate;

import java.util.List;

public abstract class AbstractValidateService<T> implements Validator<T> {

	protected List<Validator<T>> validators;
	
	@Override
	public abstract void validate(T data) throws ValidateException;
}
