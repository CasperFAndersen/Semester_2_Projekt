package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import controller.PersonCtrl;
import controller.SalesOrderCtrl;
import database.DBConnection;
import model.Customer;
import model.Product;
import model.SalesOrder;
import model.SalesOrderLine;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * SmartOrder
 * CreateSalesOrderView.java
 * Purpose: The view for creating a sales order. At the moment this view is the most central view of the program
 * @author Gruppe 1
 * @version 1.0 
 */
public class CreateSalesOrderView extends JFrame {
	private static final long serialVersionUID = 1L;
	private static CreateSalesOrderView frame;
	private JTextField txtDate;
	private JTextField txtSearchForCustomer;
	private JTable tblOrderLine;
	private JTextField txtTotal;
	private PersonCtrl personCtrl = new PersonCtrl();
	private CustomerPanel panelCustomer;
	private ProductInfoPanel productInfoPanel;
	private JScrollPane scrollPane;
	private TableModelSalesOrderLine tableModelOrderline = new TableModelSalesOrderLine();
	private SalesOrderCtrl salesOrderCtrl = new SalesOrderCtrl();
	private DropDownList<Customer> customerList;
	private static JLabel lblOnline;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new CreateSalesOrderView();
					frame.setVisible(true);
					startConnectionStatus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected static void startConnectionStatus() {
		Thread t1 = new Thread(() -> {		
			while (true) {
				try {
					if (DBConnection.getInstance().getConnection().isValid(0)) {
						setConnectionLabel("Online");
						lblOnline.setForeground(new Color(0, 128, 0));
					} else {
						setConnectionLabel("Offline");
						lblOnline.setForeground(new Color(255, 0, 0));	
						DBConnection.getInstance();
					}
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
	}

	/**
	 * Create the frame.
	 */
	public CreateSalesOrderView() {
		salesOrderCtrl.createSalesOrder();

		setTitle("Opret ordre\r\n");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.setSize(1400, 950);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		panelCustomer = new CustomerPanel();
		panelCustomer.makeTextFieldsEditable(false);
		panelCustomer.setBounds(26, 276, 295, 249);
		panelCustomer.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCustomer.typeAsComboBox(false);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(419, 42, 850, 483);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		tabbedPane.setBorder(null);

		JPanel panelOrderInfo = new JPanel();
		panelOrderInfo.setBounds(26, 65, 295, 120);
		panelOrderInfo.setBorder(new LineBorder(new Color(0, 0, 0)));

		txtSearchForCustomer = new JTextField();
		txtSearchForCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSearchForCustomer.setText("");
			}
		});

		txtSearchForCustomer.setBounds(26, 238, 295, 31);
		txtSearchForCustomer.setFont(new Font("Tahoma", Font.ITALIC, 12));
		txtSearchForCustomer.setText("S\u00F8g kunde via navn"); 
		txtSearchForCustomer.setColumns(10);

		scrollPane = new JScrollPane();

		scrollPane.setBounds(26, 607, 1243, 191);

		JLabel lblOrderLines = new JLabel("Ordrelinjer");
		lblOrderLines.setBounds(589, 583, 74, 17);
		lblOrderLines.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblCustomer = new JLabel("Kunde");
		lblCustomer.setBounds(138, 203, 45, 17);
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 14));

		JButton btnCancel = new JButton("Tilbage");
		btnCancel.addActionListener((x)-> {LoginView.getInstance().goBack();});
		btnCancel.setBounds(1180, 860, 89, 23);

		JButton btnCreateOrder = new JButton("Opret ordre");
		btnCreateOrder.addActionListener((e) -> { closeOrder();});
		btnCreateOrder.setBounds(142, 860, 111, 25);

		JButton btnSaveOrder = new JButton("Opret tilbud");
		btnSaveOrder.addActionListener((e) -> {saveAsOffer();});
		btnSaveOrder.setBounds(26, 860, 104, 25);

		txtTotal = new JTextField();
		txtTotal.setBounds(1114, 805, 155, 22);
		txtTotal.setColumns(10);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(1011, 805, 73, 20);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton btnRemove = new JButton("Fjern vare fra ordre");
		btnRemove.addActionListener((e)-> { removeSalesOrderLine(); } );
		btnRemove.setBounds(26, 811, 171, 27);
		btnRemove.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnRemove.setIcon(new ImageIcon(CreateSalesOrderView.class.getResource("/mockups/redx.png")));

		JLabel lblOrderInfo = new JLabel("Ordreinfo");
		lblOrderInfo.setBounds(26, 18, 295, 17);
		lblOrderInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderInfo.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblAddProduct = new JLabel("Tilf\u00F8j vare");
		lblAddProduct.setBounds(419, 18, 850, 17);
		lblAddProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddProduct.setFont(new Font("Tahoma", Font.BOLD, 14));

		tblOrderLine = new JTable();
		tblOrderLine.setModel(tableModelOrderline);
		//Set salesOrderCtrl object in tablemodel.
		tableModelOrderline.setSalesOrderCtrl(salesOrderCtrl);
		tblOrderLine.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					updateTxtTotal();
				}
			}
		});

		scrollPane.setViewportView(tblOrderLine);
		panelOrderInfo.setLayout(null);

		JLabel lblDato = new JLabel("Dato:\r\n");
		lblDato.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDato.setBounds(45, 48, 56, 16);
		panelOrderInfo.add(lblDato);

		txtDate = new JTextField();
		txtDate.setText(LocalDate.now().toString());
		txtDate.setColumns(10);
		txtDate.setBounds(128, 45, 116, 22);
		panelOrderInfo.add(txtDate);

		productInfoPanel = new ProductInfoPanel(this);
		productInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("Hyldevare", null, productInfoPanel, null);

		JPanel panel = new MakeProductPanel(this);
		tabbedPane.addTab("Specialdesign", null, panel, null);
		getContentPane().setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(26, 282, 296, 258);
		getContentPane().add(scrollPane_1);

		customerList = new DropDownList<Customer>(txtSearchForCustomer, scrollPane_1, salesOrderCtrl.findAllCustomers(), new CustomerListAdaptor());
		scrollPane_1.setViewportView(customerList);

		customerList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setCustomer();
			}
		});

		txtSearchForCustomer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					setCustomer();
				}
			}
		});

		getContentPane().add(lblOrderLines);
		getContentPane().add(btnRemove);
		getContentPane().add(lblTotal);
		getContentPane().add(txtTotal);
		getContentPane().add(scrollPane);
		getContentPane().add(btnSaveOrder);
		getContentPane().add(btnCreateOrder);
		getContentPane().add(btnCancel);
		getContentPane().add(lblOrderInfo);
		getContentPane().add(panelOrderInfo);

		lblOnline = new JLabel("Online");
		lblOnline.setForeground(new Color(0, 128, 0));
		lblOnline.setBounds(128, 77, 152, 30);
		panelOrderInfo.add(lblOnline);

		JLabel lblConnection = new JLabel("Forbindelse:");
		lblConnection.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblConnection.setBounds(45, 84, 91, 16);
		panelOrderInfo.add(lblConnection);
		getContentPane().add(txtSearchForCustomer);
		getContentPane().add(panelCustomer);
		getContentPane().add(lblCustomer);
		getContentPane().add(lblAddProduct);
		getContentPane().add(tabbedPane);
		
		JButton btnNewCustomer = new JButton("Ny kunde");
		btnNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewCustomerClicked();
			}
		});
		btnNewCustomer.setBounds(225, 526, 97, 25);
		getContentPane().add(btnNewCustomer);
	}

	protected void btnNewCustomerClicked() {
		CreateCustomerView ccv = new CreateCustomerView();
		ccv.showAsPopup(true);
		ccv.setCSOV(this);
		ccv.setVisible(true);
		
	}

	private void saveAsOffer() {
		if (salesOrderCtrl.getOrder().getCustomer() == null) {
			new JOptionPane().showMessageDialog(this, "Der er ikke tilføjet en kunde til ordren.");
		} else if (salesOrderCtrl.getOrder().getSalesOrderLines().size() == 0) {
			new JOptionPane().showMessageDialog(this, "Der er ikke tilføjet nogen vare til ordren.");
		} else {
			salesOrderCtrl.getOrder().setDatePlaced(LocalDate.now());
			salesOrderCtrl.getOrder().addEmployeeToSalesOrder(LoginView.getInstance().getLoggedInEmployee());
			DueDateInput ddi = new DueDateInput();
			ddi.setVisible(true);
			ddi.setCSOV(this);
		}
	}

	protected void removeSalesOrderLine() {
		if (tblOrderLine.getSelectedRow() != -1) {
			tableModelOrderline.removeElement(tblOrderLine.getSelectedRow());
			updateTxtTotal();
		} else {
			new JOptionPane().showMessageDialog(getContentPane(), "Ingen ordrelinjer valgt.");
		}
	}

	protected void setCustomer() {
		Customer c = customerList.getSelectedObject(txtSearchForCustomer.getText());
		if (c != null) {
			panelCustomer.fillTxtfields(c);
			txtSearchForCustomer.setText("");
			salesOrderCtrl.setCustomer(c);
			txtSearchForCustomer.setBorder(new LineBorder(Color.LIGHT_GRAY));;
		} else {
			new JOptionPane().showMessageDialog(getContentPane(), "Ugyldigt kundenavn");
			txtSearchForCustomer.setText("");
			txtSearchForCustomer.setBorder(new LineBorder(Color.RED));
		}
	}
	
	protected void setCustomer(Customer c) {
		customerList.setSearchElements(personCtrl.findAllCustomers());
		if (c != null) {
			txtSearchForCustomer.setText(c.getName() + " "+ c.getPhone());
			panelCustomer.fillTxtfields(c);
			txtSearchForCustomer.setText("");
			salesOrderCtrl.setCustomer(c);
			txtSearchForCustomer.setBorder(new LineBorder(Color.LIGHT_GRAY));;
		} else {
			new JOptionPane().showMessageDialog(getContentPane(), "Ugyldigt kundenavn");
			txtSearchForCustomer.setText("");
			txtSearchForCustomer.setBorder(new LineBorder(Color.RED));
		}
	}

	protected void closeOrder() {
		if (salesOrderCtrl.getOrder().getCustomer() == null) {
			new JOptionPane().showMessageDialog(this, "Der er ikke tilføjet en kunde til ordren.");
		} else if (salesOrderCtrl.getOrder().getSalesOrderLines().size() == 0) {
			new JOptionPane().showMessageDialog(this, "Der er ingen varer tilføjet til ordren.");
		} else {
			try {
				salesOrderCtrl.getOrder().setDatePlaced(LocalDate.now());
				salesOrderCtrl.setEmployee(LoginView.getInstance().getLoggedInEmployee());
				salesOrderCtrl.closeSalesOrder();
				reset();
				new JOptionPane().showMessageDialog(this, "Ordren er gemt i databasen og kunden er sendt en faktura.");
			} catch (SQLException e) {
				new JOptionPane().showMessageDialog(this, "Ordren blev ikke gemt korrekt i databasen.");
			}
		}
	}

	private void reset() {
		panelCustomer.clearTxtFields();
		tableModelOrderline.setData(new ArrayList<SalesOrderLine>());
		
		salesOrderCtrl.createSalesOrder();
	}

	protected void updateTxtTotal() {
		txtTotal.setText(String.valueOf(salesOrderCtrl.getOrder().getTotalPrice()));
	}

	public SalesOrderCtrl getSalesOrderCtrl(){
		return salesOrderCtrl;
	}

	protected void addSalesOrderLine(Product p, int amount) {
		SalesOrder so = salesOrderCtrl.getOrder();
		so.addSalesOrderLineToSalesOrder(p, amount);
		tableModelOrderline.setData(so.getSalesOrderLines());
		updateTxtTotal();
	}

	public static void setConnectionLabel(String text){
		SwingUtilities.invokeLater(
				() -> lblOnline.setText(text)
				);
	}

	public void setDueDate(LocalDate selectedDate) {
		try {
			salesOrderCtrl.saveAsOffer(selectedDate);
		} catch (SQLException e) {
			new JOptionPane().showMessageDialog(this, "Tilbuddet blev ikke gemt korrekt i databasen");
		}
	}
}
