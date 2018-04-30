package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.PersonCtrl;

/**
 * SmartOrder
 * CreateEmployeeView.java
 * Purpose: View for creating an employee
 * @author Gruppe 1
 * @version 1.0 
 */
public class CreateEmployeeView extends JPanel {
	PersonCtrl personCtrl = new PersonCtrl();
	private EmployeePanel employeePanel;
	
	/**
	 * Create the panel.
	 */
	public CreateEmployeeView() {
		setLayout(null);
		
		JLabel lblEmployeeInfo = new JLabel("Ansattes information");
		lblEmployeeInfo.setBounds(37, 11, 177, 14);
		lblEmployeeInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblEmployeeInfo);
		
		JButton btnAddEmployee = new JButton("Tilf\u00F8j ansat");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAddEmployeeClicked();
			}
		});
		btnAddEmployee.setBounds(183, 434, 89, 23);
		add(btnAddEmployee);
		
		employeePanel = new EmployeePanel();
		employeePanel.setBounds(37, 82, 235, 353);
		add(employeePanel);
	}

	protected void btnAddEmployeeClicked() {
		// TODO To be implemented
	}
}
