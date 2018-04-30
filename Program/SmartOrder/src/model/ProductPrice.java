package model;

import java.time.LocalDate;

/**
 * SmartOrder
 * ProductPrice.java
 * Purpose: Creates an ProductPrice for a Product.
 * @author Gruppe 1
 * @version 1.0 
 */
public class ProductPrice {
	private int id;
	private double purchasePrice;
	private double salesPrice;
	private LocalDate fromDate;
	
	public ProductPrice(double purchasePrice, double salesPrice, LocalDate fromDate) {
		setPurchasePrice(purchasePrice);
		setSalesPrice(salesPrice);
		setFromDate(fromDate);
	}
	
	public ProductPrice(int id, double purchasePrice, double salesPrice, LocalDate fromDate) {
		setId(id);
		setPurchasePrice(purchasePrice);
		setSalesPrice(salesPrice);
		setFromDate(fromDate);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getPurchasePrice() {
		return purchasePrice;
	}
	
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	public double getSalesPrice() {
		return salesPrice;
	}
	
	public void setSalesPrice (double salesPrice) {
		this.salesPrice = salesPrice;
	}
	
	public LocalDate getFromDate() {
		return fromDate;
	}
	
	public void setFromDate (LocalDate fromDate) {
		this.fromDate = fromDate;
	}
}
