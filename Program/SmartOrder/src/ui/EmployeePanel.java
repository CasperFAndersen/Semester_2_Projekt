package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * SmartOrder
 * EmployeePanel.java
 * Purpose: View showing a employee and its attributes
 * @author Gruppe 1
 * @version 1.0 
 */
public class EmployeePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtAddress;
	private JTextField txtCity;
	private JTextField txtZipCode;
	private JLabel lblCity;
	private JLabel lblPostalCode;
	private JTextField txtSsn;
	private JTextField txtSalary;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JLabel lblPassword;
	private JLabel lblUsername;
	private JLabel lblSalary;
	private JLabel lblSsn;
	private JLabel lblEmail;
	private JLabel lblPhone;
	
	/**
	 * Create the panel.
	 */
	public EmployeePanel() {
		setLayout(null);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(81, 13, 146, 22);
		add(txtName);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(81, 157, 146, 22);
		add(txtPhone);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(81, 190, 146, 22);
		add(txtEmail);

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(81, 48, 146, 22);
		add(txtAddress);

		JLabel lblAddress = new JLabel("Adresse:");
		lblAddress.setBounds(12, 51, 56, 16);
		add(lblAddress);

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 193, 56, 16);
		add(lblEmail);

		lblPhone = new JLabel("Telefon:");
		lblPhone.setBounds(12, 160, 56, 16);
		add(lblPhone);

		JLabel lblName = new JLabel("Navn:");
		lblName.setBounds(12, 16, 56, 16);
		add(lblName);

		txtCity = new JTextField();
		txtCity.setBounds(81, 83, 146, 22);
		add(txtCity);
		txtCity.setColumns(10);

		txtZipCode = new JTextField();
		txtZipCode.setBounds(81, 118, 146, 22);
		add(txtZipCode);
		txtZipCode.setColumns(10);

		lblCity = new JLabel("By:");
		lblCity.setBounds(12, 86, 56, 16);
		add(lblCity);

		lblPostalCode = new JLabel("Postnr:");
		lblPostalCode.setBounds(12, 121, 56, 16);
		add(lblPostalCode);
		
		lblSsn = new JLabel("Ssn:");
		lblSsn.setBounds(12, 224, 56, 16);
		add(lblSsn);
		
		txtSsn = new JTextField();
		txtSsn.setColumns(10);
		txtSsn.setBounds(81, 221, 146, 22);
		add(txtSsn);
		
		lblSalary = new JLabel("L\u00F8n:");
		lblSalary.setBounds(12, 257, 56, 16);
		add(lblSalary);
		
		txtSalary = new JTextField();
		txtSalary.setColumns(10);
		txtSalary.setBounds(81, 254, 146, 22);
		add(txtSalary);
		
		lblUsername = new JLabel("Brugernavn:");
		lblUsername.setBounds(12, 290, 69, 16);
		add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(81, 287, 146, 22);
		add(txtUsername);
		
		lblPassword = new JLabel("Kodeord:");
		lblPassword.setBounds(12, 324, 69, 16);
		add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(81, 321, 146, 22);
		add(txtPassword);
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

	public JTextField getTxtPostalCode() {
		return txtZipCode;
	}

	public void setTxtPostalCode(JTextField txtPostalCode) {
		this.txtZipCode = txtPostalCode;
	}
	
	public JTextField getTxtSsn() {
		return txtSsn;
	}

	public void setTxtSsn(JTextField txtSsn) {
		this.txtSsn = txtSsn;
	}

	public JTextField getTxtSalary() {
		return txtSalary;
	}

	public void setTxtSalary(JTextField txtSalary) {
		this.txtSalary = txtSalary;
	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public void setTxtUsername(JTextField txtUsername) {
		this.txtUsername = txtUsername;
	}

	public JTextField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(JTextField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public void makeTextFieldsEmployeeEditable(boolean input) {
		txtName.setEditable(input);
		txtAddress.setEditable(input);
		txtPhone.setEditable(input);
		txtEmail.setEditable(input);
		txtZipCode.setEditable(input);
		txtCity.setEditable(input);
		txtSsn.setEditable(input);
		txtSalary.setEditable(input);
		txtUsername.setEditable(input);
		txtPassword.setEditable(input);	
	}
}
