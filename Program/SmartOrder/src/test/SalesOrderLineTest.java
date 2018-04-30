package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import exception.AddressValidationException;
import exception.CityValidationException;
import exception.EmailValidationException;
import exception.IllegalDiscountAmountException;
import exception.NameValidationException;
import exception.PhoneValidationException;
import exception.ZipCodeValidationException;
import model.Customer;
import model.DeliveredType;
import model.OfferType;
import model.OrderCondition;
import model.OrderType;
import model.Product;
import model.ProductPrice;
import model.SalesOrder;
import model.SalesOrderLine;
import model.Supplier;

/**
 * SmartOrder
 * SalesOrderLineTest.java
 * Purpose: Tests for SalesOrderLines class and methods.
 * @author Gruppe 1
 * @version 1.0 
 */
public class SalesOrderLineTest {
	private ProductPrice price1, price2 , price3, price4;
	private Product p1, p2, p3, p4;
	private SalesOrderLine sol1;
	private SalesOrder o1, o2, o3;
	private Supplier supplier;
	

	@Before
	public void setup(){
		price1 = new ProductPrice(2000, 3000, LocalDate.now());
		price2 = new ProductPrice(1000, 3000, LocalDate.now());
		price3 = new ProductPrice(100, 300, LocalDate.now());
		price4 = new ProductPrice(5000, 8000, LocalDate.now());
			
		p1 = new Product(1, "Sofa", "Hvid Sofa", "500x500", price1, supplier, null);
		p2 = new Product(2, "Sofa - bord", "Hvidt Sofa - bord", "100x80", price2, supplier, null);
		p3 = new Product(3, "Gardin", "Mørklægsning Gardiner", "180x90", price3, supplier, null);
		p4 = new Product(4, "SpiseBord", "Hvid Sofa", "600x200", price4, supplier, null);

		o1 = new SalesOrder();
		o2 = new SalesOrder();
		o3 = new SalesOrder();
		
		sol1 = new SalesOrderLine(p1, 1);
		
		o1.addSalesOrderLineToSalesOrder(p1, 1); 
		o1.addSalesOrderLineToSalesOrder(p2, 2); 
	

		o2.addSalesOrderLineToSalesOrder(p4, 1);
		o2.addSalesOrderLineToSalesOrder(p3, 3); 
		o2.addSalesOrderLineToSalesOrder(p1, 1);
		
		
		o3.addSalesOrderLineToSalesOrder(p3, 3);
		o3.addSalesOrderLineToSalesOrder(p1, 2);
	}
	/**
	 * Testing the ability to use valid and invalid discount values on a salesOrderLine.
	 */
	@Test
	public void setDiscountTest(){
		String[] valids = {"750", "25%", "%25", "750.0"}; String[] invalids = {"-1","3001","-0.5%","101%", "Ab3c¤#"};
		for(String s : valids){
			try {
				sol1.setDiscount(s);
				assertTrue(sol1.getDiscount() == 750);
				assertTrue(sol1.getSubTotal() == 2250);
			} catch (Exception e) {
				fail();
			}}
		for (int i = 0; i < invalids.length; i++) {
			String s = invalids[i];
			try {
			sol1.setDiscount(s);
			fail();
			} catch (IllegalDiscountAmountException e) {
				assertTrue(sol1.getDiscount() == 0);
				assertTrue(sol1.getSubTotal() == 3000);
				if(i==0 || i==1){assertEquals(e.getMessage(), "Rabat kan ikke være under 0 eller over originalpris");}
				else{assertEquals(e.getMessage(), "Rabat kan ikke være under 0% eller over 100%");}
			}
			catch(NumberFormatException e) {
				assertTrue(i == 4);
				assertTrue(sol1.getDiscount() == 0);
				assertTrue(sol1.getSubTotal() == 3000);
			}
		}
		sol1.setDiscount("0%");
		assertTrue(sol1.getDiscount() == 0);
		assertTrue(sol1.getSubTotal() == 3000);
		sol1.setDiscount("0");
		assertTrue(sol1.getDiscount() == 0);
		assertTrue(sol1.getSubTotal() == 3000);
		sol1.setDiscount("100%");
		assertTrue(sol1.getDiscount() == 3000);
		assertTrue(sol1.getSubTotal() == 0);
		sol1.setDiscount("3000");
		assertTrue(sol1.getDiscount() == 3000);
		assertTrue(sol1.getSubTotal() == 0);
	}

}
