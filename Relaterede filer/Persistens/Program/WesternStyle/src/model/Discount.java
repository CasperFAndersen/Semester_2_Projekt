package model;

public class Discount {
	private int id;
	private double clubDiscountLimit = 1500;
	private double privateDiscountLimit = 2500; 
	private double clubDiscountAmount;
	private double privateDiscountAmount;

	public Discount() { 

	}

	public Discount(int id, double clubDiscountLimit, double privateDiscountLimit, 
			double clubDiscountAmount, double privateDiscountAmount) {
		this.id = id;
		this.clubDiscountLimit = clubDiscountLimit;
		this.privateDiscountLimit = privateDiscountLimit;
		this.clubDiscountAmount = clubDiscountAmount;
		this.privateDiscountAmount = privateDiscountAmount;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public double getClubDiscountLimit() {
		return clubDiscountLimit;
	}

	public void setClubDiscountLimit(double clubDiscountLimit) {
		this.clubDiscountLimit = clubDiscountLimit;
	}

	public double getPrivateDiscountLimit() {
		return privateDiscountLimit;
	}

	public void setPrivateDiscountLimit(double privateDiscountLimit) {
		this.privateDiscountLimit = privateDiscountLimit;
	}

	public double getClubDiscountAmount() {
		return clubDiscountAmount;
	}

	public void setClubDiscountAmount(double clubDiscountAmount) {
		this.clubDiscountAmount = clubDiscountAmount;
	}

	public double getPrivateDiscountAmount() {
		return privateDiscountAmount;
	}

	public void setPrivateDiscountAmount(double privateDiscountAmount) {
		this.privateDiscountAmount = privateDiscountAmount;
	}

}
