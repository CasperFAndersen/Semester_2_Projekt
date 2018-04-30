package ui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Customer;

/**
 * SmartOrder
 * CustomerPanel.java
 * Purpose: View showing a specific customer and its attributes
 * @author Gruppe 1
 * @version 1.0 
 */
public class CustomerPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JTextField txtCity;
	private JTextField txtZipCode;
	private JComboBox comboBox;
	private JTextField txtType;

	/**
	 * Create the panel.
	 */
	public CustomerPanel() {
		setLayout(null);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(81, 13, 146, 22);
		add(txtName);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(81, 150, 146, 22);
		add(txtPhone);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(81, 183, 146, 22);
		add(txtEmail);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(81, 48, 146, 22);
		add(txtAddress);

		JLabel lblAddress = new JLabel("Adresse:");
		lblAddress.setBounds(12, 51, 56, 16);
		add(lblAddress);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 186, 56, 16);
		add(lblEmail);

		JLabel lblPhone = new JLabel("Telefon:");
		lblPhone.setBounds(12, 153, 56, 16);
		add(lblPhone);

		JLabel lblName = new JLabel("Navn:");
		lblName.setBounds(12, 16, 56, 16);
		add(lblName);

		txtCity = new JTextField();
		txtCity.setBounds(81, 115, 146, 22);
		add(txtCity);
		txtCity.setColumns(10);

		txtZipCode = new JTextField();
		txtZipCode.setBounds(81, 80, 146, 22);
		add(txtZipCode);
		txtZipCode.setColumns(10);

		JLabel lblCity = new JLabel("By:");
		lblCity.setBounds(12, 118, 56, 16);
		add(lblCity);

		JLabel lblPostalCode = new JLabel("Postnr:");
		lblPostalCode.setBounds(12, 83, 56, 16);
		add(lblPostalCode);

		comboBox = new JComboBox();
		comboBox.setBounds(81, 218, 146, 22);
		loadComboBoxItems();
		add(comboBox);

		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(12, 221, 56, 16);
		add(lblType);
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtPhone() {
		return txtPhone;
	}

	public void setTxtPhone(JTextField txtPhone) {
		this.txtPhone = txtPhone;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JTextField getTxtAddress() {
		return txtAddress;
	}

	public void setTxtAddress(JTextField txtAddress) {
		this.txtAddress = txtAddress;
	}

	public JTextField getTxtCity() {
		return txtCity;
	}

	public void setTxtCity(JTextField txtCity) {
		this.txtCity = txtCity;
	}

	public JTextField getTxtZipCode() {
		return txtZipCode;
	}

	public String getSelectedType() {
		return comboBox.getSelectedItem().toString();
	}

	public void setTxtPostalCode(JTextField txtPostalCode) {
		this.txtZipCode = txtPostalCode;
	}

	public void setComboBox(String type) {
		comboBox.setSelectedItem(type.toString());
	}

	private void loadComboBoxItems() {
		comboBox.addItem("privat");
		comboBox.addItem("erhverv");
	}

	public void makeTextFieldsEditable(boolean input) {
		txtName.setEditable(input);
		txtPhone.setEditable(input);
		txtEmail.setEditable(input);
		txtAddress.setEditable(input);
		txtCity.setEditable(input);
		txtZipCode.setEditable(input);
		comboBox.setEditable(input);
	}
	
	public void fillTxtfields(Customer c){
		txtName.setText(c.getName());
		txtPhone.setText(c.getPhone());
		txtEmail.setText(c.getEmail());
		txtAddress.setText(c.getAddress());
		txtZipCode.setText(c.getZipCode());
		txtCity.setText(c.getCity());
		if(txtType != null){
			txtType.setText(c.getType());
		}
	}

	public void clearTxtFields() {
		txtName.setText("");
		txtPhone.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		txtZipCode.setText("");
		txtCity.setText("");		
	}
	
	public void typeAsComboBox(boolean b){
		if(!b){
			comboBox.setEnabled(false);
			comboBox.setVisible(false);
			txtType = new JTextField();
			txtType.setBounds(81, 215, 146, 22);
			txtType.setEditable(false);
			add(txtType);
		}
		else{
			comboBox = new JComboBox();
			comboBox.setBounds(81, 225, 146, 22);
			loadComboBoxItems();
			add(comboBox);
		}
	}
}
