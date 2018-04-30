package exception;

/**
 * SmartOrder
 * PersonNotFoundException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class PersonNotFoundException extends Exception {
	public PersonNotFoundException(String type) {
		super(type + "blev ikke fundet");
	}

}
