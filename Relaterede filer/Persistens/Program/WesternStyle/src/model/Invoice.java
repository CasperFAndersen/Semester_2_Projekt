package model;

import java.time.LocalDate;

public class Invoice {
	private int id;
	private LocalDate paymentDate;

	public Invoice() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
}
