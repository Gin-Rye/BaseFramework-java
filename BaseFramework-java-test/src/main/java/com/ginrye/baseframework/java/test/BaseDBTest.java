package com.ginrye.baseframework.java.test;

import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Before;
import org.springframework.transaction.annotation.Transactional;

import com.ginrye.baseframework.java.test.db.DBHelper;

@Transactional
public abstract class BaseDBTest extends BaseTest {

	protected abstract String[] getDataSets();

	@Before
	public void prepareDB() throws DatabaseUnitException, SQLException {
		DBHelper.prepareDB(getDataSets());
	}

	@After
	public void cleanDB() throws DatabaseUnitException, SQLException {
		DBHelper.cleanDB();
	}
}
