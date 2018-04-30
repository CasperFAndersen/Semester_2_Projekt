package controller;

import java.sql.SQLException;

/**
 * SmartOrder
 * TransactionControllerIF.java
 * Purpose: Interface for TransactionController
 * @author Gruppe 1
 * @version 1.0 
 */
@FunctionalInterface
public interface TransactionControllerIF {
	void remove(String phone) throws SQLException;

}
