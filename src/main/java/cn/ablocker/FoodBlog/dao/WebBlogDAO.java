package cn.ablocker.FoodBlog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.ablocker.FoodBlog.entity.WebBlog;

@Component
public class WebBlogDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 增加一篇博客
    @Transactional
    public int addAnBlog(String userName, WebBlog blog)
    {
        String sql1 = "insert into WebBlog(publish_time, title, content, img) values(current_timestamp(), ?, ?, ?)";
        String sql2 = "insert into BlogUser_WebBlog(user_name, blog_id) values(?, LAST_INSERT_ID())";
        int result = jdbcTemplate.update(sql1, blog.getTitle(), blog.getContent(), blog.getImg());
        if (result == 1)
            result = jdbcTemplate.update(sql2, userName);
        return result;
    }

    // 查找所有博客
    public List<WebBlog> findAllBlogs()
    {
        String sql = "select * from WebBlog";
        RowMapper<WebBlog> rowMapper = new BeanPropertyRowMapper<>(WebBlog.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    // 查找用户的所有博客
    public List<WebBlog> findUserBlogs(String userName)
    {
        String sql = "select * from WebBlog where id in (select blog_id from BlogUser_WebBlog where user_name=?)";
        RowMapper<WebBlog> rowMapper = new BeanPropertyRowMapper<>(WebBlog.class);
        return jdbcTemplate.query(sql, rowMapper, userName);
    }
}
