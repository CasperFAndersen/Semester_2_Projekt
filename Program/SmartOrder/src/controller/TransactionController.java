package controller;

import java.sql.SQLException;

import database.DBConnection;

/**
 * SmartOrder
 * TransactionController.java
 * Purpose: Used to remove objects from database
 * @author Gruppe 1
 * @version 1.0 
 */
public class TransactionController implements TransactionControllerIF {
	TransactionControllerIF transactionControllerIF;
	
	@Override
	public void remove(String phone) throws SQLException {
		try {
			DBConnection.startTransaction();
			transactionControllerIF.remove(phone);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
	}

}
