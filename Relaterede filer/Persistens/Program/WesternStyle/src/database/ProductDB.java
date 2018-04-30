package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDB implements ProductDBIF {

	private static final String findAllQ = "SELECT id, name, purchase_price, sales_price, country_of_Origin, min_stock, amount FROM product";
	private static final String findByNameQ = findAllQ + " WHERE name = ?";
	private static final String findByIdQ = findAllQ + " WHERE id = ?";	
	private static final String updateQ = "update product set amount = ? where id = ?";
	private PreparedStatement findAll, findByName, findById, update;

	public ProductDB() {
		try { 
			findAll = DBConnection.getInstance().getConnection().prepareStatement(findAllQ);
			findByName = DBConnection.getInstance().getConnection().prepareStatement(findByNameQ);
			findById = DBConnection.getInstance().getConnection().prepareStatement(findByIdQ);
			update = DBConnection.getInstance().getConnection().prepareStatement(updateQ);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> findAll() {
		ResultSet rs; 
		try { 
			rs = findAll.executeQuery();
			List<Product> res = buildObjects(rs);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Product findByProductName(String name) {
		ResultSet rs; 
		Product res = null;
		try {
			findByName.setString(1, name);
			rs = findByName.executeQuery();
			if(rs.next()) {
				res = buildObject(rs);
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	private Product buildObject(ResultSet rs) throws SQLException {
		Product p = new Product();
		p.setId(rs.getInt("id"));
		p.setName(rs.getString("name"));
		p.setPurchasePrice(rs.getDouble("purchase_Price"));
		p.setSalesPrice(rs.getDouble("sales_Price"));
		p.setAmount(rs.getInt("amount"));
		p.setMinStock(rs.getInt("min_Stock"));
		p.setCountryOfOrigin(rs.getString("country_Of_Origin"));
		return p;
	}

	private ArrayList<Product> buildObjects(ResultSet rs) throws SQLException { 
		ArrayList<Product>  res = new ArrayList<>();
		while(rs.next()) {
			Product p = buildObject(rs);
			res.add(p);
		}
		return res;
	}

	public Product findProductById(int id) {
		ResultSet rs; 
		Product res = null;
		try {
			findById.setInt(1, id);
			rs = findById.executeQuery();
			if(rs.next()) {
				res = buildObject(rs);
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void updateProductAmount(int id, int amount) throws SQLException{
		try{
		Product p = findProductById(id);
		int temp = p.getAmount();
		int newAmount = temp-amount;
		update.setInt(1, newAmount);
		update.setInt(2, id);
		update.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
