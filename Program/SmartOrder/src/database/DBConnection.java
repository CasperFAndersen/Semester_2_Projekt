package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SmartOrder
 * DBConnection.java
 * Purpose: This class establishes connection to sql-server. 
 * @author Gruppe 1
 * @version 1.0 
 */
public class DBConnection {

	private Connection connection = null;
	private static DBConnection dbConnection;
	
	private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String dbName = "dmab0916_202650";
//	private static final String serverAddress = "kraka.ucn.dk";
	private static final String serverAddress = "127.0.0.1";
	private static final int serverPort = 1433;
// 	private static final String username = "dmab0916_202650";
	private static final String username = "sa";
//	private static final String password = "Password1!";
	private static final String password = "sql";
	
	private DBConnection() {
		String connectionString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s", 
				serverAddress, serverPort, dbName, username, password);
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Could not connect to database " + dbName 
					+ "@" + serverAddress + ":" + serverPort + " as user " + username 
					+ " using password *******");
			System.out.println("Connection string was: " + 
					connectionString.substring(0, connectionString.length() - password.length()));
			e.printStackTrace();
		}
	}
	
	public static DBConnection getInstance() {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}
	
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}

	public int executeInsertWithIdentity(PreparedStatement prepStmt) throws SQLException {
		int res = -1;
		prepStmt.executeUpdate();
		ResultSet rs = prepStmt.getGeneratedKeys();
		if (rs.next()) {
			res = rs.getInt(1);
		}
		return res;
	}
	
	/**
	 * This method sets up the commit-procedure for sql to not autocommit. This makes it possible to group several statments
	 * to later on be executed under the same transaction.  
	 */
	public static void startTransaction() {
		try {
			dbConnection.connection.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method commits and executes a grouped set af statements. If an error occurs rollback insures that the state of the database remains unchanged.
	 */
	public static void commitTransaction() throws SQLException {
		try {
			dbConnection.connection.commit();
		} catch (Exception e) {
			dbConnection.connection.rollback();
		} finally {
			dbConnection.connection.setAutoCommit(true);
		}
	}
	
	public static void rollbackTransaction() throws SQLException {
		try {
			dbConnection.connection.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnection.connection.setAutoCommit(true);
		}
	}
	
	
}
