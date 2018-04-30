package exception;

/**
 * SmartOrder
 * IllegalDiscountAmountException.java
 * @author Gruppe 1
 * @version 1.0 
 */
public class IllegalDiscountAmountException extends RuntimeException {
	public IllegalDiscountAmountException(String message){
		super(message);
	}
}
