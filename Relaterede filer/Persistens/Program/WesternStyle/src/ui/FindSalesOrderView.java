package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.SalesOrderCtrl;
import model.SalesOrder;

public class FindSalesOrderView extends JPanel {
	private JTable table;
	private TableModelSalesOrder tableModel = new TableModelSalesOrder();
	private SalesOrderCtrl soCtrl = new SalesOrderCtrl();

	/**
	 * Create the panel.
	 */
	public FindSalesOrderView() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		
		JLabel lblOrdre = new JLabel("Ordrer:");
		lblOrdre.setFont(new Font("Tahoma", Font.BOLD, 24));
		add(lblOrdre, BorderLayout.NORTH);
		ArrayList<SalesOrder> so = soCtrl.getAllSalesOrders();
		tableModel.setData(so);
	}

	public JPanel getPanel() {
		return this;
	}
}
