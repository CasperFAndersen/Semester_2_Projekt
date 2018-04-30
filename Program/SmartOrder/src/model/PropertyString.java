package model;

/**
 * SmartOrder
 * PropertyString.java
 * Purpose: creates a Property as a String class.
 * @author Gruppe 1
 * @version 1.0 
 */
public class PropertyString extends Property{

	public PropertyString(String name, String value) {
		super(name, value);
	}
	
	@Override
	public Class<String> getType(){
		return String.class;
	}

}
