package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.AddressValidationException;
import exception.CityValidationException;
import exception.EmailValidationException;
import exception.NameValidationException;
import exception.PhoneValidationException;
import exception.ZipCodeValidationException;

/**
 * SmartOrder
 * inputChecker.java
 * Purpose: Makes sure name, address, city, phone, email and ssn upholds the convetions for writing to the database.
 * @author Gruppe 1
 * @version 1.0 
 */
public class InputChecker {

	/**
	 * Checks that input holds the conventions
	 * @param input as String
	 * @return name
	 * @throws NameValidationException
	 */
	public String nameValidation(String input) throws NameValidationException {
		String name = null;
		String namePattern = "[a-zA-Z]+( [a-zA-Z]+)+";
		Pattern pattern = Pattern.compile(namePattern);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			name = input;
		} 
		else {
			throw new NameValidationException();
		}
		return name;
	}

	/**
	 * Checks that input holds the conventions
	 * @param input as String
	 * @return address
	 * @throws AddressValidationException
	 */
	public String addressValidation(String input) throws AddressValidationException {
		String address = null;
		String addressPattern = "[a-zA-Z]+( [a-zA-Z]+)* [0-9]+(,[ a-zA-Z0-9.]*)?";
		Pattern pattern = Pattern.compile(addressPattern);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			address = input;
		}
		else {
			throw new AddressValidationException();
		}
		return address;
	}

	/**
	 * Checks that input holds the conventions
	 * @param input as String
	 * @return zipCode
	 * @throws ZipCodeValidationException
	 */
	public String zipCodeValidation (String input) throws ZipCodeValidationException {
		String zipCode = null;
		String zipCodePattern = "[0-9]{3,6}";
		Pattern pattern = Pattern.compile(zipCodePattern);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			zipCode = input;
		}
		else {
			throw new ZipCodeValidationException();
		}
		return zipCode;
	}
	
	/**
	 * Checks that input holds the conventions
	 * @param input as String
	 * @return city
	 * @throws CityValidationException
	 */
	public String cityValidation (String input) throws CityValidationException {
		String city = null;
		String cityPattern = "[a-zA-Z]+( [a-zA-Z]+)*";
		Pattern pattern = Pattern.compile(cityPattern);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			city = input;
		}
		else {
			throw new CityValidationException();
		}
		return city;
	}
	
	/**
	 * Checks that input holds the conventions
	 * @param input as String
	 * @return phone
	 * @throws PhoneValidationException
	 */
	public String phoneValidation (String input) throws PhoneValidationException {
		String phone = null;
		String phonePattern = "[0-9]+";
		Pattern pattern = Pattern.compile(phonePattern);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find() && input.length() >= 8) {
			phone = input;
		}
		else {
			throw new PhoneValidationException();
		}
		return phone;
	}
	
	/**
	 * Checks that input holds the conventions
	 * @param input as String
	 * @return email
	 * @throws EmailValidationException
	 */
	public String emailValidation(String input) throws EmailValidationException {
		String email = null;
		String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}";
		Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			email = input;
		} 
		else {
			throw new EmailValidationException();
		}
		return email;
	}
	
	/**
	 * Checks that input holds the conventions
	 * @param input as String
	 * @return ssn
	 */
	public String ssnValidation(String input)  {
		String ssn = null;
		String ssnPattern = "[0-9]{6}-?[0-9]{4}";
		Pattern pattern = Pattern.compile(ssnPattern);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			ssn = input;
		}
		return ssn;
	}

}

