package ui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import controller.ProductCtrl;
import model.Product;
import model.Property;

/**
 * SmartOrder
 * TableModelProperty.java
 * Purpose: Define how property should look like when they are displayed in a list
 * @author Gruppe 1
 * @version 1.0 
 */
public class TableModelProperty extends DefaultTableModel {
	private ArrayList<Property> properties;
	private ProductCtrl productCtrl = new ProductCtrl();
	private Product template;
	
	@Override
	public int getColumnCount(){
		return 2;
	}

	@Override
	public int getRowCount(){
		return properties == null ? 0 : properties.size();
	}

	@Override
	public String getColumnName(int ix) {
		switch (ix) {
		case 0: return "Navn";
		case 1: return "Værdi";
		default: System.out.println("???");return "???";
		}
	}

	@Override
	public Object getValueAt(int row, int col){
		switch (col) {
		case 0: return properties.get(row).getName();
		case 1: return properties.get(row).getValue();
		default: return null;
		}
	}

	public void setData(ArrayList<Property> data) {
		this.properties = data;
		super.fireTableDataChanged();
	}

	public Property getData(int selectedRow) {
		if (selectedRow >= 0 && selectedRow < properties.size()) {
			return this.properties.get(selectedRow);
		}
		return null;
	}
	
	@Override
	public boolean isCellEditable(int row, int column){
		if (column == 1) {
			return true;
		}
		return false;
	}
	
	@Override
	public void setValueAt(Object aValue, int row, int column) {
			if (column == 1) {
				Property p = properties.get(row);
				switch(p.getType().getSimpleName()){
				case "Double": aValue = Double.parseDouble((String) aValue); 
				break;
				case "Boolean": aValue = Boolean.parseBoolean((String) aValue);
				break;
				}
				productCtrl.setProperty(p, aValue);
			}
			setData(properties);
	}
	
	public void setProductCtrl(ProductCtrl productCtrl) {
		this.productCtrl = productCtrl;
	}
	
	public void setProductTemplate(Product template){
		this.template = template;
	}
	
	public ArrayList<Property> getProperties(){
		return properties;
	}
	
}
