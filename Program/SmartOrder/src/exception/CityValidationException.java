package exception;

/**
 * SmartOrder
 * CityValidationException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class CityValidationException extends Exception {
	public CityValidationException() {
		super("En by b�r ikke indeholde tal, men kan have mere end �t navn. Eksempel 'Hobro' eller 'Stor K�benhavn'");
	}
}
