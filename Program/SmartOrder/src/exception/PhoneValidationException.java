package exception;

/**
 * SmartOrder
 * PhoneValidationException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class PhoneValidationException extends Exception {
	public PhoneValidationException() {
		super("Et telefonnummer b�r kun indeholde tal, og skal v�re mindst 8 cifre. Eksempel: '98112233'");
	}
}
