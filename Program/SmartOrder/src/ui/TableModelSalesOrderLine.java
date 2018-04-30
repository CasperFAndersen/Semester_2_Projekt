package ui;

import java.util.ArrayList;

import javax.annotation.processing.RoundEnvironment;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import controller.SalesOrderCtrl;
import exception.IllegalDiscountAmountException;
import model.SalesOrderLine;

/**
 * SmartOrder
 * TableModelSalesOrderLine.java
 * Purpose: Define how salesorderlines should look like when they are displayed in a list
 * @author Gruppe 1
 * @version 1.0 
 */
public class TableModelSalesOrderLine extends DefaultTableModel{
	private ArrayList<SalesOrderLine> salesOrderlines;
	private SalesOrderCtrl salesOrderCtrl;
	private JTextField txtTotal;

	public TableModelSalesOrderLine() {
	}

	@Override
	public int getColumnCount(){
		return 6;
	}

	@Override
	public int getRowCount(){
		return salesOrderlines == null ? 0 : salesOrderlines.size();
	}

	@Override
	public String getColumnName(int ix) {
		switch (ix) {
		case 0: return "Varenr";
		case 1: return "Model";
		case 2: return "Antal";
		case 3: return "Stykpris";
		case 4: return "Rabat";
		case 5: return "Deltotal";
		default: System.out.println("???");return "???";
		}
	}

	@Override
	public Object getValueAt(int row, int col){
		switch (col) {
		case 0: return salesOrderlines.get(row).getProduct().getId();
		case 1: return salesOrderlines.get(row).getProduct().getModel();
		case 2: return salesOrderlines.get(row).getAmount();
		case 3: return salesOrderlines.get(row).getProduct().getProductPrice().getSalesPrice();
		case 4: return salesOrderlines.get(row).getDiscount();
		case 5: return salesOrderlines.get(row).getSubTotal();
		default: return null;
		}
	}

	public void setData(ArrayList<SalesOrderLine> data) {
		this.salesOrderlines = data;;
		super.fireTableDataChanged();
	}

	public SalesOrderLine getData(int selectedRow) {
		if (selectedRow >= 0 && selectedRow < salesOrderlines.size()) {
			return this.salesOrderlines.get(selectedRow);
		}
		return null;
	}
	
	@Override
	public boolean isCellEditable(int row, int column){
		if (column == 4) {
			return true;
		}
		return false;
		
	}
	
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		try {
			if (column == 4) {
				salesOrderCtrl.setDiscountToSalesOrderLine(salesOrderlines.get(row), (String) aValue); 
				salesOrderlines = salesOrderCtrl.getOrder().getSalesOrderLines();
			}
			setData(salesOrderlines);
		}  catch (IllegalDiscountAmountException e){
			new JOptionPane().showMessageDialog(LoginView.getInstance(), e.getMessage());
		} catch (Exception e) {
			new JOptionPane().showMessageDialog(LoginView.getInstance(), "Ugyldig rabat. Brug kun hele beløb, eller antal procent efterfulgt af '%'.");
		}
	}
	
	public void setTxtTotal(JTextField txtField){
		this.txtTotal = txtField;
	}
	
	public void removeElement(int row){
		salesOrderlines.remove(row);
		fireTableDataChanged();
	}
	
	public void setSalesOrderCtrl(SalesOrderCtrl salesorderCtrl){
		this.salesOrderCtrl = salesorderCtrl;
	}

}
