package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.DeliveredType;
import model.OfferType;
import model.OrderCondition;
import model.OrderType;

/**
 * SmartOrder
 * OrderConditionDB.java
 * Purpose: Handle OrderConditions object interaction from and to the database. 
 * @author Gruppe 1
 * @version 1.0 
 */
public class OrderConditionDB implements OrderConditionDBIF {

	private MakePreparedStatement mps = new MakePreparedStatement();
	
	/**
	 * PreparedStatements for the OrderCondition table in the database.
	 */
	private static final String findAllQ = "SELECT * FROM OrderCondition";
	private static final String findByIdQ = findAllQ + " where id = ?"; 
	private static final String insertOrderConditionQ = "INSERT INTO OrderCondition VALUES (?)";

	private PreparedStatement findAll, findById, insertOrderCondition;

	public OrderConditionDB() {
		try {
			findAll = mps.makePreparedStatement(findAllQ);
			findById = mps.makePreparedStatement(findByIdQ);
			insertOrderCondition = mps.makePreparedStatement(insertOrderConditionQ, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Finds an OrderCondition in the database based on a id.
	 * @param id as int
	 * @return Either a OfferType, OrderType or DeliveredType object
	 */
	@Override
	public OrderCondition findById(int id) {
		ResultSet rs;
		OrderCondition res = null;
		try {
			findById.setInt(1, id);
			rs = findById.executeQuery();
			if (rs.next()) {
				if (rs.getString("type").equalsIgnoreCase("OfferType")) {
					res = new OfferTypeDB().findById(rs.getInt("id"));
				} else if (rs.getString("type").equalsIgnoreCase("OrderType")) {
					res = new OrderTypeDB().findById(rs.getInt("id"));
				} else if (rs.getString("type").equalsIgnoreCase("DeliveredType")) {
					res = new DeliveredTypeDB().findById(rs.getInt("id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Inserts information in either the OfferType, OrderType or DeliverdType table in the database.
	 * @param orderCondition as OrderCondition
	 * @return Either a OfferType, OrderType or DeliveredType id.
	 */
	public int insertOrderCondition(OrderCondition orderCondition) throws SQLException {
		int id = -1;
		if (orderCondition instanceof OfferType) {
			insertOrderCondition.setString(1, "offerType");
			id = DBConnection.getInstance().executeInsertWithIdentity(insertOrderCondition);
			orderCondition.setId(id);
			new OfferTypeDB().insertOfferType((OfferType)orderCondition);
		} else if (orderCondition instanceof OrderType) {
			insertOrderCondition.setString(1, "orderType");
			id = DBConnection.getInstance().executeInsertWithIdentity(insertOrderCondition);
			orderCondition.setId(id);
			new OrderTypeDB().insertOrderType((OrderType)orderCondition);
		} else if (orderCondition instanceof DeliveredType) {
			insertOrderCondition.setString(1, "deliveredType");
			id = DBConnection.getInstance().executeInsertWithIdentity(insertOrderCondition);
			orderCondition.setId(id);
			new DeliveredTypeDB().insertDeliveredType((DeliveredType)orderCondition);
		}
		return id;
	}
}
