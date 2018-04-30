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
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.SalesOrderCtrl;
import model.Product;
import model.Property;

/**
 * SmartOrder
 * MakeProductPanel.java
 * Purpose: Used when an employee wants to sell a product with different modules
 * @author Gruppe 1
 * @version 1.0 
 */
public class MakeProductPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtFindModel;
	private JTable moduleTbl;
	private JTextArea txtDescription;
	private JTextField txtModel;
	private JTextField txtSupplier;
	private JTextField txtCategory;
	private DropDownList<Product> productList;
	private TableModelModule tableModel = new TableModelModule();
	private MyListModel listModel = new MyListModel();
	private JList moduleList;
	private CreateSalesOrderView csov;
	private SalesOrderCtrl salesOrderCtrl;
	private JTextField txtTotal;
	private Product model;
	private JComboBox<String> comboBox;

	/**
	 * Create the panel.
	 */
	public MakeProductPanel(CreateSalesOrderView csov) {
		this.csov = csov;
		setSalesOrderCtrl(csov.getSalesOrderCtrl());
		setLayout(null);
		JScrollPane productPane = new JScrollPane();

		productPane.setBounds(64, 61, 396, 262);
		add(productPane);

		JLabel lblModuleType = new JLabel("Modultype");
		lblModuleType.setBounds(28, 154, 103, 16);
		add(lblModuleType);

		txtFindModel = new JTextField();
		txtFindModel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				txtFindModel.setText("");
			}
		});

		txtFindModel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					setModel();
				}
			}
		});

		txtFindModel.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txtFindModel.setText("S\u00F8g model");
		txtFindModel.setBounds(65, 30, 394, 30);
		add(txtFindModel);
		txtFindModel.setColumns(10);

		productList = new DropDownList<>(txtFindModel, productPane, salesOrderCtrl.findAllCustomizableProducts(), new ProductListAdaptor());
		productPane.setViewportView(productList);
		productList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setModel();
			}
		});

		JLabel lblModel = new JLabel("Model:");
		lblModel.setBounds(65, 89, 56, 16);
		add(lblModel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 203, 290, 167);
		add(scrollPane);

		moduleTbl = new JTable();
		moduleTbl.setModel(tableModel);

		scrollPane.setViewportView(moduleTbl);

		comboBox = new JComboBox<String>();
		comboBox.addItem("Alle");
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterModuleTable();
			}
		});

		comboBox.setBounds(28, 174, 135, 25);
		add(comboBox);

		JScrollPane selectedModulesScrollPane = new JScrollPane();
		selectedModulesScrollPane.setBounds(475, 203, 264, 130);
		add(selectedModulesScrollPane);

		moduleList = new JList();
		moduleList.setModel(listModel);
		selectedModulesScrollPane.setViewportView(moduleList);

		JLabel lblSelectedModules = new JLabel("Valgte moduler:");
		lblSelectedModules.setBounds(475, 174, 162, 16);
		add(lblSelectedModules);

		JButton btnAddModuleToProduct = new JButton("Tilf\u00F8j ->");
		btnAddModuleToProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addModule();
			}
		});

		btnAddModuleToProduct.setBackground(new Color(60, 179, 113));
		btnAddModuleToProduct.setBounds(350, 241, 87, 25);
		add(btnAddModuleToProduct);

		JLabel lblSupplier = new JLabel("Leverand\u00F8r:");
		lblSupplier.setBounds(65, 125, 79, 16);
		add(lblSupplier);

		JLabel lblDescription = new JLabel("Beskrivelse:");
		lblDescription.setBounds(321, 73, 87, 16);
		add(lblDescription);

		JLabel lblCategory = new JLabel("Kategori:");
		lblCategory.setBounds(480, 89, 56, 16);
		add(lblCategory);

		txtDescription = new JTextArea();
		txtDescription.setBackground(Color.WHITE);
		txtDescription.setEditable(false);
		txtDescription.setLineWrap(true);
		txtDescription.setWrapStyleWord(true);
		txtDescription.setBounds(321, 89, 138, 56);
		txtDescription.setColumns(10);
		JScrollPane descriptionScrollPane = new JScrollPane(txtDescription);
		descriptionScrollPane.setBounds(321, 89, 138, 56);
		add(descriptionScrollPane);

		txtModel = new JTextField();
		txtModel.setBackground(Color.WHITE);
		txtModel.setEditable(false);
		txtModel.setBounds(148, 86, 116, 22);
		add(txtModel);
		txtModel.setColumns(10);

		txtSupplier = new JTextField();
		txtSupplier.setBackground(Color.WHITE);
		txtSupplier.setEditable(false);
		txtSupplier.setBounds(148, 122, 116, 22);
		add(txtSupplier);
		txtSupplier.setColumns(10);

		txtCategory = new JTextField();
		txtCategory.setBackground(Color.WHITE);
		txtCategory.setEditable(false);
		txtCategory.setBounds(548, 86, 191, 22);
		add(txtCategory);
		txtCategory.setColumns(10);

		JButton btnRemove = new JButton("<- Fjern");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeModule();
			}
		});

		btnRemove.setBackground(new Color(255, 99, 71));
		btnRemove.setBounds(350, 304, 87, 25);
		add(btnRemove);

		JButton btnAddProduct = new JButton("Tilføj ->");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCustomProductToOrder();
			}
		});

		btnAddProduct.setBackground(Color.ORANGE);
		btnAddProduct.setBounds(592, 412, 145, 25);
		add(btnAddProduct);

		txtTotal = new JTextField();
		txtTotal.setBounds(592, 333, 147, 22);
		add(txtTotal);
		txtTotal.setColumns(10);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(511, 336, 56, 16);
		add(lblTotal);

		JLabel lblSearch = new JLabel("S\u00F8g:");
		lblSearch.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSearch.setBounds(40, 13, 56, 16);
		add(lblSearch);
	}

	protected void filterModuleTable() {
		if (comboBox.getItemCount() != 0) {
			tableModel.setData(new ArrayList<Product>(model.getModules()));
			String selectedElement = (String) comboBox.getSelectedItem();
			System.out.println(selectedElement);
			ArrayList<Product> loadModules = new ArrayList<>();
			ArrayList<Product> modules = tableModel.getAllTableData();
			if (!selectedElement.equals("Alle")) {
				for (int i = 0; i < modules.size(); i++) {
					Product p = modules.get(i);
					for (Property prop : p.getProperties()) {
						String temp = (String) prop.getValue();
						if (prop.getName().equalsIgnoreCase("modultype") && temp.equals(selectedElement)) {
							loadModules.add(p);
						}
					}
				}
				tableModel.setData(loadModules);
			}
		}
	}

	protected void addCustomProductToOrder() {
		if (listModel.getSize() == 0) {
			new JOptionPane();
			JOptionPane.showMessageDialog(this, "Tilføj venligst moduler");
		} else {
			csov.addSalesOrderLine(model, 1);
			for (Product p : listModel.getData()) {
				csov.addSalesOrderLine(p, 1);
			}
			clearFields();
			listModel.clear();
			tableModel.clearTable();
		}
	}

	private void setSalesOrderCtrl(SalesOrderCtrl salesOrderCtrl) {
		this.salesOrderCtrl = salesOrderCtrl;
	}

	protected void removeModule() {
		if (!moduleList.isSelectionEmpty()) {
			listModel.removeElementAt(moduleList.getSelectedIndex());
			txtTotal.setText(String.valueOf(listModel.getTotal()));
		} else {
			new JOptionPane();
			JOptionPane.showMessageDialog(this, "Vælg modul der ønskes fjernet!");
		}
	}

	protected void addModule() {
		if (moduleTbl.getSelectedRowCount() != 0) {
			int[] selectedRows = moduleTbl.getSelectedRows();
			for (int i : selectedRows) {
				listModel.addElement(tableModel.getData(i));
			}
			txtTotal.setText(String.valueOf(salesOrderCtrl.getOrder().getTotalPrice()));
		} else {
			new JOptionPane().showMessageDialog(this, "Vælg modul du ønsker at tilføje!");
		}
	}
	
	protected void setModel() {
		model = productList.getSelectedObject(productList.getSelectedElement());
		if (model != null) {
			loadComboBox();
			txtDescription.setText(model.getDescription());
			txtModel.setText(model.getModel());
			txtSupplier.setText(model.getSupplier().getName());
			txtCategory.setText(model.getProductType().getCategoryName());
			LinkedList<Product> temp = model.getModules();
			List<Product> modules = new ArrayList<Product>(temp);
			tableModel.setData((ArrayList<Product>) modules);
		} else {
			new JOptionPane().showMessageDialog(this, txtFindModel.getText() + " kan ikke findes i databasen");
		}
	}

	private void clearFields(){
		txtDescription.setText("");
		txtModel.setText("");
		txtSupplier.setText("");
		txtFindModel.setText("");
		txtTotal.setText("");
		txtCategory.setText("");
	}

	public void loadComboBox(){
		comboBox.removeAllItems();
		comboBox.addItem("Alle");
		ArrayList<Product> modules = new ArrayList<Product>(model.getModules());
		ArrayList<String> properties = new ArrayList<>();
		for (Product p : modules) {
			for (Property prop : p.getProperties()) {
				if (prop.getName().equalsIgnoreCase("Modultype") && !properties.contains(prop.getValue())) {
					properties.add((String) prop.getValue());
				}
			}
		}
		for (String prop : properties) {
			comboBox.addItem(prop);
		}
	}
}
