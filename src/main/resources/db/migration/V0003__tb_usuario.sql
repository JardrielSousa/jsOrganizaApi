CREATE TABLE usuario (
	id serial PRIMARY KEY,
	nome VARCHAR (240) NOT NULL,
	email varchar(240) NOT NULL,
	senha varchar(240) NOT NULL,
	data_nascimento TIMESTAMP NOT null
);
