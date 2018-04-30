package database;

import java.sql.SQLException;
import java.util.List;

import exception.InsertFailedException;
import exception.PersonNotFoundException;
import exception.PhoneAlreadyExistException;
import exception.ZipAlreadyExistException;
import model.SalesOrder;

/**
 * SmartOrder
 * SalesOrderDBIF.java
 * Purpose:  Interface for SalesOrderDB
 * @author Gruppe 1
 * @version 1.0 
 */
public interface SalesOrderDBIF {
	List<SalesOrder> findAll();
	SalesOrder findById(int id);
	void addSalesOrderToDatabase(SalesOrder salesOrder) throws SQLException, PhoneAlreadyExistException, ZipAlreadyExistException, InsertFailedException, PersonNotFoundException;
	
}
