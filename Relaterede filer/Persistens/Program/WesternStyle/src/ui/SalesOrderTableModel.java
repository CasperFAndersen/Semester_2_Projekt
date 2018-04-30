package ui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import controller.SalesOrderCtrl;
import model.SalesOrderLine;

public class SalesOrderTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<SalesOrderLine> sol;

	private SalesOrderCtrl soCtrl;

	public SalesOrderTableModel(){
		soCtrl = new SalesOrderCtrl();	
	}

	@Override
	public int getColumnCount(){
		return 5;
	}

	@Override
	public int getRowCount(){
		return sol == null ? 0 : sol.size();
	}

	@Override
	public String getColumnName(int ix) {
		System.out.print("getColumnName(" + ix + ") ");
		switch(ix) {
		case 0: return "Varenummer";
		case 1: return "Beskrivelse";
		case 2: return "Antal";
		case 3: return "Pris pr. stk";
		case 4: return "Pris total";
		default: System.out.println("???");return "???";
		}
	}

	@Override
	public Object getValueAt(int row, int col){
		switch(col){
		case 0: return sol.get(row).getProduct().getId();
		case 1: return sol.get(row).getProduct().getName();
		case 2: return sol.get(row).getAmount();
		case 3: return sol.get(row).getProduct().getSalesPrice();
		case 4: return (sol.get(row).getProduct().getSalesPrice() * sol.get(row).getAmount());
		default: return null;
		}
	}

	public void setData(ArrayList<SalesOrderLine> data) {
		this.sol = data;;
		super.fireTableDataChanged();
	}

	public SalesOrderLine getData(int selectedRow) {
		if(selectedRow >= 0 && selectedRow < sol.size()) {
			return this.sol.get(selectedRow);
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if (column == 5){
			return true;
		}
		return false;
	}

	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return Integer.class;
		case 3:
			return Double.class;
		case 4: 
			return Double.class;
		case 5:
			return Boolean.class;
		default: return String.class;    
		}
	}

}
