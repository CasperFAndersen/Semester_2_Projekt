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
import model.Employee;

/**
 * SmartOrder
 * EmployeeDB.java
 * Purpose: Handle Employee object interaction from and to the database
 * @author Gruppe 1
 * @version 1.0 
 */
public class EmployeeDB {

	/**
	 * PreparedStatements for the Employee and password tables in the database.
	 */
	private static final String employeeParameters = " and phone.person_id = employee.id and phone.person_id = person.id and employee.id = person.id and employee.id = password.id and person.zip_code = zipcity.zip_code";

	private static final String insertEmployeeQ = "INSERT INTO employee VALUES (?, ?, ?, ?)";
	private static final String insertPasswordQ = "INSERT INTO password VALUES (?, ?)";
	private static final String findAllEmployeesQ = "SELECT * FROM person, phone, zipcity, employee, password ";
	private static final String findEmployeeByIdQ = findAllEmployeesQ + "WHERE employee.id = ?" + employeeParameters;
	private static final String findEmployeeByPhoneQ = findAllEmployeesQ + "WHERE phone.phone = ?" + employeeParameters; 
	private static final String findEmployeeByUsernameAndPasswordQ = "SELECT * FROM Employee, Password WHERE username = ? and password = ?";

	private static final String updateEmployeeByIdQ = "UPDATE employee SET ssn = ?, salary = ?, username = ? WHERE id = ?";
	private static final String updatePasswordByIdQ	= "UPDATE password SET password = ? WHERE id = ?";

	private static final String removeFromEmployeeByIdQ = "DELETE FROM employee WHERE id = ?";
	private static final String removeFromPasswordByIdQ = "DELETE FROM password WHERE id = ?";

	private PreparedStatement insertEmployee, insertPassword, findAllEmployees, findEmployeeById, findEmployeeByPhone, findEmployeeByUsernameAndPassword, 
	updateEmployeeById, updatePasswordById, removeFromEmployeeById, removeFromPasswordById;

	private MakePreparedStatement mps = new MakePreparedStatement();

	public EmployeeDB() {
		try {
			insertEmployee = mps.makePreparedStatement(insertEmployeeQ);
			insertPassword = mps.makePreparedStatement(insertPasswordQ);
			findAllEmployees = mps.makePreparedStatement(findAllEmployeesQ);
			findEmployeeById = mps.makePreparedStatement(findEmployeeByIdQ);
			findEmployeeByPhone = mps.makePreparedStatement(findEmployeeByPhoneQ);
			findEmployeeByUsernameAndPassword = mps.makePreparedStatement(findEmployeeByUsernameAndPasswordQ);
			updateEmployeeById = mps.makePreparedStatement(updateEmployeeByIdQ);
			updatePasswordById = mps.makePreparedStatement(updatePasswordByIdQ);
			removeFromEmployeeById = mps.makePreparedStatement(removeFromEmployeeByIdQ);
			removeFromPasswordById = mps.makePreparedStatement(removeFromPasswordByIdQ);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserts information in the employee table in the database based on the id.
	 * @param employee as object
	 * @param id as int
	 */
	public void insertEmployee(Employee employee, int id) throws SQLException {
		insertEmployee.setInt(1, id);
		insertEmployee.setString(2, employee.getSsn());
		insertEmployee.setDouble(3, employee.getSalary());
		insertEmployee.setString(4, employee.getUsername());
		insertEmployee.execute();

		insertPassword.setInt(1, id);
		insertPassword.setString(2, employee.getPassword());
		insertPassword.execute();
	}

	/**
	 * Finds all employees in the database.
	 * @return an ArrayList of Employee objects.
	 */
	public List<Employee> findAllEmployees() throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException{
		ResultSet rs;
		List<Employee> res = null;
		try {
			rs = findAllEmployees.executeQuery();
			res = buildEmployeeObjects(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Finds an employee in the database based on a phone number.
	 * @param phone as String
	 * @return an Employee object
	 */
	public Employee findEmployeeByPhone(String phone) throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		ResultSet rs;
		Employee res = null;
		try {
			findEmployeeByPhone.setString(1, phone);
			rs = findEmployeeByPhone.executeQuery();
			if (rs.next()) {
				res = buildEmployeeObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Finds a employee in the database based on a id.
	 * @param id as int
	 * @return an Employee object
	 */
	public Employee findEmployeeById (int id) throws NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		ResultSet rs;
		Employee res = null;
		try {
			findEmployeeById.setInt(1, id);
			rs = findEmployeeById.executeQuery();
			if (rs.next()) {
				res = buildEmployeeObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Creates a Employee Object from an ResultSet, which is filled with information retrieved from the database using a find method.
	 * @param rs as ResultSet
	 * @return A Employee object
	 */
	public Employee buildEmployeeObject(ResultSet rs) throws SQLException, NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {	
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String address = rs.getString("address"); 
		String zipCode = rs.getString("zip_code"); 
		String city = rs.getString("city");
		String phone = rs.getString("phone");
		String email = rs.getString("email");
		String ssn = rs.getString("ssn");
		double salary = rs.getDouble("salary");
		String username = rs.getString("username");
		String password = rs.getString("password");
		return new Employee(id, name, address, zipCode, city, phone, email, ssn, salary, username, password);
	}

	/**
	 * Creates an ArrayList of Employee objects from an ResultSet, which is filled with information retrieved from the database with a find method.
	 * @param rs as ResultSet
	 * @return An ArrayList of Employee objects.
	 */
	public List<Employee> buildEmployeeObjects(ResultSet rs) throws SQLException, NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		List<Employee> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildEmployeeObject(rs));
		}
		return res;
	}
/**
 * Updates the Employee's table in the database. 
 * @param id as int
 * @param ssn as String
 * @param salary as double
 * @param username as String
 * @param password as String
 */
	public void updateEmployee(int id, String ssn, double salary, String username, String password) throws SQLException {
		updateEmployeeById.setString(1, ssn);
		updateEmployeeById.setDouble(2, salary);
		updateEmployeeById.setString(3, username);
		updateEmployeeById.setInt(4, id);
		updateEmployeeById.executeUpdate();

		updatePasswordById.setString(1, password);
		updatePasswordById.setInt(2, id);
		updatePasswordById.executeUpdate();
	}

	/**
	 * Removes an Employee tuple and an Password tupel from the database.
	 * @param id as int
	 */
	public void removeFromEmployeeById(int id) throws SQLException {
		removeFromEmployeeById.setInt(1, id);
		removeFromEmployeeById.execute();

		removeFromPasswordById.setInt(1, id);
		removeFromPasswordById.execute();
	}

	/**
	 * finds an employee based on the username and password.
	 * @param username as String
	 * @param password as String
	 * @return an Employee object
	  */
	public Employee findEmployeeByUsernameAndPassword(String username, String password) throws SQLException, NameValidationException, AddressValidationException, ZipCodeValidationException, CityValidationException, PhoneValidationException, EmailValidationException {
		Employee res = null;
		String user = "";
		String pass = "";
		int id;
		findEmployeeByUsernameAndPassword.setString(1, username);
		findEmployeeByUsernameAndPassword.setString(2, password);
		ResultSet rs = findEmployeeByUsernameAndPassword.executeQuery();
		while (rs.next()) {
			user = rs.getString("username");
			pass = rs.getString("password");
			id = rs.getInt("id");
			if (user.equalsIgnoreCase(username) && pass.equals(password)) {
				res = new PersonDB().findEmployeeById(id);
			}
		}
		return res;
	}

}
