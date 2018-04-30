use dmab0916_200726;


--INSERT ZIPCODE_CITY
INSERT INTO zipcode_city VALUES('7800', 'Skive')
INSERT INTO zipcode_city VALUES('8000', 'Aarhus')
INSERT INTO zipcode_city VALUES('9000', 'Aalborg')

--INSERT PERSON
INSERT INTO person VALUES('Børge Hansen', 'Pertunievej 56', '7800', 'hansen@mail.com', 'customer');
INSERT INTO customer VALUES(1, 'privat')
INSERT INTO phone VALUES(12345678, 1)


INSERT INTO person VALUES('Hanne Nielsen', 'Humlevej 67', '8000', 'Hannepigen@mail.com', 'customer');
INSERT INTO customer VALUES(2, 'privat')
INSERT INTO phone VALUES(78451236, 2)


INSERT INTO person VALUES('Bent Jensen', 'Skalborgvej 96', '9000', 'jensensLineDance@mail.com', 'customer');
INSERT INTO customer VALUES(3, 'erhverv')
INSERT INTO phone VALUES(87654321, 3)

INSERT INTO person VALUES('Gerda Andersen', 'Countryvej 69', '9000', 'gerdaPower@mail.com', 'employee');
INSERT INTO employee VALUES(4, 3000000);
INSERT INTO phone VALUES(78456923, 4);

--INSERT ORDER_CONDITIONS
INSERT INTO order_condition VALUES('offer');
INSERT INTO order_condition VALUES('order');
INSERT INTO order_condition VALUES('delivered');

INSERT INTO offer VALUES(1, '2017-04-05', 200, '2017-04-06', '2017-04-20', '2017-04-14');
INSERT INTO orders VALUES(2, '2017-04-05', '2017-04-07');
INSERT INTO delivered VALUES(3, '2017-04-05');

-- INSERT SUPPLIER
INSERT INTO supplier VALUES('Bobs Country Bunker', 'Knoldsparkervej 78', 'Danmark', 8000, '23456789', 'bcb@mail.com');

--INSERT PRODUCTS
INSERT INTO product VALUES('Cowboy Støvler', 500, 750, 'Taiwan', 20, 35, 'clothing', 1); 
INSERT INTO product VALUES('Smith&Wesson 39', 600, 100, 'England', 10, 17, 'gun_replica', 1); 
INSERT INTO product VALUES('Sherifstjerne', 30, 75, 'Rusland', 5, 45, 'equipment', 1); 

--INSERT DISCOUNT
INSERT INTO discount VALUES(1500, 2500, 45, 45);

--INSERT SALES_ORDERS
INSERT INTO sales_order VALUES('2017-04-05', 1, 4, 2, 1);
INSERT INTO sales_order VALUES('2017-04-04', 2, 4, 1, 1);
INSERT INTO sales_order VALUES('2017-04-02', 3, 4, 3, 1);

--INSERT SALES__ORDER_LINES
INSERT INTO sales_order_line VALUES(5, 2, 1);
INSERT INTO sales_order_line VALUES(23, 3, 1);

INSERT INTO sales_order_line VALUES(3, 1, 2);
INSERT INTO sales_order_line VALUES(5, 2, 2);

INSERT INTO sales_order_line VALUES(12, 2, 3);
INSERT INTO sales_order_line VALUES(5, 3, 3);


 
 


/*select * from discount;


select * from person;

select * from employee;

SELECT person.id, person.name, person.address, 
person.zipcode, person.email, employee.salary, 
phone.phoneNR FROM person, employee, phone  
WHERE person_id = person.id AND employee.id = person.id AND person.id = 4;*/



select * from sales_order;

select * from Order_Condition;

select * from delivered;


