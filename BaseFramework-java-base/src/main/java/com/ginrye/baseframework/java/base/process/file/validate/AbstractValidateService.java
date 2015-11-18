package com.ginrye.baseframework.java.base.process.file.validate;

import java.util.List;

public abstract class AbstractValidateService<T> implements IValidator<T> {

	protected List<IValidator<T>> validators;
	
	@Override
	public abstract void validate(T data) throws ValidateException;
}
