package mockups;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * SmartOrder
 * filename.java
 * Purpose: 
 * @author Gruppe 1
 * @version 1.0 
 */
public class ProductInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtFiltrerVare;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public ProductInfoPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ProductInfoPanel.class.getResource("/mockups/stol2.jpg")));
		lblNewLabel.setBounds(650, 67, 106, 123);
		add(lblNewLabel);
		
		JLabel lblDescription = new JLabel("Beskrivelse:");
		lblDescription.setBounds(636, 192, 85, 16);
		add(lblDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 67, 533, 353);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Varenr", "Beskrivelse", "Pris per styk", "Antal p\u00E5 lager"
			}
		));
		scrollPane.setViewportView(table);
		
		txtFiltrerVare = new JTextField();
		txtFiltrerVare.setFont(new Font("Tahoma", Font.ITALIC, 12));
		txtFiltrerVare.setText("Filtrer vare");
		txtFiltrerVare.setBounds(39, 43, 201, 22);
		add(txtFiltrerVare);
		txtFiltrerVare.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(430, 42, 142, 22);
		add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(636, 210, 178, 76);
		add(textField);
		textField.setColumns(10);
		
		JButton btnTilfjVare = new JButton("Tilføj produkt til ordre");
		btnTilfjVare.setForeground(Color.BLACK);
		btnTilfjVare.setBackground(new Color(255, 165, 0));
		btnTilfjVare.setBounds(636, 395, 179, 25);
		add(btnTilfjVare);
		
		textField_1 = new JTextField();
		textField_1.setBounds(636, 327, 178, 36);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAntal = new JLabel("Antal:");
		lblAntal.setBounds(636, 310, 56, 16);
		add(lblAntal);

	}
}
