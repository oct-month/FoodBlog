-- drop database if exists FoodBlog;

-- create database FoodBlog;

-- use FoodBlog;

DROP TABLE IF EXISTS WebBlog;
DROP TABLE IF EXISTS BlogUser;

CREATE TABLE BlogUser (
	user_name varchar(255) NOT NULL,
	pass_word varchar(255) NOT NULL,
	email varchar(40) NOT NULL,
	CONSTRAINT BlogUser_PK PRIMARY KEY (user_name)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE WebBlog (
	id INT auto_increment NOT NULL,
	user_name varchar(255) NOT NULL,
	publish_time DATETIME NOT NULL,
	title varchar(255) NULL,
	content TEXT DEFAULT NULL,
	img MEDIUMBLOB NULL,
	CONSTRAINT WebBlog_PK PRIMARY KEY (id),
	CONSTRAINT WebBlog_FK FOREIGN KEY (user_name) REFERENCES BlogUser(user_name) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
