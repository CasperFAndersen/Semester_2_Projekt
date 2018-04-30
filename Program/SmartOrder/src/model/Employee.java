package model;

import exception.AddressValidationException;
import exception.CityValidationException;
import exception.EmailValidationException;
import exception.NameValidationException;
import exception.PhoneValidationException;
import exception.ZipCodeValidationException;

/**
 * SmartOrder
 * Employee.java
 * Purpose: Creates an employee.
 * @author Gruppe 1
 * @version 1.0 
 */
public class Employee extends Person {
	private String ssn;
	private double salary;
	private String username;
	private String password;
	
	public Employee(String name, String address, String zipCode, String city, String phone, String email,
			String ssn, double salary, String username, String password) throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		super(name, address, zipCode, city, phone, email);
		setSsn(ssn);
		setSalary(salary);
		setUsername(username);
		setPassword(password);
	}
	
	public Employee(int id, String name, String address, String zipCode, String city, String phone, String email, String ssn, double salary, String username, String password) throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		super(id, name, address, zipCode, city, phone, email);
		setSsn(ssn);
		setSalary(salary);
		setUsername(username);
		setPassword(password);
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
