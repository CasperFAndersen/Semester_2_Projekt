package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import model.Employee;

/**
 * SmartOrder
 * LoginView.java
 * Purpose: The view the program has to be started from. Shows the login view and passes the user to the MainMenuView.
 * @author Gruppe 1
 * @version 1.0 
 */
public class LoginView extends JFrame {
	private JPanel contentPane;
	private Stack<JPanel> panelStack = new Stack<>();
	private Employee loggedInEmployee;
	private static LoginView frame = null;
	private JPanel loginPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
					frame = new LoginView();
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
		//this.setSize(1400, 950);
		this.setBounds(250, 30, 1400, 950);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		loginPanel = new LoginPanel();
		loginPanel.setBounds(535, 304, 249, 167);
		contentPane.add(loginPanel);
	}

	public static LoginView getInstance() {
		if (frame == null) {
			frame = new LoginView();
		}
		return frame;
	}

	protected void activateContentPane(JPanel newFrame, JPanel currFrame) {
		setLastFrame(currFrame);
		setContentPane(newFrame);
		newFrame.setVisible(true);
		newFrame.revalidate();
		newFrame.requestFocus();
	}
	
	protected void activateMiddlePane(JPanel newFrame, JPanel currFrame) {
		setLastFrame(currFrame);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		loginPanel = newFrame;
		loginPanel.setBounds(450, 150, newFrame.getWidth(), newFrame.getHeight());
		contentPane.add(loginPanel);
		
		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goBack();
			}
		});
		btnBack.setBounds(1218, 821, 89, 23);
		add(btnBack);
		contentPane.setVisible(true);
		contentPane.revalidate();
	}

	public void goBack() {
		JPanel panel = panelStack.pop();
		if (panel.equals(loginPanel)) {
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(null);
			setContentPane(contentPane);
			loginPanel = new LoginPanel();
			loginPanel.setBounds(535, 304, 249, 167);
			contentPane.add(loginPanel);
		} else {
		setContentPane(panel);
		contentPane.setVisible(true);
		contentPane.revalidate();
		}
	}

	public void setLastFrame(JPanel currFrame) {
		panelStack.push(currFrame);		
	}
	
	public void setLoggedInEmployee(Employee e){
		loggedInEmployee = e;
	}
	
	public Employee getLoggedInEmployee(){
		return loggedInEmployee;
	}
	
}
