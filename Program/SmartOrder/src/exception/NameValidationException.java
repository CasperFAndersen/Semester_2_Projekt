package exception;

/**
 * SmartOrder
 * NameValidationException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class NameValidationException extends Exception {
	public NameValidationException() {
		super("Et Customer navn b�r indeholde b�de fornavn og efternavn. Eksempel: 'Mads Madsen'");
	}
}
