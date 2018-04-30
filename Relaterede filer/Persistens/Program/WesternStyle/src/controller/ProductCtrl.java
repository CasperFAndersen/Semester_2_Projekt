package controller;

import java.sql.SQLException;
import java.util.List;

import database.ProductDB;
import model.Product;

public class ProductCtrl {
	ProductDB pDB = new ProductDB();
	
	public Product findProduct(String productName) {
		Product res = pDB.findByProductName(productName);
		return res;
	}

	public List<Product> findAll() {
		return pDB.findAll();
	}
	
	public void updateProductAmount(int id, int amount) throws SQLException{
		pDB.updateProductAmount(id, amount);
	}
}
