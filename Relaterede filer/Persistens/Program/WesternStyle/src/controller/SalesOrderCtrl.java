package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import database.DiscountDB;
import database.OrderConditionDB;
import database.SalesOrderDB;
import model.Customer;
import model.Delivered;
import model.Discount;
import model.Employee;
import model.Order;
import model.OrderCondition;
import model.Product;
import model.SalesOrder;
import model.SalesOrderLine;

public class SalesOrderCtrl {
	private SalesOrder salesOrder = new SalesOrder();
	private SalesOrderDB salesOrderDB = new SalesOrderDB();
	private ProductCtrl productCtrl = new ProductCtrl();
	private CustomerCtrl cCtrl = new CustomerCtrl();
	private Customer customer;
	private Employee employee;

	public SalesOrderCtrl() {
		salesOrder = new SalesOrder();
	}

	public SalesOrder findSalesOrderById(int id) {
		return salesOrderDB.findSalesOrderById(id);
	}

	public void createSalesOrder() {
		salesOrder = new SalesOrder();
	}

	public int closeSalesOrder() {
		return salesOrderDB.addSalesOrder(salesOrder);
	}

	public void updateProductAmount(int id, int amount) throws SQLException{
		productCtrl.updateProductAmount(id, amount);
	}
	
	public void addProductToSalesOrder(Product p, int amount) {
		for(SalesOrderLine sol : salesOrder.getAllSalesOrderLines()){
			if (sol.getProduct().getId() == p.getId()){
				sol.setAmount(sol.getAmount() + amount);
			return;
			}
		}
		salesOrder.addProductToSalesOrder(p, amount);
	}

	public ArrayList<SalesOrderLine> getAllSalesOrderLines() {
		return salesOrder.getAllSalesOrderLines();
	}

	public double getTotalPrice() {
		return salesOrder.getTotalPrice();
	}

	public double getDiscountForOffer() {
		return salesOrder.getDiscountForOffer();
	}

	public double getTotalPriceAfterDiscount() {
		return salesOrder.getTotalPriceAfterDiscount();
	}

	public boolean checkDiscountPrivate() {
		return salesOrder.checkDiscountPrivate();
	}

	public boolean checkDiscountClub() {
		return salesOrder.checkDiscountClub();
	}

	public double createDiscountForOffer(double discount) {
		return salesOrder.createDiscountForOffer(discount);
	}

	public OrderCondition createOrder() {
		return salesOrder.createOrder();
	}

	public OrderCondition createOffer() {
		return salesOrder.createOffer();
	}

	public OrderCondition createDelivered() {
		return salesOrder.createDelivered();
	}

	public OrderCondition setOrderCondition(String input) throws Exception {
		return salesOrder.setOrderCondition(input);
	}

	public void setCustomer(Customer customer) {
		salesOrder.setCustomer(customer);
	}

	public void setEmployee(int id) {
		employee = cCtrl.findEmployeeById(id);
		salesOrder.setEmployee(employee);
	}

	public void setDiscount(int discId) {
		Discount disc = new DiscountDB().findById(discId);
		salesOrder.setDiscount(disc);
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setConditionToOrder(int salesOrderId) throws Exception{
		Order orderCondition = new Order();
		int orderConditionId = new OrderConditionDB().insertOrderCondition(orderCondition);
		salesOrderDB.updateCondition(orderConditionId, salesOrderId);
	}

	public void setConditionToDelivered(int salesOrderId) throws SQLException{
		Delivered orderCondition = new Delivered();
		int orderConditionId = new OrderConditionDB().insertOrderCondition(orderCondition);
		salesOrderDB.updateCondition(orderConditionId, salesOrderId);
	}

	public ArrayList<SalesOrder> getAllSalesOrders(){
		return (ArrayList<SalesOrder>) salesOrderDB.getSalesOrders();
	}
	
	public ArrayList<Customer> findAllCustomers(){
		return (ArrayList<Customer>) new CustomerCtrl().getAll();
	}
	
	public ArrayList<Product> findAllProducts(){
		return (ArrayList<Product>) new ProductCtrl().findAll();
	}
}
