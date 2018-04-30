package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import controller.SalesOrderCtrl;
import database.SalesOrderDBIF;

/**
 * SmartOrder
 * SalesOrderCtrlTest.java
 * Purpose: Tests for SalesOrderCtrl class and methods.
 * @author Gruppe 1
 * @version 1.0 
 */
public class SalesOrderCtrlTest {
	private SalesOrderCtrl salesOrderCtrl;
	private SalesOrderDBIF salesOrderDB;
	
	@Before
	public void setUp() throws Exception {
		salesOrderCtrl = new SalesOrderCtrl();
	}
	
	/**
	 * Testing the ability of creating a SalesOrder object.
	 */
	@Test
	public void createOrderTest() {
		try {
			assertNull(salesOrderCtrl.getOrder());
			salesOrderCtrl.createSalesOrder();
			assertNotNull(salesOrderCtrl.getOrder());
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	/**
	 * Testing the ability to close an order.
	 * int before: The amount of orders already saved in the database, before closing.
	 * After closing the order, testing that before and count of orders saves in database are not equal.
	 */
	@Test
	public void closeOrderTest() { 
		try{
			salesOrderCtrl.createSalesOrder();
			int before = salesOrderDB.findAll().size();
			salesOrderCtrl.closeSalesOrder();
			assertNotEquals(before, salesOrderDB.findAll().size());
			assertNull(salesOrderCtrl.getOrder());
		}catch(Exception e){
			e.getMessage();
		}
	}
}
