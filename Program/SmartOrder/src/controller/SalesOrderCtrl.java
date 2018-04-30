package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import database.DBConnection;
import database.SalesOrderDB;
import database.SalesOrderDBIF;
import exception.InsertFailedException;
import exception.PersonNotFoundException;
import exception.PhoneAlreadyExistException;
import exception.ZipAlreadyExistException;
import model.Customer;
import model.Employee;
import model.OfferType;
import model.OrderCondition;
import model.OrderType;
import model.Product;
import model.SalesOrder;
import model.SalesOrderLine;

/**
 * SmartOrder
 * SalesOrderCtrl.java
 * Purpose: Handle SalesOrder
 * @author Gruppe 1
 * @version 1.0 
 */
public class SalesOrderCtrl implements SalesOrderCtrlIF {
	private SalesOrder salesOrder = null;
	private SalesOrderDBIF salesOrderDB = new SalesOrderDB();

	@Override
	public void createSalesOrder() {
		salesOrder = new SalesOrder();
	}

	public SalesOrder getOrder() {
		return salesOrder;
	}
	
	@Override
	public void setCustomer(Customer customer) {
		salesOrder.addCustomerToSalesOrder(customer);
	}

	@Override
	public void setEmployee(Employee employee){
		salesOrder.addEmployeeToSalesOrder(employee);
	}

	/**
	 * Adds a SalesOrderLine to the current SalesOrder. 
	 * If the product already is a part of a SalesOrderLine only the amount is increased.
	 */
	@Override
	public void addSalesOrderLineToSalesOrder(Product product, int amount) {
		salesOrder.addSalesOrderLineToSalesOrder(product, amount);
	}

	@Override
	public void setSalesOrderCondition(OrderCondition orderCondition) {
		salesOrder.setOrderCondition(orderCondition);
	}

	/**
	 * Adds the active salesorder to the database. 
	 * Uses transaction to ensure it is correctly inserted to the database.
	 */
	@Override
	public void closeSalesOrder() throws SQLException {
		try {
			DBConnection.startTransaction();
			salesOrder.setOrderCondition(new OrderType(salesOrder.getDatePlaced()));
			salesOrderDB.addSalesOrderToDatabase(salesOrder);
			salesOrder = null;
			DBConnection.commitTransaction();
		} catch (SQLException | PhoneAlreadyExistException | ZipAlreadyExistException | InsertFailedException | PersonNotFoundException e) {
			e.printStackTrace();
			DBConnection.rollbackTransaction();
		}
	}

	/**
	 * Adds the active salesorder to the database as a offer.
	 * Uses transaction to ensure it is correctly inserted to the database.
	 */
	@Override
	public void saveAsOffer(LocalDate dueDate) throws SQLException {
		try {
			DBConnection.startTransaction();
			salesOrder.setOrderCondition(new OfferType(salesOrder.getDatePlaced(), dueDate, null));
			salesOrderDB.addSalesOrderToDatabase(salesOrder);
			salesOrder = null;
			DBConnection.commitTransaction();
		} catch (SQLException | PhoneAlreadyExistException | ZipAlreadyExistException | InsertFailedException | PersonNotFoundException e) {
			e.printStackTrace();
			DBConnection.rollbackTransaction();
		}
	}

	@Override
	public ArrayList<Product> findAllProducts(){
		return new ProductCtrl().findAllProducts();
	}

	@Override 
	public ArrayList<Customer> findAllCustomers(){
		return new PersonCtrl().findAllCustomers();
	}

	@Override
	public ArrayList<Product> findAllCustomizableProducts(){
		return new ProductCtrl().findAllCustomizableProducts();
	}

	@Override
	public ArrayList<Product> findAllNonCustomizableProducts(){
		return new ProductCtrl().findAllNonCustomizableProducts();
	}

	@Override
	public void setDiscountToSalesOrderLine(SalesOrderLine sol, String discountAmount) {
		sol.setDiscount(discountAmount);
	}

}
