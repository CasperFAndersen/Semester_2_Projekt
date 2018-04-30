package exception;

/**
 * SmartOrder
 * InsertFailedException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class InsertFailedException extends Exception {
	public InsertFailedException(String type) {
		super(type + " blev ikke tilføjet til databasen");
	}
}
