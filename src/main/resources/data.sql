-- 用户测试数据
insert into BlogUser(user_name, pass_word, email) values("test", "test", "oct-month@qq.com");

-- 博客测试数据
insert into WebBlog(publish_time, title, content) values(current_timestamp(), "测试", "无内容");
insert into BlogUser_WebBlog (user_name, blog_id) values("test", LAST_INSERT_ID());
insert into WebBlog (publish_time, title, content) values(current_timestamp(), "测试2", "内容");
insert into BlogUser_WebBlog (user_name, blog_id) values("test", LAST_INSERT_ID());

-- 评论测试数据
insert into Comment(content) values("非常好");
insert into WebBlog_Comment(blog_id, comment_id) values(1, LAST_INSERT_ID());
insert into Comment(content) value("不好");
insert into WebBlog_Comment(blog_id, comment_id) values(1, LAST_INSERT_ID());
