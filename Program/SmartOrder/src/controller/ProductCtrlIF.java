package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import exception.InsertFailedException;
import model.Product;
import model.ProductType;
import model.Property;
import model.Supplier;

/**
 * SmartOrder
 * ProductCtrlIF.java
 * Purpose: Interface for ProductCtrl
 * @author Gruppe 1
 * @version 1.0 
 */
public interface ProductCtrlIF {
	int createProduct(Product template, ProductType productType, String model, String description, Supplier supplier,
			String dimensions, double purchasePrice, double salesPrice, LocalDate fromDate) throws InsertFailedException, SQLException;
	void insertPartOfProduct(int moduleId, int productId) throws SQLException;
	void insertProperties(ArrayList<Property> properties, int productId) throws SQLException;
	ArrayList<Supplier> findAllSuppliers();
	ArrayList<Product> findAllProducts();
	Product findProductById(int id);
	Product findProductByModel(String model);
	Property createTemplateProperty(String name, String type);
	void setProperty(Property property, Object aValue);
	Property createProperty(String name, String value, String type);
	void addProductType(String categoryName, String type, ArrayList<Property> templateProperties)
			throws SQLException, InsertFailedException;
	Product findTemplateById(int id);
	ArrayList<Product> findAllCustomizableProducts();
	ArrayList<Product> findAllNonCustomizableProducts();
	ArrayList<ProductType> findAllProductTypes() throws SQLException;
	
}
