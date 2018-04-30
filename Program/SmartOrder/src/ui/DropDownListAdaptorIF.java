package ui;

/**
 * This interface is designed to be implemented in Adaptor Classes used with the subclass of JList called DropDownList.
 * DropDownListAdaptorIF.java
 * @author Gruppe 1
 */
public interface DropDownListAdaptorIF<T> {
	/**
	 * @param o as T
	 * @return : String representation of the object to be displayed in the list.
	 */
	String displayObjectInList(T o);

	/**
	 * @param o as T
	 * @return : String that identifies how the object is matched with search-String.
	 */
	String getIdentifier(T o);

	/**
	 * @param o as T
	 * @return : String representation of the object to be displayed in the JTextField after selected. 
	 */
	String displaySelectedObjectInTextfield(T o);
}
