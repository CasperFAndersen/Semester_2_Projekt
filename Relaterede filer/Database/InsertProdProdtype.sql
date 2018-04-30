use dmab0916_202650;

--ALTER TABLE ProductType ADD template int foreign key references Product(id);

--Insert into ProductType VALUES ('Sofa.typer', null);
--Insert into ProductType VALUES ('Madras', null);

--insert into product values ('SofaTemplate',Null, null, 1, 1, null);  
--insert into product values ('MadrasTemplate',Null, null, null, 2, null);  

--update ProductType set template = 1 where id = 1;
--update ProductType set template = 2 where id = 2;
  
SELECT * FROM Product;
SELECT * FROM ProductType;

SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE'



