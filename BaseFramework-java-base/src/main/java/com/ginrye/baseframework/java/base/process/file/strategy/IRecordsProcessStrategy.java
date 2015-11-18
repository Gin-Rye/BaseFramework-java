package com.ginrye.baseframework.java.base.process.file.strategy;

import java.util.Collection;

import com.ginrye.baseframework.java.base.process.file.IRecordProcessService;
import com.ginrye.baseframework.java.base.process.file.BaseRecord;

public interface IRecordsProcessStrategy {
	
	void process(IRecordProcessService recordProcessService, Collection<? extends BaseRecord> records);
}
