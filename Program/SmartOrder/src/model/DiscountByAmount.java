package model;

/**
 * SmartOrder
 * DiscountByAmount.java
 * Purpose: Creates a discount by a given amount. 
 * @author Gruppe 1
 * @version 1.0 
 */
public class DiscountByAmount implements DiscountIF  {
	double discount;
	Product product;

	public DiscountByAmount(String amount, Product product) {
		calculateDiscount(amount);
		this.product = product;
	}

	/**
	 * returns discount to the value of the parameter
	 */
	@Override
	public double calculateDiscount(String amount) {
		double amountCheaper = Double.parseDouble(amount);
		return discount = amountCheaper;
	}

	@Override
	public double getDiscount() {
		return discount;
	}
}
