package com.callor.shop.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection dbConn = null;

	static {
		try {
			Class.forName(DBContract.jdbcDriver);
			dbConn = DriverManager.getConnection(DBContract.DB_URL, DBContract.USER_NAME, DBContract.PASSWORD);
			System.out.println("DB Connection OK!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // end static

	public static Connection getDBConn() {
		return dbConn;
	}
}
