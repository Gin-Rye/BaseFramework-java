package com.ginrye.baseframework.java.base.process.file;

import java.util.Collection;

public interface IRecordProcessService<T extends BaseRecord> {
	
	public abstract void processRecord(T record);
	
	public abstract void preProcess(Collection<T> records);
	
	public abstract void postProcess(Collection<T> records);
}
