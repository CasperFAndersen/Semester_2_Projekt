package ui;

import model.Customer;

/**
 * SmartOrder
 * CustomerListAdaptor.java
 * Purpose: Defines how customers should be displayed in a list
 * @author Gruppe 1
 * @version 1.0 
 */
public class CustomerListAdaptor implements DropDownListAdaptorIF<Customer>{

	@Override
	public String displayObjectInList(Customer o) {
		return o.getName() + " " + o.getPhone();
	}

	@Override
	public String getIdentifier(Customer o) {
		return o.getName() + " " + o.getPhone();
	}

	@Override
	public String displaySelectedObjectInTextfield(Customer o) {
		return o.getName() + " " + o.getPhone();
	}

}