package database;

import model.OrderCondition;

/**
 * SmartOrder
 * OrderConditionDBIF.java
 * Purpose: Interface for OrderConditionDB
 * @author Gruppe 1
 * @version 1.0 
 */
public interface OrderConditionDBIF {
	OrderCondition findById(int id);
}
