use dmab0916_202650;

create table ZipCity (
	zip_code varchar(8) primary key,
	city varchar(40) not null
)

create table Person (
	id int primary key identity(1,1),
	name varchar(40) not null,
	address varchar(40) not null, 
	zip_code varchar(8) foreign key references ZipCity(zip_code) on update cascade on delete cascade, 
	email varchar(40), 
	person_type varchar(8)
)

create table Phone (
	phone varchar(20) primary key,
	person_id int foreign key references Person(id) on update cascade on delete cascade
)

create table Employee (
	id int primary key foreign key references Person(id) on update cascade on delete cascade,
	ssn varchar(20), 
	salary float,
	username varchar(20)
)

create table Password (
	id int foreign key references Employee(id) on update cascade on delete cascade,
	password varchar(20)
)

create table Customer (
	id int primary key foreign key references Person(id) on update cascade on delete cascade,
	type varchar(20)
)

create table Supplier (
	id int primary key foreign key references Person(id) on update cascade on delete cascade,
	cvr varchar(20)
)

create table OrderCondition (
	id int primary key identity(1,1),
	type varchar(20)
)

create table OfferType (
	id int primary key foreign key references OrderCondition(id) on update cascade on delete cascade,
	create_date date,
	due_date date,
	accept_date date
)

create table OrderType (
	id int primary key foreign key references OrderCondition(id) on update cascade on delete cascade,
	create_date date,
	pack_date date
)

create table DeliveredType (
	id int primary key foreign key references OrderCondition(id) on update cascade on delete cascade,
	date date
)

create table ProductPrice (
	id int primary key identity(1,1),
	purchase_price float,
	sales_price float,
	from_date date
)

create table ProductType (
	id int primary key identity(1,1),
	category_name varchar(40),
	type varchar(20),
)

create table Product (
	id int primary key identity(1,1),
	model varchar(100),
	description varchar(1000),
	dimensions varchar(40),
	product_price_id int foreign key references ProductPrice(id) on update cascade on delete cascade,
	product_type_id int foreign key references ProductType(id) on update cascade on delete cascade,
	supplier_id int foreign key (supplier_id) references Supplier(id)
)

ALTER TABLE ProductType ADD template_id int foreign key references Product(id);

create table PartOfProduct (
	id int primary key identity(1,1),
	product_part_id int foreign key references Product(id), 
	product_id int foreign key references Product(id) on update cascade on delete cascade
)

create table Property (
	id int primary key identity(1,1), 
	name varchar(40),
	string_value varchar(40),
	double_value int,
	boolean_value bit,
	type varchar(20),
	product_id int foreign key references Product(id)
)

create table SalesOrder (
	id int primary key identity(1,1),
	date_placed date, 
	paid bit not null default(0), 
	order_sent bit not null default(0),
	employee_id int foreign key references Employee(id), 
	customer_id int foreign key references Customer(id) on update cascade on delete cascade,
	order_condition_id int foreign key references OrderCondition(id) on update cascade on delete cascade
)

create table SalesOrderLine (
	id int primary key identity(1,1),
	order_id int foreign key references SalesOrder(id) on update cascade on delete cascade,
	product_id int foreign key references Product(id) on update cascade on delete cascade,
	amount int, 
)

create table SupplyOrder (
	id int primary key identity(1,1),
	date date, 
	supplier_id int foreign key references Supplier(id) on update cascade on delete cascade,
)

create table SupplyOrderLine (
	id int primary key identity(1,1),
	supply_order_id int foreign key references SupplyOrder(id) on update cascade on delete cascade,
	product_id int foreign key references Product(id) on update cascade on delete cascade,
	amount int,
	product_price_id int foreign key references ProductPrice(id)
)

