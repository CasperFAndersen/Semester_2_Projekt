package ui;

/**
 * This interface is designed to be implemented in Adaptor Classes used with the subclass of JList called DropDownList.
 * @author Arne George Ralston 
 * @version January 2017
 */
public interface DropDownListAdaptorIF<T> {
	/**
	 * @param o
	 * @return : String representation of the object to be displayed in the list.
	 */
	String displayObjectInList(T o);

	/**
	 * @param o
	 * @return : String that identifies how the object is matched with search-String.
	 */
	String getIdentifier(T o);

	/**
	 * 
	 * @param o
	 * @return : String representation of the object to be displayed in the JTextField after selected. 
	 */
	String displaySelectedObjectInTextfield(T o);

}
