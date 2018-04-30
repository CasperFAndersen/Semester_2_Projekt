package database;

import model.Discount;

public interface DiscountDBIF {
	Discount findById(int id);
}
