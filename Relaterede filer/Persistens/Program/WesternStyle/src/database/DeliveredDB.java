package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.Delivered;
import model.OrderCondition;

public class DeliveredDB implements OrderConditionDBIF {

	private static final String findByIdQ = "SELECT * FROM delivered WHERE id = ?";
	private static final String insertQ = "INSERT INTO delivered VALUES(?, ?)";
	private PreparedStatement findById, insert;

	public DeliveredDB() {
		try{
			findById = DBConnection.getInstance().getConnection().prepareStatement(findByIdQ);
			insert = DBConnection.getInstance().getConnection().prepareStatement(insertQ);
		} catch (Exception e) {
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
				res = buildObject(rs);
			}
			return res;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Delivered buildObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		LocalDate date = rs.getDate("delivery_date").toLocalDate();

		Delivered delivered = new Delivered(id, date);
		delivered.setDate(date);

		return delivered;
	}

	public void insertDelivered(Delivered delivered) throws SQLException{
		insert.setInt(1, delivered.getId());
		insert.setDate(2, java.sql.Date.valueOf(delivered.getDate()));
		insert.executeUpdate();
	}

}
