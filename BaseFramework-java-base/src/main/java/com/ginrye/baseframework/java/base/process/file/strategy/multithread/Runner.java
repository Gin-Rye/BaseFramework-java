package com.ginrye.baseframework.java.base.process.file.strategy.multithread;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import com.ginrye.baseframework.java.base.process.file.BaseRecord;
import com.ginrye.baseframework.java.base.process.file.IRecordProcessService;

public class Runner implements Runnable {

	private IRecordProcessService recordProcessService;
	private Collection<? extends BaseRecord> records;
	
	public Runner(IRecordProcessService recordProcessService, Collection<? extends BaseRecord> records) {
		this.recordProcessService = recordProcessService;
		this.records = records;
	}
	
	@Override
	public void run() {
		processRecords();
	}
	
	@Transactional
	protected void processRecords() {
		for(BaseRecord record : records) {
			recordProcessService.processRecord(record);
		}
	}
}
