package model;

public class GunReplica extends Product {
	private String caliber;
	private String material;

	public GunReplica(int id, String name, double purchasePrice, double salesPrice, String countryOfOrigin, 
			int amount, int minStock, String caliber, String material) {
		super(id, name, purchasePrice, salesPrice, countryOfOrigin, amount, minStock);
		setCalibre(caliber);
		setMaterial(material);
	}

	public String getCalibre() {
		return caliber;
	}

	public void setCalibre(String caliber) {
		this.caliber = caliber;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

}
