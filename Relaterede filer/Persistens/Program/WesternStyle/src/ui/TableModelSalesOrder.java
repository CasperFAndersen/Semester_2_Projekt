package ui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import controller.SalesOrderCtrl;
import model.SalesOrder;

public class TableModelSalesOrder extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<SalesOrder> so;

	private SalesOrderCtrl soCtrl;

	public TableModelSalesOrder() {
		soCtrl = new SalesOrderCtrl();	
	}

	@Override
	public int getColumnCount(){
		return 5;
	}

	@Override
	public int getRowCount(){
		return so == null ? 0 : so.size();
	}

	@Override
	public String getColumnName(int ix) {
		switch(ix) {
		case 0: return "Ordrenummer";
		case 1: return "Dato";
		case 2: return "Antal orderlinjer";
		case 3: return "Kunde";
		case 4: return "Status";
		case 5: return "Total pris";
		default: System.out.println("???");return "???";
		}
	}

	@Override
	public Object getValueAt(int row, int col){
		switch(col){
		case 0: return so.get(row).getId();
		case 1: return so.get(row).getCreateDate();
		case 2: return so.get(row).getAllSalesOrderLines().size();
		case 3: return so.get(row).getCustomer().getName(); 
		case 4: return so.get(row).getOrderCondition().getClass().getName().substring(6);
		case 5: return so.get(row).getTotalPriceAfterDiscount();
		default: return null;
		}
	}

	public void setData(ArrayList<SalesOrder> data) {
		this.so = data;;
		super.fireTableDataChanged();
	}

	public SalesOrder getData(int selectedRow) {
		if(selectedRow >= 0 && selectedRow < so.size()) {
			return this.so.get(selectedRow);
		}
		return null;
	}

}
