package model;

/**
 * SmartOrder
 * OrderCondition.java
 * Purpose: Abstract data type for the subclasses. used in SalesOrder to change state depending on the situation. 
 * @author Gruppe 1
 * @version 1.0 
 */
public abstract class OrderCondition {
	private int id;

	public OrderCondition() {
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}