package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import controller.SalesOrderCtrl;
import model.Product;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

/**
 * SmartOrder
 * ProductInfoPanel.java
 * Purpose: Showing all the 'shelf-products'
 * @author Gruppe 1
 * @version 1.0 
 */
public class ProductInfoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtFilterProduct;
	private JTextArea txtDescription;
	private JTextField txtAmount;
	private TableModelProduct tableModel = new TableModelProduct();
	private TableRowSorter<TableModelProduct> sorter;
	private ArrayList<Product> products;
	private SalesOrderCtrl salesOrderCtrl;
	private CreateSalesOrderView csov; 

	private JButton btnAddProduct;
	private JComboBox<String> comboBox;

	/**
	 * Create the panel.
	 */
	public ProductInfoPanel(CreateSalesOrderView csov) {
		this.csov = csov;
		setSalesOrderCtrl(csov.getSalesOrderCtrl());
		setLayout(null);
		//Find all 'shelf' products 
		products = salesOrderCtrl.findAllNonCustomizableProducts();
		products.sort((o1, o2) -> o1.getModel().compareTo(o2.getModel()));
		JLabel lblProductPicture = new JLabel("");
		lblProductPicture.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblProductPicture.setIcon(new ImageIcon(ProductInfoPanel.class.getResource("/mockups/stol2.jpg")));
		lblProductPicture.setBounds(636, 67, 95, 101);
		add(lblProductPicture);

		JLabel lblDescription = new JLabel("Beskrivelse:");
		lblDescription.setBounds(636, 192, 85, 16);
		add(lblDescription);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 67, 533, 353);
		add(scrollPane);

		table = new JTable();
		sorter = new TableRowSorter<TableModelProduct>(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(tableModel);
		table.setAutoCreateRowSorter(true);
		tableModel.setData(products);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Product product = (Product) tableModel.getData(table.getSelectedRow());
				txtDescription.setText(product.getDescription());
				txtAmount.grabFocus();
			}
		});
		scrollPane.setViewportView(table);

		txtFilterProduct = new JTextField();
		txtFilterProduct.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				filter(txtFilterProduct.getText());
			}
		});
		txtFilterProduct.setFont(new Font("Tahoma", Font.ITALIC, 12));
		txtFilterProduct.setText("Filtrer vare");
		txtFilterProduct.setBounds(39, 43, 201, 22);
		add(txtFilterProduct);
		txtFilterProduct.setColumns(10);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filterProductTable();
			}
		});
		comboBox.setBounds(430, 42, 142, 22);
		comboBox.addItem("Alle kategorier");
		loadComboBox();
		add(comboBox);

		txtDescription = new JTextArea();
		txtDescription.setBounds(636, 210, 178, 76);
		txtDescription.setLineWrap(true);
		txtDescription.setWrapStyleWord(true);
		txtDescription.setColumns(10);
		JScrollPane descriptionScrollPane = new JScrollPane(txtDescription);
		descriptionScrollPane.setBounds(636, 210, 178, 71);
		add(descriptionScrollPane);

		btnAddProduct = new JButton("Tilføj vare");
		btnAddProduct.addActionListener((e)-> {addProduct();});

		btnAddProduct.setForeground(Color.BLACK);
		btnAddProduct.setBackground(new Color(255, 165, 0));
		btnAddProduct.setBounds(636, 395, 179, 25);
		add(btnAddProduct);

		txtAmount = new JTextField();
		txtAmount.setBounds(636, 327, 178, 36);
		add(txtAmount);
		txtAmount.setColumns(10);

		JLabel lblAmount = new JLabel("Antal:");
		lblAmount.setBounds(636, 310, 56, 16);
		add(lblAmount);
	}

	protected void filterProductTable() {
		String selectedElement = (String) comboBox.getSelectedItem();
		if (!selectedElement.equals("Alle kategorier")) {
			ArrayList<Product> productsAfterFilter = new ArrayList<Product>();
			for (Product p : products) {
				if (p.getProductType().getCategoryName().equals(selectedElement)) {
					productsAfterFilter.add(p);
				}
			}
			tableModel.setData(productsAfterFilter);
		} else {
			tableModel.setData(products);
		}
	}

	protected void addProduct() {
		Product product = getSelectedProduct();
		try{
			if (product == null ) {
				new JOptionPane().showMessageDialog(this, "Der er ikke valgt nogen vare.");
			} else if (txtAmount.getText().equals("")) {
				new JOptionPane().showMessageDialog(this, "Der er ikke angivet noget antal.");
			}
			 else if (Integer.parseInt(txtAmount.getText()) < 0) {
					new JOptionPane().showMessageDialog(this, "Antal kan ikke være minus");
			 }
			else {
				int amount = Integer.parseInt(txtAmount.getText());
				csov.addSalesOrderLine(product, amount);
				txtAmount.setText("");
				txtDescription.setText("");
				table.clearSelection();
			}
		}
		catch (NumberFormatException e) {
			new JOptionPane().showMessageDialog(this, "Angiv kun tal");
		}
	}

	protected void filter(String query) {
		sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
		table.setRowSorter(sorter);
		tableModel.setData(products);
		table.revalidate();
		tableModel.fireTableDataChanged();
	}

	public int getAmount(){
		return Integer.parseInt(txtAmount.getText());
	}

	public Product getSelectedProduct() {
		return tableModel.getData(table.getSelectedRow());
	}

	public void setSalesOrderCtrl (SalesOrderCtrl salesOrderCtrl) {
		this.salesOrderCtrl = salesOrderCtrl;
	}

	public void loadComboBox(){
		ArrayList<String> catogoryNames = new ArrayList<>();
		for (Product product : products) {
			String catogoryName = product.getProductType().getCategoryName();
			if (!catogoryNames.contains(catogoryName)) {
				catogoryNames.add(catogoryName);
			}
		}
		for (String s : catogoryNames) {
			comboBox.addItem(s);
		}
	}
}
