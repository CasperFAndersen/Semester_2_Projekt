package ui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JTable;

public class MakeProduct extends JPanel {
	private JTextField txtSgModel;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public MakeProduct() {
		setLayout(null);
		
		txtSgModel = new JTextField();
		txtSgModel.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txtSgModel.setText("s\u00F8g model");
		txtSgModel.setBounds(124, 67, 165, 30);
		add(txtSgModel);
		txtSgModel.setColumns(10);
		
		JLabel lblModel = new JLabel("Model:");
		lblModel.setBounds(60, 74, 56, 16);
		add(lblModel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 155, 162, 204);
		add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(327, 150, 165, 30);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(327, 329, 165, 30);
		add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(327, 277, 165, 30);
		add(comboBox_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(60, 411, 441, 69);
		add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		JLabel lblModuler = new JLabel("Moduler:");
		lblModuler.setBounds(60, 133, 56, 16);
		add(lblModuler);
		
		JLabel lblModultype = new JLabel("Modultype:");
		lblModultype.setBounds(327, 133, 103, 16);
		add(lblModultype);
		
		JLabel lblFarve = new JLabel("Farve:");
		lblFarve.setBounds(327, 311, 56, 16);
		add(lblFarve);
		
		JLabel lblMateriale = new JLabel("Materiale");
		lblMateriale.setBounds(327, 260, 56, 16);
		add(lblMateriale);
		
		JLabel lblValgteModuler = new JLabel("Valgte moduler:");
		lblValgteModuler.setBounds(60, 382, 162, 16);
		add(lblValgteModuler);
		
		JLabel lblSammenstVare = new JLabel("Sammens\u00E6t vare");
		lblSammenstVare.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSammenstVare.setBounds(222, 22, 230, 32);
		add(lblSammenstVare);

	}
}
