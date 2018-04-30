package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.Product;
import model.ProductPrice;
import model.Supplier;

/**
 * SmartOrder
 * ProductTest.java
 * Purpose: Tests for Product class and methods.
 * @author Gruppe 1
 * @version 1.0 
 */
public class ProductTest {

	private ProductPrice price1, price2, price3;
	private Product p1, p2, p3, p4;
	private Supplier supplier;


	@Before
	public void setUp() throws Exception {
		price1 = new ProductPrice(3000, 5000, LocalDate.now());
		price2 = new ProductPrice(200, 500, LocalDate.now());
		price3 = new ProductPrice(500, 1500, LocalDate.now());
		supplier = new Supplier(0, "Hjort Knudsen", "Knoldsparkervej 24", "9000", "Aalborg", "22114466", "hjort@knudsen.dk", "66441122");
		p1 = new Product(0, "Seng", "Seng - Nielsen Design", "220x120", price1, supplier, null);
		p2 = new Product(1, "Senge puder", "Senge puder - Nielsen Design", "30x30", price2, supplier, null);
		p3 = new Product(2, "Boxmadras", "Boxmadras - Nielsen Design", "220x120", price3, supplier, null);
		p4 = new Product(3, "skab", "skab - Nielsen Design", "250x50", price1, supplier, null);
		p1.addModuleToProduct(p2);
		p1.addModuleToProduct(p3);
	}

	/**
	 * Testing ability to add any product (Module) to another product.
	 */
	@Test
	public void addmodulToProductTest() {
		try{
			p1.addModuleToProduct(p4);
			assertTrue(p1.getModules().contains(p4));
		}catch(Exception e){
			e.getMessage();
		}
	} 

	/**
	 * Testing ability to remove any product (Module) from another product.
	 */
	@Test
	public void removeModulFromProduct() {
		try {
			p1.removeModuleFromProduct(p3);
			assertFalse(p1.getModules().contains(p3));
			assertTrue(p1.getModules().size() == 1);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * Testing ability to tie ProductPrice to product.
	 */
	@Test
	public void getSalesPriceTest() {
		try {
			assertEquals(p1.getProductPrice().getSalesPrice(), 5000, 0);
			assertEquals(p2.getProductPrice().getSalesPrice(), 500, 0);
			assertEquals(p3.getProductPrice().getSalesPrice(), 1500, 0);
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
