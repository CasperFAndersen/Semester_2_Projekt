package model;

import exception.AddressValidationException;
import exception.CityValidationException;
import exception.EmailValidationException;
import exception.NameValidationException;
import exception.PhoneValidationException;
import exception.ZipCodeValidationException;

/**
 * SmartOrder
 * Person.java
 * Purpose: Super class for employee, customer, supplier. Used to create a Person
 * @author Gruppe 1
 * @version 1.0 
 */
public class Person {
	private int id;
	private String name;
	private String address;
	private String zipCode;
	private String city;
	private String phone;
	private String email;
	
	private InputChecker inputChecker = new InputChecker();

	public Person(String name, String address, String zipCode, String city, String phone, String email) throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		setName(name);
		setAddress(address);
		setZipCode(zipCode);
		setCity(city);
		setPhone(phone);
		setEmail(email);
	}
	
	public Person(int id, String name, String address, String zipCode, String city, String phone, String email) throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		setId(id);
		setName(name);
		setAddress(address);
		setZipCode(zipCode);
		setCity(city);
		setPhone(phone);
		setEmail(email);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws NameValidationException {
		this.name = inputChecker.nameValidation(name);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws AddressValidationException {
		this.address = inputChecker.addressValidation(address);
	}
	
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) throws ZipCodeValidationException {
		this.zipCode = inputChecker.zipCodeValidation(zipCode);
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) throws CityValidationException {
		this.city = inputChecker.cityValidation(city);
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) throws PhoneValidationException {
		this.phone = inputChecker.phoneValidation(phone);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws EmailValidationException {
		this.email = inputChecker.emailValidation(email);
	}

}
