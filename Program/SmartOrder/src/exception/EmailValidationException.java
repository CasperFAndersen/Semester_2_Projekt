package exception;

/**
 * SmartOrder
 * EmailValidationException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class EmailValidationException extends Exception {
	public EmailValidationException() {
		super("Ikke et valid email format - Eksempel: 'Gruppe6@ucn.dk'");
	}
}
