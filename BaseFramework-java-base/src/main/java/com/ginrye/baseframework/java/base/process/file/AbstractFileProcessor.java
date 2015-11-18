package com.ginrye.baseframework.java.base.process.file;

import java.io.File;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

import com.ginrye.baseframework.java.base.exception.BusinessException;
import com.ginrye.baseframework.java.base.process.file.convertor.IRecordsConvertService;
import com.ginrye.baseframework.java.base.process.file.strategy.IRecordsProcessStrategy;
import com.ginrye.baseframework.java.base.process.file.validate.AbstractValidateService;
import com.ginrye.baseframework.java.base.process.file.validate.ValidateException;
import com.ginrye.baseframework.java.util.LogUtils;

public abstract class AbstractFileProcessor<T extends BaseRecord> {
	
	private Logger logger = LogUtils.getLogger(this);
	
	protected AbstractValidateService<File> fileValidatorService;
	
	protected IRecordsConvertService<T> recordsConverteService;
	
	protected IRecordsProcessStrategy recordsProcessStrategy;
	
	protected IRecordProcessService<T> recordProcessService;
	
	@PostConstruct
	public void initComponent() {
		initFileValidateService();
		initRecordsConvertService();
		initRecordsProcessStrategy();
		initRecordProcessService();
	}
	
	protected abstract void initFileValidateService();
	
	protected abstract void initRecordsConvertService();
	
	protected abstract void initRecordsProcessStrategy();
	
	protected abstract void initRecordProcessService();
	
	public void process(String filePath) {
		try {
			File file = new File(filePath);
			fileValidatorService.validate(file);
			Collection<T> records = recordsConverteService.convert(file);
			recordProcessService.preProcess(records);
			recordsProcessStrategy.process(recordProcessService, records);
			recordProcessService.postProcess(records);
		} catch (ValidateException e) {
			logger.error(e.getMessage());
		} catch (BusinessException e) {
			logger.error(e.getMessage());
		}
	}
}
