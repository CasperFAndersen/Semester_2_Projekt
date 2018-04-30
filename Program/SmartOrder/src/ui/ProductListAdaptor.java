package ui;

import model.Product;

/**
 * SmartOrder
 * ProductListAdaptor.java
 * Purpose: This class implements an adaptor interface used by the DropDownList class. It purpose is the provide a DropDownList with information as to
 * how to represent a object in the the list along with an identifier String to be used for matching with DropDownList's search field text input.  
 * @author Gruppe 1
 * @version 1.0 
 */
public class ProductListAdaptor implements DropDownListAdaptorIF<Product> {

	@Override
	public String displayObjectInList(Product product) {
		return product.getModel();
	}

	@Override
	public String getIdentifier(Product product) {
		return product.getModel();
	}

	@Override
	public String displaySelectedObjectInTextfield(Product product) {
		return product.getModel();
	}

}
