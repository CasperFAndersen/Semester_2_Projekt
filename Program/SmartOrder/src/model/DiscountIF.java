package model;

/**
 * SmartOrder
 * DiscountIF
 * Purpose: Interface for the three discount classes.
 * @author Gruppe 1
 * @version 1.0 
 */
public interface DiscountIF {
	double calculateDiscount(String amount);
	double getDiscount();
}
