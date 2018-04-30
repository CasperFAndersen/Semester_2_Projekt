package model;

public class Customer extends Person {
	private String type;

	public Customer(int id, String name, String address, String email, String phone, String type) {
		super(id, name, address, email, phone);
		setType(type);
	}

	public Customer() {

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhone() {
		return super.getPhone();
	}

}
