package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * SmartOrder
 * Product.java
 * Purpose: Creates an product and holds the information belonging to that product, this also includes other products and properties.
 * @author Gruppe 1
 * @version 1.0 
 */
public class Product {
	private int id;
	private String model;
	private String description;
	private String dimensions;
	private ProductPrice productPrice; 
	private LinkedList<Product> modules; 
	private List<Property> properties = null;
	private Supplier supplier;
	private ProductType productType;
	
	public Product(){
		properties = new ArrayList<>();
	}
	
	public Product(int id, String model, String description, String dimensions, ProductPrice productprice, Supplier supplier, ArrayList<Property> properties) {
		setId(id);
		setModel(model);
		setDescription(description);
		setDimensions(dimensions);
		setProductPrice(productprice);
		modules = new LinkedList<>();
		setProperties(properties);
		setSupplier(supplier);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String name) {
		this.model = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public Supplier getSupplier(){
		return supplier;
	}
	
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public void addModuleToProduct(Product product) {
		modules.add(product);
	}

	public void removeModuleFromProduct(Product product) {
		modules.remove(product);
	}

	public ProductPrice getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(ProductPrice productPrice) {
		this.productPrice = productPrice;
	}
	
	public void setProductSalesPrice(double price) {
		productPrice.setSalesPrice(price);
	}
	
	public LinkedList<Product> getModules() {
		return new LinkedList<Product>(modules);
	}

	public void addPropertyToProduct(Property property) {
		properties.add(property);
	}

	public ArrayList<Property> getProperties() {
		return new ArrayList<>(properties);
	}
	
	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	
}
