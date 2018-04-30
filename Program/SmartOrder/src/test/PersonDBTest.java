package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import database.PersonDB;
import exception.InsertFailedException;
import exception.PhoneAlreadyExistException;
import exception.ZipAlreadyExistException;
import model.Customer;
import model.Employee;
import model.Supplier;

/**
 * SmartOrder
 * PersonDBTest.java
 * Purpose: Tests for PersonDB class and methods.
 * @author Gruppe 1
 * @version 1.0 
 */
public class PersonDBTest {
	Customer customer, anotherCustomer;
	Employee employee;
	Supplier supplier;
	PersonDB personDB;

	@Before
	public void setUp() throws Exception {
		customer = new Customer("Test Kunde", "Testvej 2", "9000", "Aalborg", "23432343", "kunde@kunde.dk", "privat");
		anotherCustomer = new Customer("Test Kunde To", "ja 2", "9000", "aalborg", "23432343", "jaognej@jaognej.dk", "privat");
		employee = new Employee("Test Ansat", "Testvej 3", "9400", "Nørresundby", "34323432", "ansat@ansat.dk", "454545-2121", 321, "ansat", "ansatkode");
		supplier = new Supplier("Test Lev", "Testvej 3", "1111", "Aabybro", "99887742", "levas@levas.dk", "12312312");
		personDB = new PersonDB();
	}

	/**
	 * Testing ability to insert customer into database.
	 * Int beforeInsert is the number already inserted.
	 * Int afterInsert is the new number in the database.
	 * Ensuring customer (inserted) is equal to tempCustomer (same customer pulled from database)
	 * @throws SQLException
	 */
	@Test
	public void insertCustomerInDatabase() throws SQLException {
		int beforeInsert = personDB.getRowCountFromPersonTable();
		try {
			personDB.insertCustomer(customer);
		} catch (SQLException | PhoneAlreadyExistException | ZipAlreadyExistException | InsertFailedException e) {
			e.printStackTrace();
		} 
		int afterInsert = personDB.getRowCountFromPersonTable();
		assertEquals(beforeInsert, afterInsert - 1);
		Customer tempCustomer = personDB.findCustomerByPhone(customer.getPhone());
		assertEquals(customer.getAddress(), tempCustomer.getAddress());
		assertEquals(customer.getZipCode(), tempCustomer.getZipCode());
		assertEquals(customer.getEmail(), tempCustomer.getEmail());
		assertEquals(customer.getPhone(), tempCustomer.getPhone());
		personDB.removeCustomerByPhone(customer.getPhone());
	}
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	/**
	 * Testing ability to insert anotherCustomer into database.
	 * Test should refuse insertion, as customer already exists.
	 */
	@Test
	public void insertCustomerWithSamePhoneTest() {
		try {
			 personDB.insertCustomer(customer);
			 personDB.insertCustomer(anotherCustomer);
			 exception.expect(InsertFailedException.class);
		} catch (SQLException | PhoneAlreadyExistException | ZipAlreadyExistException | InsertFailedException e) {
			e.getMessage();
		}
		Assert.assertNotEquals(personDB.findCustomerByPhone(anotherCustomer.getPhone()).getName(), anotherCustomer.getName());
		personDB.removeCustomerByPhone(customer.getPhone());
	}


	@Test
	public void insertEmployeeInDatabase() throws SQLException {
		int beforeInsert = personDB.getRowCountFromPersonTable();
		try {
			personDB.insertEmployee(employee);
		} catch (SQLException | PhoneAlreadyExistException | ZipAlreadyExistException | InsertFailedException e) {
			e.printStackTrace();
		} 
		int afterInsert = personDB.getRowCountFromPersonTable();
		assertEquals(beforeInsert, afterInsert - 1);
		Employee tempEmployee = personDB.findEmployeeByPhone(employee.getPhone());
		assertEquals(employee.getAddress(), tempEmployee.getAddress());
		assertEquals(employee.getZipCode(), tempEmployee.getZipCode());
		assertEquals(employee.getEmail(), tempEmployee.getEmail());
		assertEquals(employee.getPhone(), tempEmployee.getPhone());
		personDB.removeEmployeeByPhone(employee.getPhone());
	}

	@Test
	public void insertSupplierInDatabase() throws SQLException {
		int beforeInsert = personDB.getRowCountFromPersonTable();
		try {
			personDB.insertSupplier(supplier);
		} catch (SQLException | PhoneAlreadyExistException | ZipAlreadyExistException | InsertFailedException e) {
			e.printStackTrace();
		} 
		int afterInsert = personDB.getRowCountFromPersonTable();
		assertEquals(beforeInsert, afterInsert - 1);
		Supplier tempSupplier = personDB.findSupplierByPhone(supplier.getPhone());
		assertEquals(supplier.getAddress(), tempSupplier.getAddress());
		assertEquals(supplier.getZipCode(), tempSupplier.getZipCode());
		assertEquals(supplier.getEmail(), tempSupplier.getEmail());
		assertEquals(supplier.getPhone(), tempSupplier.getPhone());
		personDB.removeSupplierByPhone(supplier.getPhone());
	}
	
}
