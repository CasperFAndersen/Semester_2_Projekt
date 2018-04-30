package mockups;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * SmartOrder
 * filename.java
 * Purpose: 
 * @author Gruppe 1
 * @version 1.0 
 */
public class TestView {

	private JFrame customerFrame;
	private JFrame makeProductFrame;
	private JFrame productInfoFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestView window = new TestView();
					window.customerFrame.setVisible(true);
					window.makeProductFrame.setVisible(true);
					window.productInfoFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		createCustomerFrame();
		
		createMakeProductFrame();
		
		createProductInfoFrame();
	}

	private void createCustomerFrame() {
		customerFrame = new JFrame();
		customerFrame.setBounds(100, 100, 500, 300);
		customerFrame.setContentPane(new CustomerPanel());
		customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void createMakeProductFrame() {
		makeProductFrame = new JFrame();
		makeProductFrame.setBounds(600, 100, 600, 600);
		makeProductFrame.setContentPane(new MakeProductPanel());
		makeProductFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void createProductInfoFrame() {
		productInfoFrame = new JFrame();
		productInfoFrame.setBounds(300, 400, 300, 300);
		productInfoFrame.setContentPane(new ProductInfoPanel());
		productInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
