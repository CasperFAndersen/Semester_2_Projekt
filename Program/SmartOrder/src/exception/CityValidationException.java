package exception;

/**
 * SmartOrder
 * CityValidationException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class CityValidationException extends Exception {
	public CityValidationException() {
		super("En by bør ikke indeholde tal, men kan have mere end ét navn. Eksempel 'Hobro' eller 'Stor København'");
	}
}
