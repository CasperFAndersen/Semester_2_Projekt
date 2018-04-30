package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.OrderCondition;
import model.OrderType;

/**
 * SmartOrder
 * OrderTypeDB.java
 * Purpose: Handle OrderType object interaction from and to the database.
 * @author Gruppe 1
 * @version 1.0 
 */
public class OrderTypeDB implements OrderConditionDBIF {

	private MakePreparedStatement mps = new MakePreparedStatement();
	
	/**
	 * PreparedStatements for the OrderType table in the database.
	 */
	private static final String findAllQ = "SELECT * FROM OrderType";
	private static final String findByIdQ = findAllQ + " WHERE id = ?";
	private static final String insertQ = "INSERT INTO OrderType VALUES(?, ?, ?)";

	private PreparedStatement findAll, findById, insert;

	public OrderTypeDB() {
		try {
			findAll = mps.makePreparedStatement(findAllQ);
			findById = mps.makePreparedStatement(findByIdQ);
			insert = mps.makePreparedStatement(insertQ);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a OrderType Object from an ResultSet, which is filled with information retrieved from the database using a find method.
	 * @param rs as ResultSet
	 * @return A OrderType object
	 */
	private OrderCondition buildObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		LocalDate createDate = rs.getDate("create_date").toLocalDate();
		LocalDate packDate = rs.getDate("pack_date").toLocalDate();
		return new OrderType(id, createDate, packDate);
	}

	/**
	 * Finds a OrderType in the database based on a id.
	 * @param id as int
	 * @return an OrderType object
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
	 * Inserts information in the OrderType table in the database.
	 * @param order as OrderType
	 */
	public void insertOrderType(OrderType order) throws SQLException {
		insert.setInt(1, order.getId());
		insert.setDate(2, java.sql.Date.valueOf(order.getCreateDate()));
		insert.setDate(3, java.sql.Date.valueOf(order.getPackDate())); 
	}

}
