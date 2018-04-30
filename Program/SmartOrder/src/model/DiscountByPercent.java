package model;

/**
 * SmartOrder
 * DiscountByPercent.java
 * Purpose: Creates a discount by procent.
 * @author Gruppe 1
 * @version 1.0 
 */
public class DiscountByPercent implements DiscountIF{
	double discount;
	Product product;

	public DiscountByPercent(String amount, Product product) {
		this.product = product;
		calculateDiscount(amount);
	}
	
	/**
	 * returns res as a new product price depending on the parameter
	 * sets discount to the products sales price - res
	 */
	public double calculateDiscount(String amount) {
		double res = 0;
		if (amount.contains("%")) {
			String temp = amount.replace("%", "");
			double discountAmount = Double.parseDouble(temp);
			res = product.getProductPrice().getSalesPrice() * (1 - (discountAmount / 100));
			discount = product.getProductPrice().getSalesPrice() - res;
		}
		return res; 
	}

	@Override
	public double getDiscount() {
		return discount;
	}

}
