package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.AddressValidationException;
import exception.CityValidationException;
import exception.EmailValidationException;
import exception.NameValidationException;
import exception.PhoneValidationException;
import exception.ZipCodeValidationException;
import model.Supplier;

/**
 * SmartOrder
 * SupplierDB.java
 * Purpose: Handle supplier interaction from and to database.
 * @author Gruppe 1
 * @version 1.0 
 */
public class SupplierDB {

	/**
	 * PreparedStatements for the Supplier table in the database.
	 */
	private static final String supplierParameters = " supplier.id = person.id AND phone.person_id = person.id AND person.zip_code = ZipCity.zip_code";
	
	private static final String insertSupplierQ = "INSERT INTO supplier VALUES (?, ?)";
	private static final String findAllSuppliersQ = "SELECT * FROM person, phone, zipcity, supplier WHERE " + supplierParameters;
	private static final String findSupplierByIdQ = findAllSuppliersQ + " AND supplier.id = ?";
	private static final String findSupplierByPhoneQ = "SELECT * FROM person, phone, zipcity, supplier WHERE  supplier.id = person.id AND phone.person_id = person.id AND person.zip_code = ZipCity.zip_code AND phone = ?";
	private static final String updateSupplierByIdQ = "UPDATE supplier SET cvr = ? WHERE id = ?";
	private static final String removeFromSupplierByIdQ = "DELETE FROM supplier WHERE id = ?";

	private PreparedStatement insertSupplier, findAllSuppliers, findSupplierById, findSupplierByPhone, updateSupplierById, removeFromSupplierById;

	private MakePreparedStatement mps = new MakePreparedStatement();

	public SupplierDB() { 
		try {
			insertSupplier = mps.makePreparedStatement(insertSupplierQ);
			findAllSuppliers = mps.makePreparedStatement(findAllSuppliersQ);
			findSupplierById = mps.makePreparedStatement(findSupplierByIdQ);
			findSupplierByPhone = mps.makePreparedStatement(findSupplierByPhoneQ);
			updateSupplierById = mps.makePreparedStatement(updateSupplierByIdQ);
			removeFromSupplierById = mps.makePreparedStatement(removeFromSupplierByIdQ);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void insertSupplier(Supplier supplier, int id) throws SQLException {
		insertSupplier.setInt(1, id);
		insertSupplier.setString(2, supplier.getCvr());
		insertSupplier.execute();
	}
	
	
	public List<Supplier> findAllSuppliers() throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		ResultSet rs;
		List<Supplier> res = null;
		try {
			rs = findAllSuppliers.executeQuery();
			res = buildSupplierObjects(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return res;
	}
	
	/**
	 * Finds an Supplier in the database based on a phone number.
	 * @param phone as String
	 * @return Supplier object
	 */
	public Supplier findSupplierByPhone(String phone) throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		ResultSet rs;
		Supplier res = null; 
		try {
			findSupplierByPhone.setString(1, phone);
			rs = findSupplierByPhone.executeQuery();
			if (rs.next()) {
				res = buildSupplierObject(rs);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Finds an Supplier in the database based on a id.
	 * @param id as int
	 * @return Supplier object
	 */
	public Supplier findSupplierById(int id) throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		ResultSet rs;
		Supplier res = null;
		try {
			findSupplierById.setInt(1, id);
			rs = findSupplierById.executeQuery();
			if (rs.next()) {
				res = buildSupplierObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Supplier buildSupplierObject(ResultSet rs) throws SQLException, NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String address = rs.getString("address");
		String zipCode = rs.getString("zip_code");
		String city = rs.getString("city");
		String phone = rs.getString("phone");
		String email = rs.getString("email");
		String cvr = rs.getString("cvr");
		return new Supplier(id, name, address, zipCode, city, phone, email, cvr);
	}

	public List<Supplier> buildSupplierObjects(ResultSet rs) throws SQLException, NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		List<Supplier> res = new ArrayList<>();
		while(rs.next()) {
			res.add(buildSupplierObject(rs));
		}
		return res;
	}
	
	/**
	 * Updates the Supplier table in the database, in this case the cvr attribute.
	 * @param id as int
	 * @param cvr as String
	 */
	public void updateSupplier(int id, String cvr) throws SQLException {
		updateSupplierById.setString(1, cvr);
		updateSupplierById.setInt(2, id);
		updateSupplierById.executeUpdate();
	}
	
	public void removeFromSupplierById(int id) throws SQLException {
		removeFromSupplierById.setInt(1, id);
		removeFromSupplierById.execute();
	}

}
