package model;

import java.time.LocalDate;

public class Delivered extends OrderCondition {
	private LocalDate date;

	public Delivered() {
		date = LocalDate.now();
	}

	public Delivered(int id, LocalDate date) {
		setId(id);
		setDate(date);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}