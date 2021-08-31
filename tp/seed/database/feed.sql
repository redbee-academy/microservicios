create database feed;
\c feed;

create table posts(
	id serial primary key,
	user_id int, 
	content varchar, 
	status varchar(20), 
	creation_date date, 
	creation_user varchar(25), 
	modification_date date, 
	modification_user varchar(25)
);
create table post_likes(
	post_id int, 
	user_id int,
	PRIMARY KEY(post_id, user_id),
	CONSTRAINT fk_post
      		FOREIGN KEY(post_id) 
	  		REFERENCES posts(id)
);

create table comments(
	id serial primary key,
	post_id int, 
	user_id int, 
	content varchar, 
	status varchar(20), 
	creation_date date, 
	creation_user varchar(25), 
	modification_date date, 
	modification_user varchar(25),
	CONSTRAINT fk_post
      		FOREIGN KEY(post_id) 
	  		REFERENCES posts(id)
);
create table comment_likes(
	comment_id int, 
	user_id int,
	PRIMARY KEY(comment_id, user_id),
	CONSTRAINT fk_comment
      		FOREIGN KEY(comment_id) 
	  		REFERENCES comments(id)
);
