package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.SalesOrderLine;

public class SalesOrderLineDB implements SalesOrderLineDBIF {

	private static final String findAllQ = "SELECT * FROM sales_order_line";
	private static final String findByIdQ = findAllQ + " WHERE order_id = ?";
	private static final String insertSOLQ = "INSERT INTO sales_order_line VALUES(?, ?, ?)";

	private PreparedStatement findAll, findById, insertSOL;

	public SalesOrderLineDB() {
		try{
			findAll = DBConnection.getInstance().getConnection().prepareStatement(findAllQ);
			findById = DBConnection.getInstance().getConnection().prepareStatement(findByIdQ);
			insertSOL = DBConnection.getInstance().getConnection().prepareStatement(insertSOLQ);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<SalesOrderLine> findAllWithSalesOrderId(int salesOrderId) {
		ResultSet rs;
		ArrayList<SalesOrderLine> res = null;
		try{
			findById.setInt(1, salesOrderId);
			rs = findById.executeQuery();
			res = buildObjects(rs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private SalesOrderLine buildObject(ResultSet rs) throws SQLException {
		SalesOrderLine sOL = new SalesOrderLine(
				rs.getInt("amount"),
				new ProductDB().findProductById(rs.getInt("prod_id"))
				);
		sOL.setId(rs.getInt("id"));
		return sOL;
	}

	private ArrayList<SalesOrderLine> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<SalesOrderLine> res = new ArrayList<>();
		while(rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}

	public void insertSOLs(ArrayList<SalesOrderLine> sol, int salesOrderId ) throws SQLException{

		for(SalesOrderLine curr : sol) {
			insertSOL.setInt(1, curr.getAmount());
			insertSOL.setInt(2, curr.getProduct().getId());
			insertSOL.setInt(3, salesOrderId);
			insertSOL.executeUpdate();
		}
	}

}
