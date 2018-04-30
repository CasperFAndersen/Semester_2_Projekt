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
	type varchar(8)
)

create table Phone (
	phone varchar(20) primary key,
	person_id int foreign key references Person(id) on update cascade on delete cascade
)

create table Employee (
	id int primary key foreign key references Person(id) on update cascade on delete cascade,
	ssn varchar(20), 
	salary float,
	username varchar(20) foreign key references UsernamePassword(username) on update cascade on delete cascade
)

create table UsernamePassword (
	username varchar(20) primary key,
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
	discount float,
	due_date date,
	accept_date date
)

create table OrderType (
	id int primary key foreign key references OrderCondition(id) on update cascade on delete cascade,
	create_date date,
	discount float
)

create table DeliveredType (
	id int primary key foreign key references OrderCondition(id) on update cascade on delete cascade,
	date date
)

create table ProductPrice (
	id int primary key identity(1,1),
	purchase_price float,
	sales_price float,
	from_date float
)

create table ProductType (
	id int primary key identity(1,1),
	category_name varchar(40)
)

create table PartOfProduct (
	part_id int primary key identity(1,1),
)

create table Product (
	id int primary key identity(1,1),
	model varchar(20),
	description varchar(40),
	dimensions varchar(40),
	part_of_product_id int foreign key references PartOfProduct(part_id) on update cascade on delete cascade,
	product_price_id int foreign key references ProductPrice(id) on update cascade on delete cascade,
	product_type_id int foreign key references ProductType(id) on update cascade on delete cascade
)

alter table PartOfProduct
	add product_id int foreign key (product_id) references Product(id)


create table OrderTable (
	id int primary key identity(1,1),
	shipping_address varchar(40) not null,
	date_placed date, 
	paid bit not null default(0), 
	order_sent bit not null default(0),
	employee_id int foreign key references Employee(id), --if 'on update cascade on delete cascade' is in the script, it gives an error
	customer_id int foreign key references Customer(id) on update cascade on delete cascade,
	order_condition_id int foreign key references OrderCondition(id) on update cascade on delete cascade
)

create table OrderLine (
	id int primary key identity(1,1),
	order_id int foreign key references OrderTable(id) on update cascade on delete cascade,
	product_id int foreign key references Product(id) on update cascade on delete cascade,
	amount int, 
	product_price_id int foreign key references ProductPrice(id) --if 'on update cascade on delete cascade' is in the script, it gives an error
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
	product_price_id int foreign key references ProductPrice(id) --on update cascade on delete cascade
)

create table Property (
	id int primary key identity(1,1), 
	name varchar(40),
	value varchar(40)
)

create table ProductProperty (
	property_id int foreign key references Property(id) on update cascade on delete cascade,
	product_id int foreign key references Product(id) on update cascade on delete cascade
)
