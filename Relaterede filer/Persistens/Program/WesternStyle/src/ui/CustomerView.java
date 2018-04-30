package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomerView extends JPanel {

	/**
	 * Create the panel.
	 */
	public CustomerView() {

		JLabel lblBgs = new JLabel("label");
		add(lblBgs);
	}

	public JPanel getPanel() {
		return this;
	}

}
