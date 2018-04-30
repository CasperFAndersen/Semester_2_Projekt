package database;

import java.sql.SQLException;

import model.SalesOrderLine;

/**
 * SmartOrder
 * SalesOrderLineDBIF.java
 * Purpose: Interface for SalesOrderLine
 * @author Gruppe 1
 * @version 1.0 
 */
public interface SalesOrderLineDBIF {
	void insertSalesOrderLine(int orderId, SalesOrderLine salesOrderLine) throws SQLException;

}
