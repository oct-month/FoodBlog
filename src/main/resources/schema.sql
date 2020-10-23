-- drop database if exists FoodBlog;

-- create database FoodBlog;

-- use FoodBlog;

drop table if exists BlogUser;

create table BlogUser(
	user_name varchar(255) primary key,
	pass_word varchar(255) not null,
	email varchar(40) not null
);
