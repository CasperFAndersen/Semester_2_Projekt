package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Delivered;
import model.Offer;
import model.Order;
import model.OrderCondition;

public class OrderConditionDB implements OrderConditionDBIF {

	private static final String findAllQ = "SELECT * FROM order_condition";
	private static final String findByIdQ = findAllQ + " WHERE id = ?";
	private static final String insertOCQ = "INSERT INTO order_condition VALUES (?)";

	private PreparedStatement findAll, findById, findType, insertOC;

	public OrderConditionDB() {
		try{
			findAll = DBConnection.getInstance().getConnection().prepareStatement(findAllQ);
			findById = DBConnection.getInstance().getConnection().prepareStatement(findByIdQ);
			insertOC = DBConnection.getInstance().getConnection().prepareStatement(insertOCQ, insertOC.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderCondition findById(int id) {
		ResultSet rs;
		OrderCondition res = null;
		try{
			findById.setInt(1, id);
			rs = findById.executeQuery();
			if(rs.next()) {
				if(rs.getString("type").equalsIgnoreCase("Offer")) {
					res = new OfferDB().findById(id);
				}
				else if (rs.getString("type").equalsIgnoreCase("Order")) {
					res = new OrderDB().findById(id);
				}
				else if (rs.getString("type").equalsIgnoreCase("Delivered")) {
					res = new DeliveredDB().findById(id);
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public int insertOrderCondition(OrderCondition oc) throws SQLException {
		int id = -1;
		if(oc instanceof Offer) {
			insertOC.setString(1, "offer");
			id = DBConnection.getInstance().executeInsertWithIdentity(insertOC);
			oc.setId(id);
			new OfferDB().insertOffer((Offer)oc);
			return id;
		}

		if(oc instanceof Order) {
			insertOC.setString(1, "order");
			id = DBConnection.getInstance().executeInsertWithIdentity(insertOC);
			oc.setId(id);
			new OrderDB().insertOrder((Order) oc);
			return id;
		}

		if(oc instanceof Delivered) {
			insertOC.setString(1, "delivered");
			id = DBConnection.getInstance().executeInsertWithIdentity(insertOC);
			oc.setId(id);
			new DeliveredDB().insertDelivered((Delivered) oc);
			return id;
		}
		return 0;
	}

}
