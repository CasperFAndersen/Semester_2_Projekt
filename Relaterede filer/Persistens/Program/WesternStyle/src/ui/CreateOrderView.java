package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import controller.CustomerCtrl;
import controller.ProductCtrl;
import controller.SalesOrderCtrl;
import model.Customer;
import model.Product;
import model.SalesOrderLine;

public class CreateOrderView extends JFrame {

	private JPanel contentPane;
	private SalesOrderCtrl SOCtrl = new SalesOrderCtrl();
	private JTextField txtSgKunde;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextField txtAdresse;
	private JTextField txtGruppe;
	private JTextField txtDate;
	private JTextField txtSentDate;
	private JTextField txtDueDate;
	private JTextField txtAcceptDate;
	private JLabel lblAccepteret;
	private JLabel lblGlderTil;
	private JLabel lblSendt;
	private JTextField txtVareId;
	private JTextField txtAntalLager;
	private JLabel lblVareid;
	private JLabel lblAntalPLager;
	private JLabel lblNewLabel;
	private JTextField txtBeskrivelse;
	private JTextField txtAntal;
	private JLabel lblAntal;
	private JTable table;
	private ArrayList<Customer> customers;
	private JTextField txtNavn;
	private JTextField txtSgVare;
	private ArrayList<Product> products;
	private SalesOrderTableModel tableModel =  new SalesOrderTableModel();
	private JTextField txtId;
	private JComboBox statusBox;
	private JLabel lblVare;
	private JLabel lblKunde;
	private JLabel lblTotal;
	private JTextField txtTotal;
	private JTextField txtStkPris;
	private JLabel lblStkPris;
	private JTextField textField;
	private JLabel lblAnsat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateOrderView frame = new CreateOrderView();
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
	public CreateOrderView() {
		setTitle("Western Style Ltd.");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreateOrderView.class.getResource("/images/919c07bc7e3a7aa06d6b217516a38504.jpg")));
		//Creates a new SalesOrder-object and stores it in the controller
		SOCtrl.createOrder();
		//Setup discount rules
		SOCtrl.setDiscount(1);
		//Creates List's to load the auto-completion-lists
		customers = SOCtrl.findAllCustomers();
		products = SOCtrl.findAllProducts();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1111, 900);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenuItem mntmMenu_1 = new JMenuItem("Vis ordrer");
		mntmMenu_1.setBackground(new Color(220, 220, 220));
		mntmMenu_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mntmMenu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(new FindSalesOrderView());
				revalidate();
			}
		});

		JMenuItem mntmMenu = new JMenuItem("Opret ordre");
		mntmMenu.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mntmMenu.setBackground(new Color(220, 220, 220));
		mntmMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(contentPane);
				revalidate();
			}
		});
		menuBar.add(mntmMenu);
		menuBar.add(mntmMenu_1);
		
		JMenuItem mntmVisVare = new JMenuItem("Vis varer");
		mntmVisVare.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mntmVisVare.setBackground(new Color(220, 220, 220));
		mntmVisVare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setContentPane(new ProductView());
				revalidate();
			}
		});
		menuBar.add(mntmVisVare);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
				txtSgKunde = new JTextField();
				txtSgKunde.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						txtSgKunde.setText("");
					}
				});
				txtSgKunde.setFont(new Font("Tahoma", Font.ITALIC, 13));
				txtSgKunde.setBorder(new LineBorder(new Color(171, 173, 179), 3, true));
				txtSgKunde.setBackground(Color.WHITE);
				txtSgKunde.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						autoTextCustomer();
					}
				});
				txtSgKunde.setText("S\u00F8g kunde via navn");
				txtSgKunde.setBounds(691, 79, 317, 33);
				contentPane.add(txtSgKunde);
				txtSgKunde.setColumns(10);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(691, 113, 317, 120);
		contentPane.add(scrollPane_1);

		DropDownList<Customer> listCustomer = new DropDownList<Customer>(txtSgKunde, scrollPane_1, customers, new CustomerAdaptor());
		

		listCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				autoTextCustomer();
			}
		});

		txtSgVare = new JTextField();
		txtSgVare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSgVare.setText("");
			}
		});
		txtSgVare.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txtSgVare.setBorder(new LineBorder(new Color(171, 173, 179), 3, true));
		txtSgVare.setBounds(44, 300, 243, 33);
		contentPane.add(txtSgVare);
		txtSgVare.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				autoTextProduct();
			}
		});
		txtSgVare.setText("S\u00F8g vare via varenr");
		txtSgVare.setColumns(10);

		JScrollPane scrollPaneVare = new JScrollPane();
		scrollPaneVare.setBounds(44, 335, 243, 156);
		contentPane.add(scrollPaneVare);


		JLabel lblDato = new JLabel("Dato:");
		lblDato.setBounds(73, 57, 56, 16);
		contentPane.add(lblDato);

		String[] conditions ={"Order","Offer"};



		JLabel lblNavn = new JLabel("Navn:");
		lblNavn.setBounds(622, 128, 56, 16);
		contentPane.add(lblNavn);

		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setBounds(827, 128, 56, 16);
		contentPane.add(lblTelefon);

		txtPhone = new JTextField();
		txtPhone.setBounds(892, 125, 116, 22);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);

		txtDate = new JTextField();
		txtDate.setBounds(171, 54, 116, 22);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		txtDate.setText(LocalDate.now().toString());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 477, 985, 241);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tableModel);

		JButton btnAfslutOrdre = new JButton("Afslut ordre");
		btnAfslutOrdre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					afslutOrder();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnAfslutOrdre.setBounds(909, 759, 116, 25);
		contentPane.add(btnAfslutOrdre);
		
		DropDownList<Product> listProduct = new DropDownList<Product>(txtSgVare, scrollPaneVare, products, new ProductAdaptor());
		
		listProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				autoTextProduct();
			}
		});

		txtNavn = new JTextField();
		txtNavn.setColumns(10);
		txtNavn.setBounds(691, 125, 116, 22);
		contentPane.add(txtNavn);

		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(691, 160, 116, 22);
		contentPane.add(txtId);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(632, 163, 56, 16);
		contentPane.add(lblId);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(44, 34, 544, 174);
		contentPane.add(panel);
		panel.setLayout(null);
		statusBox = new JComboBox(conditions);
		statusBox.setBounds(128, 115, 119, 22);
		panel.add(statusBox);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(27, 118, 56, 16);
		panel.add(lblStatus);

		txtDueDate = new JTextField();
		txtDueDate.setEditable(false);
		txtDueDate.setVisible(false);
		txtDueDate.setBounds(398, 68, 116, 22);
		panel.add(txtDueDate);
		txtDueDate.setColumns(10);

		txtAcceptDate = new JTextField();
		txtAcceptDate.setEditable(false);
		txtAcceptDate.setVisible(false);
		txtAcceptDate.setBounds(398, 115, 116, 22);
		panel.add(txtAcceptDate);
		txtAcceptDate.setColumns(10);

		txtSentDate = new JTextField();
		txtSentDate.setEditable(false);
		txtSentDate.setFocusable(false);
		txtSentDate.setVisible(false);
		txtSentDate.setBounds(398, 25, 116, 22);
		panel.add(txtSentDate);
		txtSentDate.setColumns(10);

		lblAccepteret = new JLabel("Accepteret:");
		lblAccepteret.setVisible(false);
		lblAccepteret.setBounds(305, 118, 81, 16);
		panel.add(lblAccepteret);

		lblGlderTil = new JLabel("G\u00E6lder til:");
		lblGlderTil.setVisible(false);
		lblGlderTil.setBounds(305, 71, 81, 16);
		panel.add(lblGlderTil);

		lblSendt = new JLabel("Sendt:");
		lblSendt.setVisible(false);
		lblSendt.setBounds(330, 28, 56, 16);
		panel.add(lblSendt);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("4");
		textField.setBounds(128, 68, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		lblAnsat = new JLabel("Ansatid:");
		lblAnsat.setBounds(27, 71, 56, 16);
		panel.add(lblAnsat);
		statusBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(statusBox.getSelectedItem().equals("Offer")){
					txtSentDate.setVisible(true);
					txtDueDate.setVisible(true);
					txtAcceptDate.setVisible(true);
					lblSendt.setVisible(true);
					lblGlderTil.setVisible(true);
					lblAccepteret.setVisible(true);
					txtSentDate.setText(LocalDate.now().toString());
					txtDueDate.setText(LocalDate.now().plusDays(14).toString());
					txtAcceptDate.setText("For another use case");
				}
				else{
					txtSentDate.setVisible(false);
					txtDueDate.setVisible(false);
					txtAcceptDate.setVisible(false);
					lblSendt.setVisible(false);
					lblGlderTil.setVisible(false);
					lblAccepteret.setVisible(false);
				}
			}
		});

		txtGruppe = new JTextField();
		txtGruppe.setBounds(691, 198, 116, 22);
		contentPane.add(txtGruppe);
		txtGruppe.setColumns(10);

		txtAdresse = new JTextField();
		txtAdresse.setBounds(892, 195, 116, 22);
		contentPane.add(txtAdresse);
		txtAdresse.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(892, 160, 116, 22);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblGruppe = new JLabel("Gruppe:");
		lblGruppe.setBounds(622, 201, 56, 16);
		contentPane.add(lblGruppe);

		JLabel lblAdresse = new JLabel("Adresse:");
		lblAdresse.setBounds(829, 198, 56, 16);
		contentPane.add(lblAdresse);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(837, 166, 56, 16);
		contentPane.add(lblEmail);

		txtBeskrivelse = new JTextField();
		txtBeskrivelse.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.GRAY, null));
		txtBeskrivelse.setFont(new Font("Century Gothic", Font.BOLD, 14));
		txtBeskrivelse.setBounds(396, 359, 192, 80);
		contentPane.add(txtBeskrivelse);
		txtBeskrivelse.setColumns(10);

		txtAntal = new JTextField();
		txtAntal.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
		txtAntal.setFont(new Font("Century Gothic", Font.BOLD, 24));
		txtAntal.setBounds(661, 359, 98, 80);
		contentPane.add(txtAntal);
		txtAntal.setColumns(10);

		lblNewLabel = new JLabel("Beskrivelse:");
		lblNewLabel.setBounds(396, 337, 92, 16);
		contentPane.add(lblNewLabel);

		lblAntal = new JLabel("Antal:");
		lblAntal.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblAntal.setBounds(661, 335, 56, 16);
		contentPane.add(lblAntal);

		JButton btnTilfjVare = new JButton("Tilf\u00F8j vare");
		btnTilfjVare.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, Color.LIGHT_GRAY));
		btnTilfjVare.setForeground(Color.BLACK);
		btnTilfjVare.setBackground(Color.LIGHT_GRAY);
		btnTilfjVare.setFont(new Font("Century Gothic", Font.BOLD, 16));
		btnTilfjVare.setBounds(845, 357, 184, 81);
		contentPane.add(btnTilfjVare);

		lblVareid = new JLabel("Vareid:");
		lblVareid.setBounds(44, 362, 56, 16);
		contentPane.add(lblVareid);

		txtVareId = new JTextField();
		txtVareId.setBounds(172, 359, 116, 22);
		contentPane.add(txtVareId);
		txtVareId.setColumns(10);

		txtAntalLager = new JTextField();
		txtAntalLager.setBounds(172, 417, 116, 22);
		contentPane.add(txtAntalLager);
		txtAntalLager.setColumns(10);

		lblAntalPLager = new JLabel("Antal p\u00E5 lager:");
		lblAntalPLager.setBounds(44, 420, 104, 16);
		contentPane.add(lblAntalPLager);
		
		lblKunde = new JLabel("Kunde");
		lblKunde.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblKunde.setBounds(643, 34, 141, 33);
		contentPane.add(lblKunde);
		
		lblVare = new JLabel("Tilf\u00F8j vare");
		lblVare.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblVare.setBounds(73, 244, 141, 33);
		contentPane.add(lblVare);

		JLabel label = new JLabel("");
		label.setForeground(Color.WHITE);
		label.setIcon(new ImageIcon(CreateOrderView.class.getResource("/images/OrangeBar.png")));
		label.setBounds(44, 244, 985, 33);
		contentPane.add(label);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(CreateOrderView.class.getResource("/images/OrangeBar.png")));
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(622, 34, 407, 33);
		contentPane.add(label_1);
		
		lblTotal = new JLabel("TOTAL:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotal.setBounds(44, 750, 73, 40);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(124, 760, 116, 22);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		txtStkPris = new JTextField();
		txtStkPris.setColumns(10);
		txtStkPris.setBounds(171, 388, 116, 22);
		contentPane.add(txtStkPris);
		
		lblStkPris = new JLabel("Stk. pris:");
		lblStkPris.setBounds(44, 391, 104, 16);
		contentPane.add(lblStkPris);
		
		//Set default focus
		txtSgKunde.requestFocus();
		
		btnTilfjVare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					addOrderLine();
				} catch (Exception e) {
					JOptionPane.showInternalMessageDialog(contentPane, "Bruger, vare og antal bedes udfyldt korrekt... Tak!",
							"Yo Mama!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	protected void afslutOrder() throws Exception {
		SOCtrl.setCustomer(findSelectedCustomer());
		SOCtrl.setEmployee(4);
		SOCtrl.setDiscount(1);
		if(statusBox.getSelectedItem().equals("Offer")){
			SOCtrl.createOffer();
			SOCtrl.setOrderCondition("Offer");
		}
		else{
			SOCtrl.createOrder();
			SOCtrl.setOrderCondition("Order");
		}
		SOCtrl.closeSalesOrder();
		clearAllTxtFields();
		tableModel.setData(new ArrayList<SalesOrderLine>());
		updateProducts();
	}

	private void updateProducts() throws SQLException {
		ProductCtrl prodCtrl = new ProductCtrl();
		ArrayList<SalesOrderLine> sol = SOCtrl.getAllSalesOrderLines();
		for(SalesOrderLine s: sol){
			prodCtrl.updateProductAmount(s.getProduct().getId(), s.getAmount());
		}
	}

	private void clearAllTxtFields() {
		txtAcceptDate.setText("");
		txtAdresse.setText("");
		txtAntal.setText("");
		txtAntalLager.setText("");
		txtBeskrivelse.setText("");
		txtDueDate.setText("");
		txtEmail.setText("");
		txtGruppe.setText("");
		txtId.setText("");
		txtNavn.setText("");
		txtPhone.setText("");
		txtSentDate.setText("");
		txtSgKunde.setText("");
		txtSgVare.setText("");
		txtVareId.setText("");
		txtTotal.setText("");
	}

	protected void addOrderLine() {
		SOCtrl.setCustomer(findSelectedCustomer());
		SOCtrl.addProductToSalesOrder(findSelectedProduct(), Integer.parseInt(txtAntal.getText()));
		txtTotal.setText(String.valueOf(SOCtrl.getTotalPriceAfterDiscount()));
		tableModel.setData(SOCtrl.getAllSalesOrderLines());
		txtAntal.setText("");
		txtVareId.setText("");
		txtStkPris.setText("");
		txtBeskrivelse.setText("");
		txtAntalLager.setText("");
		txtSgVare.requestFocus();
	}

	protected void autoTextProduct() {
		String s = txtSgVare.getText();
		if(s.length() != 0){
			for (Product curr: products){
				if(curr.getName().equals(s)){
					txtVareId.setText(String.valueOf(curr.getId()));
					txtAntalLager.setText(String.valueOf(curr.getAmount()));
					txtBeskrivelse.setText(curr.getName());
					txtSgVare.setText("");
					txtAntal.requestFocus();
					txtStkPris.setText(String.valueOf(curr.getSalesPrice()));
				}
			}
		}
	}

	protected void autoTextCustomer() {
		String s = txtSgKunde.getText();
		if(s.length() != 0){
			for (Customer curr: customers){
				if(curr.getName().equals(s)){
					txtNavn.setText(curr.getName());
					txtId.setText(String.valueOf(curr.getId()));
					txtAdresse.setText(curr.getAddress());
					txtPhone.setText(curr.getPhone());
					txtEmail.setText(curr.getEmail());
					txtGruppe.setText(curr.getType());
					txtSgKunde.setText(curr.getName());
					txtSgKunde.setText("");
					txtSgVare.requestFocus();
					txtSgVare.selectAll();
				}
			}
		}
	}
	
	private Customer findSelectedCustomer(){
		for(Customer c : customers){
			if(c.getId() == (Integer.parseInt(txtId.getText()))){
				return c;
			}
		}
		return null;
	}
	
	private Product findSelectedProduct(){
		for(Product p : products){
			if(p.getId() == Integer.parseInt(txtVareId.getText())){
				return p;
			}
		}
		return null;
	}

	public void callAllMethods() {
		SOCtrl.createSalesOrder();
		SOCtrl.closeSalesOrder();
	}
}
