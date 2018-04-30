package database;

import java.util.List;

import model.Customer;
import model.Employee;

public interface PersonDBIF {
	List<Customer> findAllCustomers();
	Customer findCustomerById(int id);
	List<Employee> findAllEmployee();
	Employee findEmployeeById(int id);
}
