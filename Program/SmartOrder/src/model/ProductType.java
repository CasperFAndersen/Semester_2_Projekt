package model;

/**
 * SmartOrder
 * ProductType.java
 * Purpose: Creates an product type.
 * @author Gruppe 1
 * @version 1.0 
 */
public class ProductType {
	private int id;
	private int templateId;
	private String categoryName;
	private String type;
	
	public ProductType(String categoryName, String type) {
		setCategoryName(categoryName);
		setType(type);
	}
	
	public ProductType(int id, String categoryName, int templateId, String type) {
		setId(id);
		setCategoryName(categoryName);
		setTemplateId(templateId);
		setType(type);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
