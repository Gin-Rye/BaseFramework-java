package com.ginrye.baseframework.java.test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.xml.sax.InputSource;

import com.ginrye.baseframework.java.util.LogUtils;

public class DBHelper {
	private static Connection conn;
	private static IDatabaseConnection connection;
	
	static {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MYSQL", "sa", "");
			connection = new DatabaseConnection(conn);
		} catch(Exception e) {
			Logger logger = LogUtils.getLogger(DBHelper.class);
			logger.error(e.getMessage(), e);
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
	
	public static void cleanDB() throws DatabaseUnitException, SQLException {
		//禁用外键
		Statement nocheckStmt = conn.createStatement();
		nocheckStmt.execute("SET foreign_key_checks = 0");
		nocheckStmt.close();
		
		//删除数据
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_type = 'TABLE'");
		while(rs.next()) {
			Statement deleteStmt = conn.createStatement();
			String tableName = rs.getString(1);
			deleteStmt.execute("DELETE FROM " + tableName);
			deleteStmt.close();
		}
		rs.close();
		stmt.close();
		
		//启用外键
		Statement checkStmt = conn.createStatement();
		checkStmt.execute("SET foreign_key_checks = 1");
		checkStmt.close();
	}
}
