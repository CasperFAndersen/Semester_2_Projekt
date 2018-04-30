package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.PersonCtrl;
import exception.AddressValidationException;
import exception.CityValidationException;
import exception.EmailValidationException;
import exception.InsertFailedException;
import exception.NameValidationException;
import exception.PhoneAlreadyExistException;
import exception.PhoneValidationException;
import exception.ZipAlreadyExistException;
import exception.ZipCodeValidationException;
import model.Customer;

/**
 * SmartOrder
 * CreateCustomerView.java
 * Purpose: View for creating a customer
 * @author Gruppe 1
 * @version 1.0 
 */
public class CreateCustomerView extends JFrame {
	private static final long serialVersionUID = 1L;
	private static CreateCustomerView frame;
	private static CreateSalesOrderView csov;
	PersonCtrl personCtrl = new PersonCtrl();
	private CustomerPanel customerPanel;
	private boolean showAsPopUp = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new CreateCustomerView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the panel.
	 */
	public CreateCustomerView() {
		setBounds(new Rectangle(1000, 200, 440, 420));
		getContentPane().setBounds(new Rectangle(200, 200, 420, 420));

		this.setBounds(200, 200, 439, 420);
		getContentPane().setLayout(null);

		JLabel lblCustomerInfo = new JLabel("Kunde information");
		lblCustomerInfo.setBounds(37, 11, 134, 14);
		lblCustomerInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblCustomerInfo);

		JButton btnAddCustomer = new JButton("Tilf\u00F8j kunde");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAddCustomerClicked();
			}
		});
		btnAddCustomer.setBounds(152, 318, 89, 23);
		getContentPane().add(btnAddCustomer);

		customerPanel = new CustomerPanel();
		customerPanel.makeTextFieldsEditable(true);
		customerPanel.setBounds(37, 41, 311, 272);
		getContentPane().add(customerPanel);

		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goBackToLastMenu();
			}
		});
		btnBack.setBounds(1218, 821, 89, 23);
		getContentPane().add(btnBack);

		JButton btnCancel = new JButton("Annullere");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
		btnCancel.setBounds(253, 317, 97, 25);
		getContentPane().add(btnCancel);
	}

	protected void goBackToLastMenu() {
		if (showAsPopUp) {
			this.dispose();
		} else {
			LoginView.getInstance().goBack();
		}
	}

	protected void btnAddCustomerClicked() {
		try {
			Customer customer = personCtrl.createCustomer(customerPanel.getTxtName().getText(), customerPanel.getTxtAddress().getText(), 
					customerPanel.getTxtZipCode().getText(), customerPanel.getTxtCity().getText(), 
					customerPanel.getTxtPhone().getText(), customerPanel.getTxtEmail().getText(), 
					customerPanel.getSelectedType().toString());
			if (csov != null) {
				csov.setCustomer(customer);
			}
			goBack();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PhoneAlreadyExistException | ZipAlreadyExistException | InsertFailedException | NameValidationException | AddressValidationException | 
				ZipCodeValidationException | CityValidationException | PhoneValidationException | EmailValidationException e) {
			new JOptionPane().showMessageDialog(this, e.getMessage());
		}
	}

	private void goBack() {
		if(showAsPopUp ) {
			this.dispose();
		} else {
			LoginView.getInstance().goBack();
		}

	}

	public void showAsPopup(boolean popup){
		showAsPopUp = popup;
	}

	public void setCSOV(CreateSalesOrderView csov){
		this.csov = csov;
	}
}
