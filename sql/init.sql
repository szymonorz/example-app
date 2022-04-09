CREATE TABLE IF NOT EXISTS users(
	id int generated always as identity,
	username varchar(25),
	password varchar(255),
	CONSTRAINT user_id_k PRIMARY KEY(id)
);
