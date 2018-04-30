package ui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JLabel;

public class Customer extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Create the panel.
	 */
	public Customer() {
		setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(101, 177, 116, 22);
		add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(101, 139, 116, 22);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(101, 104, 116, 22);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setText("S\u00F8g kunde via navn");
		textField_3.setFont(new Font("Tahoma", Font.ITALIC, 13));
		textField_3.setColumns(10);
		textField_3.setBorder(new LineBorder(new Color(171, 173, 179), 3, true));
		textField_3.setBackground(Color.WHITE);
		textField_3.setBounds(101, 58, 317, 33);
		add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(302, 104, 116, 22);
		add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(302, 139, 116, 22);
		add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(302, 174, 116, 22);
		add(textField_6);
		
		JLabel label = new JLabel("Adresse:");
		label.setBounds(239, 177, 56, 16);
		add(label);
		
		JLabel label_1 = new JLabel("Email:");
		label_1.setBounds(247, 145, 56, 16);
		add(label_1);
		
		JLabel label_2 = new JLabel("Telefon:");
		label_2.setBounds(237, 107, 56, 16);
		add(label_2);
		
		JLabel label_3 = new JLabel("Navn:");
		label_3.setBounds(32, 107, 56, 16);
		add(label_3);
		
		JLabel label_4 = new JLabel("ID:");
		label_4.setBounds(42, 142, 56, 16);
		add(label_4);
		
		JLabel label_5 = new JLabel("Gruppe:");
		label_5.setBounds(32, 180, 56, 16);
		add(label_5);
		
		JLabel lblKunde = new JLabel("Kunde");
		lblKunde.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKunde.setBounds(216, 13, 79, 32);
		add(lblKunde);

	}
}
