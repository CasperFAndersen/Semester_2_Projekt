package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.InsertFailedException;
import exception.NotImplementedException;
import exception.PhoneAlreadyExistException;
import exception.ZipAlreadyExistException;
import model.Product;
import model.ProductPrice;
import model.ProductType;

/**
 * SmartOrder
 * ProductDBIF.java
 * Purpose: Interface for ProductDB
 * @author Gruppe 1
 * @version 1.0 
 */
public interface ProductDBIF {
	int insertProduct(Product product, ProductType productType) throws SQLException, InsertFailedException;
	int insertTemplateProduct(Product product) throws SQLException, InsertFailedException;
	int insertProductType(ProductType productType) throws SQLException, InsertFailedException;
	void insertPartOfProduct(int moduleId, int productId) throws SQLException;
	List<Product> findAll();
	Product findProductByModel(String model);
	List<Product> findModulesByProductId(int id);
	ArrayList<ProductType> findAllProductTypes() throws SQLException;
	Product findProductById(int id);
	Product findTemplateByID(int id);
	void updateProductById() throws NotImplementedException; 
	void updateProductsProductType(int productId, int productTypeId) throws SQLException;
	int insertProductPrice(ProductPrice productPrice) throws SQLException, InsertFailedException;
	
}
