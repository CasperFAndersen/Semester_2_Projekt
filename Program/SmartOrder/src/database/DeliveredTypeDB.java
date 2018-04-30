package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.DeliveredType;
import model.OrderCondition;

/**
 * SmartOrder
 * DeliveredTypeDB.java
 * Purpose: Handle DeliveredType object interaction from and to the database
 * @author Gruppe 1
 * @version 1.0 
 */
public class DeliveredTypeDB implements OrderConditionDBIF {

	private MakePreparedStatement mps = new MakePreparedStatement();
	
	/**
	 * PreparedStatements for the DeliveredType table in the database.
	 */
	private static final String findAllQ = "SELECT * FROM DeliveredType";
	private static final String findByIdQ = findAllQ + " WHERE id = ?";
	private static final String insertQ = "INSERT INTO DeliveredType VALUES(?, ?)";

	private PreparedStatement findById, insert;

	public DeliveredTypeDB() {
		try {
			findById = mps.makePreparedStatement(findByIdQ);
			insert = mps.makePreparedStatement(insertQ);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Finds a DeliveredType in the database based on a id.
	 * @param id of the DeliveredType which is to be found
	 * @return an DeliveredType object
	 */
	@Override
	public OrderCondition findById(int id) {
		ResultSet rs; 
		OrderCondition res = null;
		try {
			findById.setInt(1, id);
			rs = findById.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Creates a DeliveredType Object from an ResultSet, which is filled with information retrieved from the database using a find method.
	 * @param a ResultSet which holds the information retrieved from the database with the help of a preparedstatement. In this case an DeliveredType.
	 * @return A DeliveredType object
	 */
	private OrderCondition buildObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		LocalDate date = rs.getDate("date").toLocalDate();
		return new DeliveredType(id, date);
	}

	/**
	 * Inserts information in the DeliveredType table in the database.
	 * @param DeliveredType object
	 */
	public void insertDeliveredType(DeliveredType orderCondition) throws SQLException {
		insert.setInt(1, orderCondition.getId());
		insert.setDate(2, java.sql.Date.valueOf(orderCondition.getDate()));
		insert.executeUpdate();
	}
}
