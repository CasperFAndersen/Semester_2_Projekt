package model;

import java.time.LocalDate;

/**
 * SmartOrder
 * DeliveredType.java
 * Purpose: Sets the salesOrder OrderConition to delivered.
 * @author Gruppe 1
 * @version 1.0 
 */
public class DeliveredType extends OrderCondition {
	private LocalDate date;
	
	public DeliveredType(LocalDate date) {
		setDate(date);
	}
	
	public DeliveredType(int id, LocalDate date) {
		super.setId(id);
		setDate(date);
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
