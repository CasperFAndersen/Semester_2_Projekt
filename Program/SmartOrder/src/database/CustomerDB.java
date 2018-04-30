package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.AddressValidationException;
import exception.CityValidationException;
import exception.EmailValidationException;
import exception.InsertFailedException;
import exception.NameValidationException;
import exception.PhoneAlreadyExistException;
import exception.PhoneValidationException;
import exception.ZipAlreadyExistException;
import exception.ZipCodeValidationException;
import model.Customer;

/**
 * SmartOrder
 * CustomerDB.java
 * Purpose: Handle Customer object interaction from and to the database
 * @author Gruppe 1
 * @version 1.0 
 */
public class CustomerDB {

	/**
	 * PreparedStatements for the Customer table in the database.
	 */
	private static final String customerParameters = " and phone.person_id = customer.id and phone.person_id = person.id and customer.id = person.id and person.zip_code = zipcity.zip_code";
	private static final String insertCustomerQ = "INSERT INTO customer VALUES (?, ?)";
	private static final String findAllCustomersQ = "SELECT * FROM person, customer, phone, zipcity WHERE customer.id = person.id " + customerParameters;
	private static final String findCustomerByPhoneQ = "SELECT * FROM person, customer, phone, zipcity WHERE phone.phone = ?" + customerParameters; 
	private static final String findCustomerByIdQ = findAllCustomersQ + " AND customer.id = ?" + customerParameters;
	private static final String updateCustomerByIdQ = "UPDATE customer SET type = ? WHERE id = ?";
	private static final String removeFromCustomerByIdQ = "DELETE FROM customer WHERE id = ?";
	
	private PreparedStatement insertCustomer, findAllCustomers, findCustomerById, findCustomerByPhone, updateCustomerById, removeFromCustomerById;
	private MakePreparedStatement mps = new MakePreparedStatement();
	
	public CustomerDB() {
		try {
			insertCustomer = mps.makePreparedStatement(insertCustomerQ);
			findAllCustomers = mps.makePreparedStatement(findAllCustomersQ);
			findCustomerById = mps.makePreparedStatement(findCustomerByIdQ);
			findCustomerByPhone = mps.makePreparedStatement(findCustomerByPhoneQ);
			updateCustomerById = mps.makePreparedStatement(updateCustomerByIdQ);
			removeFromCustomerById = mps.makePreparedStatement(removeFromCustomerByIdQ);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserts information in the Customer table in the database.
	 * @param customer object
	 * @param id
	 */
	public void insertCustomer(Customer customer, int id) throws SQLException, PhoneAlreadyExistException, ZipAlreadyExistException, InsertFailedException {
		insertCustomer.setInt(1, id);
		insertCustomer.setString(2, customer.getType());
		insertCustomer.execute();
	}
	
	/**
	 * Finds an customer in the database based on a phone number.
	 * @param phone of the Customer which is to be found.
	 * @return an Customer object
	 */
	public Customer findCustomerByPhone(String phone) throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		ResultSet rs; 
		Customer res = null;
		try {
			findCustomerByPhone.setString(1, phone);
			rs = findCustomerByPhone.executeQuery();
			if (rs.next()) {
				res = buildCustomerObject(rs);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return res;
	}
	
	/**
	 * Finds a Customer in the database based on an id.
	 * @param id of the customer which is to be found
	 * @return an Customer object
	 */
	public Customer findCustomerById(int id) throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		ResultSet rs;
		Customer res = null;
		try {
			findCustomerById.setInt(1, id);
			rs = findCustomerById.executeQuery();
			if (rs.next()) {
				res = buildCustomerObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Finds all customers in the database.
	 * @return an ArrayList of Customer objects.
	 */
	public List<Customer> findAllCustomers() throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		ResultSet rs;
		List<Customer> res = null;
		try {
			rs = findAllCustomers.executeQuery();
			res = buildCustomerObjects(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Creates a Customer Object from an ResultSet, which is filled with information retrieved from the database using a find method.
	 * @param a ResultSet which holds the information retrieved from the database with the help of a PreparedStatement. In this case a Customer
	 * @return A Customer object
	 */
	public Customer buildCustomerObject(ResultSet rs) throws SQLException, NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String address = rs.getString("address");
		String zipCode = rs.getString("zip_code");
		String city = rs.getString("city");
		String phone = rs.getString("phone");
		String email = rs.getString("email");
		String type = rs.getString("type");
		return new Customer(id, name, address, zipCode, city, phone, email, type);
	}
	
	/**
	 * Creates an ArrayList of Customer Object from an ResultSet, which is filled with information retrieved from the database with a find method.
	 * @param a ResultSet which holds the information retrieved from the database with the help of a preparedstatement. In this case  Customers.
	 * @return An ArrayList of Customer objects.
	 */
	public List<Customer> buildCustomerObjects(ResultSet rs) throws SQLException, NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		List<Customer> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildCustomerObject(rs));
		}
		return res;
	}

	/**
	 * Updates the Customer's table in the database, in this case the type attribute.
	 * @param type (Either 'privat' or 'erhverv'
	 * @param id
	 */
	public void updateCustomer(String type, int id) throws SQLException {
		updateCustomerById.setString(1, type);
		updateCustomerById.setInt(2, id);
		updateCustomerById.executeUpdate();
	}
	
	/**
	 * Removes a Customer tuple from the Customer table in the database
	 * @param id of the Customer which is to be deleted
	 */
	public void removeFromCustomerById(int id) throws SQLException {
		removeFromCustomerById.setInt(1, id);
		removeFromCustomerById.execute();
	}
	
}
