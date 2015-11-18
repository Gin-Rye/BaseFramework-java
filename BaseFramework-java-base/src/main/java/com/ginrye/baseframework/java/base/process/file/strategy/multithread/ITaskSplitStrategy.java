package com.ginrye.baseframework.java.base.process.file.strategy.multithread;

import java.util.Collection;
import java.util.List;

import com.ginrye.baseframework.java.base.process.file.BaseRecord;
import com.ginrye.baseframework.java.base.process.file.IRecordProcessService;

public interface ITaskSplitStrategy {
	List<Runner> taskSplit(IRecordProcessService recordProcessService, Collection<? extends BaseRecord> records);
}
