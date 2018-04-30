package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class SalesOrder {
	private int id;
	private LocalDate createDate;
	private ArrayList<SalesOrderLine> sol;
	private Discount discount;
	private double totalPrice;
	private OrderCondition orderCondition;
	private double discountForOffer = 0;	
	private Employee employee;
	private Customer customer;

	public SalesOrder() {
		createDate = LocalDate.now();
		sol = new ArrayList<>();
		discount = new Discount();
	}

	public SalesOrder(int id, LocalDate date, ArrayList<SalesOrderLine> sol, Discount discount, 
			OrderCondition orderCondition, Employee employee, Customer customer) {
		this.id = id;
		this.createDate = date;
		this.sol = sol;
		this.discount = discount;
		this.orderCondition = orderCondition;
		this.employee = employee;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate date) {
		this.createDate = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * adds an salesOrderLine to the ArrayList
	 * @param p as Product
	 * @param amount as int
	 */
	public void addProductToSalesOrder(Product p, int amount){
		SalesOrderLine res = new SalesOrderLine(amount, p);
		sol.add(res);
	}

	/**
	 * gets all the salesOrders with a new arrayList
	 * @return arrayList
	 */
	public ArrayList<SalesOrderLine> getAllSalesOrderLines(){
		return new ArrayList<SalesOrderLine>(sol);
	}

	/**
	 * gets the totalPrice for the salesOrder
	 * @return totalPrice
	 */
	public double getTotalPrice(){
		int sum = 0;
		for(SalesOrderLine s : sol){
			double res = s.getProduct().getSalesPrice() * s.getAmount();
			sum += res;
		}

		totalPrice = sum;
		return sum;
	}

	/**
	 * get the total Price after the discount has been withdrawn from totalPrice
	 * @return totalPrice
	 */
	public double getTotalPriceAfterDiscount(){
		double res = 0;
		if(customer.getType().equalsIgnoreCase("private") && checkDiscountPrivate()){
			return res = getTotalPrice() - discount.getPrivateDiscountAmount();
		}
		else if(customer.getType().equalsIgnoreCase("club") && checkDiscountClub()) {
			return res = getTotalPrice() - discount.getClubDiscountAmount();
		}
		else{
			return totalPrice;
		}

	}

	/**
	 * gets the current discount for the salesorder
	 * @return discountForOffer
	 */
	public double getDiscountForOffer(){
		return discountForOffer;
	}

	/**
	 * Checks where or not a private customer gets free delivery fee.
	 * @return the totalprice for the salesprice - the discount.
	 */
	public boolean checkDiscountPrivate(){
		if(getTotalPrice() >= discount.getPrivateDiscountLimit()){
			return true;
		}
		else{
			return false;
		}
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	/**
	 * Checks where or not a club gets free delivery fee.
	 * @return the totalprice for the salesprice - the discount.
	 */
	public boolean checkDiscountClub(){
		if(getTotalPrice() >= discount.getClubDiscountLimit()){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * creates an discount for the salesorder
	 * @param discount as double
	 * @return discount assigned to field.
	 */
	public double createDiscountForOffer(double discount){
		return discountForOffer = discount;
	}

	/**
	 * The following methods creates three different types, which is use on the salesorder
	 * this is done to make it possible to change the status off the salesorder, depending wether it is delivered or has a gotten a discount.
	 * @return
	 */
	public OrderCondition createOrder(){
		Order o = new Order();
		return o;
	}

	public OrderCondition createOffer(){
		Offer o = new Offer();
		o.setSentDate(LocalDate.now());
		o.setDueDate(LocalDate.now().plusDays(14));
		o.setDiscount(discountForOffer);
		return o;
	}

	public OrderCondition createDelivered() {
		Delivered d = new Delivered();
		d.setDate(LocalDate.now());
		return d;
	}

	/**
	 * assigns 
	 * @param input as String
	 * @return the new status of the OrderCondition.
	 * @throws Exception
	 */
	public OrderCondition setOrderCondition(String input) throws Exception {
		if(input.equals("Order")){
			orderCondition = createOrder();
		}
		else if(input.equals("Offer")) {
			orderCondition = createOffer();
		}
		else if(input.equals("Delivered")){
			orderCondition = createDelivered();
		}
		else {
			throw new Exception("Input not correct");
		}

		return orderCondition;
	}

	public OrderCondition getOrderCondition(){
		return orderCondition;
	}


}
