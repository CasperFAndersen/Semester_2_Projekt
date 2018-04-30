package model;

/**
 * SmartOrder
 * PropertyFactory.java
 * Purpose: Creates A Property according To input.
 * @author Gruppe 1
 * @version 1.0 
 */
public class PropertyFactory {
	
	
	public PropertyFactory() {

	}
	/**
	 * The method creates instantiates a new class depending on the input
	 * @param name as String
	 * @param value as String
	 * @param type as String
	 * @return null
	 */
	public Property createProperty(String name, String value, String type) {
		switch (type) {
		case "String": return new PropertyString(name, value);
		case "Double": return new PropertyDouble(name, Double.parseDouble(value));
		case "Boolean": return new PropertyBoolean(name, Boolean.valueOf(value));
		}
		return null;
	}

	public Property createTemplateProperty(String name, String type) {
		switch (type) {
		case "String": return new PropertyString(name, null);
		case "Double": return new PropertyDouble(name, null);
		case "Boolean": return new PropertyBoolean(name, null);
		}
		return null;
	}

}
