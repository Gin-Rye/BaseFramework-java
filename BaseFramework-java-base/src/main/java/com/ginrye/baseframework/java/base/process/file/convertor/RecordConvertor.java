package com.ginrye.baseframework.java.base.process.file.convertor;

import com.ginrye.baseframework.java.base.process.file.BaseRecord;

public interface RecordConvertor<T extends BaseRecord, K> {
	T convert(K data);
}
