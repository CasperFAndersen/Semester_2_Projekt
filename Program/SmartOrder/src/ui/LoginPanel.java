package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.PersonCtrl;
import controller.PersonCtrlIF;
import model.Employee;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * SmartOrder
 * LoginPanel.java
 * Purpose: A panel used for when an employee has to login to the system
 * @author Gruppe 1
 * @version 1.0 
 */
public class LoginPanel extends JPanel {
	private JPasswordField txtPassword;
	private JTextField txtUsername;
	private PersonCtrlIF personCtrl = new PersonCtrl();
	private JLabel lblMessage;

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
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					attemptLogin();
				}
			}
		});
		txtPassword.setEchoChar('*');
		txtPassword.setBounds(101, 69, 116, 22);
		add(txtPassword);

		txtUsername = new JTextField();
		txtUsername.setBounds(101, 40, 116, 22);
		add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					attemptLogin();
				}
			}
		});

		lblMessage = new JLabel("");
		lblMessage.setBounds(12, 13, 205, 16);
		lblMessage.setVisible(true);
		add(lblMessage);

		JButton btnLogIn = new JButton("Log ind");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				attemptLogin();
			}
		});
		btnLogIn.setBounds(101, 104, 116, 25);
		add(btnLogIn);
	}

	protected void clearText() {
		txtUsername.setText("");
		txtPassword.setText("");
	}

	protected void loginSuccess() {
		MainMenuView mmv = new MainMenuView();
		LoginView.getInstance().activateContentPane((JPanel) mmv.getContentPane(), this);
	}

	protected void attemptLogin() {
		try {
			Employee employee = personCtrl.findEmployeeByUsernameAndPassword(txtUsername.getText(), txtPassword.getText());
			if (employee != null) {
				LoginView.getInstance().setLoggedInEmployee(employee);
				clearText();
				loginSuccess();
			} else {
				lblMessage.setText("Ugyldig brugernavn eller kode");
			}
		} catch (SQLException e) {
			e.getMessage();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

}

