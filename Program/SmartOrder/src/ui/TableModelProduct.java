package ui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import controller.ProductCtrl;
import model.Product;

/**
 * SmartOrder
 * TableModelProduct.java
 * Purpose: Define how products should look like when they are displayed in a list
 * @author Gruppe 1
 * @version 1.0 
 */
public class TableModelProduct extends DefaultTableModel {
	private ArrayList<Product> products;
	private ProductCtrl productCtrl;

	public TableModelProduct() {
		productCtrl = new ProductCtrl();	
	}

	@Override
	public int getColumnCount(){
		return 3;
	}

	@Override
	public int getRowCount(){
		return products == null ? 0 : products.size();
	}

	@Override
	public String getColumnName(int ix) {
		switch (ix) {
		case 0: return "Model";
		case 1: return "Kategori";
		case 2: return "Salgspris";
		default: System.out.println("???");return "???";
		}
	}

	@Override
	public Object getValueAt(int row, int col){
		switch (col) {
		case 0: return products.get(row).getModel();
		case 1: return products.get(row).getProductType().getCategoryName();
		case 2: return products.get(row).getProductPrice().getSalesPrice();
		default: return null;
		}
	}

	public void setData(ArrayList<Product> data) {
		this.products = data;
		super.fireTableDataChanged();
	}

	public Product getData(int selectedRow) {
		if (selectedRow >= 0 && selectedRow < products.size()) {
			return this.products.get(selectedRow);
		}
		return null;
	}
	
	@Override
	public boolean isCellEditable(int row, int col){
		return false;
	}
	
	public void upDate(){
		super.fireTableDataChanged();
	}
	
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex){
		Product product = products.get(rowIndex);
		products.add(rowIndex, product);
		
	}

}
