package model;

public class Clothing extends Product {
	private String size;
	private String colour;

	public Clothing(int id, String name, double purchasePrice, double salesPrice, 
			String countryOfOrigin, int amount, int minStock, String size, String colour) {
		super(id, name, purchasePrice, salesPrice, countryOfOrigin, amount, minStock);
		setSize(size);
		setColour(colour);
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

}
