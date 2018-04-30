package model;

public class Product {

	private int id;
	private String name;
	private double purchasePrice;
	private double salesPrice;
	private String countryOfOrigin;
	private int amount;
	private int minStock;

	public Product(int id, String name, double purchasePrice, double salesPrice, 
			String countryOfOrigin, int amount, int minStock) {
		setId(id);
		setName(name);
		setPurchasePrice(purchasePrice);
		setSalesPrice(salesPrice);
		setCountryOfOrigin(countryOfOrigin);
		setAmount(amount);
		setMinStock(minStock);
	}

	public Product() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
