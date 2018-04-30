package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.ProductCtrl;
import exception.InsertFailedException;
import model.Product;
import model.ProductType;
import model.Supplier;
import javax.swing.border.BevelBorder;
import java.awt.Font;

/**
 * SmartOrder
 * CreateProductView.java
 * Purpose: View for creating a product
 * @author Gruppe 1
 * @version 1.0 
 */
public class CreateProductView extends JPanel {
	private JTextField txtDimensions;
	private JTextField txtModel;
	private JTextField txtDescription;
	private JTextField txtPurchasePrice;
	private JTextField txtSalesPrice;
	private JTable propertyTable;
	private ProductCtrl productCtrl;
	private TableModelProperty tableModel = new TableModelProperty();
	private ArrayList<ProductType> productTypes = null; 
	private ArrayList<Supplier> suppliers;
	private ArrayList<Product> customizableProducts;
	private JComboBox<String> productTypeBox;
	private Product prodTemplate;
	private JComboBox<String> supplierBox;
	private JComboBox<String> moduleForBox;
	private Dimension org;
	private JLabel lblModuleFor;

	/**
	 * Create the panel.
	 */
	public CreateProductView() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		productCtrl= new ProductCtrl();
		try {
			productTypes = productCtrl.findAllProductTypes();
			suppliers = productCtrl.findAllSuppliers();
			customizableProducts = productCtrl.findAllCustomizableProducts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setLayout(null);
		moduleForBox = new JComboBox();
		moduleForBox.setBounds(145, 326, 116, 22);
		add(moduleForBox);
		loadModulBox();
		//setBounds(700, 200, 570, 440);
		setSize(570, 440);
		lblModuleFor = new JLabel("Modul til:");
		lblModuleFor.setBounds(56, 329, 56, 16);
		add(lblModuleFor);

		productTypeBox = new JComboBox();
		productTypeBox.setBounds(56, 80, 205, 22);
		add(productTypeBox);
		productTypeBox.addActionListener((e)-> { loadProductTemplate(); setModuleBoxVisibility(); } );
		loadProductTypeBox();

		JLabel lblChooseProductType = new JLabel("V\u00E6lg produkttype");

		lblChooseProductType.setBounds(56, 52, 151, 16);
		add(lblChooseProductType);

		JLabel lblModel = new JLabel("Model:");
		lblModel.setBounds(56, 137, 88, 16);
		add(lblModel);

		JLabel lblDescription = new JLabel("Beskrivelse:");
		lblDescription.setBounds(56, 169, 88, 16);
		add(lblDescription);

		JLabel lblDimensions = new JLabel("Dimensioner:");
		lblDimensions.setBounds(56, 198, 88, 16);
		add(lblDimensions);

		JLabel lblPurchasePrice = new JLabel("Indk\u00F8bspris:");
		lblPurchasePrice.setBounds(56, 230, 88, 16);
		add(lblPurchasePrice);

		JLabel lblSalesPrice = new JLabel("Salgspris:");
		lblSalesPrice.setBounds(56, 259, 88, 16);
		add(lblSalesPrice);

		txtDimensions = new JTextField();
		txtDimensions.setBounds(145, 198, 116, 22);
		add(txtDimensions);
		txtDimensions.setColumns(10);

		txtModel = new JTextField();
		txtModel.setColumns(10);
		txtModel.setBounds(145, 134, 116, 22);
		add(txtModel);

		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(145, 166, 116, 22);
		add(txtDescription);

		txtPurchasePrice = new JTextField();
		txtPurchasePrice.setColumns(10);
		txtPurchasePrice.setBounds(145, 227, 116, 22);
		add(txtPurchasePrice);

		txtSalesPrice = new JTextField();
		txtSalesPrice.setColumns(10);
		txtSalesPrice.setBounds(145, 256, 116, 22);
		add(txtSalesPrice);

		JLabel lblSpecialAttributtes = new JLabel("Specialattributter:");
		lblSpecialAttributtes.setBounds(273, 52, 116, 16);
		add(lblSpecialAttributtes);

		JLabel lblSupplier = new JLabel("Leverand\u00F8r:");
		lblSupplier.setBounds(56, 294, 88, 16);
		add(lblSupplier);

		supplierBox = new JComboBox<String>();
		supplierBox.setBounds(145, 291, 116, 22);
		add(supplierBox);
		loadSupplierBox();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(283, 80, 241, 255);
		add(scrollPane);

		propertyTable = new JTable();
		scrollPane.setViewportView(propertyTable);
		propertyTable.setModel(tableModel);
		//tableModel.setProductCtrl(productCtrl);

		JButton btnCreateProduct = new JButton("Opret vare");
		btnCreateProduct.addActionListener((e) -> { createProduct(); } );
		btnCreateProduct.addChangeListener((e) -> { propertyTable.editingStopped(e); } );
		btnCreateProduct.setBounds(427, 381, 97, 25);
		add(btnCreateProduct);

		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goBackToLastMenu();
			}
		});
		btnBack.setBounds(1218, 821, 89, 23);
		add(btnBack);
		
		JLabel lblOpretNytProdukt = new JLabel("Opret nyt produkt");
		lblOpretNytProdukt.setFont(new Font("Dialog", Font.BOLD, 14));
		lblOpretNytProdukt.setBounds(210, 12, 151, 16);
		add(lblOpretNytProdukt);
	}

	protected void goBackToLastMenu() {
		LoginView.getInstance().goBack();
	}

	private void setModuleBoxVisibility() {
		if (getSelectedProductType().getType().equalsIgnoreCase("Module")) {
			moduleForBox.setVisible(true);
			lblModuleFor.setVisible(true);
		} else {
			moduleForBox.setVisible(false);
			lblModuleFor.setVisible(false);
		}

	}
	private void loadModulBox() {
		for (Product product : customizableProducts) {
			moduleForBox.addItem(product.getModel());
		}

	}

	private void loadSupplierBox() {
		for(Supplier supplier : suppliers){
			supplierBox.addItem(supplier.getName());
		}
	}

	private void createProduct() {
		if (txtModel.getText().equals("")) {
			new JOptionPane();
			JOptionPane.showMessageDialog(this, "Model skal udfyldes");
		} else {
			try {
				String model = txtModel.getText();
				String description = txtDescription.getText();
				Supplier supplier = getSelectedSupplier();
				String dimensions = txtDimensions.getText();
				LocalDate fromDate = LocalDate.now();
				double purchasePrice = Double.parseDouble(txtPurchasePrice.getText());
				double salesPrice = Double.parseDouble(txtSalesPrice.getText());
				int id = productCtrl.createProduct(prodTemplate, getSelectedProductType(), model, description, supplier, 
						dimensions, purchasePrice, salesPrice, fromDate);

				if (getSelectedProductType().getType().equalsIgnoreCase("Module")) {
					productCtrl.insertPartOfProduct(id, getSelectedCustomizableProduct().getId());
				}
				//productCtrl.insertProperties(tableModel.getProperties(), id);
				clearTxtField();
			} catch (InsertFailedException | SQLException e) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "Fejl! Produktet blev ikke gemt i databasen.");
			}
			catch (Exception e) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "Indkøbs- og salgspris skal udfyldes, kun med tal");
			}
		}
	}
	private void clearTxtField() {
		txtDescription.setText("");
		txtDimensions.setText("");
		txtPurchasePrice.setText("");
		txtModel.setText("");
		txtSalesPrice.setText("");
		propertyTable.removeAll();
	}

	private void loadProductTemplate() {
		prodTemplate = productCtrl.findTemplateById(getSelectedProductType().getTemplateId());
		tableModel.setProductTemplate(prodTemplate);
		tableModel.setData(prodTemplate.getProperties());
	}

	private void loadProductTypeBox() {
		for (ProductType productType : productTypes) {
			productTypeBox.addItem(productType.getCategoryName());
		}
	}

	private ProductType getSelectedProductType(){
		for (ProductType productType : productTypes) {
			if (productType.getCategoryName().equals(productTypeBox.getSelectedItem())) {
				return productType;
			}
		}
		return null;
	}

	private Supplier getSelectedSupplier() {
		for (Supplier supplier : suppliers) {
			if (supplierBox.getSelectedItem().equals(supplier.getName())) {
				return supplier;
			}
		}
		return null;
	}

	private Product getSelectedCustomizableProduct() {
		for (Product product : customizableProducts) {
			if (moduleForBox.getSelectedItem().equals(product.getModel())) {
				return product;
			}
		}
		return null;
	}
}
