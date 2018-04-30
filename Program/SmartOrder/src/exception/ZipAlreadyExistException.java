package exception;

/**
 * SmartOrder
 * ZipAlreadyExistException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class ZipAlreadyExistException extends Exception {
	public ZipAlreadyExistException() {
		super ("Postnummeret eksisterer allerede i databasen");
	}
}
