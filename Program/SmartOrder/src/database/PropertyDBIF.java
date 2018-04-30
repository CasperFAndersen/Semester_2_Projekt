package database;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Property;

/**
 * SmartOrder
 * PropertyDBIF.java
 * Purpose: Interface for PropertyDB
 * @author Gruppe 1
 * @version 1.0 
 */
public interface PropertyDBIF {
	void insertProperty(Property property, int productId) throws SQLException;
	void insertTemplateProperty(Property property, int productId) throws SQLException;
	ArrayList<Property> findPropertiesByProductId(int productId) throws SQLException;
}
