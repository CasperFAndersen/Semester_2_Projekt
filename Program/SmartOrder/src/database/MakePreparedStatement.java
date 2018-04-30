package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * SmartOrder
 * MakePreparedStatement.java
 * Purpose: To make preparedstatements without having to call the DBConnection class every time its being initialized.
 * @author Gruppe 1
 * @version 1.0 
 */
public class MakePreparedStatement {
	
	/**
	 * Used to make PreparedStatement instead of calling DBConnection.getInstance().getConnection().prepareStatement(...) each time a is being initialized
	 * @param statement as String
	 * @return PreparedStatement
	 * @throws SQLException
	 */
	public PreparedStatement makePreparedStatement(String statement) throws SQLException {
		return DBConnection.getInstance().getConnection().prepareStatement(statement);
	}
	
	/**
	 * Used to make PreparedStatements like to one above, but also returns generated keys.
	 * @param statement as String, keys as int
	 * @return PreparedStatement and Generated Keys
	 * @throws SQLException
	 */
	public PreparedStatement makePreparedStatement(String statement, int keys) throws SQLException {
		return DBConnection.getInstance().getConnection().prepareStatement(statement, keys);
	}

}
