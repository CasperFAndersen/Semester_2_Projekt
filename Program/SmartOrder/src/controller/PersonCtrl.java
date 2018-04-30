package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import database.DBConnection;
import database.PersonDB;
import database.PersonDBIF;
import exception.AddressValidationException;
import exception.CityValidationException;
import exception.EmailValidationException;
import exception.InsertFailedException;
import exception.NameValidationException;
import exception.NotImplementedException;
import exception.PhoneAlreadyExistException;
import exception.PhoneValidationException;
import exception.ZipAlreadyExistException;
import exception.ZipCodeValidationException;
import model.Customer;
import model.Employee;
import model.Supplier;

/**
 * SmartOrder
 * PersonCtrl.java
 * Purpose: Handle Person-objects. 
 * @author Gruppe 1
 * @version 1.0 
 */
public class PersonCtrl implements PersonCtrlIF {
 	private PersonDBIF personDB = new PersonDB();

	/**
	 * Creates a customer object and adds it to the database. 
	 * Uses transaction to ensure the database will rollback if the process fails.
	 * @return The generated customer object
	 */
	@Override
	public Customer createCustomer(String name, String address, String zipCode, String city, String phone, String email,
			String type) throws SQLException, PhoneAlreadyExistException, ZipAlreadyExistException, InsertFailedException, 
				NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, 
				PhoneValidationException, EmailValidationException {
		Customer customer = new Customer(name, address, zipCode, city, phone, email, type);
		try {
			DBConnection.startTransaction();
			personDB.insertCustomer(customer);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
		return customer;
	}

	/**
	 * Creates a employee object and adds it to the database. 
	 * Uses transaction to ensure the database will rollback if the process fails.
	 * @return The generated employee object
	 */
	@Override
	public Employee createEmployee(String name, String address, String zipCode, String city, String phone, String email,
			String ssn, double salary, String username, String password) throws SQLException, PhoneAlreadyExistException, 
					ZipAlreadyExistException, InsertFailedException, NameValidationException, AddressValidationException, 
					ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		Employee employee = new Employee(name, address, zipCode, city, phone, email, ssn, salary, username, password);
		try {
			DBConnection.startTransaction();
			personDB.insertEmployee(employee);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
		return employee;
	}

	/**
	 * Creates a supplier object and adds it to the database. 
	 * Uses transaction to ensure the database will rollback if the process fails.
	 * @return The generated supplier object
	 */
	@Override
	public Supplier createSupplier(String name, String address, String zipCode, String city, String phone, String email,
			String cvr) throws SQLException, PhoneAlreadyExistException, ZipAlreadyExistException, InsertFailedException, 
			NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, 
			PhoneValidationException, EmailValidationException {
		Supplier supplier = new Supplier(name, address, zipCode, city, phone, email, cvr);
		try {
			DBConnection.startTransaction();
			personDB.insertSupplier(supplier);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
		return supplier;
	}

	@Override
	public Customer findCustomerByPhone(String phone) {
		return new PersonDB().findCustomerByPhone(phone);
	}

	@Override
	public Employee findEmployeeByPhone(String phone) {
		return personDB.findEmployeeByPhone(phone);
	}

	@Override
	public Employee findEmployeeByUsernameAndPassword(String username, String password) throws SQLException {
		return personDB.findEmployeeByUsernameAndPassword(username, password);
	}

	@Override
	public Supplier findSupplierByPhone(String phone) {
		return personDB.findSupplierByPhone(phone);
	}

	@Override
	public ArrayList<Customer> findAllCustomers() {
		return new ArrayList<>(personDB.findAllCustomers());
	}

	@Override
	public ArrayList<Employee> findAllEmployees() {
		return new ArrayList<>(personDB.findAllEmployees());
	}

	@Override
	public ArrayList<Supplier> findAllSuppliers() {
		return new ArrayList<>(personDB.findAllSuppliers());
	}

	/**
	 * Updates customer information and commits it to the database. 
	 * Uses transaction to ensure the database will rollback if the process fails.
	 * @return The updated customer object
	 */
	@Override
	public void updateCustomer(String name, String address, String zipCode, String city, String currPhone,
			String newPhone, String email, String person_type, String type) throws NotImplementedException, SQLException {
		try {
			DBConnection.startTransaction();
			personDB.updateCustomerByPhone(name, address, zipCode, city, currPhone, newPhone, email, person_type, type);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
	}

	/**
	 * Updates employee information and commits it to the database. 
	 * Uses transaction to ensure the database will rollback if the process fails.
	 * @return The updated employee object
	 */
	@Override
	public void updateEmployeeByPhone(String name, String address, String zipCode, String city, String currPhone, String newPhone,
			String email, String person_type, String ssn, double salary, String username, String password) throws NotImplementedException, SQLException {
		try {
			DBConnection.startTransaction();
			personDB.updateEmployeeByPhone(name, address, zipCode, city, currPhone, newPhone, email, person_type, ssn, salary, username, password);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
	}

	/**
	 * Updates supplier information and commits it to the database. 
	 * Uses transaction to ensure the database will rollback if the process fails.
	 * @return The updated supplier object
	 */
	@Override
	public void updateSupplier(String name, String address, String zipCode, String city, String currPhone, String newPhone,
			String email, String person_type, String cvr) throws NotImplementedException, SQLException {
		try {
			DBConnection.startTransaction();
			personDB.updateSupplierByPhone(name, address, zipCode, city, currPhone, newPhone, email, person_type, cvr);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
		}
	}

	@Override
	public void removeCustomerByPhone(String phone) throws SQLException {
		TransactionControllerIF transactionController = (x) -> personDB.removeCustomerByPhone(phone);
	}

	@Override
	public void removeEmployeeByPhone(String phone) throws SQLException {
		TransactionControllerIF transactionController = (x) -> personDB.removeEmployeeByPhone(phone);
	}

	@Override
	public void removeSupplierByPhone(String phone) throws SQLException {
		TransactionControllerIF transactionController = (x) -> personDB.removeSupplierByPhone(phone);
	}

}
