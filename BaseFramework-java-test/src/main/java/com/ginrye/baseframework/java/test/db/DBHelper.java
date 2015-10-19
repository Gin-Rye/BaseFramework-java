package com.ginrye.baseframework.java.test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.xml.sax.InputSource;

public class DBHelper {
	private static Connection conn;
	private static IDatabaseConnection connection;
	
	static {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
			// 获得DB 连接
			connection = new DatabaseConnection(conn);
		} catch(Exception e) {
			
		}
	}
	
	public static void prepareDB(String[] datasets) throws DatabaseUnitException, SQLException {
		for (String dataset : datasets) {
			FlatXmlProducer producer = new FlatXmlProducer(
					new InputSource(Thread.currentThread().getClass().getResourceAsStream("/dataset/" + dataset)));
			IDataSet dataSet = new FlatXmlDataSet(producer);
			DatabaseOperation.REFRESH.execute(connection, dataSet);
		}
	}
	
	public static void cleanDB(String[] datasets) throws DatabaseUnitException, SQLException {
		for (String dataset : datasets) {
			FlatXmlProducer producer = new FlatXmlProducer(
					new InputSource(Thread.currentThread().getClass().getResourceAsStream("/dataset/" + dataset)));
			IDataSet dataSet = new FlatXmlDataSet(producer);
			DatabaseOperation.DELETE_ALL.execute(connection, dataSet);
		}
	}
}
