package model;

import java.time.LocalDate;

/**
 * SmartOrder
 * OfferType.java
 * Purpose: sets the salesOrders OrderCondition if the salesOrder is an offer.
 * @author Gruppe 1
 * @version 1.0 
 */
public class OfferType extends OrderCondition {
	private LocalDate createDate;
	private LocalDate dueDate;
	private LocalDate acceptDate;
	
	public OfferType(LocalDate createDate, LocalDate dueDate, LocalDate acceptDate) { 
		setCreateDate(createDate);
		setDueDate(dueDate);
		setAcceptDate(acceptDate);
	}
	
	public OfferType(int id, LocalDate createDate, LocalDate dueDate, LocalDate acceptDate) {
		setId(id);
		setCreateDate(createDate);
		setDueDate(dueDate);
		setAcceptDate(acceptDate);
	}
	
	public LocalDate getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
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
	
}
