package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.ProductCtrl;
import model.Product;

public class ProductView extends JPanel {
	private ProductCtrl prodCtrl = new ProductCtrl();
	private TableModelProducts tableModel = new TableModelProducts();
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ProductView() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblVare = new JLabel("Vare:");
		lblVare.setFont(new Font("Tahoma", Font.BOLD, 26));
		add(lblVare, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		tableModel.setData((ArrayList<Product>) prodCtrl.findAll());
	}

	public JPanel getPanel() {
		return this;
	}

}
