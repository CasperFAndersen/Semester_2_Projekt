package database;

import java.sql.SQLException;
import java.util.List;

import exception.InsertFailedException;
import exception.PhoneAlreadyExistException;
import exception.ZipAlreadyExistException;
import model.Customer;
import model.Employee;
import model.Supplier;

/**
 * SmartOrder
 * PersonDBIF.java
 * Purpose: Interface for PersonDB
 * @author Gruppe 1
 * @version 1.0 
 */
public interface PersonDBIF {

	void insertCustomer(Customer customer) throws SQLException, PhoneAlreadyExistException, ZipAlreadyExistException, InsertFailedException;
	void insertEmployee(Employee employee) throws SQLException, PhoneAlreadyExistException, ZipAlreadyExistException, InsertFailedException;
	void insertSupplier(Supplier supplier) throws SQLException, PhoneAlreadyExistException, ZipAlreadyExistException, InsertFailedException;
	
	List<Customer> findAllCustomers();
	Customer findCustomerById(int id);
	Customer findCustomerByPhone(String phone);
	
	List<Employee> findAllEmployees();
	Employee findEmployeeById(int id);
	Employee findEmployeeByPhone(String phone);
	Employee findEmployeeByUsernameAndPassword(String username, String password) throws SQLException;
	
	List<Supplier> findAllSuppliers();
	Supplier findSupplierById(int id);
	Supplier findSupplierByPhone(String phone);

	void updateCustomerByPhone(String name, String address, String zipCode, String city, String currPhone,
			String newPhone, String email, String personType, String type) throws PhoneAlreadyExistException;
	void updateEmployeeByPhone(String name, String address, String zipCode, String city, String currPhone,
			String newPhone, String email, String personType, String ssn, double salary, String username,
			String password) throws PhoneAlreadyExistException;
	void updateSupplierByPhone(String name, String address, String zipCode, String city, String currPhone, String newPhone, 
			String email, String personType, String cvr) throws PhoneAlreadyExistException;
	
	void removeCustomerByPhone(String phone);
	void removeEmployeeByPhone(String phone);
	void removeSupplierByPhone(String phone);

	void addPhoneToPerson(String existingPhone, String extraPhone) throws SQLException, PhoneAlreadyExistException;

}
