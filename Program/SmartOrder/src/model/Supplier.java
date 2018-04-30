package model;

import exception.AddressValidationException;
import exception.CityValidationException;
import exception.EmailValidationException;
import exception.NameValidationException;
import exception.PhoneValidationException;
import exception.ZipCodeValidationException;

/**
 * SmartOrder
 * Supplier.java
 * Purpose: Creates an Supplier.
 * @author Gruppe 1
 * @version 1.0 
 */
public class Supplier extends Person {
	private String cvr;

	public Supplier(String name, String address, String zipCode, String city, String phone, String email, String cvr) throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		super(name, address, zipCode, city, phone, email);
		setCvr(cvr);
	}
	
	public Supplier(int id, String name, String address, String zipCode, String city, String phone, String email, String cvr) throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		super(id, name, address, zipCode, city, phone, email);
		setCvr(cvr);
	}

	public String getCvr() {
		return cvr;
	}

	public void setCvr(String cvr) {
		this.cvr = cvr;
	}

}
