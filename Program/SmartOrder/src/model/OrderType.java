package model;

import java.time.LocalDate;

/**
 * SmartOrder
 * OrderType.java
 * Purpose: Used to change the state on SalesOrder to a normal offer.
 * @author Gruppe 1
 * @version 1.0 
 */
public class OrderType extends OrderCondition {
	private LocalDate packDate;
	private LocalDate createDate;
	
	public OrderType(LocalDate createDate) {
		setPackDate(createDate);
		setCreateDate(createDate);
	}
	
	public OrderType(int id, LocalDate createDate, LocalDate packDate) {
		setId(id);
		setCreateDate(createDate);
		setPackDate(packDate);
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
