-- drop database if exists FoodBlog;

-- create database FoodBlog;

-- use FoodBlog;

DROP TABLE IF EXISTS WebBlog_Comment;
DROP TABLE IF EXISTS BlogUser_WebBlog;
DROP TABLE IF EXISTS Comment;
DROP TABLE IF EXISTS WebBlog;
DROP TABLE IF EXISTS BlogUser;

-- 用户表
CREATE TABLE BlogUser (
	user_name varchar(255) NOT NULL,		-- 用户名
	pass_word varchar(255) NOT NULL,		-- 密码
	email varchar(50) NOT NULL,				-- 邮箱
	CONSTRAINT BlogUser_PK PRIMARY KEY (user_name)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

-- 博客表
CREATE TABLE WebBlog (
	id INT auto_increment NOT NULL,
	publish_time DATETIME NOT NULL,			-- 发布时间
	title varchar(255) NULL,				-- 标题
	content TEXT NULL,						-- 内容
	img MEDIUMTEXT NULL,					-- 图片
	likes INT DEFAULT 0 NOT NULL,			-- 点赞数
	CONSTRAINT WebBlog_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

-- 评论表
CREATE TABLE Comment (
	id INT auto_increment NOT NULL,
	content TEXT NULL,				-- 评论内容
	CONSTRAINT Comment_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

-- 用户×博客 关系表 （一个用户对应多篇博客）
CREATE TABLE BlogUser_WebBlog (
	user_name varchar(255) NOT NULL,
	blog_id INT NOT NULL,
	CONSTRAINT BlogUser_WebBlog_FK1 FOREIGN KEY (user_name) REFERENCES BlogUser(user_name) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT BlogUser_WebBlog_FK2 FOREIGN KEY (blog_id) REFERENCES WebBlog(id) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

-- 博客 × 评论 关系表 （一篇博客对应多个评论）
CREATE TABLE WebBlog_Comment (
	blog_id INT NOT NULL,
	comment_id INT NOT NULL,
	CONSTRAINT WebBlog_Comment_FK1 FOREIGN KEY (blog_id) REFERENCES WebBlog(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT WebBlog_Comment_FK2 FOREIGN KEY (comment_id) REFERENCES Comment(id) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
