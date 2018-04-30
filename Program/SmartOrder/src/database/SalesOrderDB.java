package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exception.InsertFailedException;
import exception.NotImplementedException;
import exception.PersonNotFoundException;
import exception.PhoneAlreadyExistException;
import exception.ZipAlreadyExistException;
import model.Customer;
import model.Employee;
import model.OrderCondition;
import model.SalesOrder;
import model.SalesOrderLine;

/**
 * SmartOrder
 * SalesOrderDB.java
 * Purpose: Handle SalesOrder object interaction from and to the database
 * @author Gruppe 1
 * @version 1.0 
 */
public class SalesOrderDB implements SalesOrderDBIF {
	private MakePreparedStatement mps = new MakePreparedStatement();

	/**
	 * PreparedStatements for the SalesOrder table in the database.
	 */
	private static final String findAllQ = "SELECT * FROM salesorder, salesorderline ";
	private static final String findByIdQ = findAllQ + "WHERE salesorder.id = ? and salesorder.id = salesorderline.order_id";
	private static final String insertQ = "INSERT INTO salesorder VALUES ( ?, ?, ?, ?, ?, ?)"; 

	private static final String removeSalesOrderByIdQ = "DELETE FROM salesorder WHERE id = ?";

	private PreparedStatement findAll, findById, insert, removeSalesOrderById;

	public SalesOrderDB() {
		try {
			findAll = mps.makePreparedStatement(findAllQ);
			findById = mps.makePreparedStatement(findByIdQ);
			insert = mps.makePreparedStatement(insertQ, Statement.RETURN_GENERATED_KEYS);
			removeSalesOrderById = mps.makePreparedStatement(removeSalesOrderByIdQ);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private SalesOrder buildObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		LocalDate datePlaced = rs.getDate("date_placed").toLocalDate();
		Boolean paid = rs.getBoolean("paid");
		Boolean orderSent = rs.getBoolean("order_sent");
		Employee employee = new PersonDB().findEmployeeById(rs.getInt("employee_id"));
		Customer customer = new PersonDB().findCustomerById(rs.getInt("customer_id"));
		ArrayList<SalesOrderLine> salesOrderLines = new SalesOrderLineDB().findSalesOrderLinesBySalesOrderId(id);
		OrderCondition orderCondition = new OrderConditionDB().findById(rs.getInt("id"));
		return new SalesOrder(id, datePlaced, paid, orderSent, salesOrderLines, orderCondition, customer, employee);
	}

	private List<SalesOrder> buildObjects(ResultSet rs) throws SQLException{
		List<SalesOrder> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}


	@Override
	public List<SalesOrder> findAll() {
		ResultSet rs;
		List<SalesOrder> res = null;
		try {
			rs = findAll.executeQuery();
			res = buildObjects(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public SalesOrder findById(int id) {
		ResultSet rs;
		SalesOrder res = null;
		try {
			findById.setInt(1, id);
			rs = findById.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public void addSalesOrderToDatabase(SalesOrder salesOrder) throws SQLException, PhoneAlreadyExistException, ZipAlreadyExistException, InsertFailedException, PersonNotFoundException {
		insert.setDate(1, java.sql.Date.valueOf(salesOrder.getDatePlaced()));
		insert.setBoolean(2, salesOrder.isPaid());
		insert.setBoolean(3, salesOrder.isOrderSent());
		insert.setInt(4, salesOrder.getEmployee().getId());
		if (new PersonDB().findCustomerById(salesOrder.getCustomer().getId()) != null) {
			insert.setInt(5, salesOrder.getCustomer().getId());
		} else {
			throw new PersonNotFoundException("customer");
		}
		int conditionID = new OrderConditionDB().insertOrderCondition(salesOrder.getOrderCondition());
		insert.setInt(6, conditionID);
		int id = DBConnection.getInstance().executeInsertWithIdentity(insert);
		ArrayList<SalesOrderLine> salesOrderLines = salesOrder.getSalesOrderLines();
		for(SalesOrderLine sol : salesOrderLines){
			new SalesOrderLineDB().insertSalesOrderLine(id, sol);
		}
	}

	public void updateSalesOrder() throws NotImplementedException {
		throw new NotImplementedException();
	}

	public void removeSalesOrderById(int id) throws SQLException {
		removeSalesOrderById.setInt(1, id);
		removeSalesOrderById.executeQuery();
	}

}
