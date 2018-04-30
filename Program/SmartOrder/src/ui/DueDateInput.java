package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * SmartOrder
 * DueDateInput.java
 * Purpose: Show a dialog where the user can input a date
 * @author Gruppe 1
 * @version 1.0 
 */
public class DueDateInput extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private LocalDate today = LocalDate.now();
	private JComboBox<Integer> yearBox;
	private JComboBox<Integer> monthBox;
	private JComboBox<Integer> dayBox;
	private int[] numOfDaysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 30 , 31};
	private CreateSalesOrderView csov;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DueDateInput dialog = new DueDateInput();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DueDateInput() {
		setTitle("Gem som tilbud");
		setBounds(100, 100, 450, 232);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblLines = new JLabel("--");
		lblLines.setBounds(153, 106, 10, 16);
		contentPanel.add(lblLines);

		JLabel lblMoreLines = new JLabel("--");
		lblMoreLines.setBounds(236, 106, 10, 16);
		contentPanel.add(lblMoreLines);

		JLabel lblYear = new JLabel("YYYY");
		lblYear.setBounds(87, 85, 56, 16);
		contentPanel.add(lblYear);

		JLabel lblMonth = new JLabel("MM");
		lblMonth.setBounds(185, 85, 56, 16);
		contentPanel.add(lblMonth);

		JLabel lblDate = new JLabel("DD");
		lblDate.setBounds(269, 85, 56, 16);
		contentPanel.add(lblDate);
		JLabel lblEnterDueDate = new JLabel("Indtast forfaldsdato:");
		lblEnterDueDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterDueDate.setBounds(66, 26, 205, 16);
		contentPanel.add(lblEnterDueDate);

		yearBox = new JComboBox();
		yearBox.setBounds(56, 103, 85, 22);
		contentPanel.add(yearBox);
		loadYearBox();
		yearBox.addActionListener((e)->{loadMonthBox();});

		monthBox = new JComboBox();
		monthBox.setBounds(175, 103, 49, 22);
		contentPanel.add(monthBox);
		loadMonthBox();
		monthBox.addActionListener((e)->{loadDayBox();});

		dayBox = new JComboBox();
		dayBox.setBounds(256, 103, 49, 22);
		contentPanel.add(dayBox);
		loadDayBox();

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAccept = new JButton("OK");
				btnAccept.addActionListener((e)->{csov.setDueDate(getSelectedDate());
				this.dispose();});
				btnAccept.setActionCommand("OK");
				buttonPane.add(btnAccept);
				getRootPane().setDefaultButton(btnAccept);
			}
			{
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setActionCommand("Cancel");
				btnCancel.addActionListener((e)-> { this.dispose(); } );
				buttonPane.add(btnCancel);
			}
		}
	}

	private LocalDate getSelectedDate() {
		String selectedDate = String.valueOf(yearBox.getSelectedItem())+"-"+
				String.format("%02d",monthBox.getSelectedItem())+"-"+
				String.format("%02d",dayBox.getSelectedItem());
		return LocalDate.parse(selectedDate);
	}

	/**
	 * load the yearBox with selection options spanning from current year to + 25 
	 */
	public void loadYearBox() {
		int thisYear = today.getYear();
		yearBox.addItem(thisYear);
		for (int i = 0; i < 25; i++) {
			yearBox.addItem(thisYear += 1);
		}
	}

	/**
	 * load the monthBox. If current year is selected it load the remaining months of the year. Else all twelve months. 
	 */
	public void loadMonthBox() {
		monthBox.removeAllItems();
		int thisMonth = today.getMonthValue();
		if ((int)yearBox.getSelectedItem() == today.getYear()) {
			for(int i = thisMonth; i <= 12; i++){
				monthBox.addItem(i);
			}
		} else {
			for (int i = 1; i <= 12; i++) {
				monthBox.addItem(i);
			}
		}

	}
	/**
	 * load the dayBox with day-selection-options.
	 */
	public void loadDayBox(){
		if (isLeepYear((int) yearBox.getSelectedItem())) {
			numOfDaysInMonth[1] = 29;
		} else {
			numOfDaysInMonth[1] = 28;
		}
		if (monthBox.getItemCount() != 0) {
			dayBox.removeAllItems();
			int thisDay = today.getDayOfMonth();
			int currSelectedMonth = (int) monthBox.getSelectedItem();

			if (currSelectedMonth == today.getMonthValue()) {
				for (int i = thisDay; i <= numOfDaysInMonth[currSelectedMonth-1]; i++) {
					dayBox.addItem(i);
				}
			} else {
				for (int i = 1; i <= numOfDaysInMonth[currSelectedMonth-1]; i++) {
					dayBox.addItem(i);
				}
			}
		}
	}

	public void setCSOV(CreateSalesOrderView csov){
		this.csov = csov;
	}

	public boolean isLeepYear(int year) {
		boolean isLeepYear = false;
		if (year % 4 == 0 && year % 100 != 0) {
			isLeepYear = true;
		}
		return isLeepYear;
	}
}
