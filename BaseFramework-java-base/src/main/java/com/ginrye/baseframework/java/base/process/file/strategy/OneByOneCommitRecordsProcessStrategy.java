package com.ginrye.baseframework.java.base.process.file.strategy;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginrye.baseframework.java.base.process.file.AbstractRecordProcessService;
import com.ginrye.baseframework.java.base.process.file.BaseRecord;

@Service("oneByOneCommitRecordsProcessStrategy")
public class OneByOneCommitRecordsProcessStrategy extends AbstractRecordsProcessStrategy {

	@Override
	public void process(AbstractRecordProcessService recordProcessService, Collection<? extends BaseRecord> records) {
		for(BaseRecord record : records) {
			processRecord(recordProcessService, record);
		}
	}

	@Transactional
	private void processRecord(AbstractRecordProcessService recordsProcessService, BaseRecord record) {
		recordsProcessService.processRecord(record);
	}
}
