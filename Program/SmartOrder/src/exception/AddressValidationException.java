package exception;

/**
 * SmartOrder
 * AddressValidationException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class AddressValidationException extends Exception {
	public AddressValidationException() {
		super("En address b�r indeholde b�de vejnavn og husnummer og/eller lejlighed. Eksempel 'Dendervej 1' eller 'Dendervej 1, 2TH");
	}
}
