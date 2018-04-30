package model;

import java.time.LocalDate;

public class Offer extends OrderCondition {
	private LocalDate createDate;
	private LocalDate sentDate;
	private LocalDate dueDate;
	private LocalDate acceptDate;
	private double discount;

	public Offer() {
		createDate = LocalDate.now();
		sentDate = LocalDate.now();
		dueDate = LocalDate.now().plusDays(14);
		acceptDate = null;
	}

	public Offer(LocalDate createDate, LocalDate sentDate, LocalDate dueDate, LocalDate acceptDate, double discount) {
		this.createDate = createDate;
		this.sentDate = sentDate;
		this.dueDate = dueDate;
		this.acceptDate = acceptDate;
		this.discount = discount;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDate getSentDate() {
		return sentDate;
	}

	public void setSentDate(LocalDate sentDate) {
		this.sentDate = sentDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(LocalDate acceptDate) {
		this.acceptDate = acceptDate;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
