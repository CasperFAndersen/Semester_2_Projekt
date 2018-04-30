package mockups;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * SmartOrder
 * filename.java
 * Purpose: 
 * @author Gruppe 1
 * @version 1.0 
 */
public class LoginView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		
		setTitle("SmartOrder");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.setSize(1400, 950);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		LoginPanel loginPanel = new LoginPanel();
		loginPanel.setBounds(554, 366, 269, 186);
		contentPane.add(loginPanel);
		
	}
}
