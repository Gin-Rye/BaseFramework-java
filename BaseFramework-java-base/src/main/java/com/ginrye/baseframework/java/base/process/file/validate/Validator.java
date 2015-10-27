package com.ginrye.baseframework.java.base.process.file.validate;

public interface Validator<T> {
	void validate(T data) throws ValidateException;
}
