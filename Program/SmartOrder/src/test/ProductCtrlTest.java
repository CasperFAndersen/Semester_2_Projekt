package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.ProductCtrl;
import database.ProductDB;
import model.Product;
import model.Property;

/**
 * SmartOrder
 * ProductCtrlTest.java
 * Purpose: Tests for ProductCtrl class and methods.
 * @author Gruppe 1
 * @version 1.0 
 */
public class ProductCtrlTest {
	ProductCtrl productCtrl;
	ProductDB productDB;
	
	@Before
	public void setUp() throws Exception {
		productCtrl = new ProductCtrl();
		productDB = new ProductDB();
	}

	/**
	 * Testing ability to find a product by modelname.
	 */
	@Test
	public void productCanBeFoundByModelTest() {
		try{
			assertNull(productCtrl.findProductByModel("Sofa af - Hans Erik"));
			assertNotNull(productCtrl.findProductByModel("Kiwi modulsystem"));
		}catch (Exception e) {
			e.getMessage();
		}
	}
	
	/**
	 * Testing ability to find any and all modules tied to a product by id.
	 */
	@Test 
	public void loadModulesTest(){
		Product p = productCtrl.findProductByModel("Verona");
		assertNotNull(p);
		List<Product> modules = productDB.findModulesByProductId(p.getId());
		assertTrue(modules.size() == 2);
		LinkedList<Product> mod = p.getModules();
		assertTrue(mod.size() == 2);	
	}
	
	/**
	 * Testing ability to create any property.
	 */
	@Test
	public void createPropertyTest(){
		Property prop = productCtrl.createProperty("Stivhed", "1", "Double");
		assertNotNull(prop);
	}

}
