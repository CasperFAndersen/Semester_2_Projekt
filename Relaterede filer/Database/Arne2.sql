use dmab0916_202650;


--ALTER TABLE [dbo].[Person] DROP CONSTRAINT [FK__Person__zip_code__03DB89B3]

--ALTER TABLE productprice DROP purchase_price;

--drop table Person;
--SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE'

--delete from Property where id = 2;
select * from Product;

select * from PartOfProduct;

select * from Property; 

SELECT * FROM product, productprice where product.product_price_id = ProductPrice.id;
