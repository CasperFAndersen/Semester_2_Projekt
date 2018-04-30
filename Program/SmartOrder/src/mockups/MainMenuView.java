package mockups;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * SmartOrder
 * filename.java
 * Purpose: 
 * @author Gruppe 1
 * @version 1.0 
 */
public class MainMenuView {

	private JFrame frame;
	private CreateOrderView createOrderView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuView window = new MainMenuView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenuView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		createOrderView = new CreateOrderView();
		
		JButton btnCreateOrder = new JButton("Opret ordre");
		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(createOrderView);
			}
		});
		btnCreateOrder.setBounds(143, 126, 175, 29);
		frame.getContentPane().add(btnCreateOrder);
		
		JButton btnExistingOrders = new JButton("Eksisterende ordrer");
		btnExistingOrders.setBounds(469, 126, 175, 29);
		frame.getContentPane().add(btnExistingOrders);
		
		JButton btnSuppliers = new JButton("Leverand\u00F8rer");
		btnSuppliers.setBounds(143, 247, 175, 29);
		frame.getContentPane().add(btnSuppliers);
	}
}
