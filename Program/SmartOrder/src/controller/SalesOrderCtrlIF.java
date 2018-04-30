package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Customer;
import model.Employee;
import model.OrderCondition;
import model.Product;
import model.SalesOrderLine;

/**
 * SmartOrder
 * SalesOrderCtrlIF.java
 * Purpose: Interface for SalesOrderCtrl
 * @author Gruppe 1
 * @version 1.0 
 */
public interface SalesOrderCtrlIF {
	void createSalesOrder();
	void setSalesOrderCondition(OrderCondition orderCondition);
	ArrayList<Product> findAllProducts();
	ArrayList<Customer> findAllCustomers();
	void setCustomer(Customer customer);
	void setEmployee(Employee employee);
	ArrayList<Product> findAllCustomizableProducts();
	ArrayList<Product> findAllNonCustomizableProducts();
	void saveAsOffer(LocalDate dueDate) throws SQLException;
	void addSalesOrderLineToSalesOrder(Product product, int amount);
	void setDiscountToSalesOrderLine(SalesOrderLine salesOrderLine, String discountAmount);
	void closeSalesOrder() throws SQLException;
	
}
