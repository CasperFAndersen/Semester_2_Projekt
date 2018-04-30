package ui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Product;

/**
 * SmartOrder
 * TableModelModule.java
 * Purpose: Define how modules should look like when they are displayed in a list
 * @author Gruppe 1
 * @version 1.0 
 */
public class TableModelModule extends DefaultTableModel {
	private ArrayList<Product> products;

	public TableModelModule() {
		products = new ArrayList<>();
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
		case 0: return "Modul";
		case 1: return "Beskrivelse";
		case 2: return "Pris";
		default: System.out.println("???");return "???";
		}
	}

	@Override
	public Object getValueAt(int row, int col){
		switch (col) {
		case 0: return products.get(row).getModel();
		case 1: return products.get(row).getDescription();
		case 2: return products.get(row).getProductPrice().getSalesPrice() + " kr.";
		default: return null;
		}
	}

	public void setData(ArrayList<Product> data) {
		this.products = data;;
		super.fireTableDataChanged();
	}

	public Product getData(int selectedRow) {
		if (selectedRow >= 0 && selectedRow < products.size()) {
			return this.products.get(selectedRow);
		}
		return null;
	}

	public ArrayList<Product> getAllTableData(){
		return products;
	}

	public void clearTable(){
		products.clear();
		fireTableDataChanged();
	}

	public void removeObject(Product p){
		products.remove(p);
		super.fireTableDataChanged();
	}

	public void add(Product p){
		products.add(p);
	}

	@Override
	public boolean isCellEditable(int row, int col){
		return false;
	}


}
