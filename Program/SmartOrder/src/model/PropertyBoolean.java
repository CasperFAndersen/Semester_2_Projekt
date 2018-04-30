package model;

/**
 * SmartOrder
 * PropertyBoolean.java
 * Purpose: creates a Property as a boolean class.
 * @author Gruppe 1
 * @version 1.0 
 */
public class PropertyBoolean extends Property {
	
	public PropertyBoolean(String name, Boolean value) {
		super(name, value);
	}

	@Override
	public Class<Boolean> getType() {
		return Boolean.class;
	}

}
