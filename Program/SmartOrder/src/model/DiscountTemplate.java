package model;

/**
 * SmartOrder
 * DiscountTemplate.java
 * Purpose: sets discount = 0.
 * @author Gruppe 1
 * @version 1.0 
 */
public class DiscountTemplate implements DiscountIF{
	double discount = 0;
	
	public DiscountTemplate(String amount) {
		calculateDiscount(amount);
	}
	
	@Override
	public double calculateDiscount(String amount) {
		return 0;
	}
	
	@Override
	public double getDiscount() {
		return discount;
	}

}
