CREATE TABLE venda (
	id serial PRIMARY KEY,
	data_venda TIMESTAMP NOT null,
	nome_produto VARCHAR (240) NOT NULL,
	nome_cliente VARCHAR (240) NOT NULL,
	valor numeric NOT NULL,
	quantidade int NOT NULL,
	pagamento varchar(240) NOT NULL,
	endereco varchar(240) NOT NULL,
	contato varchar(240) NOT NULL,
	tamanho varchar (10) not null
);
