package com.ginrye.baseframework.java.base.process.file.convertor;

import java.io.File;
import java.util.Collection;

import com.ginrye.baseframework.java.base.exception.BusinessException;
import com.ginrye.baseframework.java.base.process.file.BaseRecord;

public interface IRecordsConvertService<T extends BaseRecord> {
	
	Collection<T> convert(File file) throws BusinessException;
}
