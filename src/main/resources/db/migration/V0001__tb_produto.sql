CREATE TABLE produto (
	id serial PRIMARY KEY,
	nome VARCHAR (240) NOT NULL,
	valor numeric NOT NULL,
	valor_venda numeric NOT NULL,
	quantidade int NOT NULL,
	data_compra TIMESTAMP NOT null

);