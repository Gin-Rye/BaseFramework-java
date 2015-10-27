package com.ginrye.baseframework.java.base.process.file.validate;

import com.ginrye.baseframework.java.base.process.file.BaseRecord;

public interface RecordValidator<T extends BaseRecord> extends Validator<T> {
	
	@Override
	void validate(T record) throws RecordValidateException;
}
