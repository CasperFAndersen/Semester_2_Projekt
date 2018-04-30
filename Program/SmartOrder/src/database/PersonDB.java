package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import model.Employee;
import model.Person;
import model.Supplier;

/**
 * SmartOrder
 * PersonDB.java
 * Purpose: Handle Person object interaction from and to the database
 * @author Gruppe 1
 * @version 1.0 
 */
public class PersonDB implements PersonDBIF {

	/**
	 * PreparedStatements for the Person table in the database
	 */
	private static final String insertPersonQ = "INSERT INTO person VALUES (?, ?, ?, ?, ?)";
	private static final String insertZipCityQ = "INSERT INTO zipcity VALUES (?, ?)";
	private static final String insertPhoneQ = "INSERT INTO phone VALUES (?, ?)";
	private static final String updatePersonByIdQ = "UPDATE person SET name = ?, address = ?, zip_code = ?, email = ?, person_type = ? WHERE id = ?";
	private static final String updatePhoneByIdQ = "UPDATE phone SET phone = ? WHERE person_id = ?";
	private static final String findZipCityQ = "SELECT * FROM zipcity WHERE zip_code = ?";
	private static final String findPhoneQ = "SELECT * FROM phone where phone = ?";
	private static final String removeFromPersonByIdQ = "DELETE FROM person WHERE id = ?";
	private static final String removeFromPhoneByIdQ = "DELETE FROM phone WHERE person_id = ?";

	private static final String countRowsInPersonTableQ = "select COUNT(*) from Person";

	private MakePreparedStatement mps = new MakePreparedStatement();
	private PreparedStatement insertZipCity, insertPerson, insertPhone, 
	findZipCity, findPhone,
	updatePersonById, updatePhoneById,
	removeFromPersonById, removeFromPhoneById,
	countRowsInPersonTable;

	public PersonDB(){
		try {
			findZipCity = mps.makePreparedStatement(findZipCityQ);
			findPhone = mps.makePreparedStatement(findPhoneQ);
			insertZipCity = mps.makePreparedStatement(insertZipCityQ);
			insertPerson = mps.makePreparedStatement(insertPersonQ, Statement.RETURN_GENERATED_KEYS);
			insertPhone = mps.makePreparedStatement(insertPhoneQ);
			removeFromPersonById = mps.makePreparedStatement(removeFromPersonByIdQ);
			removeFromPhoneById = mps.makePreparedStatement(removeFromPhoneByIdQ);
			countRowsInPersonTable = mps.makePreparedStatement(countRowsInPersonTableQ);
			updatePersonById = mps.makePreparedStatement(updatePersonByIdQ);
			updatePhoneById = mps.makePreparedStatement(updatePhoneByIdQ);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks whether a zipCode has been added before. 
	 * @param zipCode as String
	 * @return true or false based on whether the zipCode was found in the database or not.
	 */
	private boolean isZipExisting(String zipCode) {
		ResultSet rs = null;
		boolean res = false;
		try {
			findZipCity.setString(1, zipCode);
			rs = findZipCity.executeQuery();
			if (rs.next()) {
				res = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Checks whether a phoneNumber has been added before. 
	 * @param Phone as String
	 * @return true or false based on whether the phoneNumber was found in the database or not.
	 */
	private boolean isPhoneExisting(String phone) {
		ResultSet rs = null;
		boolean res = false;
		try {
			findPhone.setString(1, phone);
			rs = findPhone.executeQuery();
			if (rs.next()) {
				res = true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}


	/**
	 * Inserts information in the ZipCity table in the database.
	 * @param zip as String
	 * @param city as String
	 */
	private void insertZipCity(String zip, String city) throws SQLException, ZipAlreadyExistException {
		if (!isZipExisting(zip)) {
			insertZipCity.setString(1, zip);
			insertZipCity.setString(2, city);
			insertZipCity.execute();
		} else {
			throw new ZipAlreadyExistException();
		}
	}

	/**
	 * Inserts information into the Phone table in the database.
	 * @param phone as String
	 * @param id as int
	 */
	private void insertPhone(String phone, int id) throws SQLException, PhoneAlreadyExistException {
		if (!isPhoneExisting(phone)) {
			insertPhone.setString(1, phone);
			insertPhone.setInt(2, id);
			insertPhone.execute();
		} else {
			throw new PhoneAlreadyExistException();
		}
	}

	/**
	 * Inserts information into the Person table in the database, also checks if Zip is existing and if the phone is already used.
	 * @param person as String
	 * @param type as String
	 * @return the generated id for the person added to the database.
	 */
	private int insertPerson(Person person, String type) throws SQLException, ZipAlreadyExistException, InsertFailedException {
		int id = -1;
		try {
			insertZipCity(person.getZipCode(), person.getCity()); 
		} catch (ZipAlreadyExistException e) {
			e.getMessage();
		}

		insertPerson.setString(1, person.getName());
		insertPerson.setString(2, person.getAddress());
		insertPerson.setString(3, person.getZipCode());
		insertPerson.setString(4, person.getEmail());
		insertPerson.setString(5, type);
		insertPerson.executeUpdate();
		try (ResultSet generatedKeys = insertPerson.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			} else {
				throw new InsertFailedException("Person");
			}
		}

		try {
			insertPhone(person.getPhone(), id);
		} catch (PhoneAlreadyExistException e) {
			e.getMessage();
		}
		return id;
	}

	/**
	 * Uses the CustomerDB class to insert information into the Customer table.
	 * @param customer as Customer
	 */
	@Override
	public void insertCustomer(Customer customer) throws SQLException, PhoneAlreadyExistException, ZipAlreadyExistException, InsertFailedException {
		if (!isPhoneExisting(customer.getPhone())) {
			int id = insertPerson(customer, "customer");
			new CustomerDB().insertCustomer(customer, id);
		} else {
			throw new InsertFailedException("customer");
		}

	}

	/**
	 * Uses the CustomerDB class to insert information into the Employee table.
	 * @param employee as Employee
	 */
	@Override
	public void insertEmployee(Employee employee) throws SQLException, PhoneAlreadyExistException, ZipAlreadyExistException, InsertFailedException {
		if (!isPhoneExisting(employee.getPhone())) {
			int id = insertPerson(employee, "employee");
			new EmployeeDB().insertEmployee(employee, id);
		} else {
			throw new InsertFailedException("employee");
		}
	}

	/**
	 * Uses the SupplierDB class to insert information into the Supplier table.
	 * @param supplier as Supplier
	 */
	@Override
	public void insertSupplier(Supplier supplier) throws SQLException, PhoneAlreadyExistException, ZipAlreadyExistException, InsertFailedException {
		if (!isPhoneExisting(supplier.getPhone())) {
			int id = insertPerson(supplier, "supplier");
			new SupplierDB().insertSupplier(supplier, id);
		} else {
			throw new InsertFailedException("supplier");
		}
	}

	/**
	 * Finds a Person in the database based on a phone number.
	 * @param phone as String
	 * @return Person object
	 */
	private int findPersonIdByPhone(String phone) {
		ResultSet rs;
		int res = -1;
		try {
			findPhone.setString(1, phone);
			rs = findPhone.executeQuery();
			if (rs.next()) {
				res = rs.getInt("person_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Uses the CustomerDB class to find an ArrayList of all customers.
	 * @return ArrayList of all customers
	 */
	@Override
	public List<Customer> findAllCustomers() {
		try {
			return new CustomerDB().findAllCustomers();
		} catch (NameValidationException | AddressValidationException | ZipCodeValidationException
				| CityValidationException | PhoneValidationException | EmailValidationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Uses the CustomerDB class to find a customer by phone number.
	 * @param phone as String
	 * @return Customer object.
	 */
	@Override
	public Customer findCustomerByPhone(String phone) {
		try {
			return new CustomerDB().findCustomerByPhone(phone);
		} catch (NameValidationException | AddressValidationException | ZipCodeValidationException
				| CityValidationException | PhoneValidationException | EmailValidationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Uses the CustomerDb class to find a customer by id.
	 * @param id as int
	 * @return Customer object
	 */
	@Override
	public Customer findCustomerById (int id) {
		try {
			return new CustomerDB().findCustomerById(id);
		} catch (NameValidationException | AddressValidationException | ZipCodeValidationException
				| CityValidationException | PhoneValidationException | EmailValidationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * uses the EmployeeDB class to find an ArrayList of all employees.
	 * @return ArrayList of all employees
	 */
	@Override
	public List<Employee> findAllEmployees(){
		try {
			return new EmployeeDB().findAllEmployees();
		} catch (NameValidationException | AddressValidationException | ZipCodeValidationException
				| CityValidationException | PhoneValidationException | EmailValidationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Uses the EmployeeDB class to find an employee with a phone number.
	 * @param phone as String
	 * @return Employee object
	 */
	@Override
	public Employee findEmployeeByPhone(String phone) {
		try {
			return new EmployeeDB().findEmployeeByPhone(phone);
		} catch (NameValidationException | AddressValidationException | ZipCodeValidationException
				| CityValidationException | PhoneValidationException | EmailValidationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Uses the EmployeeDB class to find an employee by id.
	 * @param id as int
	 * @return Employee object
	 */
	@Override
	public Employee findEmployeeById (int id) {
		try {
			return new EmployeeDB().findEmployeeById(id);
		} catch (NameValidationException | AddressValidationException | ZipCodeValidationException
				| CityValidationException | PhoneValidationException | EmailValidationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Uses the EmployeeDB class to find an employee by username and password.
	 * @param username as String
	 * @param password as String
	 * @return Employee object
	 */
	@Override
	public Employee findEmployeeByUsernameAndPassword(String username, String password) throws SQLException {
		try {
			return new EmployeeDB().findEmployeeByUsernameAndPassword(username, password);
		} catch (NameValidationException | AddressValidationException | ZipCodeValidationException
				| CityValidationException | PhoneValidationException | EmailValidationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Uses the SupplierDB class to find an ArrayList of all suppliers.
	 * @return ArrayList of all suppliers
	 */
	@Override
	public List<Supplier> findAllSuppliers() {
		try {
			return new SupplierDB().findAllSuppliers();
		} catch (NameValidationException | AddressValidationException | ZipCodeValidationException
				| CityValidationException | PhoneValidationException | EmailValidationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Uses the SupplierDB class to find a supplier by phone number.
	 * @param phone as String
	 * @return Supplier object
	 */
	@Override
	public Supplier findSupplierByPhone(String phone) {
		try {
			return new SupplierDB().findSupplierByPhone(phone);
		} catch (NameValidationException | AddressValidationException | ZipCodeValidationException
				| CityValidationException | PhoneValidationException | EmailValidationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Uses the SupplierDB class to find a supplier by id.
	 * @param id as int
	 * @return Supplier object
	 */
	@Override
	public Supplier findSupplierById(int id) {
		try {
			return new SupplierDB().findSupplierById(id);
		} catch (NameValidationException | AddressValidationException | ZipCodeValidationException
				| CityValidationException | PhoneValidationException | EmailValidationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Uses the PersonDB and CustomerDB class to update a Customer by phone number.
	 * @param name as String
	 * @param address as String
	 * @param zipCode as String
	 * @param city as String
	 * @param currPhone as String
	 * @param newPhone as String
	 * @param email as String
	 * @param person_type as String
	 * @param type as String
	 */
	@Override
	public void updateCustomerByPhone(String name, String address, String zipCode, String city, String currPhone, String newPhone, String email, String person_type, String type) throws PhoneAlreadyExistException {
		try {
			Customer customer = findCustomerByPhone(currPhone);
			int customerId = customer.getId();
			updatePersonById(customerId, name, address, zipCode, email, person_type);
			new CustomerDB().updateCustomer(type, customerId);
			updatePhoneById(customerId, newPhone);
		} catch (SQLException e) {
			e.printStackTrace();  
		}
	}

	/**
	 * Uses the PersonDB and the EmployeeDB class to update an Employee by phone number
	 * @param name as String
	 * @param address as String
	 * @param zipCode as String
	 * @param city as String
	 * @param currPhone as String
	 * @param newPhone as String
	 * @param email as String
	 * @param person_type as String
	 * @param ssn as String
	 * @param salary as double
	 * @param username as String
	 * @param password as String
	 */
	@Override
	public void updateEmployeeByPhone(String name, String address, String zipCode, String city, String currPhone, String newPhone, String email, String person_type, String ssn, double salary, String username, String password) throws PhoneAlreadyExistException {
		try {
			Employee employee = findEmployeeByPhone(currPhone);
			int employeeId = employee.getId();
			updatePersonById(employeeId, name, address, zipCode, email, person_type);
			updatePhoneById(employeeId, newPhone);
			new EmployeeDB().updateEmployee(employeeId, ssn, salary, username, password);
		} catch (SQLException e) {
			e.printStackTrace();  
		}
	}

	/**
	 * Uses the PersonDb and the SupplierDB class to update a Supplier by phone number.
	 * @param name as String
	 * @param address as String
	 * @param zipCode as String
	 * @param city as String
	 * @param currPhone as String
	 * @param newPhone as String
	 * @param email as String
	 * @param person_type as String
	 * @param cvr as String
	 */	
	@Override
	public void updateSupplierByPhone(String name, String address, String zipCode, String city, String currPhone, String newPhone, String email, String person_type, String cvr) throws PhoneAlreadyExistException {
		try {
			Supplier supplier = findSupplierByPhone(currPhone);
			int supplierId = supplier.getId();
			updatePersonById(supplierId, name, address, zipCode, email, person_type);
			updatePhoneById(supplierId, newPhone);
			new SupplierDB().updateSupplier(supplierId, cvr);
		} catch (SQLException e) {
			e.printStackTrace();  
		}
	}

	/**
	 * Updates the information in the Person table in the database.
	 * @param id as int
	 * @param name as String
	 * @param address as String
	 * @param zipCode as String
	 * @param email as String
	 * @param person_type as String
	 */
	private void updatePersonById(int id, String name, String address, String zipCode, String email, String person_type) throws SQLException {
		updatePersonById.setString(1, name);
		updatePersonById.setString(2, address);
		updatePersonById.setString(3, zipCode);
		updatePersonById.setString(4, email);
		updatePersonById.setString(5, person_type);
		updatePersonById.setInt(6, id);
		updatePersonById.executeUpdate();
	}

	/**
	 * Updates the information in the Phone table in the database by id.
	 * @param id as int 
	 * @param phone as String
	 */
	private void updatePhoneById(int id, String phone) throws SQLException, PhoneAlreadyExistException {
		if (!isPhoneExisting(phone)) {
			updatePhoneById.setString(1, phone);
			updatePhoneById.setInt(2, id);
			updatePhoneById.executeUpdate();
		}
		else { 
			throw new PhoneAlreadyExistException();
		}
	}

	/**
	 * Uses the PersonDB and CustomerDB class to remove a customer from the database using phone number.
	 * @param phone as String
	 */
	@Override
	public void removeCustomerByPhone(String phone) {
		try {
			Customer customer = findCustomerByPhone(phone);
			new CustomerDB().removeFromCustomerById(customer.getId());
			removeFromPhoneById(customer.getId());
			removeFromPersonById(customer.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Uses the PersonDB and the EmployeeDB class to remove and employee from the database using phone number.
	 * @param phone as String
	 */
	@Override
	public void removeEmployeeByPhone(String phone) {
		try {
			Employee employee = findEmployeeByPhone(phone);
			removeFromPhoneById(employee.getId());
			new EmployeeDB().removeFromEmployeeById(employee.getId());
			removeFromPersonById(employee.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Uses the PersonDB and SupplierDB class to remove a supplier from the database using phone number.
	 * @param phone as String
	 */
	@Override
	public void removeSupplierByPhone(String phone) {
		try {
			Supplier supplier = findSupplierByPhone(phone);
			new SupplierDB().removeFromSupplierById(supplier.getId());
			removeFromPhoneById(supplier.getId());
			removeFromPersonById(supplier.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes information in the Person table in the database using id.
	 * @param id as int
	 */
	private void removeFromPersonById(int id) throws SQLException {
		removeFromPersonById.setInt(1, id);
		removeFromPersonById.execute();
	}

	/**
	 * Removes information in the Phone table in the database using id.
	 * @param id as int
	 */
	private void removeFromPhoneById(int id) throws SQLException {
		removeFromPhoneById.setInt(1, id);
		removeFromPhoneById.execute();
	}

	/**
	 * Is used to add an extra phone number to an already existing Person in the database.
	 * @param existingPhone as String
	 * @param extraPhone as String
	 */
	@Override
	public void addPhoneToPerson(String existingPhone, String extraPhone) throws SQLException, PhoneAlreadyExistException {
		int id = findPersonIdByPhone(existingPhone);
		insertPhone(extraPhone, id);
	}


	/**
	 * Method made for testing purposes. Returns the amount of tuples in the Person-table.
	 * @return amount of rows in Person table in the database.
	 */
	public int getRowCountFromPersonTable() throws SQLException {
		int res = -1;
		ResultSet rs = countRowsInPersonTable.executeQuery();
		if (rs.next()) {
			res = rs.getInt(1);
		}
		return res;
	}

}
