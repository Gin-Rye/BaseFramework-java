package com.ginrye.baseframework.java.base.process.file.strategy;

import java.util.Collection;

import com.ginrye.baseframework.java.base.process.file.AbstractRecordProcessService;
import com.ginrye.baseframework.java.base.process.file.BaseRecord;

public abstract class AbstractRecordsProcessStrategy {
	
	public abstract void process(AbstractRecordProcessService recordsProcessService, Collection<? extends BaseRecord> records);
}
