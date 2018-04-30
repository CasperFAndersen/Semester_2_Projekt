package exception;

/**
 * SmartOrder
 * PhoneAlreadyExistException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class PhoneAlreadyExistException extends Exception {
	public PhoneAlreadyExistException() {
		super("Telefonnummeret findes allerede i databasen");
	}
}
