package exception;

/**
 * SmartOrder
 * SsnValidationException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class SsnValidationException extends Exception {
	public SsnValidationException() {
		super("Ssn b�r kun indeholde tal, og have et af f�lgende formater. - Eksempel '140402-1414' eller '1404021414'");
	}
}
