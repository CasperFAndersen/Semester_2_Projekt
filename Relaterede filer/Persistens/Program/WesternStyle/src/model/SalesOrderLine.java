package model;

public class SalesOrderLine {
	private int id;
	private int amount;
	private Product product;

	public SalesOrderLine() {

	}

	public SalesOrderLine(int amount, Product p) {
		this.amount = amount;
		this.product = p;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
