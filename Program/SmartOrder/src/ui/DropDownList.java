package ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/** 
 * This class is a generic subclass of JList. It creates a Drop Down Auto Completion List which is connected to a JTextField. 
 * The list is designed to placed in a JScrollPane under JTextField used for searching for a specific object<T> in an ArrayList<T>.  
 * The list will drop down and display current matches between the content of the JTextField and the objects<T> in the ArrayList<T>.
 * An Adaptor-Class that implements DropDownListAdaptorIF is needed to define how the object is matched, how it is displayed in the list 
 * and how it displayed in the text field after being selected.
 * DropDownList.java
 * @version January 2017
 * @param <T> The type of object to be represented in the list.
 */
public class DropDownList<T> extends JList{
	private JTextField textfield;
	private JScrollPane scrollPane;
	private DefaultListModel<String> listModel; 
	private int selectionIndex = 0;
	private ArrayList<T> searchElements;
	private DropDownListAdaptorIF<T> adaptor;
	private int currListSize = 0;
	private boolean emptyTextEqualsNoList = true;

	/**
	 * Instantiates the list. Sets up MouseMotion-, Mouse- and KeyListener.
	 * @param textfield : The connected JTextField to be used for searching the ArrayList.
	 * @param scrollPane : The JScrollPane to hold the List. The list is automatically placed in ViewPortView.
	 * @param searchElements : The ArrayList that hold the elements that the user wishes to search through.
	 * @param adaptor : An object that is a subtype of the DropDownListAdaptorIF interface. 
	 */
	public DropDownList(JTextField textfield, JScrollPane scrollPane, ArrayList<T> searchElements, DropDownListAdaptorIF<T> adaptor) {
		this.textfield = textfield;
		this.scrollPane = scrollPane;
		this.searchElements = searchElements;
		this.adaptor = adaptor;
		listModel = new DefaultListModel<String>();
		this.setModel(listModel);
		//Set in scrollPane
		scrollPane.setViewportView(this);
		scrollPane.setVisible(false);
		//set Listeners
		setupMouseMotionListener();
		setupMouseListener();
		setupKeyListener();
	}

	/**
	 * This method adds and sets up a MouseLisenter to the list. 
	 * When the mouse button is clicked the method setTextFieldToSelectedElement is called,
	 * which sets textfield to the selected element.    
	 */
	private void setupMouseListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setTextfieldToSelectedElement();
			}
		});
	}

	/**
	 * This method adds and sets up a MouseMotionListener to the list. 
	 * The MouseMotionListener is implemented so that the current list selection follows the mouse cursor.
	 */
	private void setupMouseMotionListener() {
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				setSelectedIndex(locationToIndex(e.getPoint()));
				selectionIndex = getSelectedIndex();
			}
		});
	}

	/**
	 * This method adds and sets up a KeyLisenter to the list. 
	 */
	private void setupKeyListener() {
		textfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e){
				loadList(textfield.getText(), searchElements, adaptor);
				setVisibility();
				setListKeyCommands(e);	
			}
			@Override
			public void keyPressed(KeyEvent e){
				loadList(textfield.getText(), searchElements, adaptor);
			}
		});
	}
	
	/**
	 * This method add basic key commands to the list. Up and Down for selection traversal and Enter for setting the currently selected list element in the text field. 
	 * @param e : KeyEvent passed from the KeyPressed method.
	 */
	private void setListKeyCommands(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN: 
			if (!isVisible() && textfield.getText().length()==0) {
				scrollPane.setVisible(true);
				setVisible(true);
			}
			selectionIndex = (selectionIndex < listModel.getSize()-1) ? selectionIndex + 1:selectionIndex; 
			setSelectedIndex(selectionIndex);
			break;
		case KeyEvent.VK_UP:
			if (textfield.getText().length() == 0) {
				scrollPane.setVisible(true);
				setVisible(true);
			}
			selectionIndex = (selectionIndex >= 1) ? selectionIndex - 1:selectionIndex; 
			setSelectedIndex(selectionIndex);
			break;
		case KeyEvent.VK_ENTER: 
			if (isVisible()) {
				setTextfieldToSelectedElement();
			}
			break;
		}
	}

	/**
	 * This method loads the list with current matches between the text typed in the the text field and the ArrayList of search elements. 
	 * Matches are found via the the adaptor-object's getIdentifier() method, which defines how the object is identified in the given search.
	 * Matching objects are displayed in the list via the adaptor-object's displayObjectInList() method, which determines how the object should be displayed. 
	 * @param filter : The String currently in the text field that filter matches from the ArrayList.
	 * @param searchElements : ArrayList of elements to search through.
	 * @param adaptor : An adaptor object that defines how the object is matched with filter and how it is displayed in the list.
	 */
	public void loadList(String filter, ArrayList<T> searchElements, DropDownListAdaptorIF<T> adaptor) {
		listModel.clear();
		for (T currObj : searchElements) {
			if (adaptor.getIdentifier(currObj).toLowerCase().contains(filter.toLowerCase())) {
				listModel.addElement(adaptor.displayObjectInList(currObj));
			}
		}
		setDefaultSelectionIndex();
	}

	/**
	 * This method sets the default selection.
	 * When the list's state changes the selection is set to the first element (index 0).
	 */
	private void setDefaultSelectionIndex() {
		if (listModel.size() != currListSize) {
			selectionIndex = 0;
			currListSize = listModel.size();
		}
		setSelectedIndex(selectionIndex);
	}

	/**
	 * This method controls the visibility of the list and the scroll pane. If the list is empty (no matches found) the list sets visibility to false.
	 * If the text field is empty the list either displays all the elements or disappears depending and the boolean emptyTextEqualsNoList.  
	 */
	public void setVisibility(){
		if (emptyTextEqualsNoList) {
			if (listModel.isEmpty() || textfield.getText().length() == 0) { 
				scrollPane.setVisible(false); setVisible(false);
			} else {
				scrollPane.setVisible(true); 
				this.setVisible(true);
			}
		}
		else {
			if (listModel.isEmpty()) { 
				scrollPane.setVisible(false); 
				setVisible(false);
			} else {
				scrollPane.setVisible(true); 
				this.setVisible(true);
			}
		}
	}
	
	/**
	 * This method sets the text field to selected element. This method is called either by mouse click or by the Enter key.
	 * The adaptor-objects displaySelectedObjectInTextfield() method determines how the object is displayed in text field once selected. 
	 */
	private void setTextfieldToSelectedElement() {
		if (getSelectedElement() != null) {
			T obj = getSelectedObject(getSelectedElement());
			if (obj != null) {
				textfield.setText(adaptor.displaySelectedObjectInTextfield(obj));
				scrollPane.setVisible(false);
				setVisible(false);
			}
		}
	}

	/**
	 * @return : the currently selected String element in the list.
	 */
	public String getSelectedElement(){
		if (selectionIndex >= 0 && selectionIndex < listModel.size()) {
			return listModel.getElementAt(selectionIndex);
		}
		return null;
	}

	/**
	 * @param selectedElement : the currently selected String element in the list.
	 * @return : The object from the ArrayList searchElements the corresponds the selected String element in the list.
	 */
	public T getSelectedObject(String selectedElement){
		for (T obj : searchElements) {
			if (adaptor.displayObjectInList(obj).equals(selectedElement)) {
				return obj;
			}
		}
		return null;
	}

	/**
	 * This method set the boolean that controls whether or not the list shall be visible when the text field is empty. 
	 * @param b
	 */
	public void emptyTextEqualsNoList(boolean b){
		emptyTextEqualsNoList = b;
	}

	public void setSearchElements(ArrayList<T> newElements){
		searchElements = newElements;
	}

	public ArrayList<T> getSearchElements(){
		return searchElements;
	}

}