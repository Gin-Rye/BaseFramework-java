package com.ginrye.baseframework.java.base.process.file;

import java.io.File;
import java.util.Collection;

import com.ginrye.baseframework.java.base.exception.BusinessException;
import com.ginrye.baseframework.java.base.process.file.convertor.AbstractRecordsConvertService;
import com.ginrye.baseframework.java.base.process.file.strategy.AbstractRecordsProcessStrategy;
import com.ginrye.baseframework.java.base.process.file.validate.AbstractValidateService;
import com.ginrye.baseframework.java.base.process.file.validate.ValidateException;

public abstract class AbstractFileProcessor<T extends BaseRecord> {
	
	protected AbstractValidateService<File> fileValidatorService;
	
	protected AbstractRecordsConvertService<T> recordsConverteService;
	
	protected AbstractRecordsProcessStrategy recordsProcessStrategy;
	
	protected AbstractRecordProcessService<T> recordProcessService;
	
	public void process(String filePath) {
		try {
			File file = new File(filePath);
			fileValidatorService.validate(file);
			Collection<T> records = recordsConverteService.convert(file);
			recordProcessService.preProcess(records);
			recordsProcessStrategy.process(recordProcessService, records);
			recordProcessService.postProcess(records);
		} catch(ValidateException e) {
			
		} catch (BusinessException e) {
			
		}
	}
}
