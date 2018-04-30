package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import database.PropertyDB;
import model.Property;
import model.PropertyBoolean;
import model.PropertyDouble;
import model.PropertyString;

/**
 * SmartOrder
 * PropertyTest.java
 * Purpose: Tests for Property class and methods.
 * @author Gruppe 1
 * @version 1.0 
 */
public class PropertyTest {

	private Property booleanProp;
	private Property stringProp;
	private Property doubleProp;
	private PropertyDB propertyDB = new PropertyDB();


	@Before
	public void setUp() throws Exception {
		booleanProp = new PropertyBoolean("boolean", true);
		stringProp = new PropertyString("String", "stringClass");
		doubleProp = new PropertyDouble("double", 10.0);
	}

	/**
	 * Testing parameters from parameter list was set correctly, when objects were instantiated.
	 */
	@Test
	public void booleanPropTest() {
		assertTrue(booleanProp.getType().equals(Boolean.class));
		assertTrue(booleanProp.getValue().equals(true));
	}

	/**
	 * Testing parameters from parameter list was set correctly, when objects were instantiated.
	 */
	@Test
	public void stringPropTest() {
		assertTrue(stringProp.getType().equals(String.class));
		assertTrue(stringProp.getValue().equals("stringClass"));
	}

	/**
	 * Testing parameters from parameter list was set correctly, when objects were instantiated.
	 */
	@Test
	public void doublePropTest() {
		assertTrue(doubleProp.getType().equals(Double.class));
		assertTrue(doubleProp.getValue().equals(10.0));
	}

	/**
	 * Testing ability to find properties by id.
	 * @throws SQLException
	 */
	@Test
	public void testFindPropertyByProductId() throws SQLException {
		ArrayList < Property > res = propertyDB.findPropertiesByProductId(3);
		assertTrue(res.size() != 0);
	}
	
	/**
	 * Testing ability to save a new product in database.
	 * @throws SQLException
	 */
	@Test
	public void testInsertProperty() throws SQLException {
		Property p = new PropertyBoolean("Saml selv", true);
		propertyDB.insertProperty(p, 1);
		ArrayList<Property> props = propertyDB.findPropertiesByProductId(1);
		assertNotNull(props);
	}

}