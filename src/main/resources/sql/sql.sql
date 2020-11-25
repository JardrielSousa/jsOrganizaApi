CREATE TABLE produto (
	id serial PRIMARY KEY,
	nome VARCHAR (240) NOT NULL,
	valor numeric NOT NULL,
	quantidade int NOT NULL,
	data_compra TIMESTAMP NOT NULL

);

drop table produto



select * from venda v 

CREATE TABLE venda (
	id serial PRIMARY KEY,
	data_venda TIMESTAMP NOT null,
	nomeProduto VARCHAR (240) NOT NULL,
	valor numeric NOT NULL,
	quantidade int NOT NULL,
	valor numeric NOT NULL,
	pagamento varchar(240) NOT NULL,
	endereço varchar(240) NOT NULL,
	contato varchar(240) NOT NULL,

);

alter table venda rename column endereço to endereco