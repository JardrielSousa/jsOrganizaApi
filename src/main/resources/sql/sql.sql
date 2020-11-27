CREATE TABLE produto (
	id serial PRIMARY KEY,
	nome VARCHAR (240) NOT NULL,
	valor numeric NOT NULL,
	quantidade int NOT NULL,
	data_compra TIMESTAMP NOT null,
	valor_venda numeric NOT NULL

);

drop table produto
drop table venda 

delete from flyway_schema_history 

select * from flyway_schema_history 

select * from venda v 

select * from produto p 

CREATE TABLE venda (
	id serial PRIMARY KEY,
	data_venda TIMESTAMP NOT null,
	nomeProduto VARCHAR (240) NOT NULL,
	valor numeric NOT NULL,
	quantidade int NOT NULL,
	valor numeric NOT NULL,
	pagamento varchar(240) NOT NULL,
	endereco varchar(240) NOT NULL,
	contato varchar(240) NOT NULL,
	tamanho varchar (10) not null
);

alter table venda rename column endereço to endereco
alter table venda add column tamanho varchar(10)