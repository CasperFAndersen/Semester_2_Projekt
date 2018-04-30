package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.CustomerCtrl;
import model.Customer;
import model.Employee;

public class CustomerTest {

	private CustomerCtrl customerCtrl;
	@Before
	public void setUp() throws Exception {
		customerCtrl = new CustomerCtrl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindById() {
		Customer c = customerCtrl.findCustomerById(2);
		assertNotNull(c);
		assertEquals(c.getName(), "Hanne Nielsen");
	}

	@Test
	public void findEmployee() {
		Employee e = customerCtrl.findEmployeeById(4);
		assertNotNull(e);
	}

}
