package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Discount;

public class DiscountDB implements DiscountDBIF {

	private static final String findAllQ ="SELECT * FROM discount";
	private static final String findByIdQ = findAllQ + " WHERE id = ?";
	private static final String insertQ = "INSERT INTO disount VALUES(?,?,?,?)";

	private PreparedStatement findAll, findById, insert;

	public DiscountDB() {
		try {
			findAll = DBConnection.getInstance().getConnection().prepareStatement(findAllQ);
			findById = DBConnection.getInstance().getConnection().prepareStatement(findByIdQ);
			insert = DBConnection.getInstance().getConnection().prepareStatement(insertQ);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Discount findById(int id) {
		ResultSet rs;
		Discount res = null;
		try{
			findById.setInt(1, id);
			rs = findById.executeQuery();
			if(rs.next()) {
				res = buildDiscountObject(rs);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private Discount buildDiscountObject(ResultSet rs) throws SQLException {
		Discount d = new Discount(
				rs.getInt("id"),
				rs.getDouble("club_Discount_Limit"),
				rs.getDouble("private_discount_Limit"),
				rs.getDouble("club_Discount_Amount"),
				rs.getDouble("private_Discount_Amount")
				);
		return d;
	}

}
