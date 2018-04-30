package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Product;
import model.SalesOrderLine;

/**
 * SmartOrder
 * SalesOrderLineDB.java
 * Purpose: 
 * @author Gruppe 1
 * @version 1.0 
 */
public class SalesOrderLineDB implements SalesOrderLineDBIF{

	/**
	 * PreparedStatements for the SalesOrderLine table in the database.
	 */
	private static final String findAllQ = "SELECT * FROM salesorderline ";
	private static final String findByOrderIdQ = findAllQ + " where order_id = ?";
	
	private static final String insertQ = "INSERT INTO salesorderline VALUES (?, ?, ?)"; 
	
	private PreparedStatement findAll, findByOrderId, insert;
	private MakePreparedStatement mps = new MakePreparedStatement();
	
	public SalesOrderLineDB(){
		try {
			findAll = mps.makePreparedStatement(findAllQ);
			findByOrderId = mps.makePreparedStatement(findByOrderIdQ);
			insert = mps.makePreparedStatement(insertQ);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private SalesOrderLine buildObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		Product product = new ProductDB().findProductById(rs.getInt("product_id"));
		int amount = rs.getInt("amount");
		double discount = 0; //TODO Skal implementeres
		return new SalesOrderLine(id, product, amount, discount);
	}
	
	
	private ArrayList<SalesOrderLine> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<SalesOrderLine> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}
	

	public ArrayList<SalesOrderLine> findSalesOrderLinesBySalesOrderId(int orderId) {
		ResultSet rs;
		ArrayList<SalesOrderLine> res = null;
		try {
			findByOrderId.setInt(1, orderId);
			rs = findByOrderId.executeQuery();
			res = buildObjects(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Inserts a SalesOrderLine into the database.
	 * @param orderId as int
	 * @param salesOrderLine as SalesOrderLine
	 */
	@Override
	public void insertSalesOrderLine(int orderId, SalesOrderLine salesOrderLine) throws SQLException{
		insert.setInt(1, orderId);
		insert.setInt(2, salesOrderLine.getProduct().getId());
		insert.setInt(3, salesOrderLine.getAmount());
		insert.execute();
	}

}
