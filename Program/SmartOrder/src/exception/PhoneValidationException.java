package exception;

/**
 * SmartOrder
 * PhoneValidationException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class PhoneValidationException extends Exception {
	public PhoneValidationException() {
		super("Et telefonnummer bør kun indeholde tal, og skal være mindst 8 cifre. Eksempel: '98112233'");
	}
}
