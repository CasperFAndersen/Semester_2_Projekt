package model;

/**
 * SmartOrder
 * PropertyDouble.java
 * Purpose: Creates a Property as double class.
 * @author Gruppe 1
 * @version 1.0 
 */
public class PropertyDouble extends Property{
	
	public PropertyDouble(String name, Double value) {
		super(name, value);
	}

	@Override
	public Class<Double> getType() {
		return Double.class;
	}

}
