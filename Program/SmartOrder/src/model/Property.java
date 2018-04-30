package model;

/**
 * SmartOrder
 * Property.java
 * Purpose: Creates an Property which a Product uses. Super class used in three sub classes depending on input.
 * @author Gruppe 1
 * @version 1.0 
 */
public abstract class Property {
	private String name;
	private Object value;
	private Class type;
	
	public Property(String name, Object value) {
		setName(name);
		setValue(value);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public abstract Class getType();

}
