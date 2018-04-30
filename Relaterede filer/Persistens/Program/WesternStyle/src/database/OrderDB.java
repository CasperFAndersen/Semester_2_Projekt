package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Order;
import model.OrderCondition;

public class OrderDB implements OrderConditionDBIF {

	private static final String findByIdQ ="SELECT * FROM orders WHERE id = ?";
	private static final String insertQ = "INSERT INTO orders VALUES(?, ?, ?)";

	private PreparedStatement findById, insert; 

	public OrderDB() {
		try {
			findById = DBConnection.getInstance().getConnection().prepareStatement(findByIdQ);
			insert = DBConnection.getInstance().getConnection().prepareStatement(insertQ);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderCondition findById(int id) {
		ResultSet rs;
		try {
			findById.setInt(1, id);
			rs = findById.executeQuery();
			if(rs.next()) {
				return buildObject(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public OrderCondition buildObject(ResultSet rs) throws SQLException {
		Order res = new Order(); 
		res.setId(rs.getInt("id"));
		res.setCreateDate(rs.getDate("create_date").toLocalDate());
		res.setPackDate(rs.getDate("pack_date").toLocalDate());
		;
		return res;
	}

	public void insertOrder(Order order) throws SQLException{
		insert.setInt(1, order.getId());
		insert.setDate(2, java.sql.Date.valueOf(order.getCreateDate()));
		insert.setDate(3, java.sql.Date.valueOf(order.getPackDate()));
		insert.executeUpdate();
	}

}
