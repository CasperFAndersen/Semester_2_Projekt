package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import database.PersonDB;
import database.ProductDB;
import exception.InsertFailedException;
import model.Product;
import model.ProductPrice;
import model.ProductType;
import model.Property;

/**
 * SmartOrder
 * ProductDBTest.java
 * Purpose: Tests for ProductDB class and methods.
 * @author Gruppe 1
 * @version 1.0 
 */
public class ProductDBTest {
	
	ProductDB productDB = new ProductDB();
	
	/**
	 * Testing ability to insert a newly created product and associated productType into database.
	 */
	@Test
	public void insertProductTest() {
		Product product = new Product(0, "1669", "Mega blød og flot", "100 x 100 x 80", new ProductPrice(500, 1000, LocalDate.now()), new PersonDB().findSupplierByPhone("75250244"), new ArrayList<Property>());
		ProductType productType = new ProductDB().findProductTypeByName("Sofa");
		int id = 0;
		try {
			id = productDB.insertProduct(product, productType);
			productDB.removeProductById(id);
		} catch (SQLException | InsertFailedException e) {
			e.getMessage();
		}
		Assert.assertNotEquals(0, id);
	}
	 
	/**
	 * Testing ability to find a productType by name.
	 */
	@Test
	public void findProductTypeTest() {
		ProductType productType = new ProductDB().findProductTypeByName("Sofa");
		assertNotNull(productType);
	}
	
	/**
	 * Testing the ability to find an existing products id.
	 */
	@Test
	public void findProductByIdTest(){
		Product tempProduct = productDB.findProductByModel("Verona");
		Product p = productDB.findProductById(tempProduct.getId());
		assertNotNull(p);
		assertEquals(tempProduct.getId(), p.getId());
	}
	
	/**
	 * Testing ability to insert a templateProduct into database.
	 * @throws InsertFailedException
	 */
	@Test
	public void insertTemplateProductTest() throws InsertFailedException{
		Product p = new Product();
		p.setModel("lænestolTemplate");
		try {
			int id = productDB.insertTemplateProduct(p);
			p = productDB.findTemplateByID(id);
			assertNotNull(p);
			assertNotNull(id);
			productDB.updateProductsProductType(id, 1);
			p = productDB.findTemplateByID(id);
			assertEquals(p.getProductType().getId(),1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 

}
