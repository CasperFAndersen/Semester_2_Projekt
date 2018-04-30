package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private Connection connection = null;
	private static DBConnection dbConnection;

	private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String dbName = "dmab0916_200726";
	private static final String serverAddress = "kraka.ucn.dk";
	private static final int serverPort = 1433;
	private static final String username = "dmab0916_200726";
	private static final String password = "Password1!";

	private DBConnection() {
		String connectionString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s", 
				serverAddress, serverPort, dbName, username, password);
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			System.out.println("Could not load JDBC driver");
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
		if(dbConnection == null) {
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

	public int executeUpdate(String sql) throws SQLException {
		int res = -1;
		try (Statement s = connection.createStatement()) {
			res = s.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}


	public int executeInsertWithIdentity(String sql) throws SQLException {
		int res = -1;
		try(Statement s = connection.createStatement()) {
			res = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if (res > 0) {
				ResultSet rs = s.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}

	public int executeInsertWithIdentity(PreparedStatement pstmt) throws SQLException  {
		int res = -1;
		try(PreparedStatement ps = pstmt) {
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}

	public Connection getConnection() {
		return connection;
	}
}
