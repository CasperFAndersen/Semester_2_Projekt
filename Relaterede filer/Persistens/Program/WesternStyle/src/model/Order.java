package model;

import java.time.LocalDate;

public class Order extends OrderCondition {
	private LocalDate createDate;
	private LocalDate packDate;

	public Order() {
		createDate = LocalDate.now();
		packDate = LocalDate.now();
	}

	public Order(LocalDate createDate, LocalDate packDate) {
		this.createDate = createDate;
		this.packDate = packDate;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDate getPackDate() {
		return packDate;
	}

	public void setPackDate(LocalDate packDate) {
		this.packDate = packDate;
	}

}
