package mockups;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * SmartOrder
 * filename.java
 * Purpose: 
 * @author Gruppe 1
 * @version 1.0 
 */
public class LoginPanel extends JPanel {
	private JPasswordField txtPassword;
	private JTextField txtUsername;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setLayout(null);
		
		JLabel lblUsername = new JLabel("Brugernavn:");
		lblUsername.setBounds(12, 43, 77, 16);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Kode:");
		lblPassword.setBounds(12, 72, 77, 16);
		add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(101, 69, 116, 22);
		add(txtPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(101, 40, 116, 22);
		add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnLogIn = new JButton("Log ind");
		btnLogIn.setBounds(101, 104, 116, 25);
		add(btnLogIn);
		
		JLabel lblErrorLabel = new JLabel("");
		lblErrorLabel.setBounds(12, 13, 205, 16);
		add(lblErrorLabel);

	}
}
