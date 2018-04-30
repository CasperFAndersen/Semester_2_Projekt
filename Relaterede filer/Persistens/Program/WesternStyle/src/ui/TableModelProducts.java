package ui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import controller.ProductCtrl;
import model.Product;

public class TableModelProducts extends DefaultTableModel{
	private ArrayList<Product> products;
	private ProductCtrl prodCtrl;

	public TableModelProducts() {
		prodCtrl = new ProductCtrl();	
	}

	@Override
	public int getColumnCount(){
		return 5;
	}

	@Override
	public int getRowCount(){
		return products == null ? 0 : products.size();
	}

	@Override
	public String getColumnName(int ix) {
		switch(ix) {
		case 0: return "Varenr";
		case 1: return "Beskrivelse";
		case 2: return "Antal på lager";
		case 3: return "Købspris";
		case 4: return "Salgspris";
		default: System.out.println("???");return "???";
		}
	}

	@Override
	public Object getValueAt(int row, int col){
		switch(col){
		case 0: return products.get(row).getId();
		case 1: return products.get(row).getName();
		case 2: return products.get(row).getAmount();
		case 3: return products.get(row).getPurchasePrice(); 
		case 4: return products.get(row).getSalesPrice();
		default: return null;
		}
	}

	public void setData(ArrayList<Product> data) {
		this.products = data;;
		super.fireTableDataChanged();
	}

	public Product getData(int selectedRow) {
		if(selectedRow >= 0 && selectedRow < products.size()) {
			return this.products.get(selectedRow);
		}
		return null;
	}

}
