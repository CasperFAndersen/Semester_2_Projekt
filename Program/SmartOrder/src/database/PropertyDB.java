package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.*;

/**
 * SmartOrder
 * PropertyDB.java
 * Purpose: Handle Property object interaction from and to the database
 * @author Gruppe 1
 * @version 1.0 
 */
public class PropertyDB implements PropertyDBIF {

	private MakePreparedStatement mps = new MakePreparedStatement();

	/**
	 * PrepraredStatements for the Property table in the datase.
	 */
	private static final String findAllQ = "SELECT * FROM Property" ;
	private static final String findByProdIdQ = findAllQ + " WHERE product_id = ?";
	private static final String insertQ = "INSERT INTO property VALUES(?,?,?,?,?,?)";
	private PreparedStatement findAll, findByProdId, insert;

	public PropertyDB(){
		try {
			findAll = mps.makePreparedStatement(findAllQ);
			findByProdId = mps.makePreparedStatement(findByProdIdQ);
			insert = mps.makePreparedStatement(insertQ);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a Property Object from an ResultSet, which is filled with information retrieved from the database using a find method.
	 * @param rs as ResultSet
	 * @return A Property object
	 */
	private Property buildObject(ResultSet rs) throws SQLException{
		String name = rs.getString("name");
		String type = rs.getString("type");
		Property res;
		switch(type){
		case "String": res = new PropertyString(name, rs.getString("string_value"));
		break;
		case "Double": res = new PropertyDouble(name, rs.getDouble("double_value"));
		break;
		case "Boolean": res = new PropertyBoolean(name, rs.getBoolean("boolean_value"));
		break;
		default: res = null;
		}
		return res;
	}

	/**
	 * Creates an ArrayList of Property Object from an ResultSet, which is filled with information retrieved from the database with a find method.
	 * @param rs as ResultSet
	 * @return An ArrayList of Property objects.
	 */
	private ArrayList<Property> buildObjects(ResultSet rs) throws SQLException{
		ArrayList<Property> res = new ArrayList<>();
		while (rs.next()) {
			Property property = buildObject(rs);
			res.add(property);
		}
		return res;
	}

	/**
	 * Finds a Property in the database based on an id.
	 * @param id as int
	 * @return an Property object
	 */
	@Override
	public ArrayList<Property> findPropertiesByProductId(int id) throws SQLException{
		findByProdId.setInt(1, id);
		ResultSet rs = findByProdId.executeQuery();
		return buildObjects(rs);
	}

	/**
	 * Inserts information in the Property table in the database.
	 * The methods checks whether the type value of the property is String, Double, or Boolean, and inserts the value in the correct attribute.
	 * @param property as Property
	 * @param productId as int
	 */
	@Override
	public void insertProperty(Property property, int productId) throws SQLException{
		String type = property.getType().getSimpleName();
		insert.setString(1, property.getName());
		insert.setString(5, type);
		insert.setInt(6, productId);
		switch(type){
		case "String": insert.setString(2, (String) property.getValue()); insert.setNull(3, java.sql.Types.INTEGER);
		insert.setNull(4, java.sql.Types.BIT);
		break;
		case "Double": insert.setNull(2, java.sql.Types.VARCHAR); insert.setDouble(3, (double) property.getValue());
		insert.setNull(4, java.sql.Types.BIT);
		break;
		case "Boolean": insert.setNull(2, java.sql.Types.VARCHAR); insert.setNull(3, java.sql.Types.INTEGER);
		insert.setBoolean(4, (boolean)property.getValue());
		}
		insert.execute();
	}

	/**
	 * Inserts information in the Property table in the database. 
	 * In this case a Property template.
	 * @param property as Property
	 * @param productId as int
	 */
	@Override
	public void insertTemplateProperty(Property property, int productId) throws SQLException{
		String type = property.getType().getSimpleName();
		insert.setString(1, property.getName());
		insert.setNull(2, java.sql.Types.VARCHAR);
		insert.setNull(3, java.sql.Types.INTEGER);
		insert.setNull(4, java.sql.Types.BIT);
		insert.setString(5, type);
		insert.setInt(6, productId);
		insert.execute();
	}

}
