package ui;

import model.Customer;

public class CustomerAdaptor implements DropDownListAdaptorIF<Customer> {

	@Override
	public String displayObjectInList(Customer o) {
		return o.getName();
	}

	@Override
	public String getIdentifier(Customer o) {
		return o.getName();
	}

	@Override
	public String displaySelectedObjectInTextfield(Customer o) {
		return o.getName();
	}

}
