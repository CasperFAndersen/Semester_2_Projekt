package ui;

import model.Product;

public class ProductAdaptor implements DropDownListAdaptorIF<Product>{

	@Override
	public String displayObjectInList(Product o) {
		return o.getId() + " : " + o.getName();
	}

	@Override
	public String getIdentifier(Product o) {
		return String.valueOf(o.getId());
	}

	@Override
	public String displaySelectedObjectInTextfield(Product o) {
		return o.getName();
	}

}
