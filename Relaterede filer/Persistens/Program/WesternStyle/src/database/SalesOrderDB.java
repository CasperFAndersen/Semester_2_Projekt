package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Discount;
import model.Employee;
import model.OrderCondition;
import model.SalesOrder;
import model.SalesOrderLine;

public class SalesOrderDB implements SalesOrderDBIF {

	private static final String findAllQ ="SELECT * FROM sales_order";
	private static final String insertQ ="INSERT INTO sales_order VALUES (?, ?, ?, ?, ?) ";
	private static final String findByIdQ ="SELECT * FROM sales_order WHERE id = ?";
	private static final String updateOrderConditionQ = "UPDATE sales_order SET order_condition = ? WHERE id = ?";

	private OrderConditionDB ocDB;

	private PreparedStatement findAll, insert, findById, updateOC;

	public SalesOrderDB() {
		try {
			findAll = DBConnection.getInstance().getConnection().prepareStatement(findAllQ);
			insert = DBConnection.getInstance().getConnection().prepareStatement(insertQ, insert.RETURN_GENERATED_KEYS);
			findById = DBConnection.getInstance().getConnection().prepareStatement(findByIdQ);
			updateOC = DBConnection.getInstance().getConnection().prepareStatement(updateOrderConditionQ);
			ocDB = new OrderConditionDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<SalesOrder> getSalesOrders() {
		ResultSet rs;
		try {
			rs = findAll.executeQuery();
			List<SalesOrder> res = buildSalesOrderObjects(rs);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addSalesOrder(SalesOrder salesOrder) {
		int id = 0;
		try {
			insert.setDate(1, java.sql.Date.valueOf(salesOrder.getCreateDate()));
			insert.setInt(2, ocDB.insertOrderCondition(salesOrder.getOrderCondition()));
			insert.setInt(3, salesOrder.getEmployee().getId());
			insert.setInt(4, salesOrder.getCustomer().getId());
			insert.setInt(5, salesOrder.getDiscount().getId());
			id = DBConnection.getInstance().executeInsertWithIdentity(insert);
			//Insert OrderLines
			new SalesOrderLineDB().insertSOLs(salesOrder.getAllSalesOrderLines(), id);
			return id;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public SalesOrder findSalesOrderById (int id) {
		ResultSet rs;
		SalesOrder res = null;
		try{
			findById.setInt(1, id);
			rs = findById.executeQuery();
			if(rs.next()) {
				res = buildSalesOrderObject(rs);
			}
			return res;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private SalesOrder buildSalesOrderObject(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		LocalDate date = rs.getDate("create_date").toLocalDate();
		ArrayList<SalesOrderLine> sol = new SalesOrderLineDB().findAllWithSalesOrderId(id);
		Discount d = new DiscountDB().findById(rs.getInt("discount_id"));
		OrderCondition oc = new OrderConditionDB().findById(rs.getInt("order_condition"));
		Employee e = new PersonDB().findEmployeeById(rs.getInt("emp_id"));
		Customer c = new PersonDB().findCustomerById(rs.getInt("cus_id"));

		SalesOrder salesOrder = new SalesOrder(id, date, sol, d, oc, e, c);
		return salesOrder;
	}

	private List<SalesOrder> buildSalesOrderObjects(ResultSet rs) throws SQLException{
		List<SalesOrder> res = new ArrayList<>();
		while(rs.next()) {
			SalesOrder so = buildSalesOrderObject(rs);
			res.add(so);
		}
		return res;
	}

	public void updateCondition(int orderConditionId, int prodId) throws SQLException {
		updateOC.setInt(1, orderConditionId);
		updateOC.setInt(2, prodId);
		updateOC.executeUpdate();
	}
}
