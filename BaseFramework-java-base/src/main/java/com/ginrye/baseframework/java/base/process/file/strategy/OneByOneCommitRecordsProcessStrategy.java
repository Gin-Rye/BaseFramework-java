package com.ginrye.baseframework.java.base.process.file.strategy;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginrye.baseframework.java.base.process.file.IRecordProcessService;
import com.ginrye.baseframework.java.base.process.file.BaseRecord;

@Service("oneByOneCommitRecordsProcessStrategy")
@Scope("singleton")
public class OneByOneCommitRecordsProcessStrategy implements IRecordsProcessStrategy {

	@Override
	public void process(IRecordProcessService recordProcessService, Collection<? extends BaseRecord> records) {
		for(BaseRecord record : records) {
			processRecord(recordProcessService, record);
		}
	}

	@Transactional
	private void processRecord(IRecordProcessService recordProcessService, BaseRecord record) {
		recordProcessService.processRecord(record);
	}
}
