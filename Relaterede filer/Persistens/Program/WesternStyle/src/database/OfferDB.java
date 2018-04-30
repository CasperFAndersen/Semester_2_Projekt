package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Offer;
import model.OrderCondition;

public class OfferDB implements OrderConditionDBIF {

	private static final String findAllQ = "SELECT id, create_date, discount, sent_Date, due_date, accept_date FROM offer";
	private static final String findByIdQ = findAllQ + " WHERE id = ?";
	private static final String insertOfferQ = "INSERT INTO offer VALUES (?, ?, ?, ?, ?, ?)";

	private PreparedStatement findAll, findById, insertOffer;

	public OfferDB() {
		try {
			findAll = DBConnection.getInstance().getConnection().prepareStatement(findAllQ);
			findById = DBConnection.getInstance().getConnection().prepareStatement(findByIdQ);
			insertOffer = DBConnection.getInstance().getConnection().prepareStatement(insertOfferQ);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderCondition findById(int id) {
		ResultSet rs;
		Offer res = null;
		try {
			findById.setInt(1, id);
			rs = findById.executeQuery();
			if(rs.next()) {
				res = (Offer) buildObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public void insertOffer(Offer offer) throws SQLException{
		insertOffer.setInt(1, offer.getId());
		insertOffer.setDate(2, java.sql.Date.valueOf(offer.getCreateDate()));
		insertOffer.setDouble(3, offer.getDiscount());
		insertOffer.setDate(4, java.sql.Date.valueOf(offer.getSentDate()));
		insertOffer.setDate(5, java.sql.Date.valueOf(offer.getDueDate()));
		insertOffer.setDate(6, java.sql.Date.valueOf(offer.getAcceptDate()));
		insertOffer.executeUpdate();
	}

	private OrderCondition buildObject(ResultSet rs) throws SQLException {
		Offer offer = new Offer(
				rs.getDate("create_date").toLocalDate(), 
				rs.getDate("sent_Date").toLocalDate(),
				rs.getDate("due_date").toLocalDate(), 
				rs.getDate("accept_date").toLocalDate(),
				rs.getDouble("discount")
				);
		return offer;
	}

}
