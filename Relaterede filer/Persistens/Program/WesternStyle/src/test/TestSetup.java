package test;

import java.sql.SQLException;

import database.DBConnection;

public class TestSetup {

	public static void main(String[] args) throws SQLException {
		dropDB();
		createTables();
		insertTestData();
	}

	public static void dropDB() throws SQLException{
		for(int i = 0 ; i < dropScript.length; i++) {
			e(dropScript[i]);
		}
	}

	public static void createTables() throws SQLException{
		for(int i = 0 ; i < createTableScript.length; i++) {
			e(createTableScript[i]);
		}
	}

	public static void insertTestData() throws SQLException{
		for(int i = 0 ; i < insertScript.length; i++) {
			e(insertScript[i]);
		}
	}

	private static void e(String sql) throws SQLException {
		DBConnection.getInstance().getConnection().createStatement().executeUpdate(sql);
	}

	private static final String[] dropScript = {
			"drop table invoice",
			"drop table delivered",
			"drop table sales_order_line",
			"drop table gun_replica",
			"drop table equipment",
			"drop table clothing",
			"drop table product",
			"drop table orders",
			"drop table offer",
			"drop table sales_order",
			"drop table order_condition",
			"drop table discount",
			"drop table supplier", 
			"drop table phone",
			"drop table customer",
			"drop table employee",
			"drop table person",
			"drop table zipcode_city",
	};

	private static final String[] createTableScript={
			"create table zipcode_city(zipcode varchar(20) primary key,city varchar(20) not null)",
			"create table person(id int primary key identity(1,1), name varchar(40) not null,address varchar(40) not null,zipcode varchar(20) foreign key references zipcode_city(zipcode) on update cascade on delete cascade,email varchar(40),type varchar(40), )",
			"create table employee(id int foreign key references person(id) on update cascade on delete cascade,salary int,)",
			"create table customer(id int foreign key references person(id) on update cascade on delete cascade,type varchar(40))",
			"create table phone(phoneNr varchar(20) primary key,person_id int foreign key references person(id) on update cascade on delete cascade)",
			"create table supplier(id int primary key identity(1,1),name varchar(40),address varchar(40),country varchar(40),zipcode varchar(20) foreign key references zipcode_city(zipcode) on update cascade on delete cascade,phoneNr varchar(40),email varchar(40),)",
			"create table Order_Condition(id int primary key identity(1,1), type varchar(40),)",
			"create table offer(id int foreign key references order_condition(id) on update cascade on delete cascade,create_date date,discount int,sent_Date date,due_date date,accept_date date)",
			"create table orders(id int foreign key references order_condition(id) on update cascade on delete cascade,create_date date,pack_date date)",
			"create table product(id int primary key identity(1,1),name varchar(20),purchase_price int,sales_price int,country_of_Origin varchar (40),min_stock int, amount int, type varchar(40), supplier int foreign key references supplier(id))",
			"create table clothing(prod_id int foreign key references product(id) on update cascade on delete cascade,size int,colour varchar(20))",
			"create table equipment(prod_id int foreign key references product(id) on update cascade on delete cascade,type varchar(20),discription varchar(40))",
			"create table Gun_replica(prod_id int foreign key references product(id) on update cascade on delete cascade,caliber varchar(20),material varchar(40))",
			"create table discount(id int primary key identity(1,1),club_discount_limit int,private_discount_limit int,club_discount_amount float,private_discount_amount float)",
			"create table sales_order(id int primary key identity(1,1),create_date date,order_condition int foreign key references order_condition(id) on update cascade on delete cascade,emp_id int foreign key references person(id) on update cascade on delete cascade,cus_id int foreign key references person(id), discount_id int foreign key references discount(id) on update cascade on delete cascade)",
			"create table sales_order_line(id int primary key identity(1,1),amount int,prod_id int foreign key references product(id) on update cascade on delete cascade, order_id int foreign key references sales_order(id) on update cascade on delete cascade)",
			"create table delivered(id int foreign key references order_condition(id) on update cascade on delete cascade,delivery_date date)",
			"create table invoice(id int primary key identity(1,1),payment_date date,order_id int foreign key references sales_order(id) on update cascade on delete cascade)",
	};

	private static final String[] insertScript ={
			//INSERT ZIPCODE_CITY
			"INSERT INTO zipcode_city VALUES('7800', 'Skive')",
			"INSERT INTO zipcode_city VALUES('8000', 'Aarhus')",
			"INSERT INTO zipcode_city VALUES('9000', 'Aalborg')",

			//INSERT PERSON
			"INSERT INTO person VALUES('B�rge Hansen', 'Pertunievej 56', '7800', 'hansen@mail.com', 'customer')",
			"INSERT INTO customer VALUES(1, 'club')",
			"INSERT INTO phone VALUES(12345678, 1)",

			"INSERT INTO person VALUES('Hanne Nielsen', 'Humlevej 67', '8000', 'Hannepigen@mail.com', 'customer')",
			"INSERT INTO customer VALUES(2, 'private')",
			"INSERT INTO phone VALUES(78451236, 2)",


			"INSERT INTO person VALUES('Bent Jensen', 'Skalborgvej 96', '9000', 'jensensLineDance@mail.com', 'customer')",
			"INSERT INTO customer VALUES(3, 'private')",
			"INSERT INTO phone VALUES(87654321, 3)",

			"INSERT INTO person VALUES('Gerda Andersen', 'Countryvej 69', '9000', 'gerdaPower@mail.com', 'employee')",
			"INSERT INTO employee VALUES(4, 3000000)",
			"INSERT INTO phone VALUES(78456923, 4)",

			//INSERT ORDER_CONDITIONS
			"INSERT INTO order_condition VALUES('offer')",
			"INSERT INTO order_condition VALUES('order')",
			"INSERT INTO order_condition VALUES('delivered')",

			"INSERT INTO offer VALUES(1, '2017-04-05', 200, '2017-04-06', '2017-04-20', '2017-04-14')",
			"INSERT INTO orders VALUES(2, '2017-04-05', '2017-04-07')",
			"INSERT INTO delivered VALUES(3, '2017-04-05')",

			//INSERT SUPPLIER
			"INSERT INTO supplier VALUES('Bobs Country Bunker', 'Knoldsparkervej 78', 'Danmark', 8000, '23456789', 'bcb@mail.com')",

			//INSERT PRODUCTS
			"INSERT INTO product VALUES('Smith&Wesson 39', 600, 100, 'England', 10, 170, 'gun_replica', 1)",
			//Order price with this product 100
			"INSERT INTO product VALUES('Fake - Revolver22', 50, 100, 'Japan', 50, 150, 'gun_replica', 1)",
			//Order price with this product 1500
			"INSERT INTO product VALUES('Cowboy St�vler', 500, 750, 'Taiwan', 5, 22, 'clothing', 1)",
			//Order price with this product 2000
			"INSERT INTO product VALUES('Guld ring', 450, 1000, 'Rusland', 3, 8, 'equipment', 1)", 
			//Order price with this product 2500
			"INSERT INTO product VALUES('B�ltesp�nde', 25, 100, 'Taiwan', 75, 250, 'clothing', 1)",
			//Order price with this product 2800
			"INSERT INTO product VALUES('St�vle fedt', 25, 50, 'Rusland', 25, 56, 'equipment', 1)", 
			//Order price with this product 3500
			"INSERT INTO product VALUES('L�der jakke', 275, 350, 'Taiwan', 4, 10, 'clothing', 1)",
			//Order price with this product 12.000
			"INSERT INTO product VALUES('Sherifstjerne', 30, 75, 'Rusland', 5, 160, 'equipment', 1)",

			//INSERT DISCOUNT
			"INSERT INTO discount VALUES(1500, 2500, 45, 45)",

			//INSERT SALES_ORDERS
			//TestCase private
			"INSERT INTO sales_order VALUES('2017-04-05', 1, 4, 2, 1)", //SalesOrder 1
			"INSERT INTO sales_order VALUES('2017-04-04', 2, 4, 2, 1)",	//SalesOrder 2
			"INSERT INTO sales_order VALUES('2017-04-02', 1, 4, 3, 1)",	//SalesOrder 3
			"INSERT INTO sales_order VALUES('2017-04-02', 1, 4, 3, 1)",	//SalesOrder 4
			"INSERT INTO sales_order VALUES('2017-04-02', 2, 4, 3, 1)",	//SalesOrder 5
			"INSERT INTO sales_order VALUES('2017-04-02', 1, 4, 3, 1)",	//SalesOrder 6
			"INSERT INTO sales_order VALUES('2017-04-02', 1, 4, 3, 1)",	//SalesOrder 7
			//TestCase club
			"INSERT INTO sales_order VALUES('2017-04-05', 1, 4, 1, 1)",	//SalesOrder 8
			"INSERT INTO sales_order VALUES('2017-04-04', 2, 4, 1, 1)",	//SalesOrder 9
			"INSERT INTO sales_order VALUES('2017-04-02', 1, 4, 1, 1)",	//SalesOrder 10
			"INSERT INTO sales_order VALUES('2017-04-02', 1, 4, 1, 1)", //SalesOrder 11
			"INSERT INTO sales_order VALUES('2017-04-02', 2, 4, 1, 1)", //SalesOrder 12


			//INSERT SALES__ORDER_LINES
			"INSERT INTO sales_order_line VALUES(1, 2, 1)",
			"INSERT INTO sales_order_line VALUES(2, 3, 2)",
			"INSERT INTO sales_order_line VALUES(2, 4, 3)",
			"INSERT INTO sales_order_line VALUES(25, 5, 4)",
			"INSERT INTO sales_order_line VALUES(56, 6, 5)",
			"INSERT INTO sales_order_line VALUES(10, 7, 6)",
			"INSERT INTO sales_order_line VALUES(160, 8, 7)",

			// club test orderlines
			"INSERT INTO sales_order_line VALUES(1, 2, 8)",
			"INSERT INTO sales_order_line VALUES(2, 3, 9)",
			"INSERT INTO sales_order_line VALUES(2, 4, 10)",
			"INSERT INTO sales_order_line VALUES(25, 5, 11)",
			"INSERT INTO sales_order_line VALUES(56, 6, 12)"


	};
}
