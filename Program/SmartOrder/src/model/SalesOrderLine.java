package model;

import exception.IllegalDiscountAmountException;

/**
 * SmartOrder
 * SalesOrderLine.java
 * Purpose: Creates an SalesOrderLine, giving discount to the price by a subtotal.
 * @author Gruppe 1
 * @version 1.0 
 */
public class SalesOrderLine {
	private int id;
	private int amount;
	private Product product;
	private DiscountIF discountByInput;
	private double discount;
	private double subTotal;

	public SalesOrderLine(Product product, int amount) {
		setProduct(product);
		setAmount(amount);
		setSubTotal(getProductPrice() * amount);
	}

	public SalesOrderLine (int id, Product product, int amount, double discount) {
		setId(id);
		setAmount(amount);
		setProduct(product);
		setSubTotal(product.getProductPrice().getSalesPrice() * amount);
		setDiscount(discount);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getProductPrice() { 
		return subTotal = product.getProductPrice().getSalesPrice();
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public DiscountIF getDiscountIF() {
		return discountByInput;
	}
	/**
	 * Sets an discount depending on the input. Creates a new instance off a discount subclass.
	 * @param discountAmount as String
	 */
	public void setDiscount(String discountAmount)  {
		if(discountAmount.contains("%")){
			discountByInput = new DiscountByPercent(discountAmount, product);
			discount = (discountByInput.getDiscount() * amount);
			if(discount < 0 || discount > (product.getProductPrice().getSalesPrice() * amount)){
				discount = 0;
				throw new IllegalDiscountAmountException("Rabat kan ikke være under 0% eller over 100%");
			}
		}
		else if(discountAmount.isEmpty()){
			discountByInput = new DiscountTemplate(discountAmount);
		}
		else{
			discountByInput = new DiscountByAmount(discountAmount, product);
			discount = discountByInput.getDiscount();
			if(discount < 0 || discount > (product.getProductPrice().getSalesPrice() * amount)){
				discount = 0;
				throw new IllegalDiscountAmountException("Rabat kan ikke være under 0 eller over originalpris");
			}
		}
	}

	public double getSubTotal() {
		return (product.getProductPrice().getSalesPrice() * amount) - discount;
	}

	public void setSubTotal(double price) {
		this.subTotal = price;
	}
}
