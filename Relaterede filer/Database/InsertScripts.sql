use dmab0916_202650

/*
--Zipcodes and cities
insert into ZipCity (zip_code, city) values ('6880', 'Tarm');
insert into ZipCity (zip_code, city) values ('7760', 'Hurup Thy');
insert into ZipCity (zip_code, city) values ('9000', 'Aalborg');
insert into ZipCity (zip_code, city) values ('9400', 'Norresundby');
insert into ZipCity (zip_code, city) values ('9440', 'Aabybro');
insert into ZipCity (zip_code, city) values ('9460', 'Brovst');

--Persons - Customers
insert into Person (name, address, zip_code, email, person_type) values 
	('Palle Bodilsen', 'Hovedvej 4', '9000', 'palle@bodilsen.dk', 'customer');
insert into Person (name, address, zip_code, email, person_type) values 
	('Bodil Pallesen', 'Sidevej 5', '9400', 'bodil@pallesen.dk', 'customer');
insert into Person (name, address, zip_code, email, person_type) values 
	('Padil Bollesen', 'Genvej 6', '9440', 'padil@bollesen.dk', 'supplier');

--Persons - Employees
insert into Person(name, address, zip_code, email, person_type) values
	('Brian Johansen', 'Nyvej 15', '9460', 'brian@brovstbolighus.dk', 'employee');
insert into Person(name, address, zip_code, email, person_type) values
	('Mads Thorsen', 'Nyvej 15', '9460', 'mads@brovstbolighus.dk', 'employee');

--Persons - Suppliers
insert into Person(name, address, zip_code, email, person_type) values 
	('Jensen', 'Fabriksvej 13', '7760', 'jensenordre@hildinganders.com', 'supplier');
insert into Person(name, address, zip_code, email, person_type) values
	'Hjort Knudsen', 'Stabelhøjvej 1', '6880', 'mail@hjortknudsen.dk', 'supplier');

--Phone-numbers
insert into Phone (phone, person_id) values ('11223344', (select id from person where name = 'Palle Bodilsen'));
insert into Phone (phone, person_id) values ('55667788', (select id from person where name = 'Bodil Pallesen'));
insert into Phone (phone, person_id) values ('12345678', (select id from person where name = 'Padil Bollesen'));
insert into Phone (phone, person_id) values ('28862886', (select id from person where name = 'Brian Johansen'));
insert into Phone (phone, person_id) values ('68826882', (select id from person where name = 'Mads Thorsen'));
insert into Phone (phone, person_id) values ('82133000', (select id from person where name = 'Jensen'));
insert into Phone (phone, person_id) values ('75250244', (select id from person where name = ' Hjort Knudsen'));

--Usernames and passwords
insert into UsernamePassword (username, password) values ('brian', 'brianskode');
insert into UsernamePassword (username, password) values ('mads', 'madskode');

--Customers
insert into Customer (id, type) values ((select id from person where name = 'Palle Bodilsen'), 'privat');
insert into Customer (id, type) values ((select id from person where name = 'Bodil Pallesen'), 'privat');
insert into Customer (id, type) values ((select id from person where name = 'Padil Bollesen'), 'erhverv');

--Employees
insert into Employee (id, ssn, salary, username) values 
	((select id from person where name = 'Brian Johansen'), '010101-0101', 300, 
		(select usernamepassword.username from usernamepassword where username = 'brian'));
insert into Employee (id, ssn, salary, username) values	
	((select id from person where name = 'Mads Thorsen'), '020202-0202', 300,
		(select usernamepassword.username from usernamepassword where username = 'mads'));

--Suppliers
insert into Supplier (id, cvr) values ((select id from person where name = 'Jensen'), '19835901');
insert into Supplier (id, cvr) values ((select id from person where name = 'Hjort Knudsen'), '21620998');

--OrderConditions
insert into OrderCondition (type) values ('OfferType')
insert into OrderCondition (type) values ('OrderType')
insert into OrderCondition (type) values ('DeliveredType')

--OfferTypes
insert into OfferType (id, create_date, discount, due_date, accept_date) values 
	((select id from OrderCondition where type = 'OfferType'), '2017-01-01', '100', '2017-01-01', '2017-01-01')

--OrderTypes
insert into OrderType (id, create_date, discount) values
	((select id from OrderCondition where type = 'OrderType'), '2017-02-02', '200')

--DeliveredTypes
insert into DeliveredType (id, date) values 
	((select id from OrderCondition where type = 'DeliveredType'), '2017-03-03')

--ProductPrices
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('100', '200', '2016-04-04')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('30', '40', '2016-05-05')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('500', '1000', '2016-06-06')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('7', '15', '2016-07-07')

--ProductTypes
insert into ProductType (category_name) values ('Modulsofa')
insert into ProductType (category_name) values ('Seng')
*/

/*
insert into Product (model, description, dimensions, product_price_id, supplier_id) values
	('1669', 'Til den kvalitetsbevidste', null, null, (select id from supplier where cvr = '12345678'))
*/

select * from Product
/*
insert into Product (model, description, dimensions, part_of_product_id, product_price_id, product_type_id, supplier_id) values
	('HCL', 'Chaiselong højre', '160x60x75', (select id from Product where model = '1669'), 
	(select id from ProductPrice where purchase_price = '100'), (select id from producttype where category_name = 'Modulsofa'), 
	(select id from supplier where cvr = '12345678'))
*/

/*
insert into Product (model, description, dimensions, part_of_product_id, product_price_id, product_type_id, supplier_id) 
	VALUES ('Hjort Knudsen','Byg selv sofa','100x100x80');
insert into Product (model, description, dimensions, part_of_product_id, product_price_id, product_type_id, supplier_id) 
	VALUES ('Hjort Knudsen','Sofa 1979 byggesystem','200x160x80');
insert into Product (model, description, dimensions, part_of_product_id, product_price_id, product_type_id, supplier_id) 
	VALUES ('Atlanta','Soda opstilling 09','371x173');
insert into Product (model, description, dimensions, part_of_product_id, product_price_id, product_type_id, supplier_id) 
	VALUES ('Kiwi modulsystem','Byg selv sofa','269x224');
*/