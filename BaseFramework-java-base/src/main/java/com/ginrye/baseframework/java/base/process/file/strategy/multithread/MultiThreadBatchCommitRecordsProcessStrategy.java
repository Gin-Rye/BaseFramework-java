package com.ginrye.baseframework.java.base.process.file.strategy.multithread;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ginrye.baseframework.java.base.process.file.BaseRecord;
import com.ginrye.baseframework.java.base.process.file.IRecordProcessService;
import com.ginrye.baseframework.java.base.process.file.strategy.IRecordsProcessStrategy;
import com.ginrye.baseframework.java.util.LogUtils;

@Service("multiThreadBatchCommitRecordsProcessStrategy")
public class MultiThreadBatchCommitRecordsProcessStrategy implements IRecordsProcessStrategy {
	
	private Logger logger = LogUtils.getLogger(this);
	
	private ExecutorService executorService;
	
	private ITaskSplitStrategy taskSplitStrategy;
	
	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public void setTaskSplitStrategy(ITaskSplitStrategy taskSplitStrategy) {
		this.taskSplitStrategy = taskSplitStrategy;
	}

	@Override
	public void process(IRecordProcessService recordProcessService, Collection<? extends BaseRecord> records) {
		List<Runner> runners = taskSplitStrategy.taskSplit(recordProcessService, records);
		List<Future> futures = new LinkedList<Future>();
		for(Runner runner : runners) {
			Future future = executorService.submit(runner);
			futures.add(future);
		}
		for(Future future : futures) {
			try {
				future.get();
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			} catch (ExecutionException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
