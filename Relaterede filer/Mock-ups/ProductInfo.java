package ui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class ProductInfo extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;

	/**
	 * Create the panel.
	 */
	public ProductInfo() {
		setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(127, 146, 116, 22);
		add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(127, 254, 116, 22);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(127, 181, 116, 22);
		add(textField_2);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(127, 219, 116, 22);
		add(textField_5);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(58, 222, 56, 16);
		add(lblType);
		
		JLabel lblBeskrivelse = new JLabel("Beskrivelse:");
		lblBeskrivelse.setBounds(58, 184, 85, 16);
		add(lblBeskrivelse);
		
		JLabel lblVarenr = new JLabel("VareNr.");
		lblVarenr.setBounds(58, 149, 56, 16);
		add(lblVarenr);
		
		JLabel lblLeverandr = new JLabel("Leverand\u00F8r:");
		lblLeverandr.setBounds(36, 257, 85, 16);
		add(lblLeverandr);
		
		JLabel lblVare = new JLabel("Vare");
		lblVare.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVare.setBounds(127, 31, 79, 32);
		add(lblVare);

	}

}
