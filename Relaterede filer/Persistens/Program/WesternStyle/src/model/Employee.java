package model;

public class Employee extends Person {
	private double salary;

	public Employee(int id, String name, String address, String email, String phone, double salary) {
		super(id, name, address, email, phone);
		setSalary(salary);
	}

	public Employee() {

	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
