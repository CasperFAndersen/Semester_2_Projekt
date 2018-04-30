package test;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.DBConnection;

public class DBConnectionTest {
	private DBConnection dbConnection = null;

	@Before
	public void setUp() throws Exception {
		dbConnection = DBConnection.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(dbConnection);
	}

}
