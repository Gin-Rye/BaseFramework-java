package com.ginrye.baseframework.java.base.process.file.strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginrye.baseframework.java.base.exception.BusinessException;
import com.ginrye.baseframework.java.base.process.file.IRecordProcessService;
import com.ginrye.baseframework.java.base.process.file.BaseRecord;

@Service("batchCommitRecordsProcessStrategy")
@Scope("prototype")
public class BatchCommitRecordsProcessStrategy implements IRecordsProcessStrategy {

	private int batchSize = 100;
	
	@Override
	public void process(IRecordProcessService recordProcessService, Collection<? extends BaseRecord> records) {
		List recordList = new ArrayList(records);
		int startIndex = 0;
		while(startIndex < records.size()) {
			int batchSize = this.batchSize < records.size() - startIndex ? this.batchSize : records.size() - startIndex;
			batchProcess(recordProcessService, recordList.subList(startIndex, startIndex + batchSize));
		}
	}
	
	@Transactional
	private void batchProcess(IRecordProcessService recordsProcessService, List<? extends BaseRecord> records) {
		for(BaseRecord record : records) {
			recordsProcessService.processRecord(record);
		}
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) throws BusinessException {
		if(batchSize <= 0) {
			throw new BusinessException();
		}
		this.batchSize = batchSize;
	}
}
