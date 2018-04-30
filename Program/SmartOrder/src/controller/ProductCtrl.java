package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import database.DBConnection;
import database.ProductDB;
import database.ProductDBIF;
import database.PropertyDB;
import exception.InsertFailedException;
import exception.NotImplementedException;
import model.Product;
import model.ProductPrice;
import model.ProductType;
import model.Property;
import model.PropertyFactory;
import model.Supplier;

/**
 * SmartOrder
 * ProductCtrl.java
 * Purpose: Handle Product-classes
 * @author Gruppe 1
 * @version 1.0 
 */
public class ProductCtrl implements ProductCtrlIF {
	private ProductDBIF productDB = new ProductDB();

	/**
	 * Creates a product object and adds it to the database. 
	 * Uses transaction to ensure the database will rollback if the process fails.
	 * @return The generated id of the product
	 */
	@Override
	public int createProduct(Product template, ProductType pt, String model, String description, 
			Supplier supplier, String dimensions, double purchasePrice, double salesPrice,
			LocalDate fromDate) throws InsertFailedException, SQLException {
		Product p = template;
		ProductPrice pp = new ProductPrice(purchasePrice, salesPrice, fromDate);
		p.setModel(model);
		p.setDescription(description);
		p.setDimensions(dimensions);
		p.setSupplier(supplier);
		p.setProductPrice(pp);
		int id = -1;
		try {
			DBConnection.startTransaction();
			id= productDB.insertProduct(p, pt);
			insertProperties(p.getProperties(), id);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			DBConnection.rollbackTransaction();
		}
		return id;
	}
	
	@Override
	public Property createTemplateProperty(String name, String type) {
		return new PropertyFactory().createTemplateProperty(name, type);
	}

	@Override
	public Property createProperty(String name, String value, String type) {
		return new PropertyFactory().createProperty(name, value, type);
	}
	
	@Override
	public void insertPartOfProduct(int moduleId, int productId) throws SQLException {
		productDB.insertPartOfProduct(moduleId, productId);
	}

	@Override
	public void insertProperties(ArrayList<Property> properties, int productId) throws SQLException {
		PropertyDB propertyDB = new PropertyDB();
		for (Property p : properties) {
			if (p != null) {
				propertyDB.insertProperty(p, productId);
			}
		}
	}
	
	/**
	 * Adds a new product type to the database. 
	 */
	@Override
	public void addProductType(String categoryName, String type, ArrayList<Property> templateProperties) throws SQLException, InsertFailedException {
		PropertyDB propertyDB = new PropertyDB();
		ProductType pt = new ProductType(categoryName, type);
		Product p = new Product();
		p.setModel(categoryName+"Template");
		p.setProductType(pt);
		int templId = productDB.insertTemplateProduct(p);
		pt.setTemplateId(templId);
		int prodTypeId = productDB.insertProductType(pt);
		productDB.updateProductsProductType(templId, prodTypeId);
		for (Property tempProp : templateProperties) {
			propertyDB.insertTemplateProperty(tempProp, templId);
		}
	}
	
	@Override
	public Product findProductByModel(String model) {
		return productDB.findProductByModel(model);
	}

	@Override
	public Product findProductById(int id) {
		return productDB.findProductById(id);
	}

	@Override
	public ArrayList<Product> findAllProducts() {
		ArrayList<Product> products = (ArrayList<Product>) productDB.findAll();
		for (Product p : products) {
			productDB.findModulesByProductId(p.getId());
		}
		return products;
	}

	@Override
	public ArrayList<Product> findAllCustomizableProducts(){
		ArrayList<Product> res = new ArrayList<>();
		for (Product p : findAllProducts()) {
			if (p.getProductType().getType().equalsIgnoreCase("Customizable")) {
				res.add(p);
			}
		}
		return res;
	}

	@Override
	public ArrayList<Product> findAllNonCustomizableProducts(){
		ArrayList<Product> res = new ArrayList<>();
		for (Product p : findAllProducts()) {
			if (p.getProductType().getType().equalsIgnoreCase("NonCustomizable")) {
				res.add(p);
			}
		}
		return res;
	}

	@Override
	public ArrayList<ProductType> findAllProductTypes() throws SQLException {
		return productDB.findAllProductTypes();
	}

	@Override
	public void setProperty(Property property, Object aValue) {
		property.setValue(aValue);
	}

	@Override
	public ArrayList<Supplier> findAllSuppliers() {
		return new PersonCtrl().findAllSuppliers();
	}


	@Override
	public Product findTemplateById(int id) {
		return productDB.findTemplateByID(id);
	}

	public void updateProductById() throws NotImplementedException {
		throw new NotImplementedException();
	}
	

	
}
