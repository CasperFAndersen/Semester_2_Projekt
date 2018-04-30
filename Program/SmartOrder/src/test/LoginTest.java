package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import controller.PersonCtrl;
import controller.PersonCtrlIF;

/**
 * SmartOrder
 * LoginTest.java
 * Purpose: Test if it is possible to login
 * @author Gruppe 1
 * @version 1.0 
 */
public class LoginTest {
	PersonCtrlIF personCtrl;
	
	@Before
	public void setUp() throws Exception {
		personCtrl = new PersonCtrl();
	}

	/**
	 * Testing ability to log into system with already existing employee, with username and password.
	 */
	@Test
	public void loginWithUsersTest() {
		try {
			assertTrue(personCtrl.findEmployeeByUsernameAndPassword("brian", "brianskode") != null);
			assertTrue(personCtrl.findEmployeeByUsernameAndPassword("mads", "madskode") != null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Testing ability to log into system with non existing employees, with username and password.
	 */
	@Test
	public void loginWithUsersNotExisting() {
		try {
			assertFalse(personCtrl.findEmployeeByUsernameAndPassword("test", "test") != null);
			assertFalse(personCtrl.findEmployeeByUsernameAndPassword("falsk", "falsk") !=null);
			assertFalse(personCtrl.findEmployeeByUsernameAndPassword("test", "falsk") != null);
			assertFalse(personCtrl.findEmployeeByUsernameAndPassword("falsk", "test") !=null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
