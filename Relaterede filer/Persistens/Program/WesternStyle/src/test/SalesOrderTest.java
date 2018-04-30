package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.CustomerCtrl;
import controller.SalesOrderCtrl;
import database.DiscountDB;
import model.Customer;
import model.Discount;
import model.Employee;
import model.Order;
import model.OrderCondition;
import model.SalesOrder;
import model.SalesOrderLine;

public class SalesOrderTest {
	private static SalesOrder so;
	private static SalesOrderCtrl soCtrl;
	private static TestSetup testSetup = new TestSetup();

	@BeforeClass
	public static void setUp() throws Exception {
		so = new SalesOrder();
		soCtrl = new SalesOrderCtrl();
		testSetup.dropDB();
		testSetup.createTables();
		testSetup.insertTestData();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void mustBeCorrectTest() {
		assertTrue(true);
	}

	@Test
	public void testCase1OrderAmount100dkkNoDiscountPrivate(){
		so = soCtrl.findSalesOrderById(1);
		ArrayList<SalesOrderLine> sol = so.getAllSalesOrderLines();
		assertNotNull(sol);
		double totalPrice = so.getTotalPrice();
		assertEquals(100, totalPrice, 0);	
		boolean isThereDiscount = so.checkDiscountPrivate();
		assertFalse(isThereDiscount);
	}

	@Test
	public void testCase2OrderAmount1500dkkNoDiscountPrivate(){
		so = soCtrl.findSalesOrderById(2);
		ArrayList<SalesOrderLine> sol = so.getAllSalesOrderLines();
		assertNotNull(sol);
		double totalPrice = so.getTotalPrice();
		assertEquals(1500, totalPrice, 0);	
		boolean isThereDiscount = so.checkDiscountPrivate();
		assertFalse(isThereDiscount);
	}

	@Test
	public void testCase3OrderAmount2000dkkNoDiscountPrivate(){
		so = soCtrl.findSalesOrderById(3);
		ArrayList<SalesOrderLine> sol = so.getAllSalesOrderLines();
		assertNotNull(sol);
		double totalPrice = so.getTotalPrice();
		assertEquals(2000, totalPrice, 0);	
		boolean isThereDiscount = so.checkDiscountPrivate();
		assertFalse(isThereDiscount);
		double currDiscount = so.getDiscountForOffer();
		assertEquals(0, currDiscount, 0);
	}

	@Test
	public void testCase4OrderAmount2500dkkWith45dkkDiscountPrivate(){
		so = soCtrl.findSalesOrderById(4);
		ArrayList<SalesOrderLine> sol = so.getAllSalesOrderLines();
		assertNotNull(sol);
		double totalPrice = so.getTotalPrice();
		assertEquals(2500, totalPrice, 0);	
		boolean isThereDiscount = so.checkDiscountPrivate();
		assertTrue(isThereDiscount);
		double newPrice = so.getTotalPriceAfterDiscount();
		assertEquals(2455, newPrice, 0);
	}

	@Test
	public void testCase5OrderAmount2800dkkWith45dkkDiscountPrivate(){
		so = soCtrl.findSalesOrderById(5);
		ArrayList<SalesOrderLine> sol = so.getAllSalesOrderLines();
		assertNotNull(sol);
		double totalPrice = so.getTotalPrice();
		assertEquals(2800, totalPrice, 0);	
		boolean isThereDiscount = so.checkDiscountPrivate();
		assertTrue(isThereDiscount);
		double newPrice = so.getTotalPriceAfterDiscount();
		assertEquals(2755, newPrice, 0);
	}

	@Test
	public void testCase6OrderAmount3500dkkWith45dkkDiscountPrivate(){
		so = soCtrl.findSalesOrderById(6);
		ArrayList<SalesOrderLine> sol = so.getAllSalesOrderLines();
		assertNotNull(sol);
		double totalPrice = so.getTotalPrice();
		assertEquals(3500, totalPrice, 0);	
		boolean isThereDiscount = so.checkDiscountPrivate();
		assertTrue(isThereDiscount);
		double newPrice = so.getTotalPriceAfterDiscount();
		assertEquals(3455, newPrice, 0);
	}

	@Test
	public void testCase7OrderAmount12000dkkWith45dkkDiscountPrivate(){
		so = soCtrl.findSalesOrderById(7);
		ArrayList<SalesOrderLine> sol = so.getAllSalesOrderLines();
		assertNotNull(sol);
		double totalPrice = so.getTotalPrice();
		assertEquals(12000, totalPrice, 0);	
		boolean isThereDiscount = so.checkDiscountPrivate();
		assertTrue(isThereDiscount);
		double newPrice = so.getTotalPriceAfterDiscount();
		assertEquals(11955, newPrice, 0);
	}

	@Test
	public void testCase1orderAmount100dkkNoDiscountClub(){
		so = soCtrl.findSalesOrderById(8);
		ArrayList<SalesOrderLine> sol = so.getAllSalesOrderLines();
		assertNotNull(sol);
		double totalPrice = so.getTotalPrice();
		assertEquals(100, totalPrice, 0);	
		boolean isThereDiscount = so.checkDiscountClub();
		assertFalse(isThereDiscount);
	}

	@Test
	public void testCase2orderAmount1500dkk45DiscountClub(){
		so = soCtrl.findSalesOrderById(9);
		ArrayList<SalesOrderLine> sol = so.getAllSalesOrderLines();
		assertNotNull(sol);
		double totalPrice = so.getTotalPrice();
		assertEquals(1500, totalPrice, 0);	
		boolean isThereDiscount = so.checkDiscountClub();
		assertTrue(isThereDiscount);
		double newPrice = so.getTotalPriceAfterDiscount();
		assertEquals(1455, newPrice, 0);
	}

	public void testCase3orderAmount2000dkk45DkkiscountClub(){
		so = soCtrl.findSalesOrderById(2);
		ArrayList<SalesOrderLine> sol = so.getAllSalesOrderLines();
		assertNotNull(sol);
		double totalPrice = so.getTotalPrice();
		assertEquals(2000, totalPrice, 0);	
		boolean isThereDiscount = so.checkDiscountClub();
		assertTrue(isThereDiscount);
		double newPrice = so.getTotalPriceAfterDiscount();
		assertEquals(1955, newPrice, 0);
	}

	@Test
	public void testInsert() throws Exception{
		Discount disc = new DiscountDB().findById(1);
		Customer c = new CustomerCtrl().findCustomerById(2);
		Employee e = new CustomerCtrl().findEmployeeById(4);
		soCtrl.createSalesOrder();
		soCtrl.setCustomer(c);
		soCtrl.setEmployee(4);
		soCtrl.setDiscount(1);
		soCtrl.setOrderCondition("Offer");
		SalesOrder res = soCtrl.getSalesOrder();
		soCtrl.closeSalesOrder();

		SalesOrder salesOrder = soCtrl.findSalesOrderById(1);

		assertNotNull(res);
		assertNotNull(res.getEmployee());
		assertNotNull(disc);
		assertNotNull(salesOrder);
	}

	@Test
	public void testUpdateOrderCondition() throws Exception{
		soCtrl.setConditionToOrder(1);
		SalesOrder salesOrder = soCtrl.findSalesOrderById(1);
		OrderCondition oc = salesOrder.getOrderCondition();
		assertTrue(oc instanceof Order);
	}

	@Test
	public void testGetSalesOrderLines(){
		SalesOrder salesOrder = soCtrl.findSalesOrderById(1);
		ArrayList<SalesOrderLine> sol = salesOrder.getAllSalesOrderLines();
		assertNotNull(sol);
		assertEquals(sol.size(), 1);		
	}

}
