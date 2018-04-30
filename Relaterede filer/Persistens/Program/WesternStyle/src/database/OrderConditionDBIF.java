package database;

import model.OrderCondition;

public interface OrderConditionDBIF {
	OrderCondition findById(int id);
}
