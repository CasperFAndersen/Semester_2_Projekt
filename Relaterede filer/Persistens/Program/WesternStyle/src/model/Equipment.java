package model;

public class Equipment extends Product {
	private String type;
	private String description;

	public Equipment(int id, String name, double purchasePrice, double salesPrice, String countryOfOrigin, 
			int amount, int minStock, String type, String description) {
		super(id, name, purchasePrice, salesPrice, countryOfOrigin, amount, minStock);
		setType(type);
		setDescription(description);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
