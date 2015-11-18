package com.ginrye.baseframework.java.base.process.file.validate;

public interface IValidator<T> {
	void validate(T data) throws ValidateException;
}
