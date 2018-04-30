
use dmab0916_200726;


create table zipcode_city(
	zipcode varchar(20) primary key,
	city varchar(20) not null
	)

create table person(
	id int primary key identity(1,1),
	name varchar(40) not null,
	address varchar(40) not null,
	zipcode varchar(20) foreign key references zipcode_city(zipcode) on update cascade on delete cascade,
	email varchar(40), 
	type varchar(40), 
	) 

create table employee(
	id int foreign key references person(id) on update cascade on delete cascade,
	salary int,
	)

create table customer(
	id int foreign key references person(id) on update cascade on delete cascade,
	type varchar(40)
	)

create table phone(
	phoneNr varchar(20) primary key,
	person_id int foreign key references person(id) on update cascade on delete cascade
	)

create table supplier(
	id int primary key identity(1,1),
	name varchar(40),
	address varchar(40),
	country varchar(40),
	zipcode varchar(20) foreign key references zipcode_city(zipcode) on update cascade on delete cascade,
	phoneNr varchar(40),
	email varchar(40),
	)


create table Order_Condition(
	id int primary key identity(1,1),
	type varchar(40),
)

create table offer(
	id int foreign key references order_condition(id) on update cascade on delete cascade,
	create_date date,
	discount int,
	sent_Date date,
	due_date date,
	accept_date date
)

create table orders(
	id int foreign key references order_condition(id) on update cascade on delete cascade,
	create_date date,
	pack_date date
)

create table product(
	id int primary key identity(1,1),
	name varchar(20),
	purchase_price int,
	sales_price int,
	country_of_Origin varchar (40),
	min_stock int,
	amount int,
	type varchar(40),
	supplier int foreign key references supplier(id)
)

create table clothing(
	prod_id int foreign key references product(id) on update cascade on delete cascade,
	size int,
	colour varchar(20)
)

create table equipment(
	prod_id int foreign key references product(id) on update cascade on delete cascade,
	type varchar(20),
	discription varchar(40)
)

create table Gun_replica(
	prod_id int foreign key references product(id) on update cascade on delete cascade,
	caliber varchar(20),
	material varchar(40)
)

create table discount(
	id int primary key identity(1,1),
	club_discount_limit int,
	private_discount_limit int,
	club_discount_amount int,
	private_discount_amount int
	)

create table sales_order(
	id int primary key identity(1,1),
	create_date date,
	order_condition int foreign key references order_condition(id) on update cascade on delete cascade,
	emp_id int foreign key references person(id) on update cascade on delete cascade,
	cus_id int foreign key references person(id),
	--line_id int foreign key references sales_order_line(id) on update cascade on delete cascade,
	discount_id int foreign key references discount(id) on update cascade on delete cascade
	)

create table sales_order_line(
	id int primary key identity(1,1),
	amount int,
	prod_id int foreign key references product(id) on update cascade on delete cascade,
	order_id int foreign key references sales_order(id) on update cascade on delete cascade,
	)

create table delivered(
	id int foreign key references order_condition(id) on update cascade on delete cascade,
	delivery_date date
)

create table invoice(
	id int primary key,
	payment_date date,
	order_id int foreign key references sales_order(id) on update cascade on delete cascade
)
