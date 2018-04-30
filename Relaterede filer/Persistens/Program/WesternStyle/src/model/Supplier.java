package model;

public class Supplier {
	private String name;
	private String address;
	private String country;
	private String phone;
	private String email;

	public Supplier(String name, String address, String country, String phone, String email) {
		setName(name);
		setAddress(address);
		setCountry(country);
		setPhone(phone);
		setEmail(email);
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
