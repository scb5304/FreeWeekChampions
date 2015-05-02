package me.stevesite.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DataSourceMySQL creates connections to the MySQL database using the JDBC driver.
 * @see ChampionDAO
 */
public class DataSourceMySQL {

	private ConfigReader configReader;
	
	/**
	 * Create a new DataSourceMySQL object.
	 */
	public DataSourceMySQL() {
		configReader = new ConfigReader();
	}
	
	/**
	 * Create a new Connection using the JDBC driver and information supplied by the ConfigReader.
	 * @return the Connection created by the DriverManager.
	 */
	public Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(configReader.getDBurl(), configReader.getDBuser(), configReader.getDBpassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
