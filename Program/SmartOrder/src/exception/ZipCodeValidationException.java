package exception;

/**
 * SmartOrder
 * ZipCodeValidationException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class ZipCodeValidationException extends Exception {
	public ZipCodeValidationException() {
		super("Et postnummer bør kun indeholde tal, et dansk postnummer bør have 4 tal. Eksempel '9000'");
	}
}
