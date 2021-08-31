create database users;
\c users;
create table users( 
	id serial primary key, 
	mail varchar(50), 
	encrypted_password varchar(100), 
	status varchar(20), 
	creation_date date, 
	creation_user varchar(25), 
	modification_date date, 
	modification_user varchar(25)
);
