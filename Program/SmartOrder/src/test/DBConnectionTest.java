package test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import database.DBConnection;
/**
 * SmartOrder
 * DBConnectionTest.java
 * Purpose: Test if it is possible to connect to the database
 * @author Gruppe 1
 * @version 1.0 
 */
public class DBConnectionTest {
	private DBConnection dbConnection = null;

	@Before
	public void setUp() throws Exception {
		dbConnection = DBConnection.getInstance();
	}

	/**
	 * Testing the singleton pattern is able to be called.
	 */
	@Test
	public void testConnection() {
		assertNotNull(dbConnection);
	}

}
