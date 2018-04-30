use dmab0916_202650


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
	('Jensen Senge', 'Fabriksvej 13', '7760', 'jensenordre@hildinganders.com', 'supplier');
insert into Person(name, address, zip_code, email, person_type) values
	('Hjort Knudsen', 'Stabelhøjvej 1', '6880', 'mail@hjortknudsen.dk', 'supplier');

--Phone-numbers - Customers
insert into Phone (phone, person_id) values ('11223344', (select id from person where name = 'Palle Bodilsen'));
insert into Phone (phone, person_id) values ('55667788', (select id from person where name = 'Bodil Pallesen'));
insert into Phone (phone, person_id) values ('12345678', (select id from person where name = 'Padil Bollesen'));

--Phone-numbers - Employees
insert into Phone (phone, person_id) values ('28862886', (select id from person where name = 'Brian Johansen'));
insert into Phone (phone, person_id) values ('68826882', (select id from person where name = 'Mads Thorsen'));

--Phone-numbers - Suppliers
insert into Phone (phone, person_id) values ('82133000', (select id from person where name = 'Jensen Senge'));
insert into Phone (phone, person_id) values ('75250244', (select id from person where name = 'Hjort Knudsen'));

--Employees
insert into Employee (id, ssn, salary, username) values 
	((select id from person where name = 'Brian Johansen'), '010101-0101', 300, 'brian');
insert into Employee (id, ssn, salary, username) values	
	((select id from person where name = 'Mads Thorsen'), '020202-0202', 300, 'mads');

--Passwords
insert into Password (id, password) values ((select id from employee where employee.username = 'brian'), 'brianskode');
insert into Password (id, password) values ((select id from employee where employee.username = 'mads'), 'madskode');

--Customers
insert into Customer (id, type) values ((select id from person where name = 'Palle Bodilsen'), 'privat');
insert into Customer (id, type) values ((select id from person where name = 'Bodil Pallesen'), 'privat');
insert into Customer (id, type) values ((select id from person where name = 'Padil Bollesen'), 'erhverv');

--Suppliers
insert into Supplier (id, cvr) values ((select id from person where name = 'Jensen Senge'), '19835901');
insert into Supplier (id, cvr) values ((select id from person where name = 'Hjort Knudsen'), '21620998');

--OrderConditions
insert into OrderCondition (type) values ('OfferType')
insert into OrderCondition (type) values ('OrderType')
insert into OrderCondition (type) values ('DeliveredType')

--OfferTypes
insert into OfferType (id, create_date, due_date, accept_date) values 
	((select id from OrderCondition where type = 'OfferType'), '2017-01-01', '2017-01-01', '2017-01-01')

--OrderTypes
insert into OrderType (id, create_date, pack_date) values
	((select id from OrderCondition where type = 'OrderType'), '2017-02-02', '2017-03-03')

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
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('3500', '3699', '2017-05-29')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('4000', '4499', '2017-05-29')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('4500', '5999', '2017-05-29')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('4000', '7498', '2017-05-29')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('5500', '7999', '2017-05-29')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('5500', '4499', '2017-05-29')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('5500', '4799', '2017-05-29')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('2500', '4999', '2017-05-29')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('3000', '6499', '2017-05-29')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('5000', '7999', '2017-05-29')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('5000', '10498', '2017-05-29')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('2000', '3499', '2017-05-29')
insert into ProductPrice (purchase_price, sales_price, from_date) values
	('2200', '3999', '2017-05-29')

--ProductTypes
insert into ProductType values ('Modulsofa','Customizable', null);
insert into ProductType values ('Seng', 'NonCustomizable', null);
insert into ProductType values ('Sofamodul','Module', null);
insert into ProductType values ('Sofa', 'NonCustomizable', null);
insert into ProductType values ('2 prs sofa', 'NonCustomizable', null);
insert into ProductType values ('3 prs sofa', 'NonCustomizable', null);
insert into ProductType values ('Sovesofa', 'NonCustomizable', null);

--ProductTypeTemplates
insert into product values 
	('ModulSofaTemplate', null, null, null, (select id from ProductType where category_name = 'Modulsofa'),null);  
update ProductType set template_id = (select id from Product where model = 'ModulSofaTemplate') 
	where ProductType.category_name = 'Modulsofa';

insert into product values 
	('SengTemplate', null, null, null, (select id from ProductType where category_name = 'Seng'), null);  
update ProductType set template_id = (select id from Product where model = 'SengTemplate')
	where ProductType.category_name = 'Seng';

insert into product values 
	('SofaModulTemplate', null, null, null, (select id from ProductType where category_name = 'Sofamodul'), null); 
update ProductType set template_id = (select id from Product where model = 'SofaModulTemplate')
	where ProductType.category_name = 'Sofamodul';

insert into product values 
	('SofaTemplate', null, null, null, (select id from ProductType where category_name = 'sofa'), null); 
update ProductType set template_id = (select id from Product where model = 'SofaTemplate')
	where ProductType.category_name = 'Sofa';

insert into product values 
	('2 prs sofa template', null, null, null, (select id from ProductType where category_name = '2 prs sofa'), null); 
update ProductType set template_id = (select id from Product where model = '2 prs sofa template')
	where ProductType.category_name = '2 prs sofa';

insert into product values 
	('3 prs sofa template', null, null, null, (select id from ProductType where category_name = '3 prs sofa'), null); 
update ProductType set template_id = (select id from Product where model = '3 prs sofa template')
	where ProductType.category_name = '3 prs sofa';

insert into product values 
	('SovesofaTemplate', null, null, null, (select id from ProductType where category_name = 'Sovesofa'), null); 
update ProductType set template_id = (select id from Product where model = 'SovesofaTemplate')
	where ProductType.category_name = 'Sovesofa';

--Products
insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('1669','Byggesystem model 1669','1x1x1', 2, 
	(select producttype.id from ProductType where category_name = 'Modulsofa'), 
	(select id from Supplier where cvr = '21620998' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('Verona','Sofa 1979 byggesystem','200x160x80', 2, 
	(select producttype.id from ProductType where category_name = 'Modulsofa'), 
	(select id from Supplier where cvr = '21620998' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('Atlanta','Sofa opstilling 09','371x173', 3, 
	(select producttype.id from ProductType where category_name = 'Sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('Kiwi modulsystem','Byg selv sofa','269x224', 4, 
	(select producttype.id from ProductType where category_name = 'Modulsofa'), 
	(select id from Supplier where cvr = '21620998' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('Block, brun','Trendy serie betrukket i moderne farver','100x100x145', 5, 
	(select id from ProductType where category_name = '2 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('Block 2 pers. sofa, hvid','Trendy serie betrukket i moderne farver. Mulighed for flere farver.','100x100x145', 5, 
	(select producttype.id from ProductType where category_name = '2 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('Block 2 pers. sofa, sort','Trendy serie betrukket i moderne farver. Mulighed for flere farver.','100x100x145', 5, 
	(select producttype.id from ProductType where category_name = '2 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('Model 2143','2 personers kvalitets sofa med fokus på den bedste sidde komfort.','170x84x90', 6, 
	(select producttype.id from ProductType where category_name = '2 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('SOFA CL 600 i dessin 223',
	'Klassisk dansk produceret 2 prs sofa Monteret med antracit uldstof i dessin 223. 
		Tremmestel i massiv sæbebehandlet bøg. Koldskum i sædehynder og polydun i ryghynder. Dansk produceret.', '144x84x90', 7, 
	(select producttype.id from ProductType where category_name = '2 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('RHODOS 2 PERS. SOFA, sort læder','Sofa med høj komfort monteret med okselæder på alle slidflader, kan bestilles i flere farver.','156x79x90', 8, 
	(select producttype.id from ProductType where category_name = '2 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('RHODOS 2 PERS. SOFA, brun læder','Sofa med høj komfort monteret med okselæder på alle slidflader, kan bestilles i flere farver.','156x79x90', 8, 
	(select producttype.id from ProductType where category_name = '2 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('RHODOS 2 PERS. SOFA, hvid læder','Sofa med høj komfort monteret med okselæder på alle slidflader, kan bestilles i flere farver.','156x79x90', 8, 
	(select producttype.id from ProductType where category_name = '2 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('ALBO 2 PERS. SOFA, bøg','Yderst velegnet til både privat samt institutions brug. Med mange muligheder for individuelle tilpasninger, opfylder serien 
		alle ønsker til kvalitet samt et tidsløst udtryk.','156x79x90', 9, 
	(select producttype.id from ProductType where category_name = '2 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('ALBO 2 PERS. SOFA, kirsebær','Yderst velegnet til både privat samt institutions brug. Med mange muligheder for individuelle tilpasninger, opfylder serien 
		alle ønsker til kvalitet samt et tidsløst udtryk.','156x79x90', 9, 
	(select producttype.id from ProductType where category_name = '2 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('ALBO 2 PERS. SOFA, eg','Yderst velegnet til både privat samt institutions brug. Med mange muligheder for individuelle tilpasninger, opfylder serien 
		alle ønsker til kvalitet samt et tidsløst udtryk.','156x79x90', 9, 
	(select producttype.id from ProductType where category_name = '2 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('ALBO 2 PERS. SOFA, mahogni','Yderst velegnet til både privat samt institutions brug. Med mange muligheder for individuelle tilpasninger, opfylder serien 
		alle ønsker til kvalitet samt et tidsløst udtryk.','156x79x90', 9, 
	(select producttype.id from ProductType where category_name = '2 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('BORA 3 PERS MED CHAISELONG','Lækker sofa som kan vendes','235x151x80', 10, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('BLOCK 3 PERS. SOFA, brun','Trendy serie betrukket i moderne farver.','235x151x80', 10, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('BLOCK 3 PERS. SOFA, sort','Trendy serie betrukket i moderne farver.','235x151x80', 10, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('BLOCK 3 PERS. SOFA, hvid','Trendy serie betrukket i moderne farver.','235x151x80', 10, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('U-DESIGN 3 PERS. SOFA','Sofaen er monteret med slidstærkt stof.','227x70x80', 11, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('GALAXY 3 PERS, grå','Flot retro sofa med høj ryg.','222x60x100', 12, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('GALAXY 3 PERS, sort','Flot retro sofa med høj ryg.','222x60x100', 12, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('GALAXY 3 PERS, hvid','Flot retro sofa med høj ryg.','222x60x100', 12, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('GALAXY 3 PERS, brun','Flot retro sofa med høj ryg.','222x60x100', 12, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('GALAXY 3 PERS, sort læder','Flot retro sofa med høj ryg.','222x60x100', 12, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('3 PERSONER SOFA MED SUPER SOFTCOMFORT, brun stof','3 personers kvalitets sofa med fokus på den bedste sidde komfort.','217x84x90', 13, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('3 PERSONER SOFA MED SUPER SOFTCOMFORT, hvid stof','3 personers kvalitets sofa med fokus på den bedste sidde komfort.','217x84x90', 13, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('3 PERSONER SOFA MED SUPER SOFTCOMFORT, sort stof','3 personers kvalitets sofa med fokus på den bedste sidde komfort.','217x84x90', 13, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('3 PERSONER SOFA MED SUPER SOFTCOMFORT, brun læder','3 personers kvalitets sofa med fokus på den bedste sidde komfort.','217x84x90', 13, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('3 PERSONER SOFA MED SUPER SOFTCOMFORT, sort læder','3 personers kvalitets sofa med fokus på den bedste sidde komfort.','217x84x90', 13, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('3 PERSONER SOFA MED SUPER SOFTCOMFORT, hvid læder','3 personers kvalitets sofa med fokus på den bedste sidde komfort.','217x84x90', 13, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('3 PERS. SOFA CL 600','Klassisk dansk produceret 3 prs sofa Monteret med antracit uldstof i dessin 223. Tremmestel i massiv sæbebehandlet bøg. 
		Koldskum i sædehynder og polydun i ryghynder. Dansk produceret. Fås i flere varianter monteret med lækkert uldfilt, som også fåes i flere farver','206x84x90', 14, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('RHODOS 3 PERS. SOFA, sort læder','Sofa med høj komfort monteret med okselæder overalt, kan bestilles i flere farver.','211x84x90', 15, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('RHODOS 3 PERS. SOFA, brun læder','Sofa med høj komfort monteret med okselæder overalt, kan bestilles i flere farver.','211x84x90', 15, 
	(select id from ProductType where category_name = '3 prs sofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('CUBO SOVESOFA','KVALITETS SOVESOFA MED STORT MAGASIN OG KRAFTIG POSEFJEDER I BEGGE MADRASSER. 
		VENDBARE PUDER MED KONTRASTKNAPPER I SMART OG MODERNE DESIGN MED BEN I BØRSTET STÅL','200x95x40', 16, 
	(select id from ProductType where category_name = 'Sovesofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('NOAH 1 SOVESOFA, sort','Kvalitets sovesofa med stort magasin og kraftige posefjedre i begge madrasser,
		vendbare rygpuder med kontrastknapper i et smart og moderne design, ben massiv eg. 
		Mål: 95x200 cm   ( sovemål  140x200 cm ) kan bestilles i andre farvekombinationer.','200x95x40', 16, 
	(select id from ProductType where category_name = 'Sovesofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('NOAH 1 SOVESOFA, hvid','Kvalitets sovesofa med stort magasin og kraftige posefjedre i begge madrasser,
		vendbare rygpuder med kontrastknapper i et smart og moderne design, ben massiv eg. 
		Mål: 95x200 cm   ( sovemål  140x200 cm ) kan bestilles i andre farvekombinationer.','200x95x40', 16, 
	(select id from ProductType where category_name = 'Sovesofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('VIKI SOVESOFA','Sovesofa monteret med stof. Gråt stof og vendbare ryghynder, blå på den ene side og grå på den anden.
		Inkl. 2 ryghynder. Springindlæg. Magasin til opbavaring. ​Sovemål: 140x200 cm. Leveres usamlet.','200x95x40', 16, 
	(select id from ProductType where category_name = 'Sovesofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('COMO SOVESOFA','Sovesofa monteret med stof. Sort stof med vendbare ryghynder (hvid/grå bynavne.)
		Inkl. 2 ryghynder. Springindlæg. Magasin til opbavaring. ​Sovemål: 150x200 cm. Leveres usamlet.','200x95x40', 16, 
	(select id from ProductType where category_name = 'Sovesofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('ORION SOVESOFA','Kvalitets sovesofa med springindlæg og vendbare ryghynder i hvid eller grå. 
		Stort magasin med plads til sengetøj m.m. Sovemål: 200 x 140 cm.','200x95x40', 17, 
	(select id from ProductType where category_name = 'Sovesofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('LONDON KF-156 SOVESOFA','Klassisk elegant sovesofa til det lille værelse. 
		Sovesofaen findes i flere farver, så kan passer netop til kundens hjem. Mål: 156 cm. Sovemål: 185x146 cm.','156x95x40', 17, 
	(select id from ProductType where category_name = 'Sovesofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('FUJI FUTON, sort','Sovesofa med Spring Round madras med 9 cm. pocket fjedre og 3 lag bomuld.','156x95x40', 17, 
	(select id from ProductType where category_name = 'Sovesofa'), 
	(select id from Supplier where cvr = '19835901' ));

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) 
	VALUES ('FUJI FUTON, grå','Sovesofa med Spring Round madras med 9 cm. pocket fjedre og 3 lag bomuld.','156x95x40', 17, 
	(select id from ProductType where category_name = 'Sovesofa'), 
	(select id from Supplier where cvr = '19835901' ));
	
--ProductModules
insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('HCL1', 'Chaiselong højre', '160x60x75', 
	(select id from ProductPrice where purchase_price = '100'), (select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '19835901'));
insert into PartOfProduct (product_part_id, product_id) values 
	((select id from Product where description = 'Chaiselong højre'), 
	 (select id from Product where model = 'Verona'));  

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('HCL2', 'Armlæn venstre', '160x60x75', 
	(select id from ProductPrice where purchase_price = '30'), (select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '19835901'));
insert into PartOfProduct (product_part_id, product_id) values
	((select id from Product where description = 'Armlæn venstre'), 
	 (select id from Product where model = 'Verona')); 

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('CH', 'Endemodul', '85x92x81', 8, 
	(select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '21620998'));
insert into PartOfProduct (product_part_id, product_id) values 
	((select id from Product where description = 'Endemodul' and model = 'CH'), 
	 (select id from Product where model = '1669')); 

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('AH', 'Endemodul', '100x92x81', 8, 
	(select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '21620998'));
insert into PartOfProduct (product_part_id, product_id) values 
	((select id from Product where description = 'Endemodul' and model = 'AH'), 
	 (select id from Product where model = '1669')); 

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('BH', 'Endemodul', '144x92x81', 8, 
	(select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '21620998'));
insert into PartOfProduct (product_part_id, product_id) values 
	((select id from Product where description = 'Endemodul' and model = 'BH'), 
	 (select id from Product where model = '1669')); 

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('THJR', 'Hjørne', '126x126x81', 8, 
	(select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '21620998'));
insert into PartOfProduct (product_part_id, product_id) values 
	((select id from Product where description = 'Hjørne' and model = 'THJR'), 
	 (select id from Product where model = '1669')); 

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('THJ', 'Hjørne', '108x108x81', 8, 
	(select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '21620998'));
insert into PartOfProduct (product_part_id, product_id) values 
	((select id from Product where description = 'Hjørne' and model = 'THJ'), 
	 (select id from Product where model = '1669')); 

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('CO', 'Midt', '59x92x73', 8, 
	(select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '21620998'));
insert into PartOfProduct (product_part_id, product_id) values 
	((select id from Product where description = 'Midt' and model = 'CO'), 
	 (select id from Product where model = '1669')); 

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('C12O', 'Midt', '75x92x73', 8, 
	(select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '21620998'));
insert into PartOfProduct (product_part_id, product_id) values 
	((select id from Product where description = 'Midt' and model = 'C12O'), 
	 (select id from Product where model = '1669')); 

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('C', 'Lukket', '109x92x81', 8, 
	(select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '21620998'));
insert into PartOfProduct (product_part_id, product_id) values 
	((select id from Product where description = 'Lukket' and model = 'C'), 
	 (select id from Product where model = '1669')); 

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('C12', 'Lukket', '125x92x81', 8, 
	(select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '21620998'));
insert into PartOfProduct (product_part_id, product_id) values 
	((select id from Product where description = 'Lukket' and model = 'C12'), 
	 (select id from Product where model = '1669')); 

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('A15J_L', 'Armlæn', '54x15x92', 8, 
	(select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '21620998'));
insert into PartOfProduct (product_part_id, product_id) values 
	((select id from Product where description = 'Armlæn' and model = 'A15J_L'), 
	 (select id from Product where model = '1669')); 

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('A25J_L', 'Armlæn', '54x25x92', 8, 
	(select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '21620998'));
insert into PartOfProduct (product_part_id, product_id) values 
	((select id from Product where description = 'Armlæn' and model = 'A25J_L'), 
	 (select id from Product where model = '1669')); 

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('A15J_R', 'Armlæn', '54x15x92', 8, 
	(select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '21620998'));
insert into PartOfProduct (product_part_id, product_id) values 
	((select id from Product where description = 'Armlæn' and model = 'A15J_R'), 
	 (select id from Product where model = '1669')); 

insert into Product (model, description, dimensions, product_price_id, product_type_id, supplier_id) values
	('A25J_R', 'Armlæn', '54x25x92', 8, 
	(select id from producttype where category_name = 'sofamodul'), 
	(select id from supplier where cvr = '21620998'));
insert into PartOfProduct (product_part_id, product_id) values 
	((select id from Product where description = 'Armlæn' and model = 'A25J_R'), 
	 (select id from Product where model = '1669')); 

--TemplateProperty
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values 
	('Modultype', null, null, null, 'String', 
	(select id from Product where model = 'SofaModulTemplate'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Hårdhed', null, null, null, 'String', 
	(select id from Product where model = 'SofaModulTemplate'));

--Property
insert into Property  (name, string_value, double_value, boolean_value, type, product_id) values 
	('Modultype', 'Chaiselong', null, null, 'String', 
	(select id from Product where description = 'Chaiselong højre'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Modultype', 'Armlæn', null, null, 'String', 
	(select id from Product where description = 'Armlæn venstre'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Modultype', 'Endemodul', null, null, 'String', 
	(select id from Product where model = 'AH'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Modultype', 'Endemodul', null, null, 'String', 
	(select id from Product where model = 'BH'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Modultype', 'Endemodul', null, null, 'String', 
	(select id from Product where model = 'CH'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Modultype', 'Hjørne', null, null, 'String', 
	(select id from Product where model = 'THJ'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Modultype', 'Hjørne', null, null, 'String', 
	(select id from Product where model = 'THJR'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Modultype', 'Midt', null, null, 'String', 
	(select id from Product where model = 'CO'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Modultype', 'Midt', null, null, 'String', 
	(select id from Product where model = 'C12O'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Modultype', 'Lukket', null, null, 'String', 
	(select id from Product where model = 'C'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Modultype', 'Lukket', null, null, 'String', 
	(select id from Product where model = 'C12'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Modultype', 'Armlæn', null, null, 'String', 
	(select id from Product where model = 'A15J_L'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Modultype', 'Armlæn', null, null, 'String', 
	(select id from Product where model = 'A25J_L'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Modultype', 'Armlæn', null, null, 'String', 
	(select id from Product where model = 'A15J_R'));
insert into Property (name, string_value, double_value, boolean_value, type, product_id) values
	('Modultype', 'Armlæn', null, null, 'String', 
	(select id from Product where model = 'A25J_R'));



--OrderTable
--At this moment there is not any orders inserted to database, when inserting objects
	