package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;

/**
 * SmartOrder
 * MainMenuView.java
 * Purpose: The main menu view is responsible for showing buttons that the user can use to get to another view.
 * @author Gruppe 1
 * @version 1.0 
 */
public class MainMenuView extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuView frame = new MainMenuView();
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
	public MainMenuView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCreateOrder = new JButton("Opret ordre");
		btnCreateOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showCreateSalesOrderView();
			}
		});
		btnCreateOrder.setBounds(256, 274, 256, 256);
		contentPane.add(btnCreateOrder);

		JButton btnLogout = new JButton("Log ud");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logout();
			}
		});
		btnLogout.setBounds(1218, 821, 89, 23);
		contentPane.add(btnLogout);
		
		JButton btnProducts = new JButton("Produkter");
		btnProducts.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productsClicked();
			}
		});
		btnProducts.setBounds(583, 274, 256, 256);
		contentPane.add(btnProducts);
		
		JButton btnCustomersEmployeesAndSuppliers = new JButton("Kunder, ansatte og leverand\u00F8rer");
		btnCustomersEmployeesAndSuppliers.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCustomersEmployeesAndSuppliers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customersEmployeesAndSuppliersClicked();
			}
		});
		btnCustomersEmployeesAndSuppliers.setBounds(905, 274, 256, 256);
		contentPane.add(btnCustomersEmployeesAndSuppliers);
		
		JLabel lblNewLabel = new JLabel("Hovedmenu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(646, 73, 109, 64);
		contentPane.add(lblNewLabel);
		this.setSize(1400, 950);
	}

	protected void customersEmployeesAndSuppliersClicked() {
		PersonView pv = new PersonView();
		LoginView.getInstance().activateContentPane(pv, contentPane);
	}

	private void showCreateSalesOrderView() {
		CreateSalesOrderView csov = new CreateSalesOrderView();
		LoginView.getInstance().activateContentPane((JPanel) csov.getContentPane(), contentPane);
	}

	private void productsClicked() {
		ProductView pv = new ProductView();
		LoginView.getInstance().activateContentPane(pv, contentPane);
	}
	
	private void logout() {
		LoginView.getInstance().goBack();
		LoginView.getInstance().revalidate();
	}
}
