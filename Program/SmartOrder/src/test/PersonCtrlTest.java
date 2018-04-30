package test;

import static org.junit.Assert.*;
import org.junit.Test;
import controller.PersonCtrl;

/**
 * SmartOrder
 * PersonCtrlTest.java
 * Purpose: Tests for PersonCtrl class and methods.
 * @author Gruppe 1
 * @version 1.0 
 */
public class PersonCtrlTest {
	PersonCtrl personCtrl = new PersonCtrl();
	
	/**
	 * Testing ability to find already existing customer in database by phonenumber.
	 */
	@Test
	public void findCustomerByPhoneTest() {
		try {
			assertNotNull(personCtrl.findCustomerByPhone("11223344"));
			assertNull(personCtrl.findCustomerByPhone("88888888"));
			assertTrue(personCtrl.findCustomerByPhone("11223344").getName().equals("Palle Bodilsen"));
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void findEmployeeByPhoneTest() {
		try {
			assertNotNull(personCtrl.findEmployeeByPhone("68826882"));
			assertNull(personCtrl.findCustomerByPhone("88888888"));
			assertTrue(personCtrl.findEmployeeByPhone("68826882").getName().equals("Mads Thorsen"));
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void findSupplierByPhoneTest() {
		try {
			assertNotNull(personCtrl.findSupplierByPhone("82133000"));
			assertNull(personCtrl.findSupplierByPhone("88888888"));
			assertTrue(personCtrl.findSupplierByPhone("82133000").getName().equals("Jensen Senge"));
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	/**
	 * Testing ability to find all already existing customers in database.
	 */
	@Test
	public void findAllCustomersTest() { 
		try {
			assertNotNull(personCtrl.findAllSuppliers().size() != 0);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void findAllEmployeesTest() { 
		try {
			assertTrue(personCtrl.findAllEmployees().size() != 0);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test
	public void findAllSuppliersTest() { 
		try {
			assertTrue(personCtrl.findAllSuppliers().size() != 0);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
}
