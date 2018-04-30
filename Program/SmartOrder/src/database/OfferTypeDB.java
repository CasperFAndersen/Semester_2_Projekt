package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.OfferType;
import model.OrderCondition;

/**
 * SmartOrder
 * OfferTypeDB.java
 * Purpose: Handle OfferType object interaction from and to the database
 * @author Gruppe 1
 * @version 1.0 
 */
public class OfferTypeDB implements OrderConditionDBIF {
	
	
	private MakePreparedStatement mps = new MakePreparedStatement();
	/**
	 * PreparedStatements for the OfferType table in the database.
	 */
	private static final String findAllQ = "SELECT * FROM OfferType";
	private static final String findByIdQ = findAllQ + " WHERE id = ?";
	private static final String insertQ = "INSERT INTO OfferType VALUES(?, ?, ?, ?)";

	private PreparedStatement findAll, findById, insert;

	public OfferTypeDB() {
		try {
			findAll = mps.makePreparedStatement(findAllQ);
			findById = mps.makePreparedStatement(findByIdQ);
			insert = mps.makePreparedStatement(insertQ);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Finds a OfferType in the database based on a id.
	 * @param id as int
	 * @return an OfferType object
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
	 * Creates a OfferType Object from an ResultSet, which is filled with information retrieved from the database using a find method.
	 * @param rs as ResultSet
	 * @return A OfferType object
	 */
	private OrderCondition buildObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		LocalDate createDate = rs.getDate("create_date").toLocalDate();
		LocalDate dueDate = rs.getDate("due_date").toLocalDate();
		LocalDate acceptDate = rs.getDate("accept_date").toLocalDate();
		return new OfferType(id, createDate, dueDate, acceptDate);
	}

	/**
	 * Inserts information in the OfferType table in the database.
	 * @param offer as OfferType
	 */
	public void insertOfferType(OfferType offer) throws SQLException {
		insert.setInt(1, offer.getId());
		insert.setDate(2, java.sql.Date.valueOf(offer.getCreateDate()));
		insert.setDate(3, java.sql.Date.valueOf(offer.getDueDate()));
		insert.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
		insert.execute();
	}
}
