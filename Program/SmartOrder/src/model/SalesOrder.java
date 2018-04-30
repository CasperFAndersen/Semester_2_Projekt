package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * SmartOrder
 * Salesorder.java
 * Purpose: Creates the SalesOrder and holds information to the SalesOrder
 * @author Gruppe 1
 * @version 1.0 
 */
public class SalesOrder {
	private int id;
	private LocalDate datePlaced;
	private boolean paid;
	private boolean orderSent;
	private ArrayList<SalesOrderLine> salesOrderLines;
	private OrderCondition orderCondition;
	private double totalPrice;
	private Customer customer;
	private Employee employee;

	public SalesOrder() {
		salesOrderLines = new ArrayList<>();
		datePlaced = LocalDate.now();
		paid = false;
	}

	public SalesOrder(int id, LocalDate datePlaced, boolean paid, boolean orderSent, ArrayList<SalesOrderLine> salesOrderLines, OrderCondition orderCondition, Customer customer, Employee employee) {
		setId(id);
		setDatePlaced(datePlaced);
		setPaid(paid);
		setOrderSent(orderSent);
		setSalesOrderLines(salesOrderLines);
		setOrderCondition(orderCondition);
		addCustomerToSalesOrder(customer);
		addEmployeeToSalesOrder(employee);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDatePlaced() {
		return datePlaced;
	}

	public void setDatePlaced(LocalDate datePlaced) {
		this.datePlaced = datePlaced;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public boolean isOrderSent() {
		return orderSent;
	}

	public void setOrderSent(boolean orderSent) {
		this.orderSent = orderSent;
	}

	/**
	 * Calculates the totalPrice off all SalesOrderlines, this is done with a stream() operation MapToDouble() and sum().
	 * @return totalPrice
	 */
	public double getTotalPrice() {
		this.totalPrice = salesOrderLines.stream() 
				.mapToDouble(x -> x.getSubTotal())
				.sum();
		return totalPrice;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void addCustomerToSalesOrder(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void addEmployeeToSalesOrder(Employee employee) {
		this.employee = employee;
	}

	public ArrayList<SalesOrderLine> getSalesOrderLines() {
		return salesOrderLines;
	}

	public void setSalesOrderLines(ArrayList<SalesOrderLine> salesOrderLines) {
		this.salesOrderLines = salesOrderLines;
	}

	/**
	 * Adds a SalesOrderLine to the SalesOrder, if the product is already connected to the SalesOrder it's amount will be updated.
	 * @param product as Product
	 * @param amount as int
	 */
	public void addSalesOrderLineToSalesOrder(Product product, int amount) {
		SalesOrderLine res = null;
		for (SalesOrderLine sol : salesOrderLines) {
			if (sol.getProduct().equals(product)) {
				res = sol;
			}
		}
		if (res != null) {
			res.setAmount(res.getAmount() + amount);
		} else {
			salesOrderLines.add(new SalesOrderLine(product, amount));
		}
	}

	public OrderCondition getOrderCondition() {
		return orderCondition;
	}

	public void setOrderCondition(OrderCondition orderCondition) {
		this.orderCondition = orderCondition;
	}

}
