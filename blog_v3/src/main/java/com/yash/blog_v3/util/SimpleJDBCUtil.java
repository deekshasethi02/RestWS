package com.yash.blog_v3.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.PreparedStatement;

/**
 * This is a sample JDBC utility class that is used for getting
 * PreparedStatement object. This class will be responsible for closing
 * connection and PreparedStatement object. Connection object should be
 * auto-closed. ConnectionObject should not be exposed outside. This class will
 * be used for DAO and service testing purpose.
 * 
 * @author arju.yadav
 *
 */
public abstract class SimpleJDBCUtil {

	private static Properties prop = new Properties();
	private Connection con = null;
	private PreparedStatement pstmt = null;

	/**
	 * loading properties file to get jdbc details. This static block should load
	 * the driver
	 */
	static {
		try {
			InputStream inputStream = new FileInputStream("C:\\Users\\deeksha.sethi\\Desktop\\deeksha\\RestWS\\blog_v3\\jdbc.properties");
			prop.load(inputStream);
			try {
				Class.forName(prop.getProperty("jdbc.driverClassName"));
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This method returns the Connection object. It should get the jdbc detail from
	 * prop object
	 * 
	 * @return
	 */
	public Connection connect() {
		try {

			if (con == null) {
				String url = prop.getProperty("jdbc.url");
				String userName = prop.getProperty("jdbc.userName");
				String password = prop.getProperty("jdbc.password");
				con = DriverManager.getConnection(url, userName, password);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return con;
	}

	/**
	 * this method will return the object of PreparedStatement if created otherwise
	 * error message is generated
	 * 
	 * @param query
	 * @return object of PreparedStatement
	 */
	public PreparedStatement createPreparedStatement(String query) {

		try {
			connect();
			pstmt = con.prepareStatement(query);
			return pstmt;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	private void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closePreparedStatement() {
		try {
			closeConnection();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
