package com.evg.test;

import java.sql.*;


public class Conexion {
	private String jdbcURL;
	private String jdbcUserName;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public Conexion(String url, String userName, String password) {
		jdbcURL = url;
		jdbcUserName = userName;
		jdbcPassword = password;
	}
	
	public void connection() throws SQLException {
		
		//reutilizamos si ya existe una conexion
		if (jdbcConnection==null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException();
			}
			jdbcConnection=DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
		}
	}
	
	public void discconect() throws SQLException {
			jdbcConnection.close();
	}
	
	public Connection getConnection() {
		return jdbcConnection;
	}

}
