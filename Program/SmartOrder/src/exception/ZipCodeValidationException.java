package exception;

/**
 * SmartOrder
 * ZipCodeValidationException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class ZipCodeValidationException extends Exception {
	public ZipCodeValidationException() {
		super("Et postnummer b�r kun indeholde tal, et dansk postnummer b�r have 4 tal. Eksempel '9000'");
	}
}
