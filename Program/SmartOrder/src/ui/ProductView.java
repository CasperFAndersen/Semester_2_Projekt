package ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

/**
 * SmartOrder
 * ProductView.java
 * Purpose: Show the different actions an employee can do with a product and producttype
 * @author Gruppe 1
 * @version 1.0 
 */
public class ProductView extends JPanel {
	/**
	 * Create the panel.
	 */
	public ProductView() {
		setLayout(null);
		
		JButton btnCreateProduct = new JButton("Opret vare");
		btnCreateProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCreateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createProductClicked();
			}
		});
		btnCreateProduct.setBounds(256, 274, 256, 256);
		add(btnCreateProduct);

		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goBackToLastMenu();
			}
		});
		btnBack.setBounds(1218, 821, 89, 23);
		add(btnBack);
		
		JButton btnOpretProducttype = new JButton("Opret produkttype");
		btnOpretProducttype.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnOpretProducttype.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createProductTypeClicked();
			}
		});
		btnOpretProducttype.setBounds(583, 274, 256, 256);
		add(btnOpretProducttype);
		
		JLabel lblProduktmenu = new JLabel("Produktmenu");
		lblProduktmenu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProduktmenu.setBounds(646, 73, 164, 64);
		add(lblProduktmenu);
	}

	protected void createProductTypeClicked() {
		CreateProductTypeView cptv = new CreateProductTypeView();
		cptv.isPopup(false);
		LoginView.getInstance().activateMiddlePane((JPanel) (cptv.getContentPane()), getContentPane());
		//LoginView.getInstance().activateContentPane((JPanel) cptv.getContentPane(), getContentPane());
		
	}

	protected void goBackToLastMenu() {
		LoginView.getInstance().goBack();
	}

	private void createProductClicked() {
		CreateProductView cpv = new CreateProductView();
		LoginView.getInstance().activateMiddlePane((JPanel) cpv, getContentPane());
		//LoginView.getInstance().activateContentPane(cpv, this);
	}

	public JPanel getContentPane() {
		return this;
	}

}
