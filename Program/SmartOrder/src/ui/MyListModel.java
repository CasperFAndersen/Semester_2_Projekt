package ui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import model.Product;

/**
 * SmartOrder
 * MyListModel.java
 * Purpose: This class extends DefaultListModel. This class defines the methods JList 
 * use to get the value of each cell in a list and the length of the list.
 * @author Gruppe 1
 * @version 1.0 
 */
public class MyListModel extends DefaultListModel<Object> {
	private ArrayList<Product> products = new ArrayList<>();
	
	@Override
	public void addElement(Object p){
		products.add((Product) p);
		fireContentsChanged(products, 0, getSize()-1);
	}
	
	@Override
	public String getElementAt(int index) {
		return products.get(index).getModel() + " \t " + products.get(index).getProductPrice().getSalesPrice() +"kr";
	}

	@Override
	public int getSize() {
		return products.size();
	}
	
	@Override
	public Product elementAt(int i){
		return products.get(i);
	}
	
	@Override
	public Product get(int i){
		return products.get(i);
	}
	
	@Override
	public void removeElementAt(int i) {
		products.remove(i);
		fireContentsChanged(products, 0, getSize()-1);
	}
	
	public void setData(ArrayList<Product> products){
		this.products = products;
	}
	
	public ArrayList<Product> getData(){
		return products;
	}
	
	public double getTotal(){
		return	products.stream().mapToDouble((x)-> x.getProductPrice().getSalesPrice()).sum();
	}
	
	@Override
	public void clear(){
		products.clear();
		fireContentsChanged(products, 0, 0);
	}

}
