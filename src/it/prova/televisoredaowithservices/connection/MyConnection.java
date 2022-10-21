package it.prova.televisoredaowithservices.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {

	public static Connection getConnection(String DRIVER_NAME, String CONNECTION_URL) throws Exception {

		Connection connection = null;
		try {

			Class.forName(DRIVER_NAME);
			connection = DriverManager.getConnection(CONNECTION_URL);
			return connection;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
}
