package ui;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import controller.ProductCtrl;
import exception.InsertFailedException;
import model.Property;
import javax.swing.border.LineBorder;
import java.awt.Color;

/**
 * SmartOrder
 * CreateProductTypeView.java
 * Purpose: View used for creating a product type
 * @author Gruppe 1
 * @version 1.0 
 */
public class CreateProductTypeView extends JFrame {
	private JPanel contentPane;
	private JTextField txtCatName;
	private JTextField txtPropName;
	private JComboBox<String> propTypeBox;
	private DefaultListModel<String> listModel;
	private JList<?> propertyList;
	private ProductCtrl productCtrl;
	private ArrayList<Property> templateProperties;
	private JComboBox<String> prodTypeBox;
	private boolean isPopup = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProductTypeView frame = new CreateProductTypeView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateProductTypeView() {
		productCtrl = new ProductCtrl();
		templateProperties = new ArrayList<>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 444, 602);

		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setBounds(700, 200, 444, 602);
		contentPane.setLayout(null);
		LoginView.getInstance().getContentPane();

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(44, 167, 335, 298);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblAttributeName = new JLabel("Attribut-navn");
		lblAttributeName.setBounds(27, 35, 74, 16);
		panel.add(lblAttributeName);

		txtPropName = new JTextField();
		txtPropName.setBounds(27, 54, 116, 22);
		panel.add(txtPropName);
		txtPropName.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 148, 276, 123);
		panel.add(scrollPane);
		
		listModel = new DefaultListModel();
		propertyList = new JList(listModel);
		scrollPane.setViewportView(propertyList);
		
		JLabel lblAddedAttributtes = new JLabel("Tilf\u00F8jede attributter");
		lblAddedAttributtes.setBounds(32, 130, 111, 16);
		panel.add(lblAddedAttributtes);

		propTypeBox = new JComboBox<String>();
		propTypeBox.setBounds(200, 54, 103, 22);
		propTypeBox.addItem("Vælg type"); 
		propTypeBox.addItem("String");
		propTypeBox.addItem("Double");
		propTypeBox.addItem("Boolean");
		panel.add(propTypeBox);

		JLabel lblType = new JLabel("Type");
		lblType.setBounds(200, 35, 28, 16);
		panel.add(lblType);

		Button button = new Button("Tilf\u00F8j");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (performPropertyCheck()) {
					addProperty();
				}
			}
		});
		button.setBounds(27, 82, 116, 24);
		panel.add(button);

		JButton btnDelete = new JButton("");
		btnDelete.addActionListener((e) -> { deleteSelectedProperty(); } );
		btnDelete.setIcon(new ImageIcon(CreateProductTypeView.class.getResource("/mockups/redx.png")));
		btnDelete.setBounds(275, 273, 28, 12);
		panel.add(btnDelete);

		txtCatName = new JTextField();
		txtCatName.setBounds(64, 80, 116, 22);
		contentPane.add(txtCatName);
		txtCatName.setColumns(10);

		prodTypeBox = new JComboBox<String>();
		prodTypeBox.setBounds(243, 80, 103, 22);
		prodTypeBox.addItem("Vælg type"); 
		prodTypeBox.addItem("Customizable");
		prodTypeBox.addItem("NonCustomizable");
		prodTypeBox.addItem("Module");

		contentPane.add(prodTypeBox);

		JButton btnCreate = new JButton("Opret");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(performInputCheck()){addProductType();}
			}
		});
		btnCreate.setBounds(282, 489, 97, 25);
		contentPane.add(btnCreate);

		JButton btnBack = new JButton("Tilbage");
		btnBack.setVisible(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goBack();
			}
		});
		btnBack.setBounds(190, 489, 97, 25);
		contentPane.add(btnBack);

		JLabel lblCategoryName = new JLabel("Kategori-navn");
		lblCategoryName.setBounds(65, 61, 97, 16);
		contentPane.add(lblCategoryName);

		//TODO Where is this type used?
		JLabel lblType_1 = new JLabel("Grundtype");
		lblType_1.setBounds(243, 61, 74, 16);
		contentPane.add(lblType_1);

		Label lblAddAttributesToProductType = new Label("Tilf\u00F8j attributter til produkttype");
		lblAddAttributesToProductType.setBounds(64, 143, 235, 24);
		contentPane.add(lblAddAttributesToProductType);
		lblAddAttributesToProductType.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblOpretProdukttype = new JLabel("Opret produkttype");
		lblOpretProdukttype.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOpretProdukttype.setBounds(146, 13, 194, 16);
		contentPane.add(lblOpretProdukttype);
	}

	private void deleteSelectedProperty() {
		if (propertyList.isSelectionEmpty()) {
			new JOptionPane().showMessageDialog(contentPane, "Vælg attribut der ønskes slettet.");
		} else {
			listModel.removeElementAt(propertyList.getSelectedIndex());
		}
	}

	protected void goBack() {
		if (isPopup) {
			dispose();
		} else {
			LoginView.getInstance().goBack();
		}
	}

	protected boolean performPropertyCheck() {
		boolean checksOut = false;
		if (propTypeBox.getSelectedItem().equals("Vælg type")) {
			new JOptionPane().showMessageDialog(contentPane, "Attributtype mangler at blive valgt");
		} else if (txtPropName.getText().equals("")) {
			new JOptionPane().showMessageDialog(contentPane, "Attribut-navn mangler at blive udfyldt");
		} else {
			checksOut = true;
		}
		return checksOut;
	}

	protected boolean performInputCheck() {
		boolean checksOut = false;
		if (txtCatName.getText().equals("")) {
			new JOptionPane().showMessageDialog(contentPane, "Kategori-navn mangler at blive udfyldt");
		} else if (prodTypeBox.getSelectedItem().equals("Vælg type")) {
			new JOptionPane().showMessageDialog(contentPane, "Varetype mangler at blive valgt");
		} else {
			checksOut = true;
		}
		return checksOut;
	}

	protected void addProductType() {
		String catName = txtCatName.getText();
		String type = (String) prodTypeBox.getSelectedItem();
		try {
			productCtrl.addProductType(catName, type, templateProperties);
			reset();
		} catch (SQLException | InsertFailedException e) {
			new JOptionPane().showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}

	protected void addProperty() {
		String name = txtPropName.getText();
		String type = (String) propTypeBox.getSelectedItem();
		Property prop = productCtrl.createTemplateProperty(name, type);
		listModel.addElement("Navn: " +prop.getName() + "   Type: " + prop.getType().getSimpleName());
		templateProperties.add(prop);
	}

	private void reset(){
		txtCatName.setText("");
		txtPropName.setText("");
		propTypeBox.setSelectedIndex(0);
		prodTypeBox.setSelectedIndex(0);
		listModel.removeAllElements();
		templateProperties.removeAll(templateProperties);
	}

	public void isPopup(boolean b){
		isPopup = b;
	}
}
