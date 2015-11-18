package com.ginrye.baseframework.java.base.process.file.validate;

import com.ginrye.baseframework.java.base.process.file.BaseRecord;

public interface IRecordValidator<T extends BaseRecord> extends IValidator<T> {
	
	@Override
	void validate(T record) throws RecordValidateException;
}
