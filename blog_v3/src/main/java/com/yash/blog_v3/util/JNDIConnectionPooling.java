package com.yash.blog_v3.util;


import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
public abstract class JNDIConnectionPooling {

	
	private Connection con = null;
	private PreparedStatement pstmt = null;

	
	/**
	 * This method returns the Connection object. It should get the jdbc detail from
	 * prop object
	 * 
	 * @return
	 */
	public Connection connect() {
		try {

			Context ctx = new InitialContext();
			Context envContext  = (Context) ctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/blogdb");
				con = ds.getConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}
