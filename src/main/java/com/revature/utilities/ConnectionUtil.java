package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.revature.services.ReimbDAO;
import com.revature.services.ReimbDAOImpl;
import com.revature.services.UserDAO;
import com.revature.services.UserDAOImpl;


public class ConnectionUtil {

	private static Connection connection;
	
	private static final String url = System.getenv("AWS_DB2");
	private static final String username = System.getenv("CONNECTION_USERNAME");
	private static final String password = System.getenv("CONNECTION_PASSWORD");
	

	public static Connection getConnectionFromEnv() throws SQLException
	{
		if(connection == null || connection.isClosed()) {
		connection = DriverManager.getConnection(url, username, password);
		}
		
		return connection;
	}

	public static Connection getConnection() throws SQLException{
			if(connection==null || connection.isClosed()){
				try {
					Class.forName("org.postgresql.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
                connection = DriverManager.getConnection(url, username, password);
                

			}
            if (connection.isClosed()){
                connection = DriverManager.getConnection(url, username, password);
            }
		return connection;
	}
	
	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
	}
	
	public static ReimbDAO getReimbDAO() {
		return new ReimbDAOImpl();
	}
}
