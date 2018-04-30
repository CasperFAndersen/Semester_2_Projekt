package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * SmartOrder
 * PersonView.java
 * Purpose: Show the different actions an employee can do with a customer
 * @author Gruppe 1
 * @version 1.0 
 */
public class PersonView extends JPanel {
	/**
	 * Create the panel.
	 */
	public PersonView() {
		setLayout(null);

		JButton btnCreateCustomer = new JButton("Opret kunde");
		btnCreateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCustomerClicked();
			}
		});
		btnCreateCustomer.setBounds(12, 13, 192, 192);
		add(btnCreateCustomer);
		
		JButton btnUpdateCustomers = new JButton("Administrer kunder");
		btnUpdateCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCustomersClicked();
			}
		});
		btnUpdateCustomers.setBounds(12, 218, 192, 192);
		add(btnUpdateCustomers);
		
		JButton btnCreateEmployee = new JButton("Opret ansat");
		btnCreateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createEmployeeClicked();
			}
		});
		btnCreateEmployee.setBounds(216, 13, 192, 192);
		add(btnCreateEmployee);
		
		JButton btnUpdateEmployees = new JButton("Administrer ansatte");
		btnUpdateEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateEmployeesClicked();
			}
		});
		btnUpdateEmployees.setBounds(216, 218, 192, 192);
		add(btnUpdateEmployees);

		JButton btnCreateSupplier = new JButton("Opret leverand\u00F8r");
		btnCreateSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createSupplierClicked();
			}
		});
		btnCreateSupplier.setBounds(420, 13, 192, 192);
		add(btnCreateSupplier);
		
		JButton btnUpdateSuppliers = new JButton("Administrer leverand\u00F8rer");
		btnUpdateSuppliers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSuppliersClicked();
			}
		});
		btnUpdateSuppliers.setBounds(420, 218, 192, 192);
		add(btnUpdateSuppliers);
		
		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goBackToLastMenu();
			}
		});
		btnBack.setBounds(1218, 821, 89, 23);
		add(btnBack);
	}

	protected void goBackToLastMenu() {
		LoginView.getInstance().goBack();
	}

	private void createCustomerClicked() {
		CreateCustomerView ccv = new CreateCustomerView();
		LoginView.getInstance().activateContentPane((JPanel) ccv.getContentPane(), this);
	}
	
	private void updateCustomersClicked() {
		// TODO Auto-generated method stub
	}
	
	private void createEmployeeClicked() {
		CreateEmployeeView cev = new CreateEmployeeView();
		//LoginView.getInstance().activateContentPane(cev, this);
	}

	private void updateEmployeesClicked() {
		// TODO Auto-generated method stub
	}
	
	private void createSupplierClicked() {
		// TODO Auto-generated method stub
	}
	
	private void updateSuppliersClicked() {
		// TODO Auto-generated method stub
	}

	public JPanel getContentPane() {
		return this;
	}

}
