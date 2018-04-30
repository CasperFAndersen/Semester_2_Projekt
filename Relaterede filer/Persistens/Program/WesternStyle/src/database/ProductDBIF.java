package database;

import java.util.List;

import model.Product;

public interface ProductDBIF {
	List<Product> findAll();
	Product findByProductName(String name);
}
