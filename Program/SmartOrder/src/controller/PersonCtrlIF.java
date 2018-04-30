package controller;

import java.sql.SQLException;
import java.util.ArrayList;

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
 * PersonCtrlIF.java
 * Purpose: Interface for PersonCtrl
 * @author Gruppe 1
 * @version 1.0 
 */
public interface PersonCtrlIF {
	Customer createCustomer(String name, String address, String zipCode, String city, String phone, String email,
			String type) throws SQLException, PhoneAlreadyExistException, ZipAlreadyExistException, InsertFailedException, 
			NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, 
			PhoneValidationException, EmailValidationException;
	Employee createEmployee(String name, String address, String zipCode, String city, String phone, String email,
			String ssn, double salary, String username, String password) throws SQLException, PhoneAlreadyExistException, 
			ZipAlreadyExistException, InsertFailedException, NameValidationException, AddressValidationException, 
			ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException;
	Supplier createSupplier(String name, String address, String zipCode, String city, String phone, String email,
			String cvr) throws SQLException, PhoneAlreadyExistException, ZipAlreadyExistException, InsertFailedException, 
			NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, 
			PhoneValidationException, EmailValidationException;
	Customer findCustomerByPhone(String phone);
	Employee findEmployeeByPhone(String phone);
	Supplier findSupplierByPhone(String phone);
	Employee findEmployeeByUsernameAndPassword(String username, String password) throws SQLException;
	ArrayList<Customer> findAllCustomers();
	ArrayList<Employee> findAllEmployees();
	ArrayList<Supplier> findAllSuppliers();
	void updateCustomer(String name, String address, String zipCode, String city, String currPhone, String newPhone,
			String email, String person_type, String type) throws NotImplementedException, SQLException;
	void updateEmployeeByPhone(String name, String address, String zipCode, String city, String currPhone,
			String newPhone, String email, String person_type, String ssn, double salary, String username,
			String password) throws NotImplementedException, SQLException;
	void updateSupplier(String name, String address, String zipCode, String city, String currPhone, String newPhone,
			String email, String person_type, String cvr) throws NotImplementedException, SQLException;
	void removeCustomerByPhone(String phone) throws SQLException;
	void removeEmployeeByPhone(String phone) throws SQLException;
	void removeSupplierByPhone(String phone) throws SQLException;

}
