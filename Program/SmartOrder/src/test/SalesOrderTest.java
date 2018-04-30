package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.Customer;
import model.DeliveredType;
import model.OfferType;
import model.OrderCondition;
import model.OrderType;
import model.Product;
import model.ProductPrice;
import model.SalesOrder;
import model.Supplier;

/**
 * SmartOrder
 * SalesOrderTest.java
 * Purpose: 
 * @author Gruppe 1
 * @version 1.0 
 */
public class SalesOrderTest {
	private ProductPrice price1, price2 , price3, price4;
	private Product p1, p2, p3, p4;
	private OrderCondition order, delivered;
	private Customer c1, c2;
	private SalesOrder o1, o2, o3;
	private Supplier supplier;

	
	@Before
	public void setUp() throws Exception {
		price1 = new ProductPrice(3000, 5000, LocalDate.now());
		price2 = new ProductPrice(1000, 3000, LocalDate.now());
		price3 = new ProductPrice(100, 300, LocalDate.now());
		price4 = new ProductPrice(5000, 8000, LocalDate.now());
		
		p1 = new Product(1, "Sofa", "Hvid Sofa", "500x500", price1, supplier, null);
		p2 = new Product(2, "Sofa - bord", "Hvidt Sofa - bord", "100x80", price2, supplier, null);
		p3 = new Product(3, "Gardin", "Mørklægsning Gardiner", "180x90", price3, supplier, null);
		p4 = new Product(4, "SpiseBord", "Hvid Sofa", "600x200", price4, supplier, null);

		order = new OrderType(LocalDate.now());
		delivered = new DeliveredType(LocalDate.now());
		c1 = new Customer(1, "Istvan Knoll", "Istvanvej 25", "9000", "Aalborg", "12345678","istvan@ucn.dk", "Privat");

		o1 = new SalesOrder();
		o2 = new SalesOrder();
		o3 = new SalesOrder();
		
		o1.addSalesOrderLineToSalesOrder(p1, 1); 
		o1.addSalesOrderLineToSalesOrder(p2, 2); 

		o2.addSalesOrderLineToSalesOrder(p4, 1); 
		o2.addSalesOrderLineToSalesOrder(p3, 3); 
		o2.addSalesOrderLineToSalesOrder(p1, 1); 
		
		o3.addSalesOrderLineToSalesOrder(p3, 3);
		o3.addSalesOrderLineToSalesOrder(p1, 2); 
	}
	
	/**
	 * Testing ability to discount by integer and/or by percent.
	 */
	
	@Test
	public void getTotalPriceTest() {
		try {
			assertEquals(11000, o1.getTotalPrice(), 0);
			assertEquals(13900, o2.getTotalPrice(), 0);
			assertEquals(10900, o3.getTotalPrice(), 0);
			o2.getSalesOrderLines().get(0).setDiscount("1000");
			//o2.setTotalPrice();
			assertEquals(12900, o2.getTotalPrice(), 0);
			o3.getSalesOrderLines().get(1).setDiscount("10%");
			//o3.setTotalPrice();
			assertEquals(9900, o3.getTotalPrice(), 0);
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	/**
	 * Testing ability to change customer from "Istvan" to "Lise".
	 */
	@Test
	public void addCustomerToOrderTest() {
		try {
			Customer newCustomer = new Customer(0, "Lise", "Lisevej 25", "9000", "Aalborg", "88888888", "lise@ucn.dk", "privat");
			assertEquals(o1.getCustomer().getName(), "Istvan");
			o1.addCustomerToSalesOrder(newCustomer);
			assertEquals(o1.getCustomer().getName(), "Lise");
			assertNotEquals("Istvan", o1.getCustomer().getName());
		} catch(Exception e) {
			e.getMessage();
		}
	}
	/**
	 * Testing ability to change orderstatus from "order" to "delivered".
	 */
	@Test
	public void setOrderConditionTest() {
		try {
			assertTrue(o1.getOrderCondition().equals(order));
			o1.setOrderCondition(delivered);
			assertTrue(o1.getOrderCondition().equals(delivered));
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
