package model;

public class Person {
	private int id;
	private String name;
	private String address;
	private String email;
	private String phone;

	public Person() {

	}

	public Person(int id, String name, String address, String email, String phone) {
		setId(id);
		setName(name);
		setAddress(address);
		setEmail(email);
		setPhone(phone);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
