 package model;

import exception.AddressValidationException;
import exception.CityValidationException;
import exception.EmailValidationException;
import exception.NameValidationException;
import exception.PhoneValidationException;
import exception.ZipCodeValidationException;

/**
 * SmartOrder
 * Customer.java
 * Purpose: Creates a customer
 * @author Gruppe 1
 * @version 1.0 
 */
public class Customer extends Person {
	private String type;
	
	public Customer(int id, String name, String address, String zipCode, String city, String phone, String email, String type) throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		super(id, name, address, zipCode, city, phone, email);
		setType(type);
	}
	
	public Customer(String name, String address, String zipCode, String city, String phone, String email, String type) throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		super(name, address, zipCode, city, phone, email);
		setType(type);
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

}
