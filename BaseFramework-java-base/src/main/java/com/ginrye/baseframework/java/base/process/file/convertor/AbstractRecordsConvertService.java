package com.ginrye.baseframework.java.base.process.file.convertor;

import java.io.File;
import java.util.Collection;

import com.ginrye.baseframework.java.base.exception.BusinessException;
import com.ginrye.baseframework.java.base.process.file.BaseRecord;

public abstract class AbstractRecordsConvertService<T extends BaseRecord> {
	
	public abstract Collection<T> convert(File file) throws BusinessException;
}
