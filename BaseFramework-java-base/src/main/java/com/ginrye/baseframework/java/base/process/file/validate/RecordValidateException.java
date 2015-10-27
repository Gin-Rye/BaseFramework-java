package com.ginrye.baseframework.java.base.process.file.validate;

import com.ginrye.baseframework.java.base.process.file.BaseRecord;

public class RecordValidateException extends ValidateException {

	private static final long serialVersionUID = -4925751451676798828L;

	private BaseRecord record;

	public BaseRecord getRecord() {
		return record;
	}

	public void setRecord(BaseRecord record) {
		this.record = record;
	}
}
