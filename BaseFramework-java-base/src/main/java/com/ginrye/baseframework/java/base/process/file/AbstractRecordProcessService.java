package com.ginrye.baseframework.java.base.process.file;

import java.util.Collection;

public abstract class AbstractRecordProcessService<T extends BaseRecord> {
	
	public abstract void processRecord(T record);
	
	public abstract void preProcess(Collection<T> records);
	
	public abstract void postProcess(Collection<T> records);
}
