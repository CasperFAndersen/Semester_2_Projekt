package controller;

import java.util.List;

import database.PersonDB;
import model.Customer;
import model.Employee;

public class CustomerCtrl {

	public CustomerCtrl() {

	}

	public Customer findCustomerById(int id) {
		return new PersonDB().findCustomerById(id);
	}

	public List<Customer> getAll() {
		return new PersonDB().findAllCustomers();
	}

	public Employee findEmployeeById(int id) {
		return new PersonDB().findEmployeeById(id);
	}
}
