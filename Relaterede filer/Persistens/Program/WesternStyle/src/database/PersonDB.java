package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;

public class PersonDB implements PersonDBIF {
	private static final String findAllEmpQ ="SELECT person.id, person.name, person.address, person.zipcode, person.email, employee.salary, phone.phoneNR FROM person, employee, phone  WHERE person_id = person.id AND employee.id = person.id";
	private static final String findAllCusQ ="SELECT person.id, person.name, person.address, person.zipcode, person.email, customer.type, phone.phoneNR FROM person, customer, phone  WHERE person_id = person.id AND customer.id = person.id";
	private static final String findCusByIdQ = findAllCusQ + " AND person.id = ?";
	private static final String findEmpByIdQ = findAllEmpQ + " AND person.id = ?";

	private PreparedStatement findAllCus, findCusById, findAllEmp, findEmpById;

	public PersonDB() {
		try {
			findAllCus = DBConnection.getInstance().getConnection().prepareStatement(findAllCusQ);
			findCusById = DBConnection.getInstance().getConnection().prepareStatement(findCusByIdQ);
			findAllEmp = DBConnection.getInstance().getConnection().prepareStatement(findAllEmpQ);
			findEmpById = DBConnection.getInstance().getConnection().prepareStatement(findEmpByIdQ);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Customer> findAllCustomers() {
		ResultSet rs;
		try {
			rs = findAllCus.executeQuery();
			List<Customer> res = buildCustomerObjects(rs);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer findCustomerById(int id) {
		ResultSet rs;
		Customer res = null;
		try{
			findCusById.setInt(1, id);
			rs = findCusById.executeQuery();
			if (rs.next()) {
				res = buildCustomerObject(rs);
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Customer buildCustomerObject(ResultSet rs) throws SQLException{
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String address = rs.getString("address") + " " + rs.getString("zipcode");
		String email = rs.getString("email");
		String phone = rs.getString("phoneNr");
		String type = rs.getString("type");
		Customer c = new Customer(id, name, address, email, phone, type);
		return c;
	}

	private List<Customer> buildCustomerObjects(ResultSet rs) throws SQLException{
		List<Customer> res = new ArrayList<>();
		while(rs.next()) {
			Customer c = buildCustomerObject(rs);
			res.add(c);
		}
		return res;
	}

	@Override
	public List<Employee> findAllEmployee() {
		ResultSet rs;
		try {
			rs = findAllEmp.executeQuery();
			List<Employee> res = buildEmployeeObjects(rs);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee findEmployeeById(int id) {
		ResultSet rs;
		Employee res = null;
		try{
			findEmpById.setInt(1, id);
			rs = findEmpById.executeQuery();
			if (rs.next()) {
				res = buildEmployeeObject(rs);
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Employee buildEmployeeObject(ResultSet rs) throws SQLException{
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String address = rs.getString("address") + " " + rs.getString("zipcode");
		String email = rs.getString("email");
		String phone = rs.getString("phoneNR");
		int salary  = rs.getInt("salary");
		Employee e = new Employee(id, name, address, email, phone, salary);
		return e;
	}

	private List<Employee> buildEmployeeObjects(ResultSet rs) throws SQLException{
		List<Employee> res = new ArrayList<>();
		while(rs.next()) {
			Employee e = buildEmployeeObject(rs);
			res.add(e);
		}
		return res;
	}
}
